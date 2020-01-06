package eu.baseraid.core.inventories.lobbystate;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.kits.Kit;
import eu.baseraid.core.kits.Kits;
import eu.baseraid.core.scoreboard.LobbyScoreboard;
import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class KitChooserInventory {


    private Inventory inventory;

    public KitChooserInventory(){
        inventory = Bukkit.createInventory(null, 5*9, "§aWähle dein Kit aus!");

        for (int i=0; inventory.getSize() > i; i++ )
            inventory.setItem(i, (new ItemBuilder(Material.STAINED_GLASS_PANE ,1)).setItemData((short) 7).setDisplayName("§c ").build());
        int i = 0;
        for (String kitName : BaseRaidPlugin.getInstance().kits.keySet()) {
            Kits kit = BaseRaidPlugin.getInstance().kits.get(kitName);
            ItemBuilder itemBuilder = kit.getIcon();
            itemBuilder.setDisplayName(kitName);
            itemBuilder.setLore(new String[]{
                    "§b"+kit.getDescription()
            });
            inventory.setItem(i, itemBuilder.build());
            i++;
        }

    }

    public void open(Player player) {
        player.openInventory(inventory);
    }


    public static void interactEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        BaseRaidPlugin plugin = BaseRaidPlugin.getInstance();
        if (event.getClickedInventory().getName().equals("§aWähle dein Kit aus!")) {
            if (event.getCurrentItem().hasItemMeta()) {
                System.out.println(event.getCurrentItem().getItemMeta().getDisplayName());
                if (plugin.kits.containsKey( event.getCurrentItem().getItemMeta().getDisplayName())){
                    plugin.playerKits.put(player, plugin.kits.get(event.getCurrentItem().getItemMeta().getDisplayName()));
                    player.closeInventory();
                    for (Player player1 : BaseRaidPlugin.getInstance().players)
                        new LobbyScoreboard(player1).set();
                }
            }
            event.setCancelled(true);
        }
    }

}
