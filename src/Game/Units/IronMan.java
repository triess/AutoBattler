package Game.Units;

import Game.Game;
import Game.Window;
import Game.util.UnitStrategy;
import Game.util.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IronMan extends Unit {
    public IronMan(int id) {
        super(id);
    }

    public IronMan(int id, Point position) {
        super(id, position);
    }

    @Override
    public void act() {
        int d = 1000;
        Point target=null;
        Game g = Game.getInstance();
        for (Unit u:
        g.getTeam(g.getOpponent(this.getTeam()))) {
            if (Utils.isInRange(u.getPos(),this.getPos(),this.range)){
                u.takeDamage(this.atk);
                return;
            }
            if(Utils.hexDistance(u.pos,this.pos)<d){
                d=Utils.hexDistance(u.pos,this.pos);
                target = u.pos;
            }
        }
        this.pos=Utils.NSteps(this.pos,target,this.speed);
    }

    private void flee() {
        int x = pos.x;
        int y = pos.y;
        x = (x+1)% Window.MAP_HEIGHT;
        y = (y+1)%Window.MAP_WIDTH;
        pos.setLocation(x,y);
    }

    @Override
    public void loadStats() {
        name = "Iron Man";
        hp = 12;
        maxHp = 12;
        atk = 3;
        def = 2;
        tier = 3;
        speed = 1;
        range = 2;
        try {
            img = ImageIO.read(new File("res/units/IronMan.png"));
        } catch (IOException ignored) {

        }
        us = getDefaultStrategy();
    }

    @Override
    public UnitStrategy getDefaultStrategy(){
        return UnitStrategy.dodge;
    }
}
