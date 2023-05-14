package Editor;

/**
 * Интерфейс, реализующий методы для работы с чтением данных предметов
 */
public interface SubjectDao  {
	/**
	 * @param url адресс, по которому происходит коннектинг с БД
	 * @param user имя пользователя
	 * @param password пароль от БД
	 * метод выдает данные о всех предметах
	 */
	public void getSubjects(String url, String user, String password);

	/**
	 * @param url адресс, по которому происходит коннектинг с БД
	 * @param user имя пользователя
	 * @param password пароль от БД
	 * @param subject предмет, по которому получаем данных
	 * Метод выдает данные о студентах, у кого есть введенный предмет
	 */
	public void getCurrSubjectStudents(String url, String user, String password, String subject);
}
