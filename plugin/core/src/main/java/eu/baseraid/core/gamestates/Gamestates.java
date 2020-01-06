package eu.baseraid.core.gamestates;

public enum Gamestates {

    LOBBY_STATE(0),
    INGAME_STATE(1),
    GANE_END_STATE(2);

    private int id;

    Gamestates(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

}
