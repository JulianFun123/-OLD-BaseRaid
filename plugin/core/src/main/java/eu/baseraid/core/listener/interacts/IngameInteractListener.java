package eu.baseraid.core.listener.interacts;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.gamestates.Gamestates;
import eu.baseraid.core.inventories.ingame.BaseShopInventory;
import eu.baseraid.core.inventories.ingame.ShopInventory;
import eu.baseraid.core.inventories.lobbystate.KitChooserInventory;
import eu.baseraid.core.inventories.lobbystate.TeamChooserInventory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.ServicePriority;

public class IngameInteractListener implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        if (BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == Gamestates.INGAME_STATE.getId()) {
            Player player = event.getPlayer();
            if (event.hasItem()) {
                ItemStack item = event.getItem();
                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                    if (item.getItemMeta().getDisplayName().equals("§aShop")) {
                        ShopInventory shopInventory = new ShopInventory(player);
                        shopInventory.open();
                        if (!BaseRaidPlugin.getInstance().builder.contains(player))
                            event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void inventoryOpened(InventoryOpenEvent event){
        System.out.println("DEBUG: Opened Inventory named "+event.getInventory().getName());
        if (event.getInventory().getType() == InventoryType.MERCHANT)
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerInventoryInteract(InventoryClickEvent event){
        if (BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == Gamestates.INGAME_STATE.getId()) {
            String inventoryName = event.getInventory().getName();
            if (inventoryName.equals(ShopInventory.inventoryName)) {
                ShopInventory.interactEvent(event);
            } else if (inventoryName.equals(BaseShopInventory.inventoryName)) {
                BaseShopInventory.interactEvent(event);
            }

        }

    }

    @EventHandler
    public void playerInventoryItemMove(InventoryMoveItemEvent event){
        if (BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == Gamestates.INGAME_STATE.getId()) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void  playerInteractEntity(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == Gamestates.INGAME_STATE.getId()) {
            if (entity.getName().equals("§aGame Shop")) {
                event.setCancelled(true);
                new BaseShopInventory(player).open();
            }
        }
    }

    @EventHandler
    public void itemDespawn(ItemDespawnEvent event){
        if (event.getEntity().getType() == EntityType.ZOMBIE)
            event.setCancelled(true);
    }

}
