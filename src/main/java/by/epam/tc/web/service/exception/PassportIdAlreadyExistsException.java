package by.epam.tc.web.service.exception;

public class PassportIdAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;

    public PassportIdAlreadyExistsException(String message, Exception e){
        super(message, e);
    }

    public PassportIdAlreadyExistsException(String message){
        super(message);
    }

    public PassportIdAlreadyExistsException(Exception e){
        super(e);
    }

    public PassportIdAlreadyExistsException(){
        super();
    }
}
