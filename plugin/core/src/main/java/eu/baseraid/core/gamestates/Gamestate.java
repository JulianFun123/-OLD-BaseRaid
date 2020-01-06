package eu.baseraid.core.gamestates;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public interface Gamestate {

    public void start();
    public void stop();

    public void join(PlayerJoinEvent event);

    public void leave(PlayerQuitEvent event);

}
