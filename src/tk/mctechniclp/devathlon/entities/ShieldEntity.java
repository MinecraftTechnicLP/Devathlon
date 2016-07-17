package tk.mctechniclp.devathlon.entities;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.mctechniclp.devathlon.Main;
import tk.mctechniclp.devathlon.api.Element;

public class ShieldEntity extends MagicEntity {
	private Element[] es;
	
	public ShieldEntity(Element[] es, Location... locs) {
		super(true, 100, locs);
		this.es = es;
	}
	
	@Override
	public void playParticles() {
		for(Location loc : new ArrayList<Location>(locs)) {
			Location l = loc.clone().add(0, -0.5, 0);
			l.getWorld().spawnParticle(es[0].getParticle(), l, 1, 0, 0, 0, 0, null);
			
			for(int i = 0; i <= 10; i++) {
				l.getWorld().spawnParticle(es[0].getParticle(), l.add(0, 0.1, 0), 1, 0, 0, 0, 0, null);
			}
		}
	}

	@Override
	public void collide(PlayerMoveEvent ev) {
		double damage = 0;
		
		switch(es[0]) {
		case DAMAGE:
			damage = 4.0;
			break;
		case EARTH:
			damage = 2.0;
			break;
		case ELEC:
			damage = 3.0;
			break;
		case FIRE:
			damage = 3.0;
			ev.getPlayer().setFireTicks(40);
			break;
		case HEAL:
			ev.getPlayer().setHealth(ev.getPlayer().getHealth() + 2 > ev.getPlayer().getMaxHealth() ? ev.getPlayer().getMaxHealth() : ev.getPlayer().getHealth() + 2);
			break;
		case ICE:
			damage = 2.0;
			ev.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2, true, false));
			break;
		case WATER:
			damage = 1.0;
			ev.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 1, true, false));
			break;
		default:
			break;
		}
		
		if(damage == 0) return;
		
		ev.getPlayer().damage(damage, es[0].spawnArmorStand(ev.getPlayer().getLocation()));
		
		
	}

}
