package exercici5;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 1500;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVIDOR] Esperant connexions al port " + port + "...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("[SERVIDOR] Client connectat!");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 OutputStream outputStream = clientSocket.getOutputStream()) {

                String fileName = in.readLine();
                System.out.println("[SERVIDOR] Nom del fitxer sol·licitat: " + fileName);

                File file = new File(fileName);
                if (file.exists() && !file.isDirectory()) {
                    out.println("OK");
                    try (FileInputStream fileInputStream = new FileInputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    } catch (IOException e) {
                        System.err.println("[SERVIDOR] Error en enviar el fitxer: " + e.getMessage());
                    }
                } else {
                    out.println("Error: El fitxer no existeix");
                }
            }

            System.out.println("[SERVIDOR] Connexió tancada.");
        } catch (IOException e) {
            System.err.println("[SERVIDOR] Error en el servidor: " + e.getMessage());
        }
    }
}