package Game;

import Game.Units.IronMan;
import Game.Units.Unit;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    HashMap<Unit,Integer> units = new HashMap<>();



    Set<Integer> players = new HashSet<>();

    private static final Game instance = new Game();
    private Game(){}
    public static Game getInstance(){
        return instance;
    }
    public void addPlayer(int p){
        players.add(p);
    }
    public Set<Unit> getUnits() {
        return units.keySet();
    }
    public Set<Unit> getTeam(int player){
        return units.entrySet().stream().filter(entry -> entry.getValue()==player).map(Map.Entry::getKey).collect(Collectors.toSet());
    }


    public void advance(){
        updateUnits();
    }

    public int getOpponent(int player){
        if(player==1)return 0;
        return 1;
    }

    private void updateUnits() {
        for (Unit u:
                units.keySet()) {
            u.act();
        }
    }

    public void addUnit(Unit u,int player){
        u.setTeam(player);
        assert u.getPos() != null;
        //assert !isOccupied(u.getPos());
        units.put(u,player);
    }

    public Set<Integer> getPlayers() {
        return players;
    }

}
