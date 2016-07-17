package tk.mctechniclp.devathlon.api;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Element {
	
	FIRE("F", 1, ChatColor.GOLD, Particle.FLAME, DyeColor.ORANGE, Material.BLAZE_POWDER),
	WATER("\u2652", 2, ChatColor.BLUE, Particle.WATER_WAKE, DyeColor.BLUE, Material.WATER_BUCKET),
	ICE("\u2744", 3, ChatColor.AQUA, Particle.CLOUD, DyeColor.LIGHT_BLUE, Material.PACKED_ICE),
	EARTH("E", 4, ChatColor.GRAY, Particle.VILLAGER_ANGRY, DyeColor.GRAY, Material.DIRT),
	HEAL("H", 5, ChatColor.GREEN, Particle.VILLAGER_HAPPY, DyeColor.LIME, Material.EMERALD),
	DAMAGE("\u2623", 6, ChatColor.RED, Particle.REDSTONE, DyeColor.RED, Material.REDSTONE_BLOCK),
	SHIELD("S", 7, ChatColor.YELLOW, Particle.DRIP_LAVA, DyeColor.YELLOW, Material.SHIELD),
	ELEC("\u2607", 8, ChatColor.LIGHT_PURPLE, Particle.PORTAL, DyeColor.PINK, Material.SPECTRAL_ARROW),
	NONE("\u2609", -2, ChatColor.WHITE, null, null, null);
	
	private String charCode;
	private int slot;
	private ChatColor color;
	private Particle particle;
	private DyeColor dye;
	private Material mat;
	
	Element(String charCode, int slot, ChatColor color, Particle particle, DyeColor dye, Material mat) {
		this.charCode = charCode;
		this.slot = slot;
		this.color = color;
		this.particle = particle;
		this.dye = dye;
		this.mat = mat;
	}
	
	public Particle getParticle() {
		return particle;
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
	
	public Material getMaterial() {
		return mat;
	}
	
	public ItemStack getItemStack() {
		@SuppressWarnings("deprecation")
		ItemStack is = new ItemStack(Material.INK_SACK, 1, dye.getDyeData());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(getStringRepresentation() + " - §r" + getChatColor() + toString());
		is.setItemMeta(im);
		
		return is;
	}
	
	public ArmorStand spawnArmorStand(Location loc) {
		ArmorStand am = (ArmorStand) loc.getWorld().spawn(loc.clone().add(0, 50, 0), ArmorStand.class);
		am.setVisible(false);
		am.setGravity(false);
		am.setCustomName(toString());
		return am;
	}
	
	
	public static Element getBySlot(int slot) {
		for(Element e : values()) {
			if(e.getSlot() == slot) return e;
		}
		return null;
	}
	
	public static Element getByDamager(Entity e) {
		if(!(e instanceof ArmorStand)) return null;
		
		for(Element el : values()) {
			if(e.getCustomName().equals(el.toString())) return el;
		}
		return null;
	}
}
