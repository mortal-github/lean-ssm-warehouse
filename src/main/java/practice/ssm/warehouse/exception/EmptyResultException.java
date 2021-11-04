package practice.ssm.warehouse.exception;

public class EmptyResultException extends ServiceException {
    public EmptyResultException() {
    }

    public EmptyResultException(String message) {
        super(message);
    }
}
