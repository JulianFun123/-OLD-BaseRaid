package eu.baseraid.core.kits;

import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BuilderKit extends KitBase implements Kit {

    public BuilderKit(Player player){
        setPlayer(player);
        initInventory();
    }

    public BuilderKit(){
        initInventory();
    }

    private void initInventory(){
        items = new ItemStack[]{
                (new ItemBuilder(Material.GOLD_SWORD,1)).addEnchant(Enchantment.DURABILITY, 9).build(),
                (new ItemBuilder(Material.STAINED_CLAY,25)).build()
        };

        boots      = new ItemBuilder(Material.LEATHER_BOOTS, 1).addEnchant(Enchantment.DURABILITY, 9).build();
        helmet     = new ItemBuilder(Material.GOLD_HELMET, 1).addEnchant(Enchantment.DURABILITY, 9).build();
        chestplate = new ItemBuilder(Material.IRON_CHESTPLATE, 1).build();
        leggings   = new ItemBuilder(Material.LEATHER_LEGGINGS, 1).build();
    }

}
