package tk.mctechniclp.devathlon.spells;

import tk.mctechniclp.devathlon.api.Element;

public abstract class Spell {
	protected Element[] elements;
	
	public Spell(Element[] elements) {
		this.elements = elements;
	}
	
	public abstract boolean isActivatedBy(Element[] elements);
	
	public abstract void fire();
}
