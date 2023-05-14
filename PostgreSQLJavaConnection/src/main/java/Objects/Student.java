package Objects;

public class Student extends Subject{
	private
		String Name;
		String Surname;
		String Patronymic;
		int subject_id;
		
	public Student(String Name, String Subject, String Teacher) {
		super(Subject, Teacher);
		this.Name = Name;
	}
	
	public Student(String Name, String Surname, String Subject, String Teacher) {
		super(Subject, Teacher);
		this.Name = Name;
		this.Surname = Surname;	}
		
	public Student(String Name, String Surname, String Patronymic, int subject_id) {
		this.Name = Name;
		this.Surname = Surname;
		this.Patronymic = Patronymic;
		this.subject_id = subject_id;
	}
	
	public String getName() {
		return Name;
	}
	
	public String getSurname() {
		return Surname;
	}

	public String getPatronymic() {
		return Patronymic;
	}

	public int getSubject_id() {
		return subject_id;
	}
	
}
