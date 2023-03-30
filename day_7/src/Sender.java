import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Класс, выводящий сообщение
 */
public class Sender extends Thread {

    private final Socket socket;
    private final String user;

    /**
     * Конструктор по значению
     * @param socket сокет, который мы передаем
     * @param user имя пользователя
     */
    public Sender(Socket socket, String user) {
        this.socket = socket;
        this.user = user;
    }

    /**
     * Метод, который отвечает за вывод сообщения
     */
    @Override
    public void run() {
        try (OutputStreamWriter outputStream = new OutputStreamWriter(socket.getOutputStream())) {
            Scanner scanner = new Scanner(System.in);
            String str;
            LocalTime time;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            while (true) {
                if(scanner.hasNextLine()) {
                    str = scanner.nextLine();
                    time = LocalTime.now();
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStream);
                    bufferedWriter.write(time.format(formatter) + " " + user + " Send: " + str);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}