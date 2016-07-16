package tk.mctechniclp.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent ev) {
		switch(ev.getCause()) {
		case BLOCK_EXPLOSION:
			break;
		case CONTACT:
			break;
		case CUSTOM:
			break;
		case DRAGON_BREATH:
			break;
		case DROWNING:
			break;
		case ENTITY_ATTACK:
			break;
		case ENTITY_EXPLOSION:
			break;
		case FALL:
			break;
		case FALLING_BLOCK:
			break;
		case FIRE:
			break;
		case FIRE_TICK:
			break;
		case FLY_INTO_WALL:
			break;
		case HOT_FLOOR:
			break;
		case LAVA:
			break;
		case LIGHTNING:
			break;
		case MAGIC:
			break;
		case MELTING:
			break;
		case POISON:
			break;
		case PROJECTILE:
			break;
		case STARVATION:
			break;
		case SUFFOCATION:
			break;
		case SUICIDE:
			break;
		case THORNS:
			break;
		case VOID:
			break;
		case WITHER:
			break;
		default:
			break;
		
		}
	}
	
}
