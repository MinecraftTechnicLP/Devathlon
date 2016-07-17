package tk.mctechniclp.devathlon.projectiles;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class DamageStream extends StreamProjectile {

	public DamageStream(Location loc, Vector vec) {
		super(loc, vec, 0.2, 40, 0.2, Particle.REDSTONE);
	}

	@Override
	public void impact(LivingEntity e) {
		if(e == null) return;
		e.damage(4);
	}

}
