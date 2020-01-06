package eu.baseraid.core.gamestates;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.inventories.lobbystate.LobbyStateJoinInventory;
import eu.baseraid.core.kits.Kits;
import eu.baseraid.core.scoreboard.IngameScoreboard;
import eu.baseraid.core.scoreboard.LobbyScoreboard;
import eu.baseraid.core.utils.LocationUtil;
import eu.baseraid.core.utils.PlayerUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.ChatMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class GameEndState  implements Gamestate {

    private ArrayList<Player> players;
    private BaseRaidPlugin baseRaidInstance;
    private int seconds = 60;
    private int timer;

    public void start() {
        baseRaidInstance = BaseRaidPlugin.getInstance();
        players = baseRaidInstance.players;

        for (final Player player : players){
            player.teleport(LocationUtil.getLocationInConfig("game.locations.gameendspawn"));
            PlayerUtils.sendTitle(player, "THE TEAM "+baseRaidInstance.wonTeam+" won!","");
            Bukkit.getScheduler().scheduleSyncDelayedTask(baseRaidInstance, new Runnable() {
                public void run() {
                    PlayerUtils.removeTitle(player);
                }
            },60);
        }
        timer();
    }

    public void stop() {

    }

    private void timer(){
        seconds = 60;
        timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(baseRaidInstance, new Runnable() {
            public void run() {
                seconds--;


            }
        }, 0, 20);
    }

    public void join(PlayerJoinEvent event){

        Player player = event.getPlayer();
        player.kickPlayer("§cDER SERVER IST VOLL!");
    }

    public void leave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        BaseRaidPlugin.getInstance().playerKits.remove(player);
        BaseRaidPlugin.getInstance().playerCoins.remove(player);
        players.remove(player);
        event.setQuitMessage("§e[§c-§e] §3"+player.getName()+" §7hat das Spiel verlassen. §8(§a"+players.size()+"§7/§b"+baseRaidInstance.MAX_PLAYERS+"§8)");
        for (Player player1 : BaseRaidPlugin.getInstance().players)
            new IngameScoreboard(player1).set();
    }
}
