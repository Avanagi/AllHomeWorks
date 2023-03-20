import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Writing {
    public void reading() throws Exception {
        Path rootPath = Paths.get("./");
        Path javaFilePath = rootPath.resolve("ClassCompile.java");

        Scanner scanner = new Scanner(System.in);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(javaFilePath)) {
            bufferedWriter.write("public class ClassCompile implements Worker {\n");
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


        Compiling compiling = new Compiling();
        compiling.compile(rootPath, javaFilePath);
    }
}
