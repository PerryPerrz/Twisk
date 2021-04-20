package twisk.exceptions;

/**
 * La classe Uncorrect settings exception.
 */
public class UncorrectSettingsException extends Exception {
    /**
     * Constructeur de l'exception se déclanchant lorsque l'utilisateur saisie des paramètres incorrects (Délai et écart).
     *
     * @param message le message
     */
    public UncorrectSettingsException(String message) {
        super(message);
    }
}
