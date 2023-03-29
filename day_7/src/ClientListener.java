import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientListener {

    public static void main(String[] args) {
        System.out.print("Name - ");
        Scanner scanner = new Scanner(System.in);
        String clientName = scanner.nextLine();
        try (Socket clientSocket = new Socket(InetAddress.getLocalHost(), Server.serverPort)) {
            Listener listener = new Listener(clientSocket);
            Sender sender = new Sender(clientSocket, clientName);
            sendSocketName(clientSocket, clientName);
            listener.start();
            sender.start();
            listener.join();
            sender.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sendSocketName(Socket socket, String clientName) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write(clientName);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
}