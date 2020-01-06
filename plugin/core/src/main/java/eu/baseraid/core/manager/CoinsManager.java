package eu.baseraid.core.manager;

import eu.baseraid.core.BaseRaidPlugin;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CoinsManager {

    private Player player;
    private HashMap<Player, Integer> players;
    private int coins = 0;

    public CoinsManager(Player player){
        this.player = player;
        players = BaseRaidPlugin.getInstance().playerCoins;
        if (players.containsKey(player)) {
            coins = players.get(player);
        } else {
            players.put(player, 0);
        }
    }

    public CoinsManager setCoins(int coins){
        this.coins = coins;
        return this;
    }

    public CoinsManager addCoins(int coins){
        this.coins += coins;
        return this;
    }

    public CoinsManager removeCoins(int coins){
        this.coins -= coins;
        return this;
    }

    public boolean enoughCoins(int coins){
        return this.coins >= coins;
    }

    public int getCoins(){
        return coins;
    }

    public boolean buy(int coins){
        if (enoughCoins(coins)) {
            removeCoins(coins);
            return true;
        }
        return false;
    }

    public CoinsManager save(){
        players.put(player, coins);
        return this;
    }

}
