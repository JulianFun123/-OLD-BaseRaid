package eu.baseraid.core.inventories.lobbystate;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.inventories.BaseRaidInventory;
import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LobbyStateJoinInventory extends BaseRaidInventory {

    private Inventory inventory;

    public LobbyStateJoinInventory(Player player){
        inventory = player.getInventory();
        inventory.clear();

        inventory.setItem(0, (new ItemBuilder(Material.BANNER, 1)).setItemData((short) 1).setDisplayName("§aTeam auswählen").build());

        inventory.setItem(4, (new ItemBuilder(Material.CHEST, 1)).setDisplayName("§aKit auswählen").build());

        inventory.setItem(8, (new ItemBuilder(Material.REDSTONE_COMPARATOR, 1)).setDisplayName("§aSpiele Optionen").build());

    }

    public void open(Player player) {

    }
}
