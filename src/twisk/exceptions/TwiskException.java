package twisk.exceptions;

/**
 * La classe TwiskException.
 */
public abstract class TwiskException extends Exception {
    /**
     * Constructeur de la classe abstraite des exceptions.
     *
     * @param message le message
     */
    public TwiskException(String message) {
        super(message);
    }
}
