package tk.mctechniclp.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.api.Element;

public class AddElementListener implements Listener {
	
	@EventHandler
	public void onSlotChange(PlayerItemHeldEvent ev) {
		if(ev.getNewSlot() != 0) {
			MMPlayer.getByUUID(ev.getPlayer().getUniqueId()).addElement(Element.getBySlot(ev.getNewSlot()));
			ev.getPlayer().getInventory().setHeldItemSlot(0);
		}
	}
	
}
