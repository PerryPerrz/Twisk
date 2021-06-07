package twisk.exceptions;

/**
 * La classe PasUnGuichetException
 */
public class PasUnGuichetException extends TwiskException {
    /**
     * Constructeur de l'exception se déclenchant lorsque l'on essaie de changer un paramètre de guichet sur une activité.
     *
     * @param message le message
     */
    public PasUnGuichetException(String message) {
        super(message);
    }
}
