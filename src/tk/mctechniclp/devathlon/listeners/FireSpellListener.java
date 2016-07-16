package tk.mctechniclp.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import tk.mctechniclp.devathlon.api.Element;
import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.api.ShieldSE;

public class FireSpellListener implements Listener {
	
	@EventHandler
	public void onFireSpell(PlayerInteractEvent ev) {
		if(ev.getAction() == Action.RIGHT_CLICK_AIR || ev.getAction() == Action.RIGHT_CLICK_BLOCK) {
			MMPlayer.getByUUID(ev.getPlayer().getUniqueId()).fireSpell(ev.getPlayer().isSneaking());
		} else {
			System.out.println("+++++++++++++++++++++++++++++++");
			Vector vector = new Vector();
			double rotX = ev.getPlayer().getLocation().getYaw();
			vector.setX(-1 * Math.sin(Math.toRadians(rotX)));
			vector.setZ(Math.cos(Math.toRadians(rotX)));
			System.out.println(vector);
			System.out.println("+++++++++++++++++++++++++++++++");
			
			MMPlayer.getByUUID(ev.getPlayer().getUniqueId()).setShield(new ShieldSE(new Element[] {Element.ELEC, Element.FIRE, Element.SHIELD, Element.WATER, Element.DAMAGE}));
			
		}
	}
	
	
}
