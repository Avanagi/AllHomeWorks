import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
       FileGeneration fileGeneration = new FileGeneration();
       fileGeneration.setWords();
       fileGeneration.getFiles("test", 3, 2, fileGeneration.words, 2);
    }
}