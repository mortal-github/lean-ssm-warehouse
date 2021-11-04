package practice.ssm.warehouse.exception;

public class NotChangeException extends ServiceException {
    public NotChangeException() {
    }

    public NotChangeException(String message) {
        super(message);
    }
}
