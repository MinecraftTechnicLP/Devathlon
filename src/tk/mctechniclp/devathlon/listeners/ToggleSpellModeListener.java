package tk.mctechniclp.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import tk.mctechniclp.devathlon.api.MMPlayer;

public class ToggleSpellModeListener implements Listener {
	
	@EventHandler
	public void onToggleSpellMode(PlayerMoveEvent ev) {
		if(ev.getTo().getBlockY() > ev.getFrom().getBlockY() && ev.getPlayer().isSneaking()) {
			MMPlayer.getByUUID(ev.getPlayer().getUniqueId()).switchSpellMode();
		}
	}
	
	
}
