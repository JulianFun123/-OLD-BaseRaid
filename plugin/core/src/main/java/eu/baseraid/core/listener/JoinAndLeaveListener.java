package eu.baseraid.core.listener;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.gamestates.Gamestates;
import eu.baseraid.core.gamestates.GamestatesHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinAndLeaveListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Bukkit.getConsoleSender().sendMessage("§bJOINED");
        BaseRaidPlugin.getInstance().getGamestatesHandler().callJoinListener(event);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Bukkit.getConsoleSender().sendMessage("§aLEFT");
        BaseRaidPlugin.getInstance().getGamestatesHandler().callLeaveListener(event);
    }

}
