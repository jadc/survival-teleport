package me.jadc.survivalteleport;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InterfaceTeleporter {
	
	public Inventory invTeleporter;
	
	public InterfaceTeleporter(Player p) {
		invTeleporter = Bukkit.createInventory(null, 9*6, st.name);
	}
}
