package Game;

public class Player {
    private static int nextPlayerID;
    public static int getNextPlayerID(){
        nextPlayerID++;
        return nextPlayerID-1;
    }
    private int id;


    public Player(){
        id = Player.getNextPlayerID();
    }

}
