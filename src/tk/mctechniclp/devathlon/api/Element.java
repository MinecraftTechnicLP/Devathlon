package tk.mctechniclp.devathlon.api;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Element {
	
	FIRE("F", 1, ChatColor.GOLD, DamageCause.FIRE_TICK, Particle.FLAME, DyeColor.ORANGE),
	WATER("\u2652", 2, ChatColor.BLUE, DamageCause.DROWNING, Particle.WATER_WAKE, DyeColor.BLUE),
	ICE("\u2744", 3, ChatColor.AQUA, DamageCause.MELTING, Particle.CLOUD, DyeColor.LIGHT_BLUE),
	EARTH("E", 4, ChatColor.GRAY, DamageCause.ENTITY_ATTACK, Particle.VILLAGER_ANGRY, DyeColor.GRAY),
	HEAL("H", 5, ChatColor.GREEN, null, Particle.VILLAGER_HAPPY, DyeColor.LIME),
	DAMAGE("\u2623", 6, ChatColor.RED, DamageCause.CUSTOM, Particle.REDSTONE, DyeColor.RED),
	SHIELD("S", 7, ChatColor.YELLOW, DamageCause.PROJECTILE, Particle.DRIP_LAVA, DyeColor.ORANGE),
	ELEC("\u2607", 8, ChatColor.LIGHT_PURPLE, DamageCause.LIGHTNING, Particle.PORTAL, DyeColor.PINK),
	NONE("\u2609", -2, ChatColor.WHITE, null, null, null);
	
	private String charCode;
	private int slot;
	private ChatColor color;
	private DamageCause cause;
	private Particle particle;
	private DyeColor dye;
	
	Element(String charCode, int slot, ChatColor color, DamageCause cause, Particle particle, DyeColor dye) {
		this.charCode = charCode;
		this.slot = slot;
		this.color = color;
		this.cause = cause;
		this.particle = particle;
		this.dye = dye;
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
		return getChatColor().toString() + ChatColor.BOLD + getCharCode();
	}
	
	public int getSlot() {
		return slot;
	}
	
	public DyeColor getDyeColor() {
		return dye;
	}
	
	public ItemStack getItemStack() {
		@SuppressWarnings("deprecation")
		ItemStack is = new ItemStack(Material.INK_SACK, 1, dye.getDyeData());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(getStringRepresentation() + " - §r" + getChatColor() + toString());
		is.setItemMeta(im);
		
		return is;
	}
	
	
	public static Element getBySlot(int slot) {
		for(Element e : values()) {
			if(e.getSlot() == slot) return e;
		}
		return null;
	}
	
	public static Element getByDamageCause(DamageCause cause) {
		for(Element e : values()) {
			if(cause != null && e.getDamageCause() == cause) return e;
		}
		return null;
	}
}
