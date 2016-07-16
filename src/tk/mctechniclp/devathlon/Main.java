package tk.mctechniclp.devathlon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.entities.MagicEntity;
import tk.mctechniclp.devathlon.listeners.AddElementListener;
import tk.mctechniclp.devathlon.listeners.CollideListener;
import tk.mctechniclp.devathlon.listeners.DamageListener;
import tk.mctechniclp.devathlon.listeners.FireSpellListener;
import tk.mctechniclp.devathlon.projectiles.Projectile;
import tk.mctechniclp.devathlon.spells.SpellManager;
import tk.mctechniclp.devathlon.spells.SpellPriority;
import tk.mctechniclp.devathlon.spells.self.SelfShieldSpell;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		registerSpells();
		registerListeners();
		runTasks();

	}

	@Override
	public void onDisable() {
		
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	private static void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new FireSpellListener(), instance);
		Bukkit.getPluginManager().registerEvents(new AddElementListener(), instance);
		Bukkit.getPluginManager().registerEvents(new DamageListener(), instance);
		Bukkit.getPluginManager().registerEvents(new CollideListener(), instance);
	}
	
	private static void registerSpells() {
		SpellManager.registerSelfSpell(SelfShieldSpell.class, SpellPriority.LOWEST);
	}
	
	
	private void runTasks() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				for(MMPlayer p : MMPlayer.getAll()) {
					p.sendSpellBar();
				}
			}
			
		}, 0L, 40L);
		/** Sending packet more often than needed to prevent fading **/
		
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				for(MMPlayer p : MMPlayer.getAll()) {
					p.tickStatusEffects();
				}
				for(MagicEntity e : MagicEntity.getAll()) {
					e.tick();
				}
				for(Projectile p : Projectile.getAll()) {
					p.tick();
				}
			}
			
		}, 0L, 1L);
	}
}
