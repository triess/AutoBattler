package Game;

import Server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Player {

    private int id;
    private Server server;

    public Player(Server s){
        this.server = s;
        id = s.getNextPlayerID();
    }

    public Player(InetAddress ip){
        try {
            Socket s = new Socket(ip,6666);
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            server = (Server)ois.readObject();
            id = server.getNextPlayerID();
            System.out.println("yes");
        } catch (IOException | ClassNotFoundException ignored) {
            System.out.println("no");
            System.out.println(ignored);
        }
    }

}
