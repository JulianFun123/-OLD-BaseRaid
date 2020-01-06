package eu.baseraid.core.listener;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.kits.Kits;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileListener implements Listener {

    @EventHandler
    public void onHit(ProjectileHitEvent event){
        if (event.getEntity() instanceof Snowball) {
            if (event.getEntity().getShooter() instanceof Player) {
                Player player = (Player) event.getEntity().getShooter();
                if (BaseRaidPlugin.getInstance().playerKits.get(player) == Kits.SKELETON_SUMMONER) {
                    try {
                        Location location = event.getEntity().getLocation();
                        Skeleton skeleton = (Skeleton) location.getWorld().spawnEntity(location, EntityType.SKELETON);
                        skeleton.setCustomName("§cFIGHTING SKELETON");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

}
