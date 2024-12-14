package exercici2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 2222;
        Random random = new Random();
        int secretNumber = random.nextInt(101); // Genera un nombre entre 0 i 100

        System.out.println("[SERVIDOR] El número secret és: " + secretNumber);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVIDOR] Esperant connexions al port " + port + "...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("[SERVIDOR] Client connectat!");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    int clientNumber;
                    try {
                        clientNumber = Integer.parseInt(inputLine);
                    } catch (NumberFormatException e) {
                        out.println("Si us plau, envia un número vàlid.");
                        continue;
                    }

                    if (clientNumber < secretNumber) {
                        out.println("El número és més gran.");
                    } else if (clientNumber > secretNumber) {
                        out.println("El número és més petit.");
                    } else {
                        out.println("Felicitats! Has endevinat el número secret.");
                        break;
                    }}}}}}