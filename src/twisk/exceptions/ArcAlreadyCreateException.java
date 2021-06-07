package twisk.exceptions;

/**
 * La classe ArcAlreadyCreateException
 */
public class ArcAlreadyCreateException extends TwiskException {
    /**
     * Constructeur de l'exception se déclenchant lorsque l'on essai de créer un arc déjà créé.
     *
     * @param message le message
     */
    public ArcAlreadyCreateException(String message) {
        super(message);
    }
}
