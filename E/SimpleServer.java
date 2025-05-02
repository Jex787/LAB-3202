import java.net.*;
import java.io.*;

public class SimpleServer {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(1254);
            System.out.println("Server started. Waiting for client...");

            Socket s1 = s.accept();
            System.out.println("Client connected.");

            OutputStream s1out = s1.getOutputStream();
            DataOutputStream dos = new DataOutputStream(s1out);

            dos.writeUTF("Hi there");

            dos.close();
            s1out.close();
            s1.close();
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}