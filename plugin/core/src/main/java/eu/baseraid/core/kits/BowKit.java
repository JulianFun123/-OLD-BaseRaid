package eu.baseraid.core.kits;

import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BowKit extends KitBase implements Kit {

    public BowKit(Player player){
        setPlayer(player);
        initInventory();
    }

    public BowKit(){
        initInventory();
    }

    private void initInventory(){
        items = new ItemStack[]{
                (new ItemBuilder(Material.BOW,1)).addEnchant(Enchantment.ARROW_INFINITE,1).addEnchant(Enchantment.ARROW_DAMAGE, 1).build(),
                (new ItemBuilder(Material.ARROW,1)).build()
        };

        boots      = new ItemBuilder(Material.LEATHER_BOOTS, 1).build();
        helmet     = new ItemBuilder(Material.LEATHER_HELMET, 1).build();
        chestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).build();
        leggings   = new ItemBuilder(Material.LEATHER_LEGGINGS, 1).build();
    }


}