package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class ProfileNumberExceededException extends RuntimeException{
    private static final String MESSAGE ="Not possible to add more profiles.\n";
    public ProfileNumberExceededException() {
        super(MESSAGE);
    }
}
