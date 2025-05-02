import java.io.*;
import java.net.*;

public class clientPart {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // or use IP address
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server at " + serverAddress + ":" + port);

            // Send message to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello Server, I am the Client!");

            // Read response from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverMsg = in.readLine();
            System.out.println("Received from server: " + serverMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
