package tk.mctechniclp.devathlon.statuseffects;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import tk.mctechniclp.devathlon.api.Element;
import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.api.RelativeLoc2D;

public class ShieldSE extends StatusEffect {
	private static ArrayList<RelativeLoc2D> circleCache;
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
	public void tick(MMPlayer p) {
		ticks--;
		if(ticks == 0) p.removeShield();
		
		Player bp = Bukkit.getPlayer(p.getUUID());
		if(ticks == 20 || ticks == 40 || ticks == 60 || ticks == 80) {
			int heal = 0;
			for(Element e : es) {
				if(e == Element.HEAL) heal++;
			}
			bp.setHealth(bp.getHealth() + heal > 20 ? 20 : bp.getHealth() + heal);
		}
		playParticles(bp);
	}

	@Override
	protected void playParticles(Player p) {
		if(circleCache == null) {
			circleCache = new ArrayList<RelativeLoc2D>();
			for(double i = 0; i < Math.PI * 2; i += Math.PI / 5) {
				circleCache.add(new RelativeLoc2D(Math.sin(i) * 0.8, Math.cos(i) * 0.8));
			}
		}
		
		Location loc = p.getLocation();
		for(RelativeLoc2D l : circleCache) {
			loc.getWorld().spawnParticle(es[0].getParticle(), l.toLocation(loc.clone().add(0, 0.2, 0)), 1, 0, 0, 0, 0, null);
			loc.getWorld().spawnParticle(es[1].getParticle(), l.toLocation(loc.clone().add(0, 0.4, 0)), 1, 0, 0, 0, 0, null);
			loc.getWorld().spawnParticle(es[2].getParticle(), l.toLocation(loc.clone().add(0, 0.6, 0)), 1, 0, 0, 0, 0, null);
			loc.getWorld().spawnParticle(es[3].getParticle(), l.toLocation(loc.clone().add(0, 0.8, 0)), 1, 0, 0, 0, 0, null);
		}
	}
	
	public Element[] getElements() {
		return es;
	}
}
