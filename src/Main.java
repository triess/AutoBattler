import Game.Game;
import Game.Units.IronMan;
import Game.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args){

        createFrame();
    }

    public static void createFrame(){
        JFrame frame = new JFrame("Auto Battler");
        Window w = setupWindow(frame);
        setupFrame(frame,w);
        IronMan im = new IronMan(0,new Point(1,2));
        Game.getInstance().addUnit(im);
    }

    private static void setupFrame(JFrame frame,Window w) {
        JButton button = new JButton("move");
        button.setBounds(w.getBounds().x,w.getBounds().y+w.getBounds().height+10,100,40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().advance();
                frame.repaint();
            }
        });
        frame.setLayout(null);
        frame.add(w);
        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(1200,900);
        frame.repaint();
        frame.setVisible(true);
    }

    private static Window setupWindow(JFrame frame) {
        Window w = new Window(Game.getInstance());
        w.setBounds(w.getBattlefieldBounds());
        w.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Point p = new Point(e.getX(),e.getY());
                Point clicked = w.toHexCoords(p);
                IronMan i = new IronMan(0,clicked);
                Game.getInstance().addUnit(i);
                frame.repaint();
            }
        });
        return w;
    }
}
