package eu.baseraid.core.kits;

import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class TankKit extends KitBase implements Kit {

    private Player player;

    public TankKit(Player player){
        setPlayer(player);

        initInventory();
    }

    public TankKit(){
        initInventory();
    }

    private void initInventory(){

        items = new ItemStack[]{
                (new ItemBuilder(Material.WOOD_SWORD,1)).addEnchant(Enchantment.DURABILITY, 9).build()
        };

        boots      = new ItemBuilder(Material.IRON_BOOTS, 1).build();
        helmet     = new ItemBuilder(Material.IRON_HELMET, 1).setItemData((short) 11).build();
        chestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).build();
        leggings   = new ItemBuilder(Material.DIAMOND_LEGGINGS, 1).build();
    }

}
