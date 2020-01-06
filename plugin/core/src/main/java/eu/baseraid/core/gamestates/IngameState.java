package eu.baseraid.core.gamestates;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.scoreboard.IngameScoreboard;
import eu.baseraid.core.utils.ItemBuilder;
import eu.baseraid.core.utils.LocationUtil;
import eu.baseraid.core.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class IngameState implements Gamestate {
    private ArrayList<Player> players;
    private BaseRaidPlugin baseRaidInstance;
    private int schedular;
    private int seconds = 60*10;
    private int ironSpawn =    0;
    private int diamondSpawn = 0;
    private int goldSpawn =    0;

    public void start() {
        baseRaidInstance = BaseRaidPlugin.getInstance();
        players = baseRaidInstance.players;

        for (Player player : players) {
            player.teleport(PlayerUtils.teleportPlayerToBase(player));
            player.getInventory().setHelmet(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setBoots(null);
            player.getInventory().setLeggings(null);
            PlayerUtils.givePlayerKitItems(player);

            player.setHealth(20);
            player.setFoodLevel(20);
            IngameScoreboard ingameScoreboard = new IngameScoreboard(player);
            ingameScoreboard.set();
        }
        timer();
        Location gameLocation = LocationUtil.getLocationInConfig("game.locations.spectator.spawn");
        for (Entity entity : gameLocation.getWorld().getEntities()) {
            if (entity instanceof Item) {
                Item item = (Item) entity;
                if (item.getItemStack().hasItemMeta()) {
                    String displayName = item.getItemStack().getItemMeta().getDisplayName();
                    if (displayName.equals("§eTEMP__IRON") || displayName.equals("§eTEMP___GOLD") || displayName.equals("§eTEMP__DIAMOND"))
                        entity.remove();
                }
            } else if (entity instanceof Zombie) {
                entity.remove();
            }
        }

        baseRaidInstance.getTeamRedHandler().areaHandler().addShopEntity();
        baseRaidInstance.getTeamBlueHandler().areaHandler().addShopEntity();


    }


    public void timer(){
        schedular = Bukkit.getScheduler().scheduleSyncRepeatingTask(BaseRaidPlugin.getInstance(), new Runnable() {
            public void run() {

                ironSpawn++;
                goldSpawn++;
                diamondSpawn++;

                for (Player player1 : BaseRaidPlugin.getInstance().players){
                    PlayerUtils.sendActionBar(player1, "§aGame ends in "+seconds);
                    IngameScoreboard ingameScoreboard = new IngameScoreboard(player1);
                    ingameScoreboard.set();
                }

                if (ironSpawn == 7) {
                    for (String key : BaseRaidPlugin.getInstance().config.getConfigurationSection("game.locations.spawner.iron").getKeys(false)) {
                        Location loc = LocationUtil.getLocationInConfig("game.locations.spawner.iron."+key);
                        loc.getWorld().dropItemNaturally(loc, new ItemBuilder(Material.IRON_INGOT, 1).setDisplayName("§eTEMP__IRON").build());
                    }
                    ironSpawn = 0;
                }

                if (goldSpawn == 20) {
                    for (String key : BaseRaidPlugin.getInstance().config.getConfigurationSection("game.locations.spawner.gold").getKeys(false)) {
                        Location loc = LocationUtil.getLocationInConfig("game.locations.spawner.gold."+key);
                        loc.getWorld().dropItemNaturally(loc, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("§eTEMP___GOLD").build());
                    }
                    goldSpawn = 0;
                }

                if (diamondSpawn == 30) {
                    for (String key : BaseRaidPlugin.getInstance().config.getConfigurationSection("game.locations.spawner.diamond").getKeys(false)) {
                        Location loc = LocationUtil.getLocationInConfig("game.locations.spawner.diamond."+key);
                        loc.getWorld().dropItemNaturally(loc, new ItemBuilder(Material.DIAMOND, 1).setDisplayName("§eTEMP__DIAMOND").build());
                    }
                    diamondSpawn = 0;
                }

                seconds--;

            }
        },0, 20);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(schedular);
    }

    public void join(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(LocationUtil.getLocationInConfig("game.locations.spectatorspawn"));
        event.setJoinMessage("§e[§a+§e] §8"+player.getName()+" §7ist das Spiel als Spectator beigetreten.");
        for (Player player1 : BaseRaidPlugin.getInstance().players)
            new IngameScoreboard(player1).set();
    }

    public void leave(PlayerQuitEvent event){
        Player player = event.getPlayer();

        event.setQuitMessage("§e[§c-§e] §8"+player.getName()+" §7hat das Spiel als Spectator verlassen.");
        for (Player player1 : BaseRaidPlugin.getInstance().players)
            new IngameScoreboard(player1).set();
    }
}
