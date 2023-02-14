import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GenerationHelper {
    /**
     * @return возвращает сгенерированный массив слов
     * @throws IOException
     */
    public static String[] genCollectionWords() throws IOException {
        Random random = new Random();
        String[] text = new String[random.nextInt(1001)];
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char ch;
        try{
            FileReader fr = new FileReader("text.txt");
            while (fr.ready()) {
                ch = (char)fr.read();
                sb.append(ch);
                if(ch == ' '){
                    text[i] = String.valueOf(sb);
                    sb.delete(0, sb.length());
                    i++;
                }
            }
        } catch(Exception e) {
            e.getMessage();
        }
        return text;
    }
}
