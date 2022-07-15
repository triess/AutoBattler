package Communication;

import java.io.*;
import java.net.*;

public class Connection {
    public void createSocket() {
        try {
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = dis.readUTF();
            System.out.println(str);
            ss.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

