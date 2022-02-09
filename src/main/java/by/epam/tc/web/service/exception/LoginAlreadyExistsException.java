package by.epam.tc.web.service.exception;

/**
 * Represents an exception that can occur if entered login already exists in the application
 * 
 * The class {@code LoginAlreadyExistsException} is a subclass of {@code Exception}
 * 
 * @author Lizzi9223
 *
 */
public class LoginAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public LoginAlreadyExistsException(String message, Exception e) {
		super(message, e);
	}

	public LoginAlreadyExistsException(String message) {
		super(message);
	}

	public LoginAlreadyExistsException(Exception e) {
		super(e);
	}

	public LoginAlreadyExistsException() {
		super();
	}
}
