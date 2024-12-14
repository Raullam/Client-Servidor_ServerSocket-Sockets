package exercici1Threads;

import java.io.*;
import java.net.*;

public class Server1 {
    public static void main(String[] args) {
        int port = 2222;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVER1] Servidor esperant connexions al port " + port);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("[SERVER1] Error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String missatge = in.readLine();
            out.println(missatge.toUpperCase());
        } catch (IOException e) {
            System.err.println("[SERVER1] Error amb client: " + e.getMessage());
        }
    }
}