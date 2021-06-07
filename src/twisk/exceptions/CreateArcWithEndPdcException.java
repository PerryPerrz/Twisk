package twisk.exceptions;

/**
 * La classe CreateArcWithEndPdcException
 */
public class CreateArcWithEndPdcException extends TwiskException {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'on veut créer un arc à partir d'un point de contrôle déjà utilisé pour créer un autre arc.
     *
     * @param message le message
     */
    public CreateArcWithEndPdcException(String message) {
        super(message);
    }
}
