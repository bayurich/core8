package ru.netology;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) {
        final int port = 5555;


        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.printf("Server started on port: %d\n", port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.printf("New connection accepted. Port: %d\n", clientSocket.getPort());

                    final String name = in.readLine();
                    out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                } catch (Exception e) {
                    System.out.println("server error while connection: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("server error: " +e.getMessage());
        }
    }
}
