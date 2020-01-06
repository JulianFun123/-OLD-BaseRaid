package eu.baseraid.core.listener;

import eu.baseraid.core.BaseRaidPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        BaseRaidPlugin baseRaidInstance = BaseRaidPlugin.getInstance();
        if (baseRaidInstance.getGamestatesHandler().currentGamestate == 0 || baseRaidInstance.getGamestatesHandler().currentGamestate == 1 || baseRaidInstance.getGamestatesHandler().currentGamestate == 2) {
            event.setCancelled(true);
        }
    }
}
