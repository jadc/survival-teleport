package me.jadc.survivalteleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class InterfaceTeleporter implements Listener {
	
	public InterfaceTeleporter() {}
	
	public Inventory invTeleporter;
	private final int columns = 9;
	private final int rows = 6;
	
	public InterfaceTeleporter(Player p) {
		// Create inv
		invTeleporter = Bukkit.createInventory(null, columns * rows, st.name);
		
		// Border itemstack
		
		
		//// Borders
		for(int i = 0; i < columns * rows; i++) {
			if(i < columns || i >= columns * (rows - 1)) {
				invTeleporter.setItem(i, getBorder());
			}else {
				if(i % 9 == 0) {
					invTeleporter.setItem(i, getBorder());
					invTeleporter.setItem(i + (columns - 1), getBorder());
				}
			}
		}
		
		//// Players
		for(int i = 0; i < columns * rows; i++) {
			// If occupied
			if(invTeleporter.getItem(i) == null) {
				newHead(i, invTeleporter, p);
			}
		}
		
		// Open inv
		p.openInventory(invTeleporter);
		
		// Effects
		p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1, 1);
	}
	
	public static ItemStack getBorder() {
		ItemStack border = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
		ItemMeta borderMeta = border.getItemMeta();
		borderMeta.setDisplayName(" ");
		borderMeta.addEnchant(Enchantment.DURABILITY, 1, false);
		borderMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		border.setItemMeta(borderMeta);
		return border;
	}
	
	private void newHead(int index, Inventory inv, Player p) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		meta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getPlayer().getUniqueId()));
		head.setItemMeta(meta);
		inv.setItem(index, head);
	}
	
	// Interaction
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();
		
		if(e.getView().getTitle().equals(st.name)) {
			if(item == null) return;
			if(item.getType() == null) return;
			if(!item.hasItemMeta()) return;
			if(item.getItemMeta() == null) return;
			if(item.getItemMeta().getDisplayName() != null) {
				if(item.getItemMeta().getDisplayName().equals(new ItemTeleporter().getItem().getItemMeta().getDisplayName())) e.setCancelled(true);
			}
			if(item.getType().equals(Material.PLAYER_HEAD)) e.setCancelled(true);
			if(item.equals(getBorder())) e.setCancelled(true);
			
			if(item.getType() == Material.PLAYER_HEAD) {
				SkullMeta meta = (SkullMeta) item.getItemMeta();
				OfflinePlayer offp = meta.getOwningPlayer();
				if(offp.isOnline()) {
					Player target = offp.getPlayer();
					p.teleport(target);
				}else {
					p.sendMessage(ChatColor.RED + "That player is not online.");
				}
			}
		}
	}
}











