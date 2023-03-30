import java.io.*;
import java.net.Socket;

/**
 * Класс, обрабатывающий информацию на сервере
 */
public class ServerListener extends Thread {
    private Socket socket;

    /**
     * Конструктор по значению
     * @param socket сокет, который мы передаем в конструктор
     */
    public ServerListener(Socket socket) {
        this.socket = socket;
    }

    /**
     * Метод, обрабатывающий выход пользователя
     */
    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream()) {
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(inputStream));
            String message;
            while (true) {
                if(! (message = clientReader.readLine()).endsWith("quit")) {
                    sendMessage(message);
                } else {
                    String[] containName = message.split(" ");
                    sendMessage(containName[1] + " left");
                    Server.sockets.remove(socket);
                    socket.close();
                    socket = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, обрабатывающий полученное сообщение
     * @param message сообщение, которое передается
     */
    public void sendMessage(String message) {
        for(Socket socket:
                Server.sockets) {
            try {
                OutputStream outputStream = socket.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}