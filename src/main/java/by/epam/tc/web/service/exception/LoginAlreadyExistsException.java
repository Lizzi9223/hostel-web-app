package by.epam.tc.web.service.exception;

public class LoginAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;

    public LoginAlreadyExistsException(String message, Exception e){
        super(message, e);
    }

    public LoginAlreadyExistsException(String message){
        super(message);
    }

    public LoginAlreadyExistsException(Exception e){
        super(e);
    }

    public LoginAlreadyExistsException(){
        super();
    }
}
