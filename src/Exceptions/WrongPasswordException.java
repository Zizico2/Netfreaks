package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class WrongPasswordException extends RuntimeException{
    private static final String MESSAGE = "Wrong password.\n";
    public WrongPasswordException() {
        super(MESSAGE);
    }
}
