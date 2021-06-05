package twisk.outils;

import twisk.monde.Etape;
import twisk.mondeIG.EtapeIG;

import java.util.HashMap;

/**
 * The type Correspondance etapes.
 */
public class CorrespondanceEtapes {
    private final HashMap<EtapeIG, Etape> correspondances;

    /**
     * Instantiates a new Correspondance etapes.
     */
    public CorrespondanceEtapes() {
        correspondances = new HashMap<>(10);
    }

    /**
     * Ajouter.
     *
     * @param etig the etig
     * @param et   the et
     */
    public void ajouter(EtapeIG etig, Etape et) {
        correspondances.put(etig, et);
    }

    /**
     * Get etape.
     *
     * @param e the e
     * @return the etape
     */
    public Etape get(EtapeIG e) {
        return correspondances.get(e);
    }

    /**
     * Get etape ig.
     *
     * @param e the e
     * @return the etape ig
     */
    public EtapeIG get(Etape e) {
        EtapeIG eig = null;
        for (EtapeIG eIG : this.correspondances.keySet()) {
            if (this.correspondances.get(eIG) == e) {
                eig = eIG;
            }
        }
        return eig;
    }
}
