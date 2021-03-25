package twisk;

import twisk.monde.Activite;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.simulation.Simulation;

public class ClientTwisk {

    public ClientTwisk() {
    }

    public Monde ConstruMonde1() {
        Monde monde = new Monde();
        Activite balade = new Activite("balade au zoo");
        Guichet guichet = new Guichet("guichet", 1);
        Activite toboggan = new Activite("toboggan");
        balade.ajouterSuccesseur(guichet);
        guichet.ajouterSuccesseur(toboggan);
        monde.aCommeEntree(balade);
        monde.aCommeSortie(toboggan);
        monde.ajouter(balade, guichet, toboggan);
        return monde;
    }

    public Monde ConstruMonde2() {
        Monde monde = new Monde();
        Activite balade = new Activite("balade au zoo");
        Activite parking = new Activite("parking");
        Guichet guichet2 = new Guichet("guichet", 2);
        Guichet guichet2500 = new Guichet("guichet", 2500);
        Activite toboggan = new Activite("toboggan");
        guichet2500.ajouterSuccesseur(parking);
        parking.ajouterSuccesseur(balade);
        balade.ajouterSuccesseur(guichet2);
        guichet2.ajouterSuccesseur(toboggan);
        monde.aCommeEntree(guichet2500, balade);
        monde.aCommeSortie(toboggan);
        monde.ajouter(guichet2500, parking, balade, guichet2, toboggan);
        return monde;
    }

    public static void main(String[] args) {
        //Création du monde
        Monde monde;
        ClientTwisk clientTwisk = new ClientTwisk();
        monde = clientTwisk.ConstruMonde1();

        //Début de la simulation
        Simulation simulation = new Simulation();
        simulation.simuler(monde);
        /*
        //Simulation n°2
        monde = clientTwisk.ConstruMonde2();
        simulation.simuler(monde);*/
    }
}
