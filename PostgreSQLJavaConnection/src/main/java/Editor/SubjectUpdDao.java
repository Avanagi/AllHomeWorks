package Editor;
import Objects.*;

public interface SubjectUpdDao {
	public void createSubject(String url, String user, String password, Subject subject);
	public void updateSubject(String url, String user, String password);
	public void deleteSubject(String url, String user, String password, Subject subject);
}
