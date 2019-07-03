package me.jadc.survivalteleport;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class ItemTeleporter implements Listener {
	
	private String name = ChatColor.AQUA + st.name;
	private Material material = Material.ENDER_PEARL;
	
	public ItemTeleporter() { }
	
	public ItemStack getItem() {
		ItemStack tp = new ItemStack(material);
		ItemMeta meta = tp.getItemMeta();
		meta.setDisplayName(name);
		meta.addEnchant(Enchantment.DURABILITY, (int)(Math.random() * 1000), true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		tp.setItemMeta(meta);
		return tp;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getItem() == null) return;
			if(!e.getItem().getType().equals(material)) return;
			if(!e.getItem().hasItemMeta()) return;
			if(e.getItem().getItemMeta() == null) return;
			if(!e.getItem().getItemMeta().hasDisplayName()) return;
			if(e.getItem().getItemMeta().getDisplayName() == null) return;
			if(e.getItem().getItemMeta().getDisplayName().equals(name)) {
				e.setCancelled(true);
				new InterfaceTeleporter(e.getPlayer());
			}
		}
	}
}
