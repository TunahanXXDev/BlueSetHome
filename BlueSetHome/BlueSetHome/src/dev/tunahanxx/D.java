package dev.tunahanxx;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import dev.tunahanxx.commands.HomeCommands;

public class D extends JavaPlugin {

	public static D i;
	
	FileConfiguration c = this.getConfig();
	
	@Override
	public void onEnable() {
		
		this.getCommand("sethome").setExecutor(new HomeCommands());
		this.getCommand("home").setExecutor(new HomeCommands());
		this.getCommand("delhome").setExecutor(new HomeCommands());
		
		i = this;
		
		onConfigLoader();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void onConfigLoader() {
		
		c.options().copyDefaults(true);
		saveConfig();
	}
}