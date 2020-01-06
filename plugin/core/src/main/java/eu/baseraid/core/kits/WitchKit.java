package eu.baseraid.core.kits;

import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class WitchKit extends KitBase implements Kit {

    private Player player;

    public WitchKit(Player player){
        setPlayer(player);

        initInventory();
    }

    public WitchKit(){
        initInventory();
    }

    private void initInventory(){

        items = new ItemStack[]{
                (new ItemBuilder(Material.WOOD_SWORD,1)).build(),
                new Potion(PotionType.INSTANT_DAMAGE).splash().toItemStack(5),
                new Potion(PotionType.POISON).splash().toItemStack(2),
                new Potion(PotionType.WEAKNESS).splash().toItemStack(1)
        };

        boots      = new Potion(PotionType.SLOWNESS).splash().toItemStack(1);
        helmet     = new ItemBuilder(Material.BANNER, 1).setItemData((short) 5).build();
        chestplate = new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).build();
        leggings   = new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1).build();
    }
}
