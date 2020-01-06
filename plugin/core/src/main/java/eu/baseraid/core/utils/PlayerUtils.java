package eu.baseraid.core.utils;

import com.google.gson.Gson;
import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.kits.Kit;
import eu.baseraid.core.kits.Kits;
import eu.baseraid.core.teams.BaseHandler;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerUtils {

    public static Location teleportPlayerToBase(Player player){
        if (BaseRaidPlugin.getInstance().teamRed.contains(player))
            return LocationUtil.getLocationInConfig("game.locations.teams.red.spawn");
        else if (BaseRaidPlugin.getInstance().teamBlue.contains(player))
            return LocationUtil.getLocationInConfig("game.locations.teams.blue.spawn");
        else
            return LocationUtil.getLocationInConfig("game.locations.spectator.spawn");
    }

    public static void givePlayerKitItems(Player player){
        player.getInventory().clear();
        Kits kit = BaseRaidPlugin.getInstance().playerKits.get(player);
        Kit playerKit = kit.getKit();
        playerKit.setPlayer(player);
        player.getInventory().setItem(8, new ItemBuilder(Material.NETHER_STAR,1).setDisplayName("§aShop").build() );
        player.getInventory().setBoots(playerKit.getBoots());
        player.getInventory().setChestplate(playerKit.getChestplate());
        player.getInventory().setLeggings(playerKit.getLeggings());
        player.getInventory().setHelmet(playerKit.getHelmet());
        for (ItemStack item : playerKit.getItems()) {
            player.getInventory().addItem(item);
        }
    }

    public static String setTeam(){
        String out = "SPECTATOR";

        return out;
    }

    public static void sendActionBar(Player player, String text){
        ActionBarJsonTemplate actionBarJsonTemplate = new ActionBarJsonTemplate();
        actionBarJsonTemplate.text = text;
        String json = new Gson().toJson(actionBarJsonTemplate);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a(json), (byte) 2));
    }

    public static void sendTitle(Player player, String text, String subtitle){
        ((CraftPlayer) player).sendTitle(text, subtitle);
        /* CraftWorld world = (CraftWorld) Bukkit.getWorld("asfda");
        Location location = world.getSpawnLocation();
        EntityArmorStand entity = new EntityArmorStand(world.getHandle(), location.getX(), location.getY(), location.getZ());
        PacketPlayOutSpawnEntityLiving entityPacket = new PacketPlayOutSpawnEntityLiving(entity);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(entityPacket);*/
    }

    public static void removeTitle(Player player){
        ((CraftPlayer) player).resetTitle();
    }

    public static class ActionBarJsonTemplate{
        public ActionBarJsonTemplate(){}
        public String text;
    }

    public static BaseHandler getPlayerTeam(Player player){
        if (BaseRaidPlugin.getInstance().getTeamBlueHandler().getPlayers().contains(player))
            return BaseRaidPlugin.getInstance().getTeamBlueHandler();
        if (BaseRaidPlugin.getInstance().getTeamRedHandler().getPlayers().contains(player))
            return BaseRaidPlugin.getInstance().getTeamRedHandler();
        return null;
    }

}
