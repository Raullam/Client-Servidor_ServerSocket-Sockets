package exercici3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 2222;

        try (Socket socket = new Socket(hostName, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("[CLIENT] Connectat al servidor a " + hostName + ":" + port);
            String response;

            while (true) {
                System.out.println("Introdueix les dades per crear un JSON.");
                
                System.out.print("Carrer: ");
                String carrer = scanner.nextLine();
                
                System.out.print("Codi Postal: ");
                String codiPostal = scanner.nextLine();
                
                System.out.print("Pais: ");
                String pais = scanner.nextLine();
                
                System.out.print("Nombre de Casa: ");
                String nombreCasa = scanner.nextLine();
                
                String jsonData = String.format("{\"Carrer\":\"%s\", \"CodiPostal\":\"%s\", \"Pais\":\"%s\", \"NombreCasa\":\"%s\"}", carrer, codiPostal, pais, nombreCasa);
                System.out.println("[CLIENT] JSON generat: " + jsonData);

                out.println(jsonData);
                response = in.readLine();

                System.out.println("[SERVIDOR] " + response);
                if (response.equals("OK")) {
                    break;
                }
            }

            System.out.println("[CLIENT] Connexió tancada.");
        } catch (IOException e) {
            System.err.println("[CLIENT] Error en el client: " + e.getMessage());
        }
    }
}
