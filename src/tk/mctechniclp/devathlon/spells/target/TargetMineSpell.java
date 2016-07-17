package tk.mctechniclp.devathlon.spells.target;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import tk.mctechniclp.devathlon.api.Element;
import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.entities.MineEntity;
import tk.mctechniclp.devathlon.spells.TargetSpell;

public class TargetMineSpell extends TargetSpell {

	public TargetMineSpell(Element[] elements) {
		super(elements, new Element[] {Element.EARTH, Element.SHIELD});
	}
	
	/**
	 * Only Use this constructor to register the Spell
	 * @param required required Elements
	 */
	public TargetMineSpell() {
		super(new Element[] {Element.EARTH, Element.SHIELD});
	}
	
	@Override
	public void fire(MMPlayer p) {
		Player bp = Bukkit.getPlayer(p.getUUID());
		Location loc = bp.getLocation().clone().add(bp.getLocation().getDirection());
		
		while(!loc.getBlock().getType().isSolid()) {
			loc.add(0, -1, 0);
		}
		
		new MineEntity(elements, loc);
	}

}
