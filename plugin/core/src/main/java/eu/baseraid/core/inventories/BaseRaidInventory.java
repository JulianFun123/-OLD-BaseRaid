package eu.baseraid.core.inventories;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BaseRaidInventory {

    private Inventory inventory;

    public Inventory getInventory(){
        return inventory;
    }

    void open(Player player) {
        player.openInventory(inventory);
    }

}
