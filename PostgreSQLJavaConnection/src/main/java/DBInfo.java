import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DBInfo implements DBInfoInterface{
	
	private static final Logger logger = Logger.getLogger(DBInfo.class.getName());
	
	private static final String url = "jdbc:postgresql://127.0.0.1:5432/school";
    private static final String user = "postgres";
    private static final String password = "2281488";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    
    private String query = "";
	
	public void showStudents() {
		logger.info("Начинаем вывод всех студентов");
		query = "select * from students";
		
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            
            while (rs.next()) {
            	System.out.println(rs.getString(1)
	            + " " + rs.getString(2) 
	            + " " + rs.getString(3) 
	            + " " + rs.getString(4) 
	            + " " + rs.getString(5) );
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
	
	public void showSubjects() {
		logger.info("Начинаем вывод всех предметов");
		query = "select * from subject";
		
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            
            while (rs.next()) {
            	System.out.println(rs.getString(1)
	            + " " + rs.getString(2) 
	            + " " + rs.getString(3) );
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
	
	public void showCurrStudentSubjects(String name) {
		logger.info("Начинаем вывод студента и предметов, которые у него есть");
		query = "select st.name, st.surname, sub.subject, sub.teacher\r\n"
				+ "from students st\r\n"
				+ "inner join subject sub on st.name = '" + name + "'\r\n"
				+ "group by st.name,st.surname, sub.subject, sub.teacher";
		
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            System.out.println("Name "
    	            + " Surname " 
    	            + " Subject "
    	            + " Teacher ");
            
            while (rs.next()) {
            	System.out.println(rs.getString(1)
	            + " " + rs.getString(2) 
	            + " " + rs.getString(3) 
	            + " " + rs.getString(4) );
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
	
	public void showCurrSubjectStudents(String subject) {
		logger.info("Начинаем вывод предмета и студентов, которые обучаются ему");
		query = "select st.name, sub.subject, sub.teacher\r\n"
				+ "	from students st\r\n"
				+ "	inner join subject sub on sub.subject = '"+ subject +"'\r\n"
				+ "	group by st.name, sub.subject, sub.teacher";
		
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            System.out.println("Name "
    	            + " Subject "
    	            + " Teacher ");
            
            while (rs.next()) {
            	System.out.println(rs.getString(1)
	            + " " + rs.getString(2) 
	            + " " + rs.getString(3) );
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
	
	public void showStudentsAndSubject() {
		logger.info("Начинаем вывод всех студентов и предметов");
		query = "select st.name, st.surname, st.patronymic, sub.subject, sub.teacher\r\n"
		 		+ "from students st \r\n"
		 		+ "left join subject sub on st.subject_id = sub.id\r\n"
		 		+ "group by st.name,st.surname, st.patronymic, sub.subject, sub.teacher";
		
		try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            System.out.println("Name "
    	            + " Surname "
    	            + " Patronymic " 
    	            + " Subject "
    	            + " Teacher ");
            
            while (rs.next()) {
            	System.out.println(rs.getString(1)
	            + " " + rs.getString(2) 
	            + " " + rs.getString(3) 
	            + " " + rs.getString(4) 
	            + " " + rs.getString(5) );
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
