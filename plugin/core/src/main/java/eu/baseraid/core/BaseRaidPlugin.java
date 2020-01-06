package eu.baseraid.core;

import eu.baseraid.core.commands.BuildCommand;
import eu.baseraid.core.commands.SetLocationCommand;
import eu.baseraid.core.gamestates.Gamestates;
import eu.baseraid.core.gamestates.GamestatesHandler;
import eu.baseraid.core.kits.BowKit;
import eu.baseraid.core.kits.Kit;
import eu.baseraid.core.kits.Kits;
import eu.baseraid.core.listener.*;
import eu.baseraid.core.listener.interacts.IngameInteractListener;
import eu.baseraid.core.listener.interacts.LobbyInteractListener;
import eu.baseraid.core.teams.BaseHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseRaidPlugin extends JavaPlugin {

    public static BaseRaidPlugin instance;
    public GamestatesHandler gamestatesHandler;
    public HashMap<String, Kits> kits;
    public FileConfiguration config;

    public HashMap<Player, Kits> playerKits;
    public HashMap<Player, Integer> playerCoins;

    public String wonTeam = "";

    public ArrayList<Player> builder;
    public ArrayList<Player> players;
    public ArrayList<Player> teamBlue;
    public ArrayList<Player> teamRed;
    public ArrayList<Player> cooldownPlayers;
    public BaseHandler teamRedHandler;
    public BaseHandler teamBlueHandler;

    public final int MAX_PLAYERS = 8,
                     MIN_PLAYERS = 1;

    private PluginManager pluginManager;

    public void onEnable(){
        instance = this;
        config = getConfig();
        teamBlue = new ArrayList<Player>();
        teamRed  = new ArrayList<Player>();
        cooldownPlayers = new ArrayList<Player>();
        pluginManager = Bukkit.getPluginManager();

        playerCoins = new HashMap<Player, Integer>();

        playerKits = new HashMap<Player, Kits>();
        kits = new HashMap<String, Kits>();

        builder  = new ArrayList<Player>();
        players  = new ArrayList<Player>();
        Bukkit.getConsoleSender().sendMessage("§a[BaseRaid Core] Das Plugin wurde gestartet");

        gamestatesHandler = new GamestatesHandler();
        gamestatesHandler.startState(Gamestates.LOBBY_STATE);

        teamBlueHandler = new BaseHandler("blue", teamBlue);
        teamRedHandler  = new BaseHandler("red", teamRed);


        registerCommands();
        registerListener();
        registerKits();
    }

    public void onDisable(){

    }

    public void registerListener(){
        pluginManager.registerEvents(new JoinAndLeaveListener(), this);
        pluginManager.registerEvents(new FoodChangedListener(), this);
        pluginManager.registerEvents(new PlayerDamagedListener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new PickupListener(), this);
        pluginManager.registerEvents(new IngameInteractListener(), this);
        pluginManager.registerEvents(new LobbyInteractListener(), this);
        pluginManager.registerEvents(new PlayerDropItemListener(), this);
        pluginManager.registerEvents(new BlockBreakAndPlaceListener(),this);
        pluginManager.registerEvents(new ProjectileListener(), this);
    }

    public void registerCommands(){
        getCommand("setloc").setExecutor(new SetLocationCommand());
        getCommand("build").setExecutor(new BuildCommand());
    }

    public void registerKits(){
        kits.put("Bow Kit", Kits.BOW);
        kits.put("Assasine", Kits.ASSASINE);
        kits.put("Healer", Kits.HEALER);
        kits.put("Tank", Kits.TANK);
        kits.put("Builder", Kits.BUILDER);
        kits.put("Witch", Kits.WITCH);
        kits.put("Skeleton Summoner", Kits.SKELETON_SUMMONER);
    }

    public GamestatesHandler getGamestatesHandler(){
        return gamestatesHandler;
    }

    public BaseHandler getTeamBlueHandler() {
        return teamBlueHandler;
    }

    public BaseHandler getTeamRedHandler() {
        return teamRedHandler;
    }

    public static BaseRaidPlugin getInstance(){
        return instance;
    }



}
