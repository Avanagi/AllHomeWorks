package Editor;

public interface SubjectDao  {
	public void getSubjects(String url, String user, String password);
	public void getCurrSubjectStudents(String url, String user, String password, String subject);
}
