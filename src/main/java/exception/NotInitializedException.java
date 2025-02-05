package exception;

public class NotInitializedException extends Exception{
    public NotInitializedException() {
        super();
    }

    public NotInitializedException(String message) {
        super(message);
    }
}
