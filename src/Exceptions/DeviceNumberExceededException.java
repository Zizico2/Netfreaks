package Exceptions;

/**
 *
 * @author Bernardo Borda d'Agua    53648
 * @author Tiago Guerreiro          53649
 *
 */
public class DeviceNumberExceededException  extends RuntimeException{

    private static final String MESSAGE = "Not possible to connect more devices.\n";
    public DeviceNumberExceededException() {
        super(MESSAGE);
    }
}
