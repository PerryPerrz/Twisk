package twisk.exceptions;

/**
 * La classe GuichetToGuichetException
 */
public class GuichetToGuichetException extends TwiskException {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'on essai faire un arc entre deux guichets.
     *
     * @param message le message
     */
    public GuichetToGuichetException(String message) {
        super(message);
    }
}
