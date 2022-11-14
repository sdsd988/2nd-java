package test.apply.Exception;

public class AfterSubmitException extends RuntimeException {

    public AfterSubmitException() {
        super();
    }

    public AfterSubmitException(String message) {
        super(message);
    }

    public AfterSubmitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AfterSubmitException(Throwable cause) {
        super(cause);
    }
}
