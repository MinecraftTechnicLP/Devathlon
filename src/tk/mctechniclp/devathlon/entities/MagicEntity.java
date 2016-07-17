package tk.mctechniclp.devathlon.entities;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;

public abstract class MagicEntity {
	protected ArrayList<Location> locs;
	private boolean physical;
	protected int ticks;
	
	public MagicEntity(boolean physical, int ticks, Location... locs) {
		this.locs = new ArrayList<Location> (Arrays.asList(locs));
		this.physical = physical;
		this.ticks = ticks;
		registerEntity(this);
	}
	
	public MagicEntity(boolean physical, Location... locs) {
		this(physical, -1, locs);
	}
	
	public abstract void collide(PlayerMoveEvent ev);
	public abstract void playParticles();
	
	public ArrayList<Location> getLocations() {
		return new ArrayList<Location> (locs);
	}
	
	public void tick() {
		if(ticks > 0) ticks--;
		if(ticks == 0) die();
		
		playParticles();
	}
	
	protected void die() {
		removeEntity(this);
	}
	
	public boolean isPhysical() {
		return physical;
	}
	
	
	
	
	private static ArrayList<MagicEntity> all;
	
	static {
		all = new ArrayList<MagicEntity>();
	}
	
	protected static void registerEntity(MagicEntity e) {
		all.add(e);
	}
	
	protected static void removeEntity(MagicEntity e) {
		all.remove(e);
	}
	
	public static ArrayList<MagicEntity> getAll() {
		return new ArrayList<MagicEntity> (all);
	}
	
}
