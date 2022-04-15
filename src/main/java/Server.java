import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8081;
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

                out.println("Write your name");
                String name = in.readLine();

                out.println("Hi " + name + "! Write your age");
                int age = Integer.parseInt(in.readLine());

                if (age <= 18) {
                    out.println("Welcome to the kids area, " + name + "! Let's play!");
                } else {
                    out.println("Welcome to the adult zone, " + name + "! Have a good rest, or a good working day!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
