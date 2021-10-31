package ru.netology;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainClient {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 5555;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            out.println("TASK1\n");
            String response = in.readLine();
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.out.println("client error while connection: " +e.getMessage());
        }
    }
}
