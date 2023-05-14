package Editor;

import Objects.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Класс, реализующий чтение предмета
 */
public class SubjectDaoImpl implements SubjectDao{
	
	private static final Logger logger = Logger.getLogger(SubjectDaoImpl.class.getName());

    private Connection con;
    private Statement stmt;
    private ResultSet rs;
	
	public void getSubjects(String url, String user, String password) {
		logger.info("Начинаем вывод всех предметов");
		
		String query = "select subject, teacher from subject";
		
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            ArrayList<Subject> subArr = new ArrayList<>();
            
            while (rs.next()) {
            	Subject sub = new Subject(rs.getString(1), rs.getString(2));
            	subArr.add(sub);
            }
            
            for(Subject sub : subArr) {
            	System.out.println(sub.getSubject() + " " + sub.getTeacher());
            }
            
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
        	try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}
	
	public void getCurrSubjectStudents(String url, String user, String password, String subject) {
		logger.info("Начинаем вывод предмета и студентов, которые обучаются ему");
		
		String query = "select st.name, st.surname, sub.teacher\r\n"
				+ "	from students st\r\n"
				+ "	inner join subject sub on sub.subject = '"+ subject +"'\r\n"
				+ "	group by st.name, st.surname, sub.teacher";
		
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            ArrayList<Student> subArr = new ArrayList<>();
            
            while (rs.next()) {
            	Student sub = new Student(rs.getString(1), rs.getString(2), rs.getString(3));
            	subArr.add(sub);
            }
            for(Student sub : subArr) {
            	System.out.println(sub.getName() + " " + sub.getTeacher());
            }
            
            
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
        	try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}
}
