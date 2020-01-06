package eu.baseraid.core.teams;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.utils.LocationUtil;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseHandler {

    private String teamName;
    private Location obsidianLocation;
    private Location spawnLocation;
    private Location shop;
    private Zombie shopEntity;
    public ArrayList<Player> players;
    public ArrayList<String> items;
    public Entity area;
    private boolean entityAtBase = false;
    private int enemiesAtBase=0;

    public BaseHandler(String teamName, ArrayList<Player> teamPlayers){
        this.teamName = teamName;
        this.players = teamPlayers;
        items = new ArrayList<String>();
        obsidianLocation = LocationUtil.getLocationInConfig("game.locations.teams."+teamName+".obsidian");
        spawnLocation = LocationUtil.getLocationInConfig("game.locations.teams."+teamName+".spawn");
    }

    public BaseHandler areaHandler(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(BaseRaidPlugin.getInstance(), new Runnable() {
            public void run() {
                enemiesAtBase = 0;
                obsidianLocation
                        .getWorld()
                        .getPlayers()
                        .stream()
                        .filter(p -> p.getLocation()
                                .distance(obsidianLocation) < 12)
                        .forEach(player -> {
                    if (!players.contains(player)){
                        enemiesAtBase++;
                        entityAtBase = true;
                        if (items.contains("ENEMY_POISON"))
                            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON,150,0));
                        else if (items.contains("ENEMY_WEAKNESS"))
                            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,150,1));
                    } else {
                        if (items.contains("OWN_HEALING"))
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,150,0));
                    }
                });

                if (enemiesAtBase == 0)
                    entityAtBase = false;

            }
        },0,20);

        obsidianLocation.getWorld().setFullTime(16000);

        return this;
    }

    public BaseHandler addShopEntity(){
        shop = LocationUtil.getLocationInConfig("game.locations.teams."+teamName+".shop");
        shopEntity = (Zombie) shop.getWorld().spawnEntity(shop, EntityType.ZOMBIE);
        shopEntity.setCustomName("§aGame Shop");
        shopEntity.setCustomNameVisible(true);
        shopEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,Integer.MAX_VALUE,1000));
        shopEntity.setVillager(true);
        shopEntity.setRemoveWhenFarAway(false);
        return this;
    }

    public BaseHandler killShopEntity(){
        shopEntity.remove();
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public boolean isEntityAtBase() {
        return entityAtBase;
    }
}
