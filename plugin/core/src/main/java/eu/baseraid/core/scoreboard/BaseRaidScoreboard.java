package eu.baseraid.core.scoreboard;

import eu.baseraid.core.BaseRaidPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class BaseRaidScoreboard {

    public void initTeams(Scoreboard scoreboard){
        scoreboard.registerNewTeam("0001Blue");
        scoreboard.registerNewTeam("0002Red");
        scoreboard.registerNewTeam("0003Spectator");

        scoreboard.getTeam("0001Blue").setPrefix("§bBlue §9| §7");
        scoreboard.getTeam("0002Red").setPrefix("§cRed §9| §7");
        scoreboard.getTeam("0003Spectator").setPrefix("§7");


        for (Player playerInList : Bukkit.getOnlinePlayers()) {
            String team = "0003Spectator";
            if (BaseRaidPlugin.getInstance().teamRed.contains(playerInList))
                team = "0002Red";
            else if (BaseRaidPlugin.getInstance().teamBlue.contains(playerInList))
                team = "0001Blue";

            scoreboard.getTeam(team).addPlayer(playerInList);
            playerInList.setDisplayName(scoreboard.getTeam(team).getPrefix()+playerInList.getName()+"§r");

        }
    }

}
