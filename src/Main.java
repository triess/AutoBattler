import Game.Game;
import Game.Units.IronMan;
import Game.Window;
import Game.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args){
        createFrame();
    }

    public static void createFrame(){
        JFrame frame = new JFrame("Auto Battler");
        Window w = setupWindow(frame);
        setupFrame(frame,w);
        IronMan im = new IronMan(0,new Point(1,2));
        Game.getInstance().addUnit(im,0);
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
        JButton button1 = new JButton("Connect");
        JButton button2 = new JButton("Message");
        JTextField textField = new JTextField("ip");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    InetAddress ip = InetAddress.getByName(textField.getText());
                    System.out.println("connection exists");
                    System.out.println(ip.isReachable(5000));
                } catch (UnknownHostException ex) {
                    System.out.println("error converting ip");
                    System.out.println(ex);
                } catch (IOException ex) {
                    System.out.println("error connecting");
                    System.out.println(ex);
                }
            }
        });

        button1.setBounds(button.getX()+120,button.getY(),button.getWidth(),button.getHeight());
        button2.setBounds(button1.getX()+120,button1.getY(),button1.getWidth(),button1.getHeight());
        textField.setBounds(button2.getX()+120,button2.getY(),button2.getWidth(),button2.getHeight());
        frame.setLayout(null);
        frame.add(w);
        frame.add(button);
        frame.add(button2);
        frame.add(button1);
        frame.add(textField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(1777,1000);
        frame.repaint();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private static Window setupWindow(JFrame frame) {
        Window w = new Window();
        w.setBounds(w.getBattlefieldBounds());
        w.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Point p = new Point(e.getX(),e.getY());
                Point clicked = w.toHexCoords(p);
                IronMan i = new IronMan(0,clicked);
                Game.getInstance().addUnit(i,1);
                frame.repaint();
            }
        });
        return w;
    }
}
