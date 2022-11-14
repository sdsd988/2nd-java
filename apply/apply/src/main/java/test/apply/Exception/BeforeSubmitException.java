package test.apply.Exception;

public class BeforeSubmitException extends RuntimeException {

    public BeforeSubmitException() {
        super();
    }

    public BeforeSubmitException(String message) {
        super(message);
    }

    public BeforeSubmitException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeforeSubmitException(Throwable cause) {
        super(cause);
    }
}
