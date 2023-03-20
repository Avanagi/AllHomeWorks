import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

public class Compiling {
    public void compile(Path rootPath, Path javaFilePath) throws Exception {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        javaCompiler.run(null, null, null, javaFilePath.toAbsolutePath().toString());

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{rootPath.toUri().toURL()});
        Class<?> someClass = Class.forName("ClassCompile", true, classLoader);
        Worker worker = (Worker) someClass.newInstance();
        worker.doWork();
    }
}
