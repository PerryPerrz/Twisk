package twisk.exceptions;

/**
 * La classe CreateLoopException
 */
public class CreateLoopException extends TwiskException {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'on crée un arc qui crée un circuit dans le monde.
     *
     * @param message le message
     */
    public CreateLoopException(String message) {
        super(message);
    }
}
