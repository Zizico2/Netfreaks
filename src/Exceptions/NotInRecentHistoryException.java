package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class NotInRecentHistoryException extends RuntimeException{
    private static final String MESSAGE = "Can only rate recently seen shows.\n";
    public NotInRecentHistoryException() {
        super(MESSAGE);
    }
}
