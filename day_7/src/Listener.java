import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * Класс - слушатель
 */
public class Listener extends Thread {
    private final Socket socket;

    /**
     * Конструктор по значению
     * @param socket сокет, который передается
     */
    public Listener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream()) {
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(inputStream));
            String message;

            while (true) {
                if((message = clientReader.readLine()) != null) {
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}