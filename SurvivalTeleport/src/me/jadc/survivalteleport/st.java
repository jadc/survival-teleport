package me.jadc.survivalteleport;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class st extends JavaPlugin {
	
	public static st instance;
	public static String name = "Ender Location Proxy";
	
	@Override
	public void onEnable() {
		instance = this;
		
		Bukkit.getPluginManager().registerEvents(new ItemTeleporter(), this);
		RecipeTeleporter.register();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static st getInstance() {
		return instance;
	}
}
