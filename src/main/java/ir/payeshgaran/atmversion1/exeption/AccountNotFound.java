package ir.payeshgaran.atmversion1.exeption;

public class AccountNotFound extends RuntimeException {
    public AccountNotFound(String message) {
        super(message);
    }
}
