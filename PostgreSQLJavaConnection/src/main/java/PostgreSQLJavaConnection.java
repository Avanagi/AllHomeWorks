import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PostgreSQLJavaConnection {
	
	public static void main(String[] args) throws SQLException {
		//dbEditor.createStudent("Alexander", "Ramzanov", "Borisovich", 2);
		//dbEditor.createSubject("Chemistry", "Lopashov");
		//dbEditor.deleteStudent("Alexander");
		//dbEditor.deleteSubject("Chemistry");
		//dbUpdate.updateStudent();
		
		//ArrayList<Student> stl = new ArrayList<Student>();
		//Student st = new Student("Artem", "Dobryi", "Grob", 1);
		//stl.add(st);
		//st = new Student("Lodgf", "ngfh", "Grotttb", 3);
		//stl.add(st);
		//DBUpdateStudent dbUpdate = new DBUpdateStudent();
		//dbUpdate.createStudent(stl);
		
		
		DBInfo dbinfo = new DBInfo();
		dbinfo.showCurrStudentSubjects("GG");
		dbinfo.showCurrSubjectStudents("English");
	}
	
}
