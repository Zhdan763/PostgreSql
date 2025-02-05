package exception;

public class ControllerException extends Exception  {
    public ControllerException() {
        super();
    }

    public ControllerException(String message) {
        super(message);
    }
}
