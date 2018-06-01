package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class InexistantAccountException extends RuntimeException {
    private static final String MESSAGE = "Account does not exist.\n";
    public InexistantAccountException() {
        super(MESSAGE);
    }
}
