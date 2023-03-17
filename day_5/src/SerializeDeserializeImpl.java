import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SerializeDeserializeImpl implements SerializeDeserialize {
    ArrayList<Object> classParams = new ArrayList<>();
    private static final Set<String> WRAPPER_TYPES = getWrapperTypes();

    private static Set<String> getWrapperTypes() {
        return new HashSet<>(Arrays.asList("Boolean", "Character", "Byte", "Short", "Integer",
                "Long", "Float", "Double", "String"));
    }

    /**
     * Сверка ссылочных типов
     *
     * @param type именование ссылочного типа данных
     * @return имеется  / не имеется
     */
    private static boolean isWrapperType(String type) {
        return WRAPPER_TYPES.contains(type);
    }

    /**
     * Метод, сериализующий объект в XML-файл
     *
     * @param object сериализуемый обьект
     * @param path   путь, в котором будет сохранен сериализованный файл
     */
    public void serialize(Object object, String path) {
        if(object == null) return;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(object.getClass().getSimpleName()).append(">");
        classParams.add(object.getClass().getSimpleName());
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field: fields) {
            if(field.getType().isPrimitive() || isWrapperType(field.getType().getSimpleName())) {
                field.setAccessible(true);
                try {
                    stringBuilder.append("<")
                            .append(field.getName()).append(" ").append("type = \"")
                            .append(field.getType().getSimpleName()).append("\">")
                            .append(field.get(object)).append("</")
                            .append(field.getName()).append(">");
                    classParams.add(field.getName());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        stringBuilder.append("</").append(object.getClass().getSimpleName()).append(">");

        try (OutputStream outStream = new FileOutputStream("./" + path + "/" + object.getClass().getSimpleName() + ".xml")) {
            outStream.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Метод, возвращающий десериализованный объект
     *
     * @param path путь, откуда будет десериализован сериализованный файл
     * @return Десериализованный объект
     */
    @Override
    public Object deSerialize(String path) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document;
        try {
            document = documentBuilder.parse(new File("./" + path + "/" + classParams.get(0) + ".xml"));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        Object objectType = classParams.get(0);
        classParams.remove(0);
        int count = 0;
        Class<?> clazz;
        try {
            clazz = Class.forName((String) objectType);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Object person;
        try {
            person = (Object) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for(Object params:
                classParams) {
            String data = document.getElementsByTagName((String) params).item(0).getTextContent();
            Field fields = clazz.getDeclaredFields()[count];
            fields.setAccessible(true);
            if(data.matches("[-+]?\\d+")) {
                try {
                    fields.setInt(person, Integer.parseInt(data));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    fields.set(person, data);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            count++;
        }
        return person;
    }
}
