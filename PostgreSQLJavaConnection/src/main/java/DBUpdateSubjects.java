import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;

public class DBUpdateSubjects implements DBUpdateSubjectsInterface{
	
	private static final Logger logger = Logger.getLogger(DBUpdateSubjects.class.getName());
	
	private static final String url = "jdbc:postgresql://127.0.0.1:5432/school";
	private static final String user = "postgres";
	private static final String password = "2281488";

	private static Connection con;
	private static Statement stmt;
	 
	private String query = "";
	
	public void createSubject(Subject subject) {
		logger.info("Добавляем 1 предмет в БД");
		
		query = "insert into subject (subject, teacher) values ('" + subject.getSubject() +"', '" + subject.getTeacher() +"')";
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
	
	public void updateSubject() {
		logger.info("Изменяем 1 предмет в БД");
		Scanner sc = new Scanner(System.in);
	    System.out.println("Example");
	    System.out.println("UPDATE subject SET subject = 'Math' WHERE subject = 'Mathematics';");
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
	
	public void deleteSubject(Subject subject) {
		logger.info("Удаляем 1 предмет в БД");
		query = "DELETE FROM subject where subject = '" + subject.getSubject() + "'";
		try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println(query);
            try {
            	stmt.executeQuery(query);
            } catch (Exception e) {
            	
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
	}
}
