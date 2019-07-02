package me.jadc.survivalteleport;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeTeleporter {
	public static void register() {
		Bukkit.addRecipe(new ShapedRecipe(new NamespacedKey(st.getInstance(), "Teleporter"), new ItemTeleporter().getItem())
				.shape("R R", "IOI", "IPI")
				.setIngredient('R', Material.REDSTONE_TORCH)
				.setIngredient('I', Material.IRON_INGOT)
				.setIngredient('O', Material.ENDER_PEARL)
				.setIngredient('P', Material.PISTON)
		);
	}
}
