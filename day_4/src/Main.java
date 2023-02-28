import java.io.*;
import org.jsoup.Jsoup;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            var doc = Jsoup.connect("http://www.gutenberg.org/cache/epub/17168/pg17168.txt").get();
            var elem = doc.selectFirst('body');
            System.out.println(elem.text());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
