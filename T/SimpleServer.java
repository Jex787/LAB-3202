import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started. Waiting for client...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        BufferedReader in = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Client: " + inputLine);
            out.println("Server echoes: " + inputLine);
        }

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}