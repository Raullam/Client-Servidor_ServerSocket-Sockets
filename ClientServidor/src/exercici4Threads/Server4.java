package exercici4Threads;

import java.io.*;
import java.net.*;

public class Server4 {
    public static void main(String[] args) {
        int port = 2000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVER4] Servidor esperant connexions al port " + port);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> gestionarClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("[SERVER4] Error: " + e.getMessage());
        }
    }

    private static void gestionarClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request = in.readLine();
            String[] parts = request.split(" ");
            String operation = parts[0];
            double num1 = Double.parseDouble(parts[1]);
            double num2 = Double.parseDouble(parts[2]);
            double result = 0;

            switch (operation) {
                case "suma": result = num1 + num2; break;
                case "resta": result = num1 - num2; break;
                case "multiplicacio": result = num1 * num2; break;
                case "divisio": result = num1 / num2; break;
            }

            out.println("Resultat: " + result);
        } catch (IOException e) {
            System.err.println("[SERVER4] Error amb client: " + e.getMessage());
        }
    }
}
