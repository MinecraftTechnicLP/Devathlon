package tk.mctechniclp.devathlon.api;

import org.bukkit.Location;

public class RelativeLoc2 {
	private double x, z;

	public RelativeLoc2(double x, double z) {
		this.x = x;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getZ() {
		return z;
	}

	public Location toLocation(Location start) {
		return new Location(start.getWorld(), x + start.getX(), start.getY(), z + start.getZ());
	}
}
