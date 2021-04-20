package twisk.exceptions;

/**
 * La classe Same activity exception.
 */
public class SameActivityException extends TwiskException {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'on essai de créer un arc sur une même activité.
     *
     * @param message le message
     */
    public SameActivityException(String message) {
        super(message);
    }
}
