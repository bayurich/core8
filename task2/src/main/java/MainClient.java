import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) {
        //добавить в hosts
        String host = "netology.homework";
        int port = 5555;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                String response = in.readLine();
                System.out.println("Server response: " + response);
                String msg = scanner.nextLine();
                out.println(msg);
            }

        } catch (Exception e) {
            System.out.println("client error while connection: " +e.getMessage());
        }
    }
}
