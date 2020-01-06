package eu.baseraid.core.utils;

import eu.baseraid.core.BaseRaidPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class LocationUtil {
    public static void setLocationInConfig(String path, Location location){

        FileConfiguration config = BaseRaidPlugin.getInstance().config;
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float pitch = location.getPitch();
        float yaw   = location.getYaw();
        String world = location.getWorld().getName();

        config.set(path+".x", x);
        config.set(path+".y", y);
        config.set(path+".z", z);
        config.set(path+".yaw", yaw);
        config.set(path+".pitch", pitch);
        config.set(path+".world", world);
        BaseRaidPlugin.getInstance().saveConfig();
    }

    public static Location getLocationInConfig(String path) {
        FileConfiguration config = BaseRaidPlugin.getInstance().config;
        double x = config.getDouble(path+".x");
        double y = config.getDouble(path+".y");
        double z = config.getDouble(path+".z");
        float  pitch = (float) config.getDouble(path+".pitch");
        float  yaw = (float) config.getDouble(path+".yaw");
        World  world = Bukkit.getWorld(config.getString(path+".world"));

        Location location = new Location(world, x,y,z,yaw, pitch);
        return location;
    }

}
