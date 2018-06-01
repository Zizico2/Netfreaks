package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class InexistantProductException extends RuntimeException{
    private static final String MESSAGE = "Show does not exist.\n";
    public InexistantProductException() {
        super(MESSAGE);
    }
}
