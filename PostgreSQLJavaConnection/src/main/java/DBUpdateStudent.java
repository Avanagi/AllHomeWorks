import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;

public class DBUpdateStudent implements DBUpdateStudentInterface{
	
	private static final Logger logger = Logger.getLogger(DBUpdateStudent.class.getName());
	
    private static final String url = "jdbc:postgresql://127.0.0.1:5432/school";
    private static final String user = "postgres";
    private static final String password = "2281488";

    private static Connection con;
    private static Statement stmt;
    
    private String query = "";

	public void createStudent(Student student){
		logger.info("Добавляем 1 студента в БД");
		query = "insert into students (name, surname, patronymic, subject_id) values ('" + student.getName() +
				"', '" + student.getSurname() +"', '" + student.getPatronymic() +"', " + student.getSubject_id() +")";
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            try {
            	stmt.executeQuery(query);
            } catch (Exception e) {
            	
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
	}
	
	public void createStudent(ArrayList<Student> stList) {
		logger.info("Добавляем список студентов в БД");
		for(Student student : stList) {
			query = "insert into students (name, surname, patronymic, subject_id) values ('" + student.getName() +
					"', '" + student.getSurname() +"', '" + student.getPatronymic() +"', " + student.getSubject_id() +")";
			try {
	            con = DriverManager.getConnection(url, user, password);
	            stmt = con.createStatement();
	            try {
	            	stmt.executeQuery(query);
	            } catch (Exception e) {
	            	
	            }
	        } catch (SQLException sqlEx) {
	            sqlEx.printStackTrace();
	        }
		}
	}
	
	
	public void updateStudent() {
		logger.info("Изменяем 1 студента в БД");
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Example");
	    System.out.println("UPDATE students SET subject_id = '4' WHERE subject_id = '1' and name = 'Roman';");
	    query = sc.nextLine();
		try {
            con = DriverManager.getConnection(url, user, password);
            try {
            	stmt.executeQuery(query);
            } catch (Exception e) {
            	
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
	}
	
	public void deleteStudent(Student student) {
		logger.info("Удаляем 1 студента в БД");
		query = "DELETE FROM students where name = '" + student.getName() + "'";
		try {
            con = DriverManager.getConnection(url, user, password);
            try {
            	stmt.executeQuery(query);
            } catch (Exception e) {
            	
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
	}

}
