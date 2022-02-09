package by.epam.tc.web.dao;

/**
 * Represents an exception that can occur on application's DAO layer 
 * 
 * The class {@code DAOException} is a subclass of {@code Exception}
 * 
 * @author Lizzi9223
 *
 */
public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;

	public DAOException(String message, Exception e) {
		super(message, e);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException() {
		super();
	}
}
