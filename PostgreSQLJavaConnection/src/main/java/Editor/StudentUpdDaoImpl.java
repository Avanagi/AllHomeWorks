package Editor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;
import Objects.*;

public class StudentUpdDaoImpl implements StudentUpdDao{
	
	private static final Logger logger = Logger.getLogger(StudentUpdDaoImpl.class.getName());
	
    private Connection con;
    private Statement stmt;
    
	public void createStudent(String url, String user, String password, Student student){
		logger.info("Добавляем 1 студента в БД");
		String query = "insert into students (name, surname, patronymic, subject_id) values ('" + student.getName() +
				"', '" + student.getSurname() +"', '" + student.getPatronymic() +"', " + student.getSubject_id() +")";
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
	}
	
	public void createStudent(String url, String user, String password, ArrayList<Student> stList) {
		logger.info("Добавляем список студентов в БД");
		for(Student student : stList) {
			String query = "insert into students (name, surname, patronymic, subject_id) values ('" + student.getName() +
					"', '" + student.getSurname() +"', '" + student.getPatronymic() +"', " + student.getSubject_id() +")";
			try {
	            con = DriverManager.getConnection(url, user, password);
	            stmt = con.createStatement();
	            stmt.executeQuery(query);
	        } catch (SQLException sqlEx) {
	            sqlEx.printStackTrace();
	        }
		}
	}
	
	
	public void updateStudent(String url, String user, String password) {
		logger.info("Изменяем 1 студента в БД");
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Example");
	    System.out.println("UPDATE students SET subject_id = '4' WHERE subject_id = '1' and name = 'Roman';");
	    String query = sc.nextLine();
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt.executeQuery(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
	}
	
	public void deleteStudent(String url, String user, String password, Student student) {
		logger.info("Удаляем 1 студента в БД");
		String query = "DELETE FROM students where name = '" + student.getName() + "'";
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt.executeQuery(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
	}

}
