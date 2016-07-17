package tk.mctechniclp.devathlon.spells;

import java.util.Arrays;

import tk.mctechniclp.devathlon.api.Element;
import tk.mctechniclp.devathlon.api.MMPlayer;

public abstract class Spell {
	protected Element[] elements;
	protected Element[] required;
	
	public Spell(Element[] elements, Element[] required) {
		this.elements = elements;
		this.required = required;
		removeRequireds();
	} 
	
	/**
	 * Only Use this constructor to register the Spell
	 * @param required required Elements
	 */
	public Spell(Element[] required) {
		this.required = required;
	}
	
	
	public boolean isActivatedBy(Element[] news) {
		
		System.out.println("default: " + Arrays.toString(required));
		
		for(int i = 0; i < news.length; i++) {
			for(int j = 0; j < required.length; j++) {
				if(required[j] == news[i]) {
					required[j] = null;
				}
				System.out.println(Arrays.asList(news));
				System.out.println(Arrays.asList(required));
			}
		}
		
		for(int i = 0; i < required.length; i++) {
			if(required[i] != null) return false;
		}
		
		return true;
	}
	
	public abstract void fire(MMPlayer p);
	
	protected void removeRequireds() {
		int index = 0;
		Element[] required = Arrays.copyOf(this.required, this.required.length);
		Element[] newEs = new Element[elements.length - required.length];
		
		for(int i = 0; i < elements.length; i++) {
			for(int j = 0; j < required.length; j++) {
				if(elements[i] == required[j]) {
					required[j] = null;
					break;
				}
				if(j == required.length) index++;
			}
			newEs[index] = elements[i];
		}
		System.out.println("removed:" + Arrays.toString(newEs));
		System.out.println("removed:" + Arrays.toString(elements));
		
		this.elements = newEs;
		
	}
}
