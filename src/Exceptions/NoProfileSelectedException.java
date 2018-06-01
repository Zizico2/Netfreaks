package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class NoProfileSelectedException extends RuntimeException{
    private static final String MESSAGE = "No profile is selected.";
    public NoProfileSelectedException() {
        super(MESSAGE);
    }
}
