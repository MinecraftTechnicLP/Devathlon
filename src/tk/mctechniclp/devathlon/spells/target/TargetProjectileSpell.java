package tk.mctechniclp.devathlon.spells.target;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import tk.mctechniclp.devathlon.api.Element;
import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.projectiles.DamageStream;
import tk.mctechniclp.devathlon.spells.TargetSpell;

public class TargetProjectileSpell extends TargetSpell {

	public TargetProjectileSpell(Element[] elements) {
		super(elements);
	}

	@Override
	public boolean isActivatedBy(Element[] elements) {
		return Arrays.asList(elements).contains(Element.EARTH);
	}

	@Override
	public void fire(MMPlayer p) {
		Player bp = Bukkit.getPlayer(p.getUUID());
		new DamageStream(bp.getEyeLocation(), bp.getEyeLocation().getDirection()).launch();
	}

}
