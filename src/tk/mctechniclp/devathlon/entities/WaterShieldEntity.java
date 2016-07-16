package tk.mctechniclp.devathlon.entities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WaterShieldEntity extends ShieldEntity {

	public WaterShieldEntity(Location... locs) {
		super(locs);
	}

	@Override
	public void collide(PlayerMoveEvent ev) {
		ev.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2, true, false));
	}

	@Override
	protected Particle getParticle() {
		return Particle.WATER_WAKE;
	}
	
	
	
	
}
