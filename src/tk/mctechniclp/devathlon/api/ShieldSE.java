package tk.mctechniclp.devathlon.api;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ShieldSE extends StatusEffect {
	private static ArrayList<RelativeLoc2> circleCache;
	private Element[] es;
	
	public ShieldSE(Element[] elements) {
		super(100);
		es = new Element[4];
		boolean substracted = false;
		int j = 0;
		for(int i = 0; i < elements.length; i++) {
			if(elements[i] != Element.SHIELD || substracted) {
				es[j] = elements[i];
				j++;
			} else {
				substracted = true;
			}
		}
	}
	
	@Override
	protected void tick(MMPlayer p) {
		super.tick(p);
		if(circleCache == null) {
			circleCache = new ArrayList<RelativeLoc2>();
			for(double i = 0; i < Math.PI * 2; i += Math.PI / 5) {
				circleCache.add(new RelativeLoc2(Math.sin(i) * 0.8, Math.cos(i) * 0.8));
			}
		}
		
		Location loc = Bukkit.getPlayer(p.getUUID()).getLocation();
		for(RelativeLoc2 l : circleCache) {
			loc.getWorld().spawnParticle(es[0].getParticle(), l.toLocation(loc.clone().add(0, 0.2, 0)), 1, 0, 0, 0, 0, null);
			loc.getWorld().spawnParticle(es[1].getParticle(), l.toLocation(loc.clone().add(0, 0.4, 0)), 1, 0, 0, 0, 0, null);
			loc.getWorld().spawnParticle(es[2].getParticle(), l.toLocation(loc.clone().add(0, 0.6, 0)), 1, 0, 0, 0, 0, null);
			loc.getWorld().spawnParticle(es[3].getParticle(), l.toLocation(loc.clone().add(0, 0.8, 0)), 1, 0, 0, 0, 0, null);
		}
	}
	
}
