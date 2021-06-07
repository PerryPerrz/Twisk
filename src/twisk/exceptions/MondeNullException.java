package twisk.exceptions;

/**
 * La classe MondeNullException
 */
public class MondeNullException extends TwiskException {
    /**
     * Constructeur de l'exception se déclenchant lorsque le monde que l'on récupère d'un fichier sauvegardé est null.
     *
     * @param message le message
     */
    public MondeNullException(String message) {
        super(message);
    }
}
