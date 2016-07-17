package tk.mctechniclp.devathlon.spells;

import tk.mctechniclp.devathlon.api.Element;

public abstract class SelfSpell extends Spell {

	public SelfSpell(Element[] elements, Element[] required) {
		super(elements, required);
	}
	
	/**
	 * Only Use this constructor to register the Spell
	 * @param required required Elements
	 */
	public SelfSpell(Element[] required) {
		super(required);
	}
	
	
}
