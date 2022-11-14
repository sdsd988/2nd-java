package test.apply.Exception;

public class AlreadySubmitException extends RuntimeException {

    public AlreadySubmitException() {
        super();
    }

    public AlreadySubmitException(String message) {
        super(message);
    }

    public AlreadySubmitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadySubmitException(Throwable cause) {
        super(cause);
    }
}
