package exercici2Threads;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Server2 {
    public static void main(String[] args) {
        int port = 2222;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVER2] Servidor esperant connexions al port " + port);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> gestionarClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("[SERVER2] Error: " + e.getMessage());
        }
    }

    private static void gestionarClient(Socket clientSocket) {
        int numeroSecret = new Random().nextInt(101);
        
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String respostaNumeroClient;
            while ((respostaNumeroClient = in.readLine()) != null) {
                int respostaUsuari = Integer.parseInt(respostaNumeroClient);
                if (respostaUsuari > numeroSecret) {
                    out.println("MENOR");
                } else if (respostaUsuari < numeroSecret) {
                    out.println("MAJOR");
                } else {
                    out.println("CORRECTE");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("[SERVER2] Error amb client: " + e.getMessage());
        }
    }
}