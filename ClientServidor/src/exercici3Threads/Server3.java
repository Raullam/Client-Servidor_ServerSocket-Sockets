package exercici3Threads;

import java.io.*;
import java.net.*;

public class Server3 {
    public static void main(String[] args) {
        int port = 2222;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVER3] Servidor esperant connexions al port " + port);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> gestionarClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("[SERVER3] Error: " + e.getMessage());
        }
    }

    private static void gestionarClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String jsonData = in.readLine();
            try (FileWriter fileWriter = new FileWriter("direcciones.txt", true)) {
                fileWriter.write(jsonData + "\n");
            }
            out.println("OK");
        } catch (IOException e) {
            System.err.println("[SERVER3] Error amb client: " + e.getMessage());
        }
    }
}