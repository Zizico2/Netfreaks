package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class ProductNotFoundException extends RuntimeException{
    private static final String MESSAGE = "No show found.\n";
    public ProductNotFoundException() {
        super(MESSAGE);
    }
}
