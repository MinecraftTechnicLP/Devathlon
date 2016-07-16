package tk.mctechniclp.devathlon.utils;

import org.bukkit.entity.Player;

public class ActionBarUtils {

	public static void sendActionBar(Player player, String message) {
		try {
			Class<?> playOutChat = ReflectionUtils.getNMSClass("PacketPlayOutChat");
			Class<?> chatComponentText = ReflectionUtils.getNMSClass("ChatComponentText");
			Class<?> iChatBaseComponent = ReflectionUtils.getNMSClass("IChatBaseComponent");
			
			Object o = chatComponentText.getConstructor(new Class[] { String.class }).newInstance(new Object[] { message });
			Object ppoc = playOutChat.getConstructor(new Class[] { iChatBaseComponent, Byte.TYPE }).newInstance(new Object[] { o, Byte.valueOf((byte) 2) });
			
			ReflectionUtils.sendPacket(player, ppoc);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
