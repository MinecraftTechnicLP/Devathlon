package tk.mctechniclp.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import tk.mctechniclp.devathlon.api.MMPlayer;

public class FireSpellListener implements Listener {
	
	@EventHandler
	public void onFireSpell(PlayerInteractEvent ev) {
		if(ev.getAction() == Action.RIGHT_CLICK_AIR || ev.getAction() == Action.RIGHT_CLICK_BLOCK) {
			MMPlayer.getByUUID(ev.getPlayer().getUniqueId()).fireSpell(ev.getPlayer().isSneaking());
		}
	}
	
	
}
