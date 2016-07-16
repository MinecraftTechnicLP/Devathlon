package tk.mctechniclp.devathlon.projectiles;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.server.v1_10_R1.AxisAlignedBB;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public abstract class Projectile {
	protected Location loc;
	protected Vector vec;
	protected ArrayList<Particle> particles;
	protected double tolerance;
	
	public Projectile(Location loc, Vector vec, double thickness, Particle... particles) {
		this.loc = loc;
		this.vec = vec;
		this.tolerance = thickness / 2;
		this.particles = new ArrayList<Particle> (Arrays.asList(particles));
	}
	
	public void launch() {
		register(this);
	}
	
	protected LivingEntity getColliding() {
		for(Chunk chunk : loc.getWorld().getLoadedChunks()) {
			for(Entity e : chunk.getEntities()) {
				if(!(e instanceof LivingEntity)) continue;
				
				AxisAlignedBB box = ((CraftLivingEntity) e).getHandle().getBoundingBox();
				if(loc.getX() >= box.a && loc.getY() >= box.b && loc.getZ() >= box.c && loc.getX() <= box.d && loc.getY() <= box.e && loc.getZ() <= box.f) {
					return (LivingEntity) e;
				}
			}
		}
		
		return null;
	}

	public abstract void tick();
	protected abstract void impact(LivingEntity e);
	
	
	
	private static ArrayList<Projectile> all;
	
	static {
		all = new ArrayList<Projectile>();
	}
	
	protected static void register(Projectile p) {
		all.add(p);
	}
	
	protected static void remove(Projectile p) {
		all.remove(p);
	}
	
	public static ArrayList<Projectile> getAll() {
		return new ArrayList<Projectile>(all);
	}
	
}
