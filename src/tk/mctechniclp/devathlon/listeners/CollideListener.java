package tk.mctechniclp.devathlon.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tk.mctechniclp.devathlon.entities.MagicEntity;
import tk.mctechniclp.devathlon.projectiles.Projectile;
import tk.mctechniclp.devathlon.projectiles.StreamProjectile;

public class CollideListener implements Listener {
	
	@EventHandler
	public void onCollide(PlayerMoveEvent ev) {
		for(MagicEntity e : MagicEntity.getAll()) {
			for(Location loc : e.getLocations()) {
				if(loc.getBlock().getLocation().equals(ev.getTo().getBlock().getLocation()) || loc.getBlock().getLocation().equals(ev.getTo().getBlock().getLocation().clone().add(0, 1, 0))) {
					e.collide(ev);
					if(e.isPhysical()) ev.setCancelled(true);
				}
			}
		}
		
		for(Projectile p : Projectile.getAll()) {
			if(!(p instanceof StreamProjectile)) continue;
			StreamProjectile sp = (StreamProjectile) p;
			
			for(Location loc : sp.getLocations()) {
				if(loc.getBlock().getLocation().equals(ev.getTo().getBlock().getLocation()) || loc.getBlock().getLocation().equals(ev.getTo().getBlock().getLocation().clone().add(0, 1, 0))) {
					sp.impact(ev.getPlayer());
				}
			}
		}
		
	}
	
}
