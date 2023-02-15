import java.io.*;
import java.nio.file.*;
import java.util.Random;

public class FileGeneration {
    private final String Symbols = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private final Random random = new Random();

    /**
     * метод, конструирующий слово из рандомных букв и возвращающий его
     *
     * @param len длина слова
     * @return возвращает слово
     */
    public String generateWord(int len) {
        StringBuilder sentence = new StringBuilder();
        for(int i = 0; i < len; i++) {
            sentence.append(Symbols.charAt(random.nextInt(Symbols.length())));
        }
        return String.valueOf(sentence);
    }

    /**
     * метод, конструирующий слово для предложения из рандомных букв и возвращающий его
     *
     * @param len        длина слова для составления предложения
     * @param wordsCount дополнительная переменная, помогающая установить "
     * @return возвращает сгенерированное слово
     */
    public String generateWordForSentence(int len, int wordsCount) {
        StringBuilder sentence = new StringBuilder(generateWord(len + 1));
        int zap = random.nextInt(11);
        if(zap == 1) {
            sentence.replace(len - 1, len, ",");
        }
        sentence.replace(len, len, " ");
        if(wordsCount == 0 && zap == 10) {
            sentence.replace(0, 1, String.valueOf('"'));
        }
        return String.valueOf(sentence);
    }

    /**
     * метод, конструирующий предложение из созданных ранее слов и возвращающий его
     *
     * @param len         количество слов в предложении
     * @param probability вероятность использования слов из массива слов
     * @param words       массив слов
     * @return возвращает сгенерированное предложения
     */
    public String generateSentence(int len, int probability, String[] words) {
        int lettersCount;
        int wordsCount = 0;
        StringBuilder sentence = new StringBuilder();
        for(int i = 0; i < len; i++) {
            if(random.nextInt(100) < (double) 1 / probability * 100) {
                sentence.append(words[random.nextInt(words.length)]);
                sentence.append(" ");
            } else {
                lettersCount = 1 + (int) (Math.random() * 14);
                sentence.append(generateWordForSentence(lettersCount, wordsCount));
            }
            wordsCount++;
        }
        correctSentence(sentence);
        return String.valueOf(sentence);
    }

    /**
     * метод, изменяющий предложения по правилам
     *
     * @param sentence предложение, подлежащее обработке
     */
    public void correctSentence(StringBuilder sentence) {
        if(sentence.charAt(0) == '"') {
            String first_letter = String.valueOf(sentence.charAt(1));
            sentence.replace(1, 2, first_letter.toUpperCase());
            sentence.replace(sentence.length() - 2, sentence.length(), String.valueOf('"'));
        } else {
            String first_letter = String.valueOf(sentence.charAt(0));
            sentence.replace(0, 1, first_letter.toUpperCase());
            int sign = random.nextInt(3);
            if(sign == 0) {
                sentence.replace(sentence.length() - 2, sentence.length(), ".");
            } else if(sign == 1) {
                sentence.replace(sentence.length() - 2, sentence.length(), "!");
            } else {
                sentence.replace(sentence.length() - 2, sentence.length(), "?");
            }
        }
    }

    /**
     * метод, конструирующий абзац из созданных ранее предложений и возвращающий его
     *
     * @param lg          количество предложений в абзаце
     * @param probability вероятность, необходимая для генерирования предложений
     * @param words       массив слов
     * @return возвращает сгенерированный абзац
     */
    public String generateParagraph(int lg, int probability, String[] words) {
        int len = 1 + (int) (Math.random() * 19);
        int l = 1 + (int) (Math.random() * 14);
        StringBuilder paragraph = new StringBuilder();
        for(int i = 0; i < lg; i++) {
            for(int j = 0; j < len; j++) {
                paragraph.append(generateSentence(l, probability, words));
            }
            paragraph.append('\n');
        }
        return String.valueOf(paragraph);
    }

    /**
     * @param path        имя директории(необходимо заранее создать), где будут создваться временные(с обычными вылезают ошибки по типу прав доступа) текстовые файлы
     * @param n           количество файлов
     * @param size        количество абзацев
     * @param words       сгенерированный массив слов
     * @param probability вероятность для работы с массивом слов
     * @throws IOException выдаа ошибки
     */
    public void getFiles(String path, int n, int size, String[] words, int probability) throws IOException {
        Path rootDir = null;
        Path[] files;
        try {
            throw (Throwable) (rootDir = Paths.get(path));
        } catch (Throwable exception) {
            System.out.println("You have no same directory");
        }
        files = new Path[n];
        for(int i = 0; i < n; i++) {
            if(rootDir != null) {
                files[i] = Files.createTempFile(rootDir, "test", ".txt");
            }
            FileWriter myWriter = new FileWriter(files[i].toFile());
            myWriter.write(String.valueOf(generateParagraph(size, probability, words)));
            myWriter.close();
        }
    }
}
