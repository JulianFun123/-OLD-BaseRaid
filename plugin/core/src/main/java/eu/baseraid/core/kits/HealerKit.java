package eu.baseraid.core.kits;

import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class HealerKit extends KitBase implements Kit {

    private Player player;

    public HealerKit(Player player){
        setPlayer(player);

        initInventory();
    }

    public HealerKit(){
        initInventory();
    }

    private void initInventory(){

        items = new ItemStack[]{
                (new ItemBuilder(Material.STONE_SWORD,1)).build(),
                new Potion(PotionType.INSTANT_HEAL).splash().toItemStack(5),
                new Potion(PotionType.REGEN).splash().toItemStack(2)
        };

        boots      = new ItemBuilder(Material.CHAINMAIL_BOOTS, 1).build();
        helmet     = new ItemBuilder(Material.BANNER, 1).setItemData((short) 11).build();
        chestplate = new ItemBuilder(Material.GOLD_CHESTPLATE, 1).build();
        leggings   = new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1).build();
    }

}
