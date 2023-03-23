public class Main {
    public static void main(String[] args) throws Exception {
        Worker worker = new WorkerImpl();
        ConsoleCode consoleCode = new ConsoleCode();
        consoleCode.input();
        JavaWorkCompile javaWorkCompile = new JavaWorkCompile();
        javaWorkCompile.compileWork(consoleCode.getJavaFilePath());
        ClassLoaderWorker cl = new ClassLoaderWorker();
        Class<?> aClass = cl.findClass("./src/WorkerImpl");
        Object o = aClass.newInstance();
        worker = (Worker) o;
        worker.doWork();
    }
}