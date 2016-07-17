package tk.mctechniclp.devathlon.entities;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.mctechniclp.devathlon.api.Element;

public class MineEntity extends MagicEntity {
	private Element[] es;
	private Item i;
	
	public MineEntity(Element[] es, Location... locs) {
		super(false, 1200, locs);
		this.es = es;
		i = locs[0].getWorld().dropItem(locs[0], new ItemStack(es[0].getMaterial()));
		i.setPickupDelay(1201);
	}

	@Override
	public void collide(PlayerMoveEvent ev) {
		for(Location loc : locs) {
			loc.getWorld().spawnParticle(es[0].getParticle(), loc, 1, 0.5, 0.5, 0.5, 0.5, null);
			loc.getWorld().createExplosion(loc, 0.8F, false);
		}
		
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

	@Override
	public void playParticles() {
		for(Location loc : locs) {
			if(new Random().nextInt(8) == 0) {
				loc.getWorld().spawnParticle(es[0].getParticle(), loc, 1, 0.1, 0.1, 0.1, 0.01, null);
			}
		}
	}
	
	@Override
	protected void die() {
		super.die();
		i.remove();
	}
	
	
	
}
