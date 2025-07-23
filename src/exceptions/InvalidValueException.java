package exceptions;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String mensagem) {
        super(mensagem);
    }
}