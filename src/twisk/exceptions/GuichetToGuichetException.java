package twisk.exceptions;

/**
 * La classe Guichet to guichet exception.
 */
public class GuichetToGuichetException extends TwiskException {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'on essaie faire un arc entre deux guichets.
     *
     * @param message le message
     */
    public GuichetToGuichetException(String message) {
        super(message);
    }
}
