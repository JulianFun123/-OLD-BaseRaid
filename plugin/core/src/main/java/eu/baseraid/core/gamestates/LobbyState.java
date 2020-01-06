package eu.baseraid.core.gamestates;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.inventories.BaseRaidInventory;
import eu.baseraid.core.inventories.lobbystate.LobbyStateJoinInventory;
import eu.baseraid.core.kits.Kits;
import eu.baseraid.core.scoreboard.BaseRaidScoreboard;
import eu.baseraid.core.scoreboard.IngameScoreboard;
import eu.baseraid.core.scoreboard.LobbyScoreboard;
import eu.baseraid.core.utils.LocationUtil;
import eu.baseraid.core.utils.PlayerUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class LobbyState implements Gamestate {

    private ArrayList<Player> players;
    private BaseRaidPlugin baseRaidInstance;
    private int seconds = 60;
    private int timer;

    public void start() {
        baseRaidInstance = BaseRaidPlugin.getInstance();
        players = baseRaidInstance.players;
        timer();
    }

    public void stop() {

    }

    private void timer(){
        seconds = 60;
        timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(baseRaidInstance, new Runnable() {
            public void run() {
                seconds--;

                for (Player player1 : BaseRaidPlugin.getInstance().players){
                    PlayerUtils.sendActionBar(player1, "§aGame starts in "+seconds);
                }

                switch (seconds) {
                    case 60:
                    case 45:
                    case 30:
                    case 15:
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        Bukkit.broadcastMessage("§aDas Spiel startet in "+seconds+" Sekunden");
                        for (Player player : players) {
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                        }
                        break;
                    case 1:
                        Bukkit.broadcastMessage("§aDas Spiel startet in einer Sekunde");
                        for (Player player : players) {
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                        }
                        break;
                    case 0:
                        Bukkit.getScheduler().cancelTask(timer);
                        if (players.size() > BaseRaidPlugin.getInstance().MIN_PLAYERS-1) {
                            Bukkit.broadcastMessage("Das Spiel ist gestartet");
                            BaseRaidPlugin.getInstance().getGamestatesHandler().startState(Gamestates.INGAME_STATE);
                        } else {
                            Bukkit.broadcastMessage("§cDas Spiel konnte nicht gestartet werden");
                            Bukkit.broadcastMessage("§aDas Spiel startet in 60 Sekunden");
                            timer();
                        }
                        break;

                }
            }
        }, 0, 20);
    }

    public void join(PlayerJoinEvent event){

        Player player = event.getPlayer();
        BaseRaidPlugin.getInstance().playerKits.put(player, Kits.ASSASINE);

        if (players.size() < BaseRaidPlugin.getInstance().MAX_PLAYERS) {
            players.add(player);
            LobbyStateJoinInventory lobbyInv = new LobbyStateJoinInventory(player);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.getInventory().setLeggings(null);
            player.getInventory().setBoots(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setHelmet(null);

            player.setGameMode(GameMode.SURVIVAL);
            player.teleport(LocationUtil.getLocationInConfig("game.locations.wartelobby"));
            BaseRaidPlugin.getInstance().teamRed.add(player);
            LobbyScoreboard lobbyScoreboard = new LobbyScoreboard(player);
            lobbyScoreboard.set();
            event.setJoinMessage("§e[§a+§e] §3" + player.getName() + " §7ist das Spiel beigetreten. §8(§a" + players.size() + "§7/§b" + baseRaidInstance.MAX_PLAYERS + "§8)");
            BaseRaidPlugin.getInstance().playerCoins.put(player, 0);
        } else {
            player.kickPlayer("§cDER SERVER IST VOLL!");
        }

        for (Player player1 : BaseRaidPlugin.getInstance().players)
            new LobbyScoreboard(player1).set();
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
