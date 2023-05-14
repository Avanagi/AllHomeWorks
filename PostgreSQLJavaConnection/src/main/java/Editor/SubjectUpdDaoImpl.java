package Editor;
import java.sql.Connection;
import Objects.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;

public class SubjectUpdDaoImpl implements SubjectUpdDao{
	
	private static final Logger logger = Logger.getLogger(SubjectUpdDaoImpl.class.getName());

	private Connection con;
	private Statement stmt;
	
	public void createSubject(String url, String user, String password, Subject subject) {
		logger.info("Добавляем 1 предмет в БД");
		
		String query = "insert into subject (subject, teacher) values ('" + subject.getSubject() +"', '" + subject.getTeacher() +"')";
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
	}
	
	public void updateSubject(String url, String user, String password) {
		logger.info("Изменяем 1 предмет в БД");
		Scanner sc = new Scanner(System.in);
	    System.out.println("Example");
	    System.out.println("UPDATE subject SET subject = 'Math' WHERE subject = 'Mathematics';");
	    String query = sc.nextLine();
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt.executeQuery(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
	}
	
	public void deleteSubject(String url, String user, String password, Subject subject) {
		logger.info("Удаляем 1 предмет в БД");
		String query = "DELETE FROM subject where subject = '" + subject.getSubject() + "'";
		try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println(query);
            stmt.executeQuery(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
	}
}
