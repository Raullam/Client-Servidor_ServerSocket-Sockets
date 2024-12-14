package exercici3;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 2222;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVIDOR] Esperant connexions al port " + port + "...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("[SERVIDOR] Client connectat!");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String jsonInput;
                while ((jsonInput = in.readLine()) != null) {
                    System.out.println("[SERVIDOR] JSON rebut: " + jsonInput);
                    
                    // Guarda el JSON al fitxer direcciones.txt
                    try (FileWriter fileWriter = new FileWriter("direcciones.txt", true)) {
                        fileWriter.write(jsonInput + "\n");
                    } catch (IOException e) {
                        System.err.println("[SERVIDOR] Error en guardar el fitxer: " + e.getMessage());
                    }

                    // Respon al client
                    out.println("OK");
                }
            }

            System.out.println("[SERVIDOR] Connexió tancada.");
        } catch (IOException e) {
            System.err.println("[SERVIDOR] Error en el servidor: " + e.getMessage());
        }
    }
}