package eu.baseraid.core;

import org.bukkit.scoreboard.Team;

public enum  Teams {

    RED(0, "§c"),
    BLUE(1, "§b");

    private int id;
    private String color;

    Teams(int id, String color){
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }
}
