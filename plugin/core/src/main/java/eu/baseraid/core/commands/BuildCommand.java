package eu.baseraid.core.commands;

import eu.baseraid.core.BaseRaidPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        if (player.hasPermission("baseraid.build")) {
            if (BaseRaidPlugin.getInstance().builder.contains(player)) {
                BaseRaidPlugin.getInstance().builder.remove(player);
                player.sendMessage("§c|-> Der BuildingModus wurde deaktiviert");
            } else {
                BaseRaidPlugin.getInstance().builder.add(player);
                player.sendMessage("§a|-> Der BuildingModus wurde aktiviert");
            }
        }

        return false;
    }
}
