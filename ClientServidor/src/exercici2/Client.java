package exercici2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
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
                System.out.print("Introdueix un número entre 0 i 100: ");
                String userInput = scanner.nextLine();

                try {
                    int userNumber = Integer.parseInt(userInput);
                    if (userNumber < 0 || userNumber > 100) {
                        System.out.println("Si us plau, introdueix un número entre 0 i 100.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Si us plau, introdueix un número vàlid.");
                    continue;
                }

                out.println(userInput);
                response = in.readLine();

                System.out.println("[SERVIDOR] " + response);
                if (response.equals("Felicitats! Has endevinat el número secret.")) {
                    break;
                }
            }

            System.out.println("[CLIENT] Connexió tancada.");
        } catch (IOException e) {
            System.err.println("[CLIENT] Error en el client: " + e.getMessage());
        }
    }
}
