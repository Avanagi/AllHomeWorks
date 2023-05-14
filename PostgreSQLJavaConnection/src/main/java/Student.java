
public class Student {
	private
		String Name;
		String Surname;
		String Patronymic;
		int subject_id;
		
	public Student(String Name, String Surname, String Patronymic, int subject_id) {
		this.Name = Name;
		this.Surname = Surname;
		this.Patronymic = Patronymic;
		this.subject_id = subject_id;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getSurname() {
		return Surname;
	}
	
	public void setSurname(String surname) {
		Surname = surname;
	}
	
	public String getPatronymic() {
		return Patronymic;
	}
	
	public void setPatronymic(String patronymic) {
		Patronymic = patronymic;
	}
	
	public int getSubject_id() {
		return subject_id;
	}
	
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
}
