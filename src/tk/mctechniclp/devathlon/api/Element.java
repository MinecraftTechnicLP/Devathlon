package tk.mctechniclp.devathlon.api;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public enum Element {
	
	FIRE(ChatColor.GOLD + "F", 1, ChatColor.GOLD, DamageCause.FIRE_TICK, Particle.FLAME),
	WATER("\u2652", 2, ChatColor.BLUE, DamageCause.DROWNING, Particle.WATER_WAKE),
	ICE("\u2744", 3, ChatColor.AQUA, DamageCause.MELTING, Particle.CLOUD),
	EARTH("E", 4, ChatColor.GRAY, DamageCause.SUFFOCATION, Particle.VILLAGER_ANGRY),
	HEAL("H", 5, ChatColor.GREEN, null, Particle.VILLAGER_HAPPY),
	DAMAGE("\u2623", 6, ChatColor.RED, DamageCause.CUSTOM, Particle.REDSTONE),
	SHIELD("S", 7, ChatColor.YELLOW, DamageCause.PROJECTILE, Particle.DRIP_LAVA),
	ELEC("\u2607", 8, ChatColor.LIGHT_PURPLE, DamageCause.LIGHTNING, Particle.PORTAL),
	NONE("N", -2, ChatColor.WHITE, null, null);
	//\u2609
	private String charCode;
	private int slot;
	private ChatColor color;
	private DamageCause cause;
	private Particle particle;
	
	Element(String charCode, int slot, ChatColor color, DamageCause cause, Particle particle) {
		this.charCode = charCode;
		this.slot = slot;
		this.color = color;
		this.cause = cause;
		this.particle = particle;
	}
	
	public Particle getParticle() {
		return particle;
	}
	
	public DamageCause getDamageCause() {
		return cause;
	}
	
	public ChatColor getChatColor() {
		return color;
	}
	
	public String getCharCode() {
		return charCode;
	}
	
	public String getStringRepresentation() {
		return getChatColor().toString() + ChatColor.BOLD + getCharCode() + ChatColor.RESET;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public static Element getBySlot(int slot) {
		for(Element e : values()) {
			if(e.getSlot() == slot) return e;
		}
		return null;
	}
}
