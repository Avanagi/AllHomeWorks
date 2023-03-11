import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializeDeSerialize implements SerializeDeserializeInterface {
    /**
     * @param object Объект, который передается в функцию
     * @param file Файл, куда заносить данные
     */
    @Override
    public void serialize(Object object, String file) {
        try (ObjectOutputStream in = new ObjectOutputStream(new FileOutputStream(file))) {
            in.writeObject(object);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param file Файл, откуда считываются данные
     * @return возвращает данные файла
     */
    @Override
    public Object deSerialize(String file) {
        Object newPeople = null;
        try (ObjectInputStream out = new ObjectInputStream(new FileInputStream(file))) {
            newPeople = out.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return newPeople;
    }
}
