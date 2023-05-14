package Editor;

/**
 * Интерфейс, который реализует чтение данных студента
 */
public interface StudentDao {
	/**
	 * @param url адресс, по которому происходит коннектинг с БД
	 * @param user имя пользователя
	 * @param password пароль от БД
	 * Метод выдает всех студентов и их данные
	 */
	public void getStudents(String url, String user, String password);

	/**
	 * @param url адресс, по которому происходит коннектинг с БД
	 * @param user имя пользователя
	 * @param password пароль от БД
	 * @param name имя студента, чьи данные хотим получить
	 * Метод выдает данные конкретного студента
	 */
	public void getCurrStudentSubjects(String url, String user, String password, String name);
}
