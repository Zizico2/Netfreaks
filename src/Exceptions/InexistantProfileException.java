package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class InexistantProfileException extends RuntimeException{
    private static final String MESSAGE = "Profile does not exist.\n";
    public InexistantProfileException() {
        super(MESSAGE);
    }
}
