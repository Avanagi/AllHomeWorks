import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SerializeDeserializeImpl implements SerializeDeserialize {
    ArrayList<String> classParams = new ArrayList<String>();

    /**
     * Сериализует объект в XML файл
     * @param object Сериализующийся объект
     * @param files Имя XML файла
     */
    @Override
    public void serialize(Object object, String files) throws ParserConfigurationException, TransformerException,
            IllegalAccessException {
        String root = "Settings";
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element rootElement = document.createElement(root);
        document.appendChild(rootElement);

        Class<?> cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        classParams.add(cls.getName());
        for(Field field: fields) {
            if(! field.getName().equals("serialVersionUID")) {
                Class<?> fld = field.getType();
                field.setAccessible(true);
                Object data = (Object) field.get(object);
                Element em = document.createElement(field.getName());
                classParams.add(field.getName());
                em.appendChild(document.createTextNode(String.valueOf(data)));
                rootElement.appendChild(em);
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new StringWriter());
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
        transformer.transform(source, result);

        FileOutputStream fop = null;
        File file;
        try {
            file = new File(files);
            fop = new FileOutputStream(file);
            if(! file.exists()) {
                file.createNewFile();
            }

            String xmlString = result.getWriter().toString();
            byte[] contentInBytes = xmlString.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Метод, возвращающий десериализованный объект
     * @param file Файл, откуда происходит десериализация
     * @return Десериализованный объект
     */
    @Override
    public Object deSerialize(String file) throws ParserConfigurationException, IOException, SAXException,
            IllegalAccessException, ClassNotFoundException, InstantiationException {

        File readFile = new File(file);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(readFile);
        String objectType = classParams.get(0);
        classParams.remove(0);
        int count = 0;
        Class<?> clazz = Class.forName(objectType);
        Person person = (Person) clazz.newInstance();
        for(String params:
                classParams) {
            String data = document.getElementsByTagName(params).item(0).getTextContent();
            Field fields = clazz.getDeclaredFields()[count];
            fields.setAccessible(true);
            if(data.matches("[-+]?\\d+")) {
                fields.setInt(person, Integer.parseInt(data));
            } else {
                fields.set(person, data);
            }
            count++;
        }
        return person;
    }
}
