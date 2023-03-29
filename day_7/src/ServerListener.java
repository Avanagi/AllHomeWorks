import java.io.*;
import java.net.Socket;

public class ServerListener extends Thread {
    private Socket socket;

    public ServerListener(Socket socket) {
        this.socket = socket;
    }

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