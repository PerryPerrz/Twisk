package twisk.exceptions;

/**
 * La classe MondeException
 */
public class MondeException extends TwiskException {
    /**
     * Constructeur de l'exception se déclanchant lorsque le monde que l'on regarde n'est pas correct.
     *
     * @param message le message
     */
    public MondeException(String message) {
        super(message);
    }
}
