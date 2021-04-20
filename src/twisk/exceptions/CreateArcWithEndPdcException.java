package twisk.exceptions;

/**
 * La classe Create arc with end pdc exception.
 */
public class CreateArcWithEndPdcException extends TwiskException {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'on veut créer un arc à partir d'un point de control déjà utilisé pour créer un autre arc.
     *
     * @param message le message
     */
    public CreateArcWithEndPdcException(String message) {
        super(message);
    }
}
