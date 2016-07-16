package tk.mctechniclp.devathlon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import tk.mctechniclp.devathlon.listeners.FireSpellListener;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		registerListener();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	private static void registerListener() {
		Bukkit.getPluginManager().registerEvents(new FireSpellListener(), instance);
	}
}
