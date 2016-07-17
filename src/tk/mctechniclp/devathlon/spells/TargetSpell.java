package tk.mctechniclp.devathlon.spells;

import tk.mctechniclp.devathlon.api.Element;

public abstract class TargetSpell extends Spell {

	public TargetSpell(Element[] elements, Element[] required) {
		super(elements, required);
	}
	
	/**
	 * Only Use this constructor to register the Spell
	 * @param required required Elements
	 */
	public TargetSpell(Element[] required) {
		super(required);
	}

}
