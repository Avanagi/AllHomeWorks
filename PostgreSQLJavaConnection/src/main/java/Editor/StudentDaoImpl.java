package Editor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import Objects.*;

public class StudentDaoImpl implements StudentDao{
	
	private static final Logger logger = Logger.getLogger(StudentDaoImpl.class.getName());

    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    	
	public void getStudents(String url, String user, String password) {
		logger.info("Начинаем вывод всех студентов");
		String query = "select name, surname, patronymic, subject_id from students";
		
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            ArrayList<Student> subArr = new ArrayList<Student>();
 
            while (rs.next()) {
            	Student sub = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            	subArr.add(sub);
            }
            
            for(Student sub : subArr) {
            	System.out.println(sub.getName() + " " + sub.getSurname() + " " + sub.getPatronymic() + " " + sub.getSubject_id());
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
	
	
	
	public void getCurrStudentSubjects(String url, String user, String password, String name) {
		logger.info("Начинаем вывод студента и предметов, которые у него есть");
		String query = "select st.name, st.surname, sub.subject, sub.teacher\r\n"
				+ "from students st\r\n"
				+ "inner join subject sub on st.name = '" + name + "'\r\n"
				+ "group by st.name,st.surname, sub.subject, sub.teacher";
		
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            ArrayList<Student> subArr = new ArrayList<Student>();
            
            while (rs.next()) {
            	Student sub = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            	subArr.add(sub);
            }
            
            for(Student sub : subArr) {
            	System.out.println(sub.getName() + " " + sub.getSurname() + " " + sub.getSubject() + " " + sub.getTeacher());
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
