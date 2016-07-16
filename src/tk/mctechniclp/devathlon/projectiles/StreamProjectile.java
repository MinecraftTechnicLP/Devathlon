package tk.mctechniclp.devathlon.projectiles;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import tk.mctechniclp.devathlon.entities.MagicEntity;
import tk.mctechniclp.devathlon.entities.ShieldEntity;

public abstract class StreamProjectile extends Projectile {
	protected int maxIterations;
	private ArrayList<Location> particleLocs;
	private int iterations = 0;
	
	public StreamProjectile(Location loc, Vector vec, double stepSize, int range, double thickness, Particle... particles) {
		super(loc, vec, thickness, particles);
		this.vec.multiply(stepSize);
		this.maxIterations = (int) Math.round(range / stepSize);
		this.particleLocs = new ArrayList<Location>();
	}

	@Override
	public void tick() {
		step();
		step();
		step();
		step();
		
		for(Location l : new ArrayList<Location> (particleLocs)) {
			for(Particle particle : new ArrayList<Particle>(particles)) {
				l.getWorld().spawnParticle(particle, l, 1, tolerance, tolerance, tolerance, 0, null);
			}
		}
		
	}
	
	private void step() {
		particleLocs.add(loc.clone());
		loc.add(vec);
		iterations++;
		
		if(iterations >= maxIterations) remove(this);
		if(iterations <= 4) return;
		
		LivingEntity col = getColliding();
		if(col != null || loc.getBlock().getType().isSolid()) {
			impact(col);
			remove(this);
			return;
		}
		
		for(MagicEntity me : MagicEntity.getAll()) {
			for(Location l : me.getLocations()) {
				if(l.getBlock().getLocation().equals(loc.getBlock().getLocation()) && me instanceof ShieldEntity) {
					remove(this);
					return;
				}
			}
		}
	}

}
