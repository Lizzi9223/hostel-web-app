package by.epam.tc.web.dao.database.connection_pool;

/**
 * Represents an exception that can occur in connection pool
 * 
 * The class {@code ConnectionPoolException} is a subclass of {@code Exception}
 * 
 * @author Lizzi9223
 *
 */
public class ConnectionPoolException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}

	public ConnectionPoolException() {
		super();
	}
}
