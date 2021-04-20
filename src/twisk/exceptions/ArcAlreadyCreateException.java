package twisk.exceptions;

/**
 * La classe Arc already create exception.
 */
public class ArcAlreadyCreateException extends TwiskException {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'on essai de créer un arc déjà créer.
     *
     * @param message le message
     */
    public ArcAlreadyCreateException(String message) {
        super(message);
    }
}
