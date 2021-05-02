package twisk.outils;

import twisk.monde.Etape;
import twisk.mondeIG.EtapeIG;

import java.util.HashMap;

public class CorrespondanceEtapes {
    private final HashMap<EtapeIG, Etape> correspondances;

    public CorrespondanceEtapes() {
        correspondances = new HashMap<>(10);
    }

    public void ajouter(EtapeIG etig, Etape et) {
        correspondances.put(etig, et);
    }

    public Etape get(EtapeIG e) {
        return correspondances.get(e);
    }
}
