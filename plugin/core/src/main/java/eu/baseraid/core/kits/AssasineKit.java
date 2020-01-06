package eu.baseraid.core.kits;

import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class AssasineKit extends KitBase implements Kit {

    private Player player;

    public AssasineKit(Player player){
        setPlayer(player);

        initInventory();
    }

    public AssasineKit(){
        initInventory();
    }

    private void initInventory(){
        items = new ItemStack[]{
                (new ItemBuilder(Material.DIAMOND_SWORD, 1)).build()
        };

        boots      = new ItemBuilder(Material.CHAINMAIL_BOOTS, 1).build();
        helmet     = new ItemBuilder(Material.CHAINMAIL_HELMET, 1).build();
        chestplate = new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1).build();
        leggings   = new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1).build();
    }

}
