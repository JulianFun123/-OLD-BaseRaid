package eu.baseraid.core.kits;

import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SkeletonSummoner extends KitBase implements Kit {

    private Player player;

    public SkeletonSummoner(Player player){
        setPlayer(player);

        initInventory();
    }

    public SkeletonSummoner(){
        initInventory();
    }

    private void initInventory(){

        items = new ItemStack[]{
                (new ItemBuilder(Material.WOOD_SWORD,1)).addEnchant(Enchantment.DURABILITY, -3).build(),
                (new ItemBuilder(Material.SNOW_BALL,5)).build()
        };

        boots      = null;
        helmet     = new ItemBuilder(Material.ENCHANTMENT_TABLE,0).build();
        chestplate = new ItemBuilder(Material.GOLD_CHESTPLATE, 1).build();
        leggings   = null;
    }

}
