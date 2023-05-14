package Editor;
import Objects.*;
import java.util.ArrayList;

/**
 * Интерфейс, реализующий методы для работы со студентами
 */
public interface StudentUpdDao {
	/**
	 * @param url адресс, по которому происходит коннектинг с БД
	 * @param user имя пользователя
	 * @param password пароль от БД
	 * @param student объект, хранящий в себе данные студента
	 * Метод создает студента
	 */
	public void createStudent(String url, String user, String password, Student student);

	/**
	 * @param url адресс, по которому происходит коннектинг с БД
	 * @param user имя пользователя
	 * @param password пароль от БД
	 * @param stList список, в котором хранятся студенты
	 * Метод создает сразу несколько студентов
	 */
	public void createStudent(String url, String user, String password, ArrayList<Student> stList);

	/**
	 * @param url адресс, по которому происходит коннектинг с БД
	 * @param user имя пользователя
	 * @param password пароль от БД
	 * Метод изменяет данные студента
	 */
	public void updateStudent(String url, String user, String password);

	/**
	 * @param url адресс, по которому происходит коннектинг с БД
	 * @param user имя пользователя
	 * @param password пароль от БД
	 * @param student
	 * Метод удаляет студента
	 */
	public void deleteStudent(String url, String user, String password, Student student);
}
