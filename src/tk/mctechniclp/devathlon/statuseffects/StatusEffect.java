package tk.mctechniclp.devathlon.statuseffects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import tk.mctechniclp.devathlon.api.MMPlayer;

public abstract class StatusEffect {
	protected int ticks;
	
	/**
	 * 
	 * @param duration in ticks (1 second equals 20 ticks)
	 */
	public StatusEffect(int duration) {
		this.ticks = duration;
	}
	
	protected abstract void playParticles(Player p);
	
	public void tick(MMPlayer p) {
		ticks--;
		if(ticks == 0) p.removeStatusEffect(this);
		playParticles(Bukkit.getPlayer(p.getUUID()));
	}
	
}
