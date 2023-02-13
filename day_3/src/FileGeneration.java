import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

public class FileGeneration {
    String[] words;
    String passSymbols = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    Random random = new Random();

    /**
     * Добавляет в массив слов рандомные слова
     */
    public void setWords() {
        int len;
        words = new String[random.nextInt(1001)];
        for(int i = 0; i < words.length; i++) {
            len = 1 + (int) (Math.random() * 14);
            words[i] = Arrays.toString(generateWord(len));
        }
    }

    /**
     * метод, конструирующий слово из рандомных букв и возвращающий его
     * @param len длина слова
     * @return возвращает слово
     */
    public char[] generateWord(int len) {
        char[] password = new char[len];
        for(int i = 0; i < len; i++) {
            password[i] = passSymbols.charAt(random.nextInt(passSymbols.length()));
        }
        return password;
    }

    /**
     * метод, конструирующий слово для предложения из рандомных букв и возвращающий его
     * @param len длина слова для составления предложения
     * @param words_count дополнительная переменная, помогающая установить "
     * @return возвращает сгенерированное слово
     */
    public char[] generateWordForSentence(int len, int words_count) {
        char[] password = generateWord(len + 1);
        int zap = random.nextInt(11);
        if(zap == 1) {
            password[len - 1] = ',';
        }
        password[len] = ' ';
        if(words_count == 0 && zap == 10) {
            password[0] = '"';
        }
        return password;
    }

    /**
     * метод, конструирующий предложение из созданных ранее слов и возвращающий его
     * @param len количество слов в предложении
     * @param probability вероятность использования слов из массива слов
     * @return возвращает сгенерированное предложения
     */
    public char[] generateSentence(int len, int probability) {
        int letters_count;
        int words_count = 0;
        int luck;
        int nw;
        StringBuilder password = new StringBuilder();
        for(int i = 0; i < len; i++) {
            luck = random.nextInt(100);
            if(luck < (double)1/probability * 100){
                nw = random.nextInt(words.length);
                for(int j = 0; j < words[i].length() - 1; j++) {
                    if(words[i].charAt(j) != ',' && words[i].charAt(j) != ' ' && words[i].charAt(j) != '[' && words[i].charAt(j) != ']') {
                        password.append(words[i].charAt(j));
                    }
                }
                System.out.println(words[i]);
                password.append(" ");

            } else {
                letters_count = 1 + (int) (Math.random() * 14);
                password.append(generateWordForSentence(letters_count, words_count));
            }
            words_count++;
        }
        correctSentence(password);
        return password.toString().toCharArray();
    }

    /**
     * метод, изменяющий предложения по правилам
     * @param sentence предложение, подлежащее обработке
     */
    public void correctSentence(StringBuilder sentence) {
        if(sentence.charAt(0) == '"') {
            String first_letter = String.valueOf(sentence.charAt(1));
            sentence.replace(1, 2, first_letter.toUpperCase());
            sentence.replace(sentence.length() - 2, sentence.length() - 1, String.valueOf('"'));
        } else {
            String first_letter = String.valueOf(sentence.charAt(0));
            sentence.replace(0, 1, first_letter.toUpperCase());
            int znak = random.nextInt(3);
            if(znak == 0) {
                sentence.replace(sentence.length() - 2, sentence.length() - 1, ".");
            } else if(znak == 1) {
                sentence.replace(sentence.length() - 2, sentence.length() - 1, "!");
            } else {
                sentence.replace(sentence.length() - 2, sentence.length() - 1, "?");
            }
        }
    }

    /**
     * метод, конструирующий абзац из созданных ранее предложений и возвращающий его
     * @param lg количество предложений в абзаце
     * @param probability вероятность, необходимая для генерирования предложений
     * @return возвращает сгенерированный абзац
     */
    public StringBuilder generateParagraph(int lg, int probability) {
        int len = 1 + (int) (Math.random() * 19);
        int l = 1 + (int) (Math.random() * 14);
        StringBuilder paragraph = new StringBuilder();
        for(int i = 0; i < lg; i++) {
            for(int j = 0; j < len; j++) {
                paragraph.append(generateSentence(l, probability));
            }
            paragraph.append('\n');
        }
        return paragraph;
    }

    /**
     * @param path имя директории(необходимо заранее создать), где будут создваться временные(с обычными вылезают ошибки по типу прав доступа) текстовые файлы
     * @param n количество файлов
     * @param size количество абзацев
     * @param words сгенерированный массив слов
     * @param probability вероятность для работы с массивом слов
     * @throws IOException выдаа ошибки
     */
    public void getFiles(String path, int n, int size, String[] words, int probability) throws IOException {
        Path rootDir = null;
        Path[] files;
        try {
            throw (Throwable) (rootDir = Paths.get(path));
        } catch (Throwable exception){
            System.out.println("You have no same directory");
        }
        try {
            files = new Path[n];
            for(int i = 0; i < n; i++) {
                files[i] = Files.createTempFile(rootDir, "test", ".txt");
                FileWriter myWriter = new FileWriter(files[i].toFile());
                myWriter.write(String.valueOf(generateParagraph(size, probability)));
                myWriter.close();
            }
        }catch (Throwable exception){
            System.out.println("No directory where you can create files");
        }
    }
}
