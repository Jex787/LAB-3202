import java.io.*;
import java.net.*;

public class serverPart {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for client on port " + port + "...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            // Input from client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientMsg = in.readLine();
            System.out.println("Received from client: " + clientMsg);

            // Output to client
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello from Server!");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


