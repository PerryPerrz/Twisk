package twisk.exceptions;

/**
 * La classe Wrong direction exception.
 */
public class WrongDirectionException extends TwiskException {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'on essaie d'ajouter un arc vers un guichet dans mauvais sens.
     *
     * @param message le message
     */
    public WrongDirectionException(String message) {
        super(message);
    }
}
