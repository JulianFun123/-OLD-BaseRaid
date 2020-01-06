package eu.baseraid.core.scoreboard;

import eu.baseraid.core.BaseRaidPlugin;
import eu.baseraid.core.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class IngameScoreboard extends BaseRaidScoreboard{

    private Player player;
    private Scoreboard scoreboard;

    @SuppressWarnings("deprecation")
    public IngameScoreboard(Player player){
        this.player = player;

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective scoreboardObj = scoreboard.registerNewObjective("asgf","asgf");
        scoreboardObj.setDisplayName("§aBaseRaid");

        String team = "§7Spectator";
        if (BaseRaidPlugin.getInstance().teamBlue.contains(player))
            team = "§bBlue";
        else if (BaseRaidPlugin.getInstance().teamRed.contains(player))
            team = "§cRed";

        String baseStatus = "§aOK";

        if (PlayerUtils.getPlayerTeam(player).isEntityAtBase())
            baseStatus = "§cWARNING";

        scoreboardObj.getScore("§eTeam: §b"+team).setScore(0);
        if (PlayerUtils.getPlayerTeam(player).items.contains("BASE_WARNINGS"))
            scoreboardObj.getScore("§eBase Status: §b" + baseStatus).setScore(-1);

        scoreboardObj.getScore("§eName: §b"+player.getName()).setScore(-2);
        scoreboardObj.getScore("§eKit: §b"+BaseRaidPlugin.getInstance().playerKits.get(player).getName()).setScore(-3);
        scoreboardObj.getScore(" ").setScore(-4);
        scoreboardObj.getScore("§eCoins: §b"+BaseRaidPlugin.getInstance().playerCoins.get(player)).setScore(-5);
        scoreboardObj.setDisplaySlot(DisplaySlot.SIDEBAR);
        initTeams(scoreboard);
    }

    public void set(){
        player.setScoreboard(scoreboard);
    }

}
