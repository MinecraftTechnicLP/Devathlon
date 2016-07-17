package tk.mctechniclp.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import tk.mctechniclp.devathlon.api.MMPlayer;

public class MoveItemListener implements Listener {
	
	@EventHandler
	public void onPlayerMoveItem(InventoryClickEvent ev) {
		MMPlayer p = MMPlayer.getByUUID(ev.getWhoClicked().getUniqueId());
		if(p.isInSpellMode()) ev.setCancelled(true);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent ev) {
		MMPlayer p = MMPlayer.getByUUID(ev.getPlayer().getUniqueId());
		if(p.isInSpellMode()) ev.setCancelled(true);
	}
	
	
}
