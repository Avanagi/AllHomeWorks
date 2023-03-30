import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс, запускающий сервер
 */
public class Server {
    public final static int serverPort = 1000;
    public static final List<Socket> sockets = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        System.out.println("Add Users Using Class - Client!");
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        if(socket != null) {
                            sockets.add(socket);
                            String nameSocket = getSocketName(socket);
                            ServerListener listener = new ServerListener(socket);
                            LocalTime localTime;
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss ");
                            localTime = LocalTime.now();
                            listener.sendMessage(localTime.format(dateTimeFormatter) + nameSocket + " Is Online");
                            listener.start();
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();
            thread.join();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод, достающий имя пользователя из сокета
     * @param socket сокет, из которого мы получаем имя пользователя
     * @return имя пользователя
     * @throws Exception
     */
    private static String getSocketName(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.readLine();
    }
}
