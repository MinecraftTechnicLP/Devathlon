package tk.mctechniclp.devathlon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import tk.mctechniclp.devathlon.api.MMPlayer;
import tk.mctechniclp.devathlon.listeners.AddElementListener;
import tk.mctechniclp.devathlon.listeners.FireSpellListener;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		registerListeners();
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				for(MMPlayer p : MMPlayer.getAll()) {
					p.sendSpellBar();
				}
			}
			
		}, 0L, 40L);
		/** Sending packet more often than needed to prevent fading **/
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
	}
}
