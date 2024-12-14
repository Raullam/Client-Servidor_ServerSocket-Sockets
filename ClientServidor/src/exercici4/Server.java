package exercici4;// SERVER CODE
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 2000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVIDOR] Esperant connexions al port " + port + "...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("[SERVIDOR] Client connectat!");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String input;
                while ((input = in.readLine()) != null) {
                    System.out.println("[SERVIDOR] Dades rebudes: " + input);
                    
                    String[] parts = input.split(" ");
                    if (parts.length != 3) {
                        out.println("Error: Entrada no vàlida");
                        continue;
                    }
                    
                    String operacio = parts[0];
                    double num1, num2;
                    
                    try {
                        num1 = Double.parseDouble(parts[1]);
                        num2 = Double.parseDouble(parts[2]);
                    } catch (NumberFormatException e) {
                        out.println("Error: Nombres no vàlids");
                        continue;
                    }
                    
                    double resultat;
                    switch (operacio.toLowerCase()) {
                        case "suma":
                            resultat = num1 + num2;
                            break;
                        case "resta":
                            resultat = num1 - num2;
                            break;
                        case "multiplicacio":
                            resultat = num1 * num2;
                            break;
                        case "divisio":
                            if (num2 == 0) {
                                out.println("Error: No es pot dividir per zero");
                                continue;
                            }
                            resultat = num1 / num2;
                            break;
                        default:
                            out.println("Error: Operació no reconeguda");
                            continue;
                    }

                    out.println("Resultat: " + resultat);
                }
            }

            System.out.println("[SERVIDOR] Connexió tancada.");
        } catch (IOException e) {
            System.err.println("[SERVIDOR] Error en el servidor: " + e.getMessage());
        }
    }
}

