package tk.mctechniclp.devathlon.api;

import org.bukkit.ChatColor;

public enum Element {
	
	FIRE(ChatColor.GOLD + "F", 1, ChatColor.GOLD),
	WATER("\u2652", 2, ChatColor.BLUE),
	ICE("\u2744", 3, ChatColor.AQUA),
	EARTH("E", 4, ChatColor.GRAY),
	HEAL("H", 5, ChatColor.GREEN),
	DAMAGE("\u2623", 6, ChatColor.RED),
	SHIELD("S", 7, ChatColor.YELLOW),
	ELEC("\u2607", 8, ChatColor.LIGHT_PURPLE),
	NONE("N", -2, ChatColor.WHITE);
	//\u2609
	private String charCode;
	private int slot;
	private ChatColor color;
	
	Element(String charCode, int slot, ChatColor color) {
		this.charCode = charCode;
		this.slot = slot;
		this.color = color;
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
