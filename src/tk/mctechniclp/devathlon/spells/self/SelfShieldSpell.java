package tk.mctechniclp.devathlon.spells.self;

import java.util.Arrays;

import tk.mctechniclp.devathlon.api.Element;
import tk.mctechniclp.devathlon.spells.SelfSpell;

public class SelfShieldSpell extends SelfSpell {

	public SelfShieldSpell(Element[] elements) {
		super(elements);
	}

	@Override
	public boolean isActivatedBy(Element[] elements) {
		return Arrays.asList(elements).contains(Element.SHIELD);
	}

	@Override
	public void fire() {
		System.out.println("nice1 :D");
	}
	
	
}
