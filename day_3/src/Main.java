import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileGeneration fileGeneration = new FileGeneration();
        String[] text = GenerationHelper.genCollectionWords();
        fileGeneration.getFiles("test", 3, 1, text, 2);
    }
}