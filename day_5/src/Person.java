import java.io.Serializable;

public class Person implements Serializable {
    private final String Name;
    private final String Surname;
    private final int Age;
    private final long serialVersionUID;

    public Person() {
        Name = "";
        Surname = "";
        Age = 0;
        serialVersionUID = 1L;
    }

    public Person(String Name, String Surname, int Age) {
        this.Name = Name;
        this.Surname = Surname;
        this.Age = Age;
        serialVersionUID = 4L;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Name = '" + Name + '\'' +
                ", Surname = '" + Surname + '\'' +
                ", Age = " + Age +
                ", serialVersionUID = " + serialVersionUID +
                '}';
    }
}
