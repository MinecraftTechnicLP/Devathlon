package tk.mctechniclp.devathlon.spells.target;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import tk.mctechniclp.devathlon.api.Element;
import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.entities.ShieldEntity;
import tk.mctechniclp.devathlon.spells.TargetSpell;

public class TargetShieldSpell extends TargetSpell {

	public TargetShieldSpell(Element[] elements) {
		super(elements, new Element[] {Element.SHIELD});
	}
	
	/**
	 * Only Use this constructor to register the Spell
	 * @param required required Elements
	 */
	public TargetShieldSpell() {
		super(new Element[] {Element.SHIELD});
	}
	

	@Override
	public void fire(MMPlayer p) {
		Location loc = Bukkit.getPlayer(p.getUUID()).getEyeLocation();
		Location[] locs = new Location[8];
		
		for(int i = 0; i < 8; i++) {
			double d = i * Math.PI / 4;
			locs[i] = new Location(loc.getWorld(), Math.sin(d) * 1.5 + loc.getX(), loc.getY(), Math.cos(d) * 1.5 + loc.getZ());
		}
		
		new ShieldEntity(elements, locs);
		
	}
	
}
