package twisk.exceptions;

/**
 * La classe URLIncorrectException
 */
public class URLIncorrectException extends TwiskException {
    /**
     * Constructeur de l'exception se déclenchant lorsque le dossier de sauvegarde temporaire n'existe pas.
     *
     * @param message le message
     */
    public URLIncorrectException(String message) {
        super(message);
    }
}
