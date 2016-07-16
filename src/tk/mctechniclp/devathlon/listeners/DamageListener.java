package tk.mctechniclp.devathlon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import tk.mctechniclp.devathlon.api.Element;
import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.api.ShieldSE;

public class DamageListener implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent ev) {
		if(ev.getEntity() instanceof Player) {
			MMPlayer p = MMPlayer.getByUUID(((Player) ev.getEntity()).getUniqueId());
			
			ShieldSE shield = p.getShield();
			if(shield == null) return;
			
			Element e = Element.getByDamageCause(ev.getCause());
			if(e == null) return;
			
			float factor = 1;
			for(Element es : shield.getElements()) {
				if(es == e) factor -= 0.25;
			}
			
			ev.setDamage(ev.getDamage() * factor);
		}
	}
	
}
