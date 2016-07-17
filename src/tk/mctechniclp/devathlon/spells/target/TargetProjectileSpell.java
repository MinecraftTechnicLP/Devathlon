package tk.mctechniclp.devathlon.spells.target;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import tk.mctechniclp.devathlon.api.Element;
import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.projectiles.DamageStream;
import tk.mctechniclp.devathlon.spells.TargetSpell;

public class TargetProjectileSpell extends TargetSpell {

	public TargetProjectileSpell(Element[] elements) {
		super(elements, new Element[] {Element.DAMAGE});
	}
	
	/**
	 * Only Use this constructor to register the Spell
	 * @param required required Elements
	 */
	public TargetProjectileSpell() {
		super(new Element[] {Element.DAMAGE});
	}
	
	@Override
	public void fire(MMPlayer p) {
		Player bp = Bukkit.getPlayer(p.getUUID());
		new DamageStream(bp.getEyeLocation(), bp.getEyeLocation().getDirection()).launch();
	}

}
