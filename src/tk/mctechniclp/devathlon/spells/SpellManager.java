package tk.mctechniclp.devathlon.spells;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;

import tk.mctechniclp.devathlon.api.Element;

public class SpellManager {
	
	private static HashMap<Class<? extends SelfSpell>, SpellPriority> selfSpells;
	private static HashMap<Class<? extends TargetSpell>, SpellPriority> targetSpells;
	
	static {
		selfSpells = new HashMap<Class<? extends SelfSpell>, SpellPriority>();
		targetSpells = new HashMap<Class<? extends TargetSpell>, SpellPriority>();
	}
	
	public static void registerSelfSpell(Class<? extends SelfSpell> spell, SpellPriority p) {
		selfSpells.put(spell, p);
	}
	
	public static void registerTargetSpell(Class<? extends TargetSpell> spell, SpellPriority p) {
		targetSpells.put(spell, p);
	}
	
	public static SelfSpell getSelfSpell(Element[] elements) {
		HashMap<Class<? extends SelfSpell>, SpellPriority> list = new HashMap<Class<? extends SelfSpell>, SpellPriority> (selfSpells);
		
		Entry<Class<? extends SelfSpell>, SpellPriority> selected = null;
		
		for(Entry<Class<? extends SelfSpell>, SpellPriority> e : list.entrySet()) {
			try {
				Method m = e.getKey().getMethod("isActivatedBy", Element[].class);
				System.out.print(m.toString());
				if((boolean) m.invoke(null, new Object[] {elements})) {
					if(selected == null || selected.getValue().higherThan(selected.getValue())) {
						selected = e;
						if(e.getValue() == SpellPriority.MAGICK) break;
					}
				}
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		
		
		try {
			return SelfSpell.class.getConstructor(Element[].class).newInstance((Object) elements);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
			return null;
		}
	}
}
