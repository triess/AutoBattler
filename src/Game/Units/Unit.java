package Game.Units;

import Game.util.UnitStrategy;

import java.awt.*;

public abstract class Unit {
    private int unitID;
    private int objectID;
    private int team;
    String name;
    int hp;
    int maxHp;
    int atk;
    int def;
    int tier;
    int speed;
    int range;
    Point pos;
    Image img;
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
    public void takeDamage(int dmg) {
        if(dmg-def<=0)return;
        this.hp -= dmg-def;
        if(this.hp<0)this.hp=0;
    }
    public int getMaxHp() {
        return maxHp;
    }

    public float getHpPercent(){
        float f = (float)hp/(float)maxHp;
        return f;
    }
    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
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

    public Image getImg() {
        return img;
    }

    public UnitStrategy getStrategy() {
        return us;
    }

    public String getName() {
        return name;
    }
}
