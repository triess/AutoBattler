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
        JButton button = new JButton("move");
        Game g = new Game();
        Window w = new Window(g);
        IronMan im = new IronMan(0,new Point(1,2));
        g.addUnit(im);
        w.setBounds(w.getBattlefieldBounds());
        button.setBounds(w.getBounds().x,w.getBounds().y+w.getBounds().height+10,100,40);
        frame.setLayout(null);
        frame.add(w);
        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(1200,900);
        frame.repaint();
        frame.setVisible(true);
        w.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Point p = new Point(e.getX(),e.getY());
                Point clicked = w.toHexCoords(p);
                IronMan i = new IronMan(0,clicked);
                g.addUnit(i);
                frame.repaint();
            }
        });


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.advance();
                frame.repaint();
            }
        });

    }
}
