package eu.baseraid.core.kits;

import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public interface Kit {
    void setPlayer(Player player);
    ItemStack getBoots();
    ItemStack getLeggings();
    ItemStack getChestplate();
    ItemStack getHelmet();
    ItemStack[] getItems();

}
