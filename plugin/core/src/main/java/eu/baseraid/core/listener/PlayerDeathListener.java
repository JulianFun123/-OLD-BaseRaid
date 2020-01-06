package eu.baseraid.core.listener;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.manager.CoinsManager;
import eu.baseraid.core.scoreboard.IngameScoreboard;
import eu.baseraid.core.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        final Player player = event.getPlayer();
        BaseRaidPlugin baseRaidInstance = BaseRaidPlugin.getInstance();
        if (baseRaidInstance.getGamestatesHandler().currentGamestate == 1) {
            PlayerUtils.givePlayerKitItems(player);

            Bukkit.getScheduler().scheduleSyncDelayedTask(BaseRaidPlugin.getInstance(), new Runnable() {
                public void run() {
                    player.teleport(PlayerUtils.teleportPlayerToBase(player));
                }
            }, 1);
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = event.getEntity();
            BaseRaidPlugin baseRaidInstance = BaseRaidPlugin.getInstance();
            if (baseRaidInstance.getGamestatesHandler().currentGamestate == 1) {
                event.getDrops().clear();
                event.setDeathMessage("");
                if (player.getKiller() != null) {
                    Player killer = (Player) player.getKiller();

                    Bukkit.broadcastMessage("§cDer Spieler §4"+player.getName()+" §cwurde von §3"+killer.getName()+"§c getötet.");

                    baseRaidInstance.cooldownPlayers.add(player);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(baseRaidInstance, new Runnable() {
                        @Override
                        public void run() {
                            baseRaidInstance.cooldownPlayers.remove(player);
                        }
                    }, 60);

                    new CoinsManager(killer).addCoins(350).save();
                    CoinsManager playerCoinsManager = new CoinsManager(player);
                    playerCoinsManager.buy(playerCoinsManager.getCoins()/5);
                    playerCoinsManager.save();
                    new IngameScoreboard(player).set();
                    new IngameScoreboard(killer).set();

                } else {
                    Bukkit.broadcastMessage("§cDer Spieler §4"+player.getName()+" §cist gestorben.");
                }
            }
        }
    }


}
