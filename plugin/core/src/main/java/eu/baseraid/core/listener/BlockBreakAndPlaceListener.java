package eu.baseraid.core.listener;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.gamestates.Gamestates;
import eu.baseraid.core.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockBreakAndPlaceListener implements Listener {

    @EventHandler
    public void onPlace(final BlockPlaceEvent event){
        Player player = event.getPlayer();
        int gameState = BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate;
        if (gameState==0) {
            if (!BaseRaidPlugin.getInstance().builder.contains(player))
                event.setCancelled(true);
        } else if (gameState == 1) {
            if (event.getBlock().getType() == Material.STAINED_CLAY) {
                if (BaseRaidPlugin.getInstance().teamBlue.contains(player))
                    event.getBlock().setData((byte) 11);
                else
                    event.getBlock().setData((byte) 14);

                Material replace = Material.AIR;

                if (event.getBlockReplacedState() != null)
                    replace = event.getBlockReplacedState().getBlock().getType();
                player.sendMessage(event.getBlockAgainst().getType().toString());
                player.sendMessage(event.getBlockReplacedState().getBlock().getType().toString());
                final Material finalReplace = replace;

                Bukkit.getScheduler().scheduleSyncDelayedTask(BaseRaidPlugin.getInstance(), new Runnable() {
                    public void run() {
                        event.getBlock().setType(Material.REDSTONE_BLOCK);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(BaseRaidPlugin.getInstance(), new Runnable() {
                            public void run() {
                                event.getBlock().setType(Material.AIR);
                            }
                        }, 2*20);
                    }
                }, 3*20);
            }
            else if (!BaseRaidPlugin.getInstance().builder.contains(player))
                event.setCancelled(true);
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        int gameState = BaseRaidPlugin.getInstance().getGamestatesHandler().currentGamestate;
        if (gameState==0) {
            if (!BaseRaidPlugin.getInstance().builder.contains(player)) {
                event.setCancelled(true);
            }
        } else if (gameState == 1) {
            Block block = event.getBlock();
            Location blockLocation = block.getLocation();

            Location blueObsidian = LocationUtil.getLocationInConfig("game.locations.teams.blue.obsidian");
            Location redObsidian  = LocationUtil.getLocationInConfig("game.locations.teams.red.obsidian");


            if (
                    block.getLocation().getX() == Math.floor(redObsidian.getX()) &&
                    block.getLocation().getY() == Math.floor(redObsidian.getY()) &&
                    block.getLocation().getZ() == Math.floor(redObsidian.getZ()) &&
                    BaseRaidPlugin.getInstance().teamBlue.contains(player)
            ) {
                BaseRaidPlugin.getInstance().wonTeam = "BLUE";
                BaseRaidPlugin.getInstance().getGamestatesHandler().startState(Gamestates.GANE_END_STATE);
                event.setCancelled(true);
            } else  if (
                    block.getLocation().getX() == Math.floor(blueObsidian.getX()) &&
                    block.getLocation().getY() == Math.floor(blueObsidian.getY()) &&
                    block.getLocation().getZ() == Math.floor(blueObsidian.getZ()) &&
                    BaseRaidPlugin.getInstance().teamRed.contains(player)
            ) {
                BaseRaidPlugin.getInstance().wonTeam = "BLUE";
                BaseRaidPlugin.getInstance().getGamestatesHandler().startState(Gamestates.GANE_END_STATE);
                event.setCancelled(true);
            }

            else if (!BaseRaidPlugin.getInstance().builder.contains(player))
                event.setCancelled(true);
        } else {
            event.setCancelled(true);
        }
    }

}
