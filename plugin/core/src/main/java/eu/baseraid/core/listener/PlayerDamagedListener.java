package eu.baseraid.core.listener;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.gamestates.Gamestates;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamagedListener implements Listener {

    @EventHandler
    public void playerDamaged(EntityDamageByEntityEvent event){

        if (BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == Gamestates.LOBBY_STATE.getId()) {
            event.setCancelled(true);
        } else if (BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == 2){
            event.setCancelled(true);
        } else if(BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate == Gamestates.INGAME_STATE.getId()) {
            if ((event.getEntity() instanceof Zombie && event.getEntity().getName().equals("§aGame Shop") || event.getDamager() instanceof Zombie && event.getDamager().getName().equals("§aGame Shop")))
                event.setCancelled(true);
            if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
                String myTeam = "";
                Player player = (Player) event.getEntity();
                Player damager = (Player) event.getDamager();
                if (BaseRaidPlugin.getInstance().teamBlue.contains(player))
                    myTeam = "BLUE";
                else if (BaseRaidPlugin.getInstance().teamRed.contains(player))
                    myTeam = "RED";

                if (myTeam == "RED" && BaseRaidPlugin.getInstance().teamRed.contains(damager))
                    event.setCancelled(true);
                if (myTeam == "BLUE" && BaseRaidPlugin.getInstance().teamBlue.contains(damager))
                    event.setCancelled(true);

                if (BaseRaidPlugin.getInstance().cooldownPlayers.contains(player)) {
                    event.setCancelled(true);
                    damager.sendMessage("§cPlayer has a 3 secounds cooldown.");
                }

                if (BaseRaidPlugin.getInstance().cooldownPlayers.contains(damager)) {
                    event.setCancelled(true);
                    damager.sendMessage("§cPlayer you've got a 3 secounds cooldown.");
                }


            }
        }

    }

}
