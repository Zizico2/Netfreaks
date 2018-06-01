package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class SameEmailException extends RuntimeException {
    private static final String MESSAGE = "There is another account with email ";
    public SameEmailException() {
        super(MESSAGE);
    }
}
