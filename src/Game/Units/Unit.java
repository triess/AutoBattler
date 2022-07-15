package Game.Units;

import Game.util.UnitStrategy;

import java.awt.*;

public abstract class Unit {
    private int unitID;
    private int objectID;
    String name;
    int hp;
    int atk;
    int def;
    int tier;
    Point pos;
    Color img;
    UnitStrategy us;
    private static int nextObjectID;

    public static int getNextObjectID(){
        nextObjectID++;
        return nextObjectID-1;
    }

    public Unit(int id){
        objectID=getNextObjectID();
        unitID=id;
        pos = new Point(0,0);
        loadStats();
    }

    public Unit(int id, Point position){
        objectID=getNextObjectID();
        unitID=id;
        pos = position;
        loadStats();
    }

    public abstract void act();
    public void setStrategy(UnitStrategy us){
        this.us=us;
    }
    public abstract void loadStats();
    public UnitStrategy getDefaultStrategy(){
        return UnitStrategy.balanced;
    }

    public Point getPos() {
        return pos;
    }

    public int getUnitID() {
        return unitID;
    }

    public int getObjectID() {
        return objectID;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getTier() {
        return tier;
    }

    public Color getImg() {
        return img;
    }

    public UnitStrategy getStrategy() {
        return us;
    }

    public String getName() {
        return name;
    }
}
