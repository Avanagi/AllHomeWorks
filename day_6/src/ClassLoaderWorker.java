import java.io.*;

public class ClassLoaderWorker extends ClassLoader {

    /**
     * Переопределяем метод findClass, которому надо передать путь к файлу с расширением .class, с помощью потока
     * считываем файл в массив байт, с помощью функции defineClass загружаем класс
     *
     * @param path The <a href="#binary-name">binary name</a> of the class
     * @return созданный класс
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String path) throws ClassNotFoundException {
        File file = new File(path + ".class");
        if(! file.isFile())
            throw new ClassNotFoundException("No Such Class " + path);
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[(int) file.length()];
            inputStream.read(bytes);
            Class cls = defineClass("WorkerImpl", bytes, 0, bytes.length);
            return cls;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException("Wrongs With Bytes");
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
