import java.util.ArrayList;

public interface DBUpdateStudentInterface {
	public void createStudent(Student student);
	public void createStudent(ArrayList<Student> stList);
	public void updateStudent();
	public void deleteStudent(Student student);
}
