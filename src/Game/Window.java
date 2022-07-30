package Game;

import Game.Units.Unit;
import Game.util.Hexagon;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Window extends JPanel{

    private Point lastPoint;
    private final int HEX_SIZE = 70;
    private final int UNIT_SIZE = 50;
    private final int HEALTH_LENGTH = 60;
    private final int HEALTH_HEIGHT = 4;
    public static final int MAP_HEIGHT = 7;
    public static final int MAP_WIDTH = 9;
    private final Hexagon[][] map = new Hexagon[MAP_HEIGHT][MAP_WIDTH];
    private final Point TOP_LEFT = new Point(80,80);

    public int getHEX_SIZE() {
        return HEX_SIZE;
    }

    public int getUNIT_SIZE() {
        return UNIT_SIZE;
    }

    public Rectangle getBattlefieldBounds() {
        return battlefieldBounds;
    }

    private Rectangle battlefieldBounds = new Rectangle();


    public Window(){
        Hexagon firstHex = new Hexagon(TOP_LEFT.x,TOP_LEFT.y, HEX_SIZE);
        Hexagon currentHex = firstHex;
        map[0][0] = firstHex;
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 1; j < MAP_WIDTH; j++) {
                Hexagon h = new Hexagon(0,0, HEX_SIZE);
                currentHex.attach(h, Hexagon.Direction.East);
                map[i][j] = h;
                currentHex = h;
            }
            if (i!= MAP_HEIGHT-1) {
                Hexagon h = new Hexagon(0,0, HEX_SIZE);
                if (i%2==0) {
                    firstHex.attach(h, Hexagon.Direction.SouthEast);
                }else {
                    firstHex.attach(h, Hexagon.Direction.SouthWest);
                }
                firstHex=h;
                currentHex=h;
                map[i+1][0]=h;
            }
        }
        battlefieldBounds.setLocation(map[0][0].getBounds().getLocation());
        int height = map[MAP_HEIGHT-1][0].getBounds().y+map[MAP_HEIGHT-1][0].getBounds().height+1;
        int width = map[1][MAP_WIDTH-1].getBounds().x+map[1][MAP_WIDTH-1].getBounds().width+3;
        battlefieldBounds.setSize(width,height);

        /*
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e){
                Graphics g = getGraphics();
                g.drawLine(lastPoint.x,lastPoint.y,e.getX(), e.getY());
                g.dispose();
            }
        });
        */
    }

    public Point toHexCoords(Point p) {
        if(!battlefieldBounds.contains(p))return null;
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                if(map[i][j].contains(p))return new Point(i,j);
            }
        }
        return null;
    }

    public Hexagon getHexagonAtAbsolute(Point p){
        if(!battlefieldBounds.contains(p))return null;
        for (Hexagon[] row:
        map){
            for (Hexagon h :
                    row) {
                if (h.contains(p))return h;
            }

        }
        return null;
    }

    @Override
    public void update(Graphics g){
        super.update(g);
        paintUnits(g);
    }

    private void paintUnits(Graphics g) {
        for (Unit u :
                Game.getInstance().getUnits()) {
            Point2D p = map[u.getPos().x][u.getPos().y].getCenter();
            Point place = new Point((int) p.getX() - UNIT_SIZE / 2,(int) p.getY() - UNIT_SIZE / 2);
            if(u.getTeam()==0) {
                g.drawImage(u.getImg(),place.x, place.y, null);
            }else{
                g.drawImage(u.getImg(),place.x, place.y, null);
            }
            place.translate((UNIT_SIZE-HEALTH_LENGTH)/2,UNIT_SIZE+1);
            int l = Math.round(u.getHpPercent()*HEALTH_LENGTH);
            g.setColor(Color.GREEN);
            g.fillRect(place.x,place.y,l, HEALTH_HEIGHT);
            g.setColor(Color.RED);
            g.fillRect(place.x+l, place.y, HEALTH_LENGTH-l,HEALTH_HEIGHT);
        }
        g.setColor(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintHexes(g);
        paintUnits(g);
    }

    private void paintHexes(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i=0;i<MAP_HEIGHT;i++) {
            for (int j=0;j<MAP_WIDTH;j++) {
                if (map[i][j] != null) {
                    g.drawPolygon(map[i][j]);
                    char[] c = new char[2];
                    c[0] = (char)(i+'0');
                    c[1] = (char)(j+'0');
                    Point2D centre = map[i][j].getCenter();
                    g.drawChars(c,0,2,(int)centre.getX(),(int)centre.getY());
                }

            }
        }
    }
}
