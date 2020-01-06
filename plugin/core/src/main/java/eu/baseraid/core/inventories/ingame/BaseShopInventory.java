package eu.baseraid.core.inventories.ingame;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.manager.CoinsManager;
import eu.baseraid.core.scoreboard.IngameScoreboard;
import eu.baseraid.core.utils.ItemBuilder;
import eu.baseraid.core.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.HashMap;

public class BaseShopInventory extends eu.baseraid.core.inventories.ShopInventory {

    public static String inventoryName = "§aBase Shop";

    public BaseShopInventory(final Player player){
        this.player = player;
        items = new HashMap<String, Object[]>();
        inventory = Bukkit.createInventory(null, 6*9, inventoryName);
        inventory.clear();

        addItem(0, "Weakness for Enemies", new Potion(PotionType.WEAKNESS,1).toItemStack(1), 1, 5200, new BaseShopInventoryOnClick() {
            public void click(InventoryClickEvent event) {
                Player player = (Player) event.getWhoClicked();
                if (player != null)
                    PlayerUtils.getPlayerTeam(player).getItems().add("ENEMY_WEAKNESS");
            }
        }, new String[]{
                "§bGives the other enemies Weakness in your base"
        });

        addItem(1, "Poison for Enemies", new Potion(PotionType.POISON,1).toItemStack(1), 1, 7100, new BaseShopInventoryOnClick() {
            public void click(InventoryClickEvent event) {
                Player player = (Player) event.getWhoClicked();
                if (player != null)
                    PlayerUtils.getPlayerTeam(player).getItems().add("ENEMY_POISON");
            }
        }, new String[]{
                "§bGives the other enemies Poison in your base"
        });

        addItem(9, "Regeneration for your team", new Potion(PotionType.REGEN,1).toItemStack(1), 1, 6200, new BaseShopInventoryOnClick() {
            public void click(InventoryClickEvent event) {
                Player player = (Player) event.getWhoClicked();
                if (player != null)
                    PlayerUtils.getPlayerTeam(player).getItems().add("OWN_HEALING");
            }
        }, new String[]{
                "§bGives your Team regenaration"
        });

        addItem(27, "Warning", new ItemStack(Material.EYE_OF_ENDER), 1, 2600, new BaseShopInventoryOnClick() {
            public void click(InventoryClickEvent event) {
                Player player = (Player) event.getWhoClicked();
                if (player != null)
                    PlayerUtils.getPlayerTeam(player).getItems().add("BASE_WARNINGS");
            }
        }, new String[]{
                "§bGives you a warning if a enemie comes near your base"
        });



    }

    public void addPotion(int place, String name, Potion potion, int amount, int price, BaseShopInventoryOnClick onClick, String[] description){
        addItem(place, name+" Potion", potion.toItemStack(amount), amount, price, onClick, description);
    }

    public void addItem(int place, String name, ItemStack item, int amount, int price, BaseShopInventoryOnClick onClick, String[] description){
        String[] lore = new String[description.length+1];
        lore[0] = "§8Preis: §e"+price;
        for (int i=0;description.length > i;i++)
            lore[i+1] = description[i];

        inventory.setItem(place, (new ItemBuilder(item.getType(), amount))
                .setItemStack(item)
                .setAmount(item.getAmount())
                .setDisplayName(name)
                .setLore(lore).build());

        items.put(name, new Object[]{
                price,
                onClick
        });
    }

    public static void interactEvent(InventoryClickEvent event) {
        System.out.println("DEBUG2");
        Player player = (Player) event.getWhoClicked();
        BaseRaidPlugin plugin = BaseRaidPlugin.getInstance();
        if (event.getClickedInventory().getName().equals(inventoryName)) {
            if (event.getCurrentItem().hasItemMeta()) {
                CoinsManager coinsManager = new CoinsManager(player);
                BaseShopInventoryOnClick onClickEvent = null;
                int price = 0;
                String itemName = event.getCurrentItem().getItemMeta().getDisplayName();


                for (String name : items.keySet()) {
                    if (itemName.equals(name)){
                        onClickEvent = (BaseShopInventoryOnClick) items.get(name)[1];
                        price = (Integer) items.get(name)[0];
                    }
                }


                if (price > 0 && onClickEvent != null) {
                    if (coinsManager.buy(price)) {
                        player.sendMessage("§aDu hast erfolgreich §b"+event.getCurrentItem().getItemMeta().getDisplayName()+" gekauft! §7(§c-"+price+" coins§7)");
                        onClickEvent.click(event);
                    } else
                        player.sendMessage("§cDu hast nicht genügend Coins!");
                    player.closeInventory();
                    coinsManager.save();
                    new IngameScoreboard(player).set();
                }
            }
            event.setCancelled(true);
            System.out.println("DEBUG3");
        }
    }

    public interface BaseShopInventoryOnClick{
        void click(InventoryClickEvent event);
    }
}
