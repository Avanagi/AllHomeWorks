package Objects;

public class Subject{
	private
		String subject;
		String teacher;
		
	public Subject() {
		this.subject = "";
		this.teacher = "";
	}
		
	public Subject(String subject, String teacher) {
		this.subject = subject;
		this.teacher = teacher;
	}

	public String getSubject() {
		return subject;
	}

	public String getTeacher() {
		return teacher;
	}

}
