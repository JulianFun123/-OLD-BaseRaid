package eu.baseraid.core.commands;

import eu.baseraid.core.utils.LocationUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLocationCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("baseraid.admin")) {
            LocationUtil.setLocationInConfig("game.locations."+args[0], player.getLocation());
        }

        return false;
    }
}
