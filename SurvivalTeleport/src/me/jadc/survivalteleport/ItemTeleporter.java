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
	
	private String name = ChatColor.GOLD + st.name;
	
	public ItemTeleporter() { }
	
	public ItemStack getItem() {
		ItemStack tp = new ItemStack(Material.ENDER_PEARL);
		ItemMeta meta = tp.getItemMeta();
		meta.setDisplayName(name);
		meta.addEnchant(Enchantment.KNOCKBACK, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		tp.setItemMeta(meta);
		return tp;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() != Action.RIGHT_CLICK_AIR || e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if(e.getItem() == null) return;
		if(!e.getItem().getType().equals(getItem().getType())) return;
		if(!e.getItem().hasItemMeta()) return;
		if(e.getItem().getItemMeta() == null) return;
		if(!e.getItem().getItemMeta().hasDisplayName()) return;
		if(e.getItem().getItemMeta().getDisplayName() == null) return;
		if(e.getItem().getItemMeta().getDisplayName().equals(getItem().getItemMeta().getDisplayName())) {
			e.setCancelled(true);
			new InterfaceTeleporter(e.getPlayer());
		}
	}
}
