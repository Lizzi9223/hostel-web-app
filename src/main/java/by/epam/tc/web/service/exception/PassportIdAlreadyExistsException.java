package by.epam.tc.web.service.exception;

/**
 * Represents an exception that can occur if entered passport identifier already exists in the application
 * 
 * The class {@code LoginAlreadyExistsException} is a subclass of {@code Exception}
 * 
 * @author Lizzi9223
 *
 */
public class PassportIdAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public PassportIdAlreadyExistsException(String message, Exception e) {
		super(message, e);
	}

	public PassportIdAlreadyExistsException(String message) {
		super(message);
	}

	public PassportIdAlreadyExistsException(Exception e) {
		super(e);
	}

	public PassportIdAlreadyExistsException() {
		super();
	}
}
