package twisk.exceptions;

/**
 * La classe FichierNullException
 */
public class FichierNullException extends TwiskException {
    /**
     * Constructeur de l'exception se déclenchant lorsque la liste de fichiers de sauvegarde est nulle.
     *
     * @param message le message
     */
    public FichierNullException(String message) {
        super(message);
    }
}
