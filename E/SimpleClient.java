import java.net.*;
import java.io.*;

public class SimpleClient {
    public static void main(String[] args) {
        try {
            // Connect to the server running on localhost at port 1254
            Socket s1 = new Socket("localhost", 1254);
            System.out.println("Connected to the server.");

            // Get input stream and read message from server
            InputStream s1In = s1.getInputStream();
            DataInputStream dis = new DataInputStream(s1In);

            String message = dis.readUTF();
            System.out.println("Server says: " + message);

            // Close all connections
            dis.close();
            s1In.close();
            s1.close();
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}



