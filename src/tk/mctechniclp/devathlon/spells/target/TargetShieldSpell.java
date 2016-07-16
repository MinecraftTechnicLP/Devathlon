package tk.mctechniclp.devathlon.spells.target;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import tk.mctechniclp.devathlon.api.Element;
import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.entities.WaterShieldEntity;
import tk.mctechniclp.devathlon.spells.TargetSpell;

public class TargetShieldSpell extends TargetSpell {

	public TargetShieldSpell(Element[] elements) {
		super(elements);
	}

	@Override
	public boolean isActivatedBy(Element[] elements) {
		return Arrays.asList(elements).contains(Element.SHIELD);
	}

	@Override
	public void fire(MMPlayer p) {
		ArrayList<Element> list = new ArrayList<Element> (Arrays.asList(elements));
		
		Location loc = Bukkit.getPlayer(p.getUUID()).getLocation();
		Location[] locs = new Location[8];
		
		double yaw = Math.toRadians(loc.getYaw() - 30 + 180);
		locs[0] = loc.clone().add(Math.sin(yaw) * 4, 0, Math.cos(yaw) * 4);
		locs[1] = locs[0].clone().add(0, 1, 0);
		
		yaw = Math.toRadians(loc.getYaw() - 10 + 180);
		locs[2] = loc.clone().add(Math.sin(yaw) * 4, 0, Math.cos(yaw) * 4);
		locs[3] = locs[2].clone().add(0, 1, 0);
		
		yaw = Math.toRadians(loc.getYaw() + 10 + 180);
		locs[4] = loc.clone().add(Math.sin(yaw) * 4, 0, Math.cos(yaw) * 4);
		locs[5] = locs[4].clone().add(0, 1, 0);
		
		yaw = Math.toRadians(loc.getYaw() + 30 + 180);
		locs[6] = loc.clone().add(Math.sin(yaw) * 2, 0, Math.cos(yaw) * 4);
		locs[7] = locs[6].clone().add(0, 1, 0);
		
		for(Location l : locs) {
			System.out.println(l.getBlockX() + " - " + l.getBlockY() + " - " + l.getBlockZ());
		}
		
		if(list.contains(Element.WATER)) {
			new WaterShieldEntity(locs);
		}
		
	}
	
}
