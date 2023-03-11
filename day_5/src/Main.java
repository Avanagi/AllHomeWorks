import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args)
    {
        String[] skills = {"Clever", "Social"};
        ArrayList<Object> people = new ArrayList<Object>();
        people.add(new Person("Roman", "Gorelov", 19, skills));
        people.add(new Person("Sam", "Porter", 38, skills));


        SerializeDeSerialize serializeDeSerialize = new SerializeDeSerialize();
        serializeDeSerialize.serialize(people, "./test.dat");
        serializeDeSerialize.serialize(new Person("Alexey", "Gorelov", 45, new String[]{"Big"}), "./test1.dat");
        serializeDeSerialize.serialize("Hello World!", "./test2.dat");
        System.out.println(serializeDeSerialize.deSerialize("./test.dat"));
        System.out.println(serializeDeSerialize.deSerialize("./test1.dat"));
        System.out.println(serializeDeSerialize.deSerialize("./test2.dat"));
    }
}