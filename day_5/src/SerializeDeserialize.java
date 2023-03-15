import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface SerializeDeserialize {
    public void serialize(Object object, String file) throws ParserConfigurationException, IOException,
            TransformerException, NoSuchFieldException, IllegalAccessException;

    public Object deSerialize(String file) throws ParserConfigurationException, IOException, SAXException,
            NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException,
            ClassNotFoundException, InstantiationException;
}
