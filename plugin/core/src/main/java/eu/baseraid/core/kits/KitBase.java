package eu.baseraid.core.kits;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitBase {

    public Player player;

    public  ItemStack boots,
            helmet,
            leggings,
            chestplate;

    public ItemStack[] items;


    public ItemStack getBoots() {
        return boots;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ItemStack[] getItems(){
        return items;
    }


}
