package tk.mctechniclp.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import tk.mctechniclp.devathlon.api.MMPlayer;

public class ToggleSpellModeListener implements Listener {
	
	@EventHandler
	public void onToggleSpellMode(PlayerToggleFlightEvent ev) {
		if(ev.isFlying() && ev.getPlayer().isSneaking()) {
			MMPlayer.getByUUID(ev.getPlayer().getUniqueId()).switchSpellMode();
		}
	}
	
	
}
