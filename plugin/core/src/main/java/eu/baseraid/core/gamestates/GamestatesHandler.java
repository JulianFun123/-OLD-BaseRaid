package eu.baseraid.core.gamestates;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GamestatesHandler {


    public static GamestatesHandler instance;

    public Gamestate[] gamestates;
    public int currentGamestate = -1;

    public GamestatesHandler(){
        instance = this;

        gamestates = new Gamestate[] {
                new LobbyState(),
                new IngameState(),
                new GameEndState()
        };

    }


    private Gamestate getCurrentGamestate(){
        return gamestates[currentGamestate];
    }

    private void setGamestate(Gamestates gamestate) {
        currentGamestate = gamestate.getId();
    }

    public void startState(Gamestates gamestate){
        setGamestate(gamestate);
        if (currentGamestate != -1)
            getCurrentGamestate().stop();
        getCurrentGamestate().start();
    }

    public void callJoinListener(PlayerJoinEvent event){
        getCurrentGamestate().join(event);
    }

    public void callLeaveListener(PlayerQuitEvent event){
        getCurrentGamestate().leave(event);
    }

    public static GamestatesHandler getInstance() {
        return instance;
    }

}
