package eu.baseraid.core.listener.interacts;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.gamestates.Gamestates;
import eu.baseraid.core.inventories.lobbystate.KitChooserInventory;
import eu.baseraid.core.inventories.lobbystate.TeamChooserInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class LobbyInteractListener implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        if (BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == Gamestates.LOBBY_STATE.getId()) {
            Player player = event.getPlayer();
            if (event.hasItem()) {
                ItemStack item = event.getItem();
                if (item.hasItemMeta()) {
                    player.sendMessage(item.getItemMeta().getDisplayName());
                    if (item.getItemMeta().getDisplayName().equals("§aTeam auswählen")) {
                        TeamChooserInventory teamChooserInventory = new TeamChooserInventory("RED");
                        teamChooserInventory.open(player);
                    } else if (item.getItemMeta().getDisplayName() == "§aKit auswählen") {
                        KitChooserInventory kitChooserInventory = new KitChooserInventory();
                        kitChooserInventory.open(player);
                    } else if (item.getItemMeta().getDisplayName() == "§aSpiele Optionen") {

                    }
                }
                if (!BaseRaidPlugin.getInstance().builder.contains(player))
                    event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void playerInventoryInteract(InventoryClickEvent event){
        if (BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == Gamestates.LOBBY_STATE.getId()) {
            String inventoryName = event.getInventory().getName();
            if (inventoryName.equals("§aWähle dein Team aus!")) {
                TeamChooserInventory.interactEvent(event);
            } else if (inventoryName.equals("§aWähle dein Kit aus!")) {
                KitChooserInventory.interactEvent(event);
            }

        }

    }

    @EventHandler
    public void playerInventoryItemMove(InventoryMoveItemEvent event){
        if (BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == Gamestates.LOBBY_STATE.getId()) {
            String inventoryName = event.getSource().getName();
            event.setCancelled(true);
        }
    }

}
