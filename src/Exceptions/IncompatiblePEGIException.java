package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class IncompatiblePEGIException extends RuntimeException{
    private static final String MESSAGE = "Show not available.\n";
    public IncompatiblePEGIException() {
        super(MESSAGE);
    }
}
