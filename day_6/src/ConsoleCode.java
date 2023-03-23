import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConsoleCode {
    private Path rootPath;
    private Path javaFilePath;

    /**
     * Записывает в файл код с консоли
     *
     * @throws Exception
     */
    public void input() throws Exception {
        rootPath = Paths.get("./src/");
        javaFilePath = rootPath.resolve("WorkerImpl.java");

        Scanner scanner = new Scanner(System.in);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(javaFilePath)) {
            bufferedWriter.write("public class WorkerImpl implements Worker {\n");
            bufferedWriter.write("  @Override\n");
            bufferedWriter.write("  public void doWork() {\n");

            while (true) {
                String text = scanner.nextLine();
                if(text.equals("")) {
                    break;
                }
                bufferedWriter.write("      " + text);
            }

            bufferedWriter.write("\n");
            bufferedWriter.write("  }\n");
            bufferedWriter.write("}");

        }
    }

    /**
     * @return возвращает путь до файла
     */
    public Path getJavaFilePath() {
        return javaFilePath;
    }
}
