package eu.baseraid.core.inventories.ingame;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.manager.CoinsManager;
import eu.baseraid.core.scoreboard.IngameScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.HashMap;

public class ShopInventory extends eu.baseraid.core.inventories.ShopInventory {

    public static String inventoryName = "§aShop";

    public ShopInventory(Player player){
        this.player = player;
        items = new HashMap<String, Object[]>();
        inventory = Bukkit.createInventory(null, 6*9, inventoryName);
        inventory.clear();

        addPotion(0, "Instant Healing", new Potion(PotionType.INSTANT_HEAL), 1, 160);
        addPotion(1, "Regeneration", new Potion(PotionType.REGEN), 1, 160);
        addPotion(2, "Jump", new Potion(PotionType.JUMP).splash(), 1, 300);
        addPotion(3, "Speed", new Potion(PotionType.SPEED).splash(), 1, 400);
        addPotion(4, "Invisibility", new Potion(PotionType.INVISIBILITY).splash(), 1, 600);
        addPotion(5, "Strength", new Potion(PotionType.STRENGTH).splash(), 1, 1100);

        addPotion(9, "Instant Damage Splash", new Potion(PotionType.INSTANT_DAMAGE).splash(), 1, 250);
        addPotion(10, "Poison Splash", new Potion(PotionType.POISON).splash(), 1, 350);
        addPotion(11, "Slowness Splash", new Potion(PotionType.SLOWNESS).splash(), 1, 370);
        addPotion(11, "Weaknes Splash", new Potion(PotionType.WEAKNESS).splash(), 1, 370);


        addItem(27, "Milk", new ItemStack(Material.MILK_BUCKET), 1, 60);

        addItem(36, "Rod", new ItemStack(Material.FISHING_ROD), 1, 1000);
        addItem(37, "Clay", new ItemStack(Material.STAINED_CLAY, 15, (short) 13), 15,500);
        addItem(38, "Enderpearl", new ItemStack(Material.ENDER_PEARL), 1, 800);

        addItem(45, "Iron Pickaxe", new ItemStack(Material.IRON_PICKAXE), 1,3900);
        addItem(46, "Diamond Pickaxe", new ItemStack(Material.DIAMOND_PICKAXE), 1,5000);
    }

    public static void interactEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        BaseRaidPlugin plugin = BaseRaidPlugin.getInstance();
        if (event.getClickedInventory().getName().equals(inventoryName)) {
            if (event.getCurrentItem().hasItemMeta()) {
                CoinsManager coinsManager = new CoinsManager(player);
                ItemStack item = null;
                int price = 0;
                String itemName = event.getCurrentItem().getItemMeta().getDisplayName();


                for (String name : items.keySet()) {
                    if (itemName.equals(name)){
                        item = (ItemStack) items.get(name)[1];
                        price = (Integer) items.get(name)[0];
                    }
                }


                if (price > 0 && item != null) {
                    if (coinsManager.buy(price)) {
                        player.sendMessage("§aDu hast erfolgreich §b"+event.getCurrentItem().getItemMeta().getDisplayName()+" gekauft! §7(§c-"+price+" coins§7)");
                        player.getInventory().addItem(item);
                    } else
                        player.sendMessage("§cDu hast nicht genügend Coins!");
                    player.closeInventory();
                    coinsManager.save();
                    new IngameScoreboard(player).set();
                }
            }
            event.setCancelled(true);
        }
    }

}
