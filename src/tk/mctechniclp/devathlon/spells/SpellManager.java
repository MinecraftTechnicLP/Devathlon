package tk.mctechniclp.devathlon.spells;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;

import tk.mctechniclp.devathlon.api.Element;

public class SpellManager {
	
	private static HashMap<SelfSpell, SpellPriority> selfSpells;
	private static HashMap<TargetSpell, SpellPriority> targetSpells;
	
	static {
		selfSpells = new HashMap<SelfSpell, SpellPriority>();
		targetSpells = new HashMap<TargetSpell, SpellPriority>();
	}
	
	public static void registerSelfSpell(SelfSpell spell, SpellPriority p) {
		selfSpells.put(spell, p);
	}
	
	public static void registerTargetSpell(TargetSpell spell, SpellPriority p) {
		targetSpells.put(spell, p);
	}
	
	public static Spell getSpell(Element[] elements, boolean self) {
		HashMap<Spell, SpellPriority> list = new HashMap<Spell, SpellPriority> (self ? selfSpells : targetSpells);
		
		Entry<Spell, SpellPriority> selected = null;
		
		for(Entry<Spell, SpellPriority> e : list.entrySet()) {
			if(e.getKey().isActivatedBy(elements) && (selected == null || e.getValue().higherThan(selected.getValue()))) {
				selected = e;
				if(e.getValue() == SpellPriority.MAGICK) return e.getKey();
			}
		}
		
		return instantiate(selected.getKey(), elements);
	}
	
	private static Spell instantiate(Spell s, Element[] elements) {
		try {
			return s.getClass().getConstructor(Element[].class).newInstance(new Object[] {elements});
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
