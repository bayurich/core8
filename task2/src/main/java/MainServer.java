import com.sun.deploy.util.StringUtils;

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

                    String line;
                    String name = "";
                    String response = "Write your name";
                    out.println(response);
                    boolean isErr = false;
                    int i = 1;
                    while ((line = in.readLine()) != null) {
                        System.out.println("Received message: " + line);
                        if ("".equals(line)) {
                            out.println("Empty value. Please repeat");
                            continue;
                        }
                        switch (i) {
                            case (1):
                                name = line;
                                response = "Are you child? (yes/no)";
                                break;
                            case (2):
                                line = line.toLowerCase();
                                if ("yes".equals(line)){
                                    response = String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!! Let's play!", name);
                                } else if ("no".equals(line)){
                                    response = String.format("Welcome to the kids area, %s! Let's play!", name);
                                } else {
                                    isErr = true;
                                }
                                break;
                            default:
                                response = "Your message: " + line;
                                break;
                        }
                        if (isErr){
                            response = "Unexpected value. Please repeat";
                            out.println(response);
                            isErr = false;
                            continue;
                        }

                        out.println(response);
                        i++;
                    }
                    /*out.println("Write your name");
                    String name = in.readLine();
                    System.out.println("User name: " + name);
                    out.println("Are you child? (yes/no)");
                    String area = in.readLine();
                    System.out.println("Is the user a child: " + area);
                    if (area != null && "yes".equals(area.toLowerCase())){
                        out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!! Let's play!", name));
                    } else {
                        out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                    }*/
                } catch (Exception e) {
                    System.out.println("server error while connection: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("server error: " +e.getMessage());
        }
    }
}
