package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class SameProfileNameException extends RuntimeException{
    private static final String MESSAGE = "There is already a profile ";
    public SameProfileNameException() {
        super(MESSAGE);
    }
}
