import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Roman", "Gorelov", 19);
        SerializeDeserializeImpl serializeDeserialize = new SerializeDeserializeImpl();
        serializeDeserialize.serialize(person, "test");
        Object object = serializeDeserialize.deSerialize("test");
        Method method;
        try {
            method = object.getClass().getDeclaredMethod("toString");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        method.setAccessible(true);
        try {
            System.out.println(method.invoke(object));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}