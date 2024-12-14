package exercici5Threads;

import java.io.*;
import java.net.*;

public class Server5 {
    public static void main(String[] args) {
        int port = 1500;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVER5] Servidor esperant connexions al port " + port);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> gestionarClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("[SERVER5] Error: " + e.getMessage());
        }
    }

    private static void gestionarClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             OutputStream outputStream = clientSocket.getOutputStream()) {

            String fileName = in.readLine();
            File file = new File(fileName);
            if (file.exists() && !file.isDirectory()) {
                out.println("OK");
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                out.println("Error: El fitxer no existeix");
            }
        } catch (IOException e) {
            System.err.println("[SERVER5] Error amb client: " + e.getMessage());
        }
    }
}
