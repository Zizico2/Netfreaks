package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class SameMembershipException extends RuntimeException {
    private static final String MESSAGE = "No membership plan change.\n";
    public SameMembershipException() {
        super(MESSAGE);
    }
}
