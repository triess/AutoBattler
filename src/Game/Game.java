package Game;

import Game.Units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public List<Unit> getUnits() {
        return units;
    }

    List<Unit> units = new ArrayList<>();
    public Game(){

    }

    public void advance(){
        updateUnits();
    }

    private void updateUnits() {
        for (Unit u:
                units) {
            u.act();
        }
    }

    public void addUnit(Unit u){
        assert u.getPos() != null;
        //assert !isOccupied(u.getPos());
        units.add(u);
    }
}
