package tk.mctechniclp.devathlon.entities;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Particle;

public abstract class ShieldEntity extends MagicEntity {

	public ShieldEntity(Location... locs) {
		super(true, 100, locs);
	}
	
	protected abstract Particle getParticle();
	
	@Override
	public void playParticles() {
		for(Location loc : new ArrayList<Location>(locs)) {
			Location l = loc.clone().add(0, -0.5, 0);
			l.getWorld().spawnParticle(getParticle(), l, 1, 0, 0, 0, 0, null);
			
			for(int i = 0; i <= 10; i++) {
				l.getWorld().spawnParticle(getParticle(), l.add(0, 0.1, 0), 1, 0, 0, 0, 0, null);
			}
		}
	}

}
