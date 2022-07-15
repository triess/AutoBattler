import Game.Units.IronMan;
import Game.Window;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        IronMan im = new IronMan(1,new Point(1,1));
        Window w = new Window();
        w.addUnit(im);
        createFrame(w);
    }

    public static void createFrame(Window w){
        JFrame frame = new JFrame("Great Programm");
        frame.getContentPane().add(w, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Graphics g =frame.getGraphics();
        w.update(g);
    }
}
