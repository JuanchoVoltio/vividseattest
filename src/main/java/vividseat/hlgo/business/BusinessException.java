package vividseat.hlgo.business;

public class BusinessException extends Throwable {
    public BusinessException(Throwable cause, String message) {
        super(message, cause);
    }
}
