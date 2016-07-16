package tk.mctechniclp.devathlon.spells;

import java.util.HashMap;

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
}
