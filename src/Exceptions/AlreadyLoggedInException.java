package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class AlreadyLoggedInException extends RuntimeException{
    private static final String MESSAGE = "Client already logged in.\n";
    public AlreadyLoggedInException() {
        super(MESSAGE);
    }
}
