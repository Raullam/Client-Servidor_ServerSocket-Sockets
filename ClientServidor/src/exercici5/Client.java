package exercici5;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 1500;

        try (Socket socket = new Socket(hostName, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             InputStream inputStream = socket.getInputStream();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("[CLIENT] Connectat al servidor a " + hostName + ":" + port);

            System.out.print("Introdueix la ruta absoluta del arxiu que vols rebre del servidor: ");
            // pots posar una ruta relativa o absoluta com la següent
            //C:\Users\Rulox\Desktop\1 trimestre\Threads\novembre\ClientServidor\src\exercici5\hola.txt
            String fileName = scanner.nextLine();
            out.println(fileName);

            String response = in.readLine();
            if ("OK".equals(response)) {
                System.out.println("[CLIENT] El fitxer existeix. Rebent el contingut...");
                
                try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, bytesRead);
                    }
                    
                    String fileContent = byteArrayOutputStream.toString();
                    System.out.println("[CLIENT] Contingut del fitxer:\n" + fileContent);
                } catch (IOException e) {
                    System.err.println("[CLIENT] Error en rebre el fitxer: " + e.getMessage());
                }
            } else {
                System.out.println("[CLIENT] " + response);
            }

            System.out.println("[CLIENT] Connexió tancada.");
        } catch (IOException e) {
            System.err.println("[CLIENT] Error en el client: " + e.getMessage());
        }
    }
}
