package Editor;

public interface StudentDao {
	public void getStudents(String url, String user, String password);
	public void getCurrStudentSubjects(String url, String user, String password, String name);
}
