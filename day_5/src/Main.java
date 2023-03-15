import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException,
            IllegalAccessException, SAXException, InvocationTargetException, NoSuchMethodException,
            ClassNotFoundException, InstantiationException {
        Person person = new Person("Roman", "Gorelov", 19);
        SerializeDeserializeImpl serializeDeserialize = new SerializeDeserializeImpl();
        serializeDeserialize.serialize(person, "./test1.txt");
        Object object = serializeDeserialize.deSerialize("./test1.txt");
        Method method = object.getClass().getDeclaredMethod("toString");
        method.setAccessible(true);
        System.out.println(method.invoke(object));
    }

}