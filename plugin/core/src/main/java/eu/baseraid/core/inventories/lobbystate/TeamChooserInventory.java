package eu.baseraid.core.inventories.lobbystate;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.inventories.BaseRaidInventory;
import eu.baseraid.core.scoreboard.LobbyScoreboard;
import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TeamChooserInventory extends BaseRaidInventory {

    private Inventory inventory;

    public TeamChooserInventory(String team){
        inventory = Bukkit.createInventory(null, 9, "§aWähle dein Team aus!");

        for (int i=0; inventory.getSize() > i; i++ )
            inventory.setItem(i, (new ItemBuilder(Material.STAINED_GLASS_PANE ,1)).setItemData((short) 8).setDisplayName("§c-").build());

        ItemBuilder teamBlueItemBuilder = (new ItemBuilder(Material.BANNER,1)).setItemData((short) 4).setDisplayName("§bTeam Blue");
        ItemBuilder teamRedItemBuilder = (new ItemBuilder(Material.BANNER,1)).setItemData((short) 1).setDisplayName("§cTeam Red");

        List<String> teamRedLore = new ArrayList<String>();
        List<String> teamBlueLore = new ArrayList<String>();

        for (Player p : BaseRaidPlugin.getInstance().teamRed)
            teamRedLore.add("§c"+p.getName());

        for (Player p : BaseRaidPlugin.getInstance().teamBlue)
            teamBlueLore.add("§c"+p.getName());

        teamBlueItemBuilder.setLore(teamBlueLore);
        teamRedItemBuilder.setLore(teamRedLore);

        if (team.equals("BLUE"))
            teamBlueItemBuilder.addEnchant(Enchantment.LUCK, 1);
        else if (team .equals("RED"))
            teamRedItemBuilder.addEnchant(Enchantment.LUCK, 1);

        ItemStack teamBlueItem = teamBlueItemBuilder.build();
        ItemStack teamRedItem = teamRedItemBuilder.build();

        inventory.setItem(2, teamRedItem );
        inventory.setItem(6,  teamBlueItem);
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public static void interactEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        BaseRaidPlugin plugin = BaseRaidPlugin.getInstance();

        if (event.getCurrentItem().hasItemMeta()) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bTeam Blue")) {
                if (!plugin.teamBlue.contains(player)) {
                    if (plugin.teamRed.contains(player))
                        plugin.teamRed.remove(player);

                    plugin.teamBlue.add(player);

                    LobbyScoreboard lobbyScoreboard = new LobbyScoreboard(player);
                    lobbyScoreboard.set();

                    player.closeInventory();
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cTeam Red")) {
                if (!plugin.teamRed.contains(player)) {
                    if (plugin.teamBlue.contains(player))
                        plugin.teamBlue.remove(player);

                    plugin.teamRed.add(player);

                    LobbyScoreboard lobbyScoreboard = new LobbyScoreboard(player);
                    lobbyScoreboard.set();

                    player.closeInventory();
                }
            }
        }
        for (Player player1 : plugin.players)
            new LobbyScoreboard(player1).set();
        
        event.setCancelled(true);
    }


}
