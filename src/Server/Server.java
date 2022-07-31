package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Serializable {
    private int nextPlayerID=0;
    private static final long serialVersionUID = 1L;

    public static void main(String[] args){
        try {
            Server server = new Server();
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(server);
            oos.flush();
            //DataInputStream dis = new DataInputStream(s.getInputStream());
            //String str = (String)dis.readUTF();
            //System.out.println(str);
            ss.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNextPlayerID(){
        nextPlayerID++;
        return nextPlayerID;
    }
}
