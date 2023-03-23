import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.nio.file.Path;

public class JavaWorkCompile {
    /**
     * @param javaFilePath переменная, хрянящая путь до файла
     * @throws Exception
     */
    public void compileWork(Path javaFilePath) throws Exception {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        javaCompiler.run(null, null, null, javaFilePath.toAbsolutePath().toString());
    }
}
