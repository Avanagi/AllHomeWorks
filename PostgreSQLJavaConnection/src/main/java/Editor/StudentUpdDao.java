package Editor;
import Objects.*;
import java.util.ArrayList;

public interface StudentUpdDao {
	public void createStudent(String url, String user, String password, Student student);
	public void createStudent(String url, String user, String password, ArrayList<Student> stList);
	public void updateStudent(String url, String user, String password);
	public void deleteStudent(String url, String user, String password, Student student);
}
