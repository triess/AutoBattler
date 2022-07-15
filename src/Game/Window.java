package Game;

import Game.Units.Unit;
import Game.util.Hexagon;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Window extends JPanel{

    private Point lastPoint;
    private final int HEXSIZE = 50;
    private final int UNITSIZE = 30;
    private final Hexagon[][] map = new Hexagon[8][9];
    private final Point TOP_LEFT = new Point(60,60);

    private List<Unit> units;


    public Window(){
        units = new ArrayList<>();
        Hexagon firstHex = new Hexagon(TOP_LEFT.x,TOP_LEFT.y,HEXSIZE);
        Hexagon currentHex = firstHex;
        map[0][0] = firstHex;
        for (int i = 0; i < map.length-1; i++) {
            for (int j = 0; j < map[0].length-1; j++) {
                Hexagon h = new Hexagon(0,0,HEXSIZE);
                currentHex.attach(h, Hexagon.Direction.East);
                map[i+1][j+1] = h;
                currentHex = h;
            }
            if (i!= map.length-2) {
                Hexagon h = new Hexagon(0,0,HEXSIZE);
                if (i%2==0) {
                    firstHex.attach(h, Hexagon.Direction.SouthEast);
                }else {
                    firstHex.attach(h, Hexagon.Direction.SouthWest);
                }
                firstHex=h;
                currentHex=h;
                map[0][i+1]=h;
            }
        }
        /*
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastPoint = new Point(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e){
                Graphics g = getGraphics();
                g.drawLine(lastPoint.x,lastPoint.y,e.getX(), e.getY());
                g.dispose();
            }
        });
        */



    }

    @Override
    public void update(Graphics g){
        super.update(g);
        updateUnits();
        paintUnits(g);
    }

    private void paintUnits(Graphics g) {
        for (Unit u :
                units) {
            g.setColor(u.getImg());
            System.out.println(u.getPos());
            System.out.println(map[u.getPos().x][u.getPos().y]);
            Point2D p = map[u.getPos().x][u.getPos().y].getCenter();
            g.fillOval((int)p.getX()-UNITSIZE/2, (int)p.getY()-UNITSIZE/2,UNITSIZE,UNITSIZE);
        }
        g.setColor(Color.BLACK);
    }

    private void updateUnits() {
        for (Unit u:
             units) {
            u.act();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(900,600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintHexes(g);
        paintUnits(g);
    }

    private void paintHexes(Graphics g) {
        g.setColor(Color.BLACK);
        for (Hexagon[] hexRow:map
             ) {
            for (Hexagon h:hexRow
                 ) {
                if (h != null)
                    g.drawPolygon(h);
            }
        }
    }

    public void addUnit(Unit u){
        assert u.getPos() != null;
        assert !isOccupied(u.getPos());
        units.add(u);
    }

    public boolean isOccupied(Point p){
        for (Unit u:
             units) {
            if (u.getPos().equals(p)) return true;
        }
        return false;
    }
}
