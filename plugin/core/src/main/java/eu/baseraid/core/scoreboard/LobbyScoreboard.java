package eu.baseraid.core.scoreboard;

import eu.baseraid.core.BaseRaidPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class LobbyScoreboard extends BaseRaidScoreboard {

    private Player player;
    private Scoreboard scoreboard;

    @SuppressWarnings("deprecation")
    public LobbyScoreboard(Player player){
        this.player = player;

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective scoreboardObj = scoreboard.registerNewObjective("asgf","asgf");
        scoreboardObj.setDisplayName("§b- §aBaseRaid §b-");
        String team = "§7Spectator";
        if (BaseRaidPlugin.getInstance().teamBlue.contains(player))
            team = "§bBlue";
        else if (BaseRaidPlugin.getInstance().teamRed.contains(player))
            team = "§cRed";

        scoreboardObj.getScore("§eHello §c"+player.getName()).setScore(3);
        scoreboardObj.getScore("§eTeam: §b"+team).setScore(2);
        scoreboardObj.getScore("§eKit: §b"+BaseRaidPlugin.getInstance().playerKits.get(player).getName()).setScore(1);
        scoreboardObj.setDisplaySlot(DisplaySlot.SIDEBAR);
        initTeams(scoreboard);
    }

    public void set(){
        player.setScoreboard(scoreboard);
    }

}
