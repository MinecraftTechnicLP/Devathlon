package tk.mctechniclp.devathlon.api;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import tk.mctechniclp.devathlon.spells.SpellManager;
import tk.mctechniclp.devathlon.statuseffects.ShieldSE;
import tk.mctechniclp.devathlon.statuseffects.StatusEffect;
import tk.mctechniclp.devathlon.utils.ActionBarUtils;


public class MMPlayer {
	private UUID uuid;
	private Element[] spell;
	private String spellMsg;
	private ArrayList<StatusEffect> effects;
	private ShieldSE shield;
	private ItemStack[] backup;
	
	/**
	 * <b>DO NOT USE THIS CONSTRUCTOR</b><br>
	 * Use {@link MMPlayer#getByUUID(UUID)} instead
	 * @param uuid UUID of the Minecraft Player
	 */
	public MMPlayer(UUID uuid) {
		this.uuid = uuid;
		this.effects = new ArrayList<StatusEffect>();
		clearSpell();
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
			spellMsg += spell[i].getStringRepresentation();
		}
		sendSpellBar();
	}
	
	public void sendSpellBar() {
		ActionBarUtils.sendActionBar(Bukkit.getPlayer(uuid), spellMsg);
	}
	
	public void fireSpell(boolean self) {
		if(spell[4] == Element.NONE || !isInSpellMode()) return;
		SpellManager.getSpell(spell, self).fire(this);
		clearSpell();
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
	
	public void setShield(ShieldSE shield) {
		this.shield = shield;
	}

	public void removeShield() {
		shield = null;
	}
	
	public ShieldSE getShield() {
		return shield;
	}
	
	private void clearSpell() {
		this.spell = new Element[] {Element.NONE, Element.NONE, Element.NONE, Element.NONE, Element.NONE};
		this.spellMsg = Element.NONE.getStringRepresentation() + Element.NONE.getStringRepresentation() + Element.NONE.getStringRepresentation() + Element.NONE.getStringRepresentation() + Element.NONE.getStringRepresentation();
	}
	
	public boolean isInSpellMode() {
		return backup != null;
	}
	
	public void activateSpellMode() {
		if(isInSpellMode()) return;
		
		Player p = Bukkit.getPlayer(uuid);
		backup = p.getInventory().getContents();
		ItemStack iMain = p.getInventory().getItemInMainHand();
		ItemStack iOff = p.getInventory().getItemInOffHand();
		
		p.getInventory().clear();
		
		for(Element e : Element.values()) {
			if(e == Element.NONE) continue;
			p.getInventory().setItem(e.getSlot(), e.getItemStack());
		}
		p.getInventory().setItem(0, iMain);
		p.getInventory().setItemInOffHand(iOff);
		
	}
	
	public void deactivateSpellMode() {
		if(!isInSpellMode()) return;
		
		Player p = Bukkit.getPlayer(uuid);
		p.getInventory().setContents(backup);
		backup = null;
	}
	
	public void switchSpellMode() {
		if(isInSpellMode()) {
			deactivateSpellMode();
		} else {
			activateSpellMode();
		}
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
	
}
