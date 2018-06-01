package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class NoAccountLoggedInException extends RuntimeException{
    private static final String MESSAGE = "No client is logged in.\n";
    public NoAccountLoggedInException() {
        super(MESSAGE);
    }
}
