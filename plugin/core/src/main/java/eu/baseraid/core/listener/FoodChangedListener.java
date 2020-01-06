package eu.baseraid.core.listener;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.gamestates.Gamestates;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChangedListener implements Listener {

    @EventHandler
    public void onFoodChanged(FoodLevelChangeEvent event) {
        //if (BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == Gamestates.LOBBY_STATE.getId()) {
            event.setCancelled(true);
        //}
    }

}
