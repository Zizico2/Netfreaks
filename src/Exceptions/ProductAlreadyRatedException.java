package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class ProductAlreadyRatedException extends RuntimeException{
    private static final String MESSAGE = "Show already rated.\n";
    public ProductAlreadyRatedException() {
        super(MESSAGE);
    }
}
