package Editor;
import Objects.*;

/**
 * Интерфейс, реализующий методы для работы с предметами
 */
public interface SubjectUpdDao {
	/**
	 * @param url адресс, по которому происходит коннектинг с БД
	 * @param user имя пользователя
	 * @param password пароль от БД
	 * @param subject объект, хранящий данные предмета
	 * Метод создает предмет
	 */
	public void createSubject(String url, String user, String password, Subject subject);

	/**
	 * @param url адресс, по которому происходит коннектинг с БД
	 * @param user имя пользователя
	 * @param password пароль от БД
	 * Метод изменяет данные о предмете
	 */
	public void updateSubject(String url, String user, String password);

	/**
	 * @param url адресс, по которому происходит коннектинг с БД
	 * @param user имя пользователя
	 * @param password пароль от БД
	 * @param subject название предмета для удаления
	 * Метод удаляет данные о предмете
	 */
	public void deleteSubject(String url, String user, String password, Subject subject);
}
