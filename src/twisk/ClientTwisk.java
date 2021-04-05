package twisk;

import twisk.monde.Activite;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.simulation.Simulation;

/**
 * The type Client twisk.
 */
public class ClientTwisk {

    /**
     * Instantiates a new Client twisk.
     */
    public ClientTwisk() {
    }

    /**
     * Constru monde 1 monde.
     *
     * @return the monde
     */
    public Monde ConstruMonde1() {
        Monde monde = new Monde();
        Activite balade = new Activite("balade au zoo");
        Guichet guichet = new Guichet("guichet", 1);
        Activite toboggan = new ActiviteRestreinte("toboggan");
        balade.ajouterSuccesseur(guichet);
        guichet.ajouterSuccesseur(toboggan);
        monde.aCommeEntree(balade);
        monde.aCommeSortie(toboggan);
        monde.ajouter(balade, guichet, toboggan);
        return monde;
    }

    /*A décommenter lorsque l'implémentation des multiples successeurs à une même Activité est codée
    public Monde ConstruMonde2() {
        Monde monde = new Monde();
        Activite balade = new Activite("balade au zoo");
        Activite parking = new ActiviteRestreinte("parking");
        Guichet guichet2 = new Guichet("guichet2", 2);
        Guichet guichet2500 = new Guichet("guichet2500", 2500);
        Activite toboggan = new ActiviteRestreinte("toboggan");
        guichet2500.ajouterSuccesseur(parking);
        parking.ajouterSuccesseur(balade);
        balade.ajouterSuccesseur(guichet2);
        guichet2.ajouterSuccesseur(toboggan);
        monde.aCommeEntree(guichet2500, balade);
        monde.aCommeSortie(toboggan);
        monde.ajouter(guichet2500, parking, balade, guichet2, toboggan);
        return monde;
    }*/

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //Création du monde
        Monde monde;
        ClientTwisk clientTwisk = new ClientTwisk();
        monde = clientTwisk.ConstruMonde1();

        //Début de la simulation
        Simulation simulation = new Simulation();
        simulation.setNbClients(5);
        simulation.simuler(monde);

        /* A décommenter lorsque l'implémentation des multiples successeurs à une même Activité est codée
        //Simulation n°2
        monde = clientTwisk.ConstruMonde2();
        simulation.setNbClients(5);
        simulation.simuler(monde);
        */
    }
}
