package eu.baseraid.core.listener;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.manager.CoinsManager;
import eu.baseraid.core.scoreboard.IngameScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupListener implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        Player player = event.getPlayer();
        if (BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == 1) {
            if (event.getItem().getItemStack().hasItemMeta()) {
                int plusCoins = 0;
                if (event.getItem().getItemStack().getItemMeta().getDisplayName().equals("§eTEMP__IRON")) {
                    plusCoins = event.getItem().getItemStack().getAmount()*15;
                } else if (event.getItem().getItemStack().getItemMeta().getDisplayName().equals("§eTEMP___GOLD")) {
                    plusCoins = event.getItem().getItemStack().getAmount()*60;
                } else if (event.getItem().getItemStack().getItemMeta().getDisplayName().equals("§eTEMP__DIAMOND")) {
                    plusCoins = event.getItem().getItemStack().getAmount()*125;
                }

                if (plusCoins != 0) {
                    CoinsManager coinsManager = new CoinsManager(player);
                    coinsManager.addCoins(plusCoins);
                    coinsManager.save();
                    event.getItem().remove();

                    IngameScoreboard ingameScoreboard = new IngameScoreboard(player);
                    ingameScoreboard.set();

                    event.setCancelled(true);
                }
            }
        }
    }

}
