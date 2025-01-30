/**
 * Exceção não verificada de quantidade de caixas maior que a suportada.
 */
public class TooManyATMsException extends RuntimeException {
    public TooManyATMsException(String errorMessage) {
        super(errorMessage);
    }
}
