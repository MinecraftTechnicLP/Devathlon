package tk.mctechniclp.devathlon.api;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;

import tk.mctechniclp.devathlon.spells.SpellManager;
import tk.mctechniclp.devathlon.utils.ActionBarUtils;


public class MMPlayer {
	private UUID uuid;
	private Element[] spell;
	private String spellMsg;
	private ArrayList<StatusEffect> effects;
	private ShieldSE shield;
	
	/**
	 * <b>DO NOT USE THIS CONSTRUCTOR</b><br>
	 * Use {@link MMPlayer#getByUUID(UUID)} instead
	 * @param uuid UUID of the Minecraft Player
	 */
	public MMPlayer(UUID uuid) {
		this.uuid = uuid;
		this.spell = new Element[] {Element.NONE, Element.NONE, Element.NONE, Element.NONE, Element.NONE};
		this.spellMsg = Element.NONE.getChatColor() + Element.NONE.getCharCode() + Element.NONE.getChatColor() + Element.NONE.getCharCode() + Element.NONE.getChatColor() + Element.NONE.getCharCode() + Element.NONE.getChatColor() + Element.NONE.getCharCode() + Element.NONE.getChatColor() + Element.NONE.getCharCode();
		this.effects = new ArrayList<StatusEffect>();
	}
	
	public UUID getUUID() {
		return uuid;
	}

	public Element[] getSpell() {
		return spell;
	}
	
	public void addElement(Element e) {
		for(int i = 0; i < 5; i++) {
			if(spell[i] == Element.NONE) {
				spell[i] = e;
				updateIndicator();
				return;
			}
		}
	}
	
	private void updateIndicator() {
		spellMsg = "";
		for(int i = 0; i < 5; i++) {
			spellMsg += spell[i].getChatColor() + spell[i].getCharCode();
		}
		sendSpellBar();
	}
	
	public void sendSpellBar() {
		ActionBarUtils.sendActionBar(Bukkit.getPlayer(uuid), spellMsg);
	}
	
	public void fireSpell(boolean self) {
		if(self) {
			SpellManager.getSelfSpell(spell);
		}
	}
	
	public void addStatusEffect(StatusEffect e) {
		effects.add(e);
	}
	
	public void removeStatusEffect(StatusEffect e) {
		effects.remove(e);
	}
	
	public void tickStatusEffects() {
		for(StatusEffect st : new ArrayList<StatusEffect> (effects)) {
			st.tick(this);
		}
		if(shield != null) shield.tick(this);
	}
	
	
	
	
	public static ArrayList<MMPlayer> all = new ArrayList<MMPlayer>();
	
	public static MMPlayer getByUUID(UUID uuid) {
		for(MMPlayer p : getAll()) {
			if(p.getUUID().equals(uuid)) {
				return p;
			}
		}
		MMPlayer p = new MMPlayer(uuid);
		all.add(p);
		return p;
	}
	
	public static ArrayList<MMPlayer> getAll() {
		return new ArrayList<MMPlayer> (all);
	}

	public void setShield(ShieldSE shield) {
		this.shield = shield;
	}
	
}
