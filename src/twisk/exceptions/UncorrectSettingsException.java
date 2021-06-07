package twisk.exceptions;

/**
 * La classe UncorrectSettingsException
 */
public class UncorrectSettingsException extends Exception {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'utilisateur saisi des paramètres incorrects (Délai et écart).
     *
     * @param message le message
     */
    public UncorrectSettingsException(String message) {
        super(message);
    }
}
