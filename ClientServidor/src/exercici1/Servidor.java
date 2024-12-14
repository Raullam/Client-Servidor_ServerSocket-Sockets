package exercici1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        final int PORT = 2222;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor en marxa. Esperant connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accepta connexions
                System.out.println("Client connectat!");

                try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    // Llegeix el text enviat pel client
                    String text = in.readLine();
                    System.out.println("Missatge rebut: " + text);

                    // Envia el text en majúscules
                    String resposta = text.toUpperCase();
                    out.println(resposta);
                    System.out.println("Resposta enviada: " + resposta);

                } catch (Exception e) {
                    System.err.println("Error gestionant el client: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println("Error al servidor: " + e.getMessage());
        }
    }
}
