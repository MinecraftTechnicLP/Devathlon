package tk.mctechniclp.devathlon.spells.self;

import tk.mctechniclp.devathlon.api.Element;
import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.spells.SelfSpell;
import tk.mctechniclp.devathlon.statuseffects.ShieldSE;

public class SelfShieldSpell extends SelfSpell {

	public SelfShieldSpell(Element[] elements) {
		super(elements, new Element[] {Element.SHIELD});
	}
	
	/**
	 * Only Use this constructor to register the Spell
	 * @param required required Elements
	 */
	public SelfShieldSpell() {
		super(new Element[] {Element.SHIELD});
	}
	
	@Override
	public void fire(MMPlayer p) {
		p.setShield(new ShieldSE(elements));
	}
	
	
}
