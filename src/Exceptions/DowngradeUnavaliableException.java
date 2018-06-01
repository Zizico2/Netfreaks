package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class DowngradeUnavaliableException extends RuntimeException{

    private static final String MESSAGE = "Cannot downgrade membership plan at the moment.";
    public DowngradeUnavaliableException() {
        super(MESSAGE);
    }
}
