package twisk.exceptions;

/**
 * La classe ChargementSauvegardeException.
 */
public class ChargementSauvegardeException extends TwiskException {
    /**
     * Constructeur de l'exception se déclenchant lorsque le chargement d'une sauvegarde de monde échoue.
     *
     * @param message le message
     */
    public ChargementSauvegardeException(String message) {
        super(message);
    }
}
