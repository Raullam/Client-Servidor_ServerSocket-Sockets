package exercici4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 2000;

        try (Socket socket = new Socket(hostName, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("[CLIENT] Connectat al servidor a " + hostName + ":" + port);
            String response;

            while (true) {
                System.out.print("Introdueix l'operació (suma, resta, multiplicacio, divisio) i dos nombres separats per espais (ex: suma 5 3): ");
                String input = scanner.nextLine();

                out.println(input);
                response = in.readLine();

                System.out.println("[SERVIDOR] " + response);

                System.out.print("Vols realitzar una altra operació? (si/no): ");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("no")) {
                    break;
                }
            }

            System.out.println("[CLIENT] Connexió tancada.");
        } catch (IOException e) {
            System.err.println("[CLIENT] Error en el client: " + e.getMessage());
        }
    }
}
