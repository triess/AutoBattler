package Game.Units;

import Game.util.UnitStrategy;

import java.awt.*;

public class IronMan extends Unit {
    public IronMan(int id) {
        super(id);
    }

    public IronMan(int id, Point position) {
        super(id, position);
    }

    @Override
    public void act() {
        UnitStrategy s = getStrategy();
        switch (s){
            case defense:
                return;
            case offense:
                return;
            case dodge:
                flee();
            case random:
                return;
            case balanced:
                return;
        }
    }

    private void flee() {
        int x = pos.x;
        int y = pos.y;
        x = (x+1)%9;
        y= (y+1)%7;
        pos.setLocation(x,y);
    }

    @Override
    public void loadStats() {
        name = "Iron Man";
        hp = 10;
        atk = 3;
        def = 4;
        tier = 3;
        img = Color.RED;
        us = getDefaultStrategy();
    }

    @Override
    public UnitStrategy getDefaultStrategy(){
        return UnitStrategy.dodge;
    }
}
