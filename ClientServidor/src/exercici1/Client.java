package exercici1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 2222;

        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //poniendo flush nos aseguramos de que se envie todo
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Escriu un text per enviar al servidor: ");
            String text = scanner.nextLine();

            // Envia el text al servidor
            out.println(text);

            // Rep la resposta del servidor
            String resposta = in.readLine();
            System.out.println("Resposta del servidor: " + resposta);

        } catch (Exception e) {
            System.err.println("Error al client: " + e.getMessage());
        }
    }
}
