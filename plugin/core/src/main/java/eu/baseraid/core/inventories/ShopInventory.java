package eu.baseraid.core.inventories;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.manager.CoinsManager;
import eu.baseraid.core.scoreboard.IngameScoreboard;
import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

import java.util.HashMap;

public class ShopInventory extends BaseRaidInventory {

    public Inventory inventory;
    public Player player;
    public static HashMap<String, Object[]> items;
    public static String inventoryName;

    public void addPotion(int place, String name, Potion potion, int amount, int price){
        addItem(place, name+" Potion", potion.toItemStack(amount), amount, price);

    }

    public void addItem(int place, String name, ItemStack item, int amount, int price){
        inventory.setItem(place, (new ItemBuilder(item.getType(), amount))
                .setItemStack(item)
                .setAmount(item.getAmount())
                .setDisplayName(name)
                .setLore(new String[]{"§8Preis: §e"+price}).build());

        items.put(name, new Object[]{
                price,
                item
        });
    }

    public void open() {
        player.openInventory(inventory);
    }
}
