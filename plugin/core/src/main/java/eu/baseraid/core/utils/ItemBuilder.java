package eu.baseraid.core.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.Potion;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material, int amount){
        item = new ItemStack(material, amount);
        itemMeta = item.getItemMeta();
    }

    public ItemBuilder setItemStack(ItemStack itemStack){
        item = itemStack;
        return this;
    }

    public ItemBuilder setPotion(Potion potion){
        item = potion.toItemStack(1);
        return this;
    }

    public ItemBuilder setPotion(Potion potion, int amount){
        item = potion.toItemStack(amount);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setType(Material material) {
        item.setType(material);
        return this;
    }

    public ItemBuilder setDisplayName(String name){
        itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(List lores){
        itemMeta.setLore(lores);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level){
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder setItemData(short data){
        item.setDurability(data);
        return this;
    }

    public ItemBuilder setLore(String[] lores){
        ArrayList<String> list = new ArrayList<String>();
        for (String lore : lores)
            list.add(lore);

        itemMeta.setLore(list);
        return this;
    }

    public ItemStack build(){
        item.setItemMeta(itemMeta);
        return item;
    }



}
