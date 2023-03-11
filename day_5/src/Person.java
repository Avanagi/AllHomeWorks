import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

public class Person implements Serializable {
    private final String Name;
    private final String Surname;
    private final int Age;
    private final String[] Skills;
    private final UUID serialVersionUID;

    /**
     * Конструктор для реализации класса Person
     * @param Name Имя человека
     * @param Surname Фамилия человека
     * @param Age Возраст человека
     * @param Skills Умения человека
     */
    public Person(String Name, String Surname, int Age, String[] Skills) {
        this.Name = Name;
        this.Surname = Surname;
        this.Age = Age;
        this.Skills = Skills;
        serialVersionUID = UUID.randomUUID();
    }

    /**
     * @return возвращает все данные
     */
    @Override
    public String toString() {
        return "Person{" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Age=" + Age +
                ", Skills=" + Arrays.toString(Skills) +
                ", serialVersionUID=" + serialVersionUID +
                '}';
    }

}
