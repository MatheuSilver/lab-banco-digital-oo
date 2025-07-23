package exceptions;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(String mensagem) {
        super(mensagem);
    }
}