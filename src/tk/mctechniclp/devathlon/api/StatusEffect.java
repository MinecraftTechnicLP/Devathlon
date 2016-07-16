package tk.mctechniclp.devathlon.api;

public abstract class StatusEffect {
	private int ticks;
	
	/**
	 * 
	 * @param duration in ticks (1 second equals 20 ticks)
	 */
	public StatusEffect(int duration) {
		this.ticks = duration;
	}
	
	protected void tick(MMPlayer p) {
		ticks--;
		if(ticks == 0) p.removeStatusEffect(this);
	}
	
}
