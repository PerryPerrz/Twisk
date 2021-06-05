package twisk;

import twisk.monde.Activite;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.GestionnaireThreads;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * La classe ClientTwisk.
 */
public class ClientTwisk {

    /**
     * Instancie un nouveau ClientTwisk.
     */
    public ClientTwisk() {
    }

    /**
     * Construit un monde simple.
     *
     * @return le monde
     */
    public Monde ConstruMonde1() {
        Monde monde = new Monde();
        Activite balade = new Activite("balade au zoo");
        Guichet guichet = new Guichet("guichet", 1);
        Activite toboggan = new ActiviteRestreinte("toboggan");
        balade.ajouterSuccesseur(guichet);
        guichet.ajouterSuccesseur(toboggan);
        monde.aCommeEntree(balade, guichet);
        monde.aCommeSortie(toboggan);
        monde.ajouter(balade, guichet, toboggan);
        return monde;
    }


    /**
     * Constru monde 2 monde.
     *
     * @return le monde
     */
    public Monde ConstruMonde2() {
        Monde monde = new Monde();
        Activite balade = new Activite("balade au zoo");
        Activite parking = new ActiviteRestreinte("parking");
        Guichet guichet2 = new Guichet("guichet2", 2);
        Guichet guichet3 = new Guichet("guichet3", 3);
        Activite toboggan = new ActiviteRestreinte("toboggan");
        guichet3.ajouterSuccesseur(parking);
        parking.ajouterSuccesseur(balade, guichet2);
        balade.ajouterSuccesseur(guichet2);
        guichet2.ajouterSuccesseur(toboggan);
        monde.aCommeEntree(guichet3);
        monde.aCommeSortie(toboggan, balade);
        monde.ajouter(guichet3, parking, balade, guichet2, toboggan);
        return monde;
    }

    /**
     * Le point d'entrée de l'application.
     *
     * @param args les arguments d'entrée
     */
    public static void main(String[] args) {
        //Création du monde
        Monde monde;
        ClientTwisk clientTwisk = new ClientTwisk();
        monde = clientTwisk.ConstruMonde1();

        //Début de la simulation du premier monde
        ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
        try {
            Class<?> clS = classLoaderPerso.loadClass("twisk.simulation.Simulation");
            Constructor<?> constructeur = clS.getDeclaredConstructor();
            Object instanceSim = constructeur.newInstance();
            Method fonctionSetNbClients = clS.getMethod("setNbClients", int.class);
            fonctionSetNbClients.invoke(instanceSim, 5);
            Method fonctionSimuler = clS.getMethod("simuler", Monde.class);
            fonctionSimuler.invoke(instanceSim, monde);
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la classe twisk.simulation.Simulation !");
        } catch (NoSuchMethodException e) {
            System.out.println("Erreur, fonction non trouvée dans la classe twisk.simulation.Simulation !");
        } catch (InvocationTargetException e) {
            System.out.println("Erreur lors de l'appel d'une fonction de la classe twisk.simulation.Simulation !");
        } catch (IllegalAccessException e) {
            System.out.println("Erreur, appel illegal d'une fonction de la classe twisk.simulation.Simulation !");
        } catch (InstantiationException e) {
            System.out.println("Erreur lors de l'instanciation de la classe twisk.simulation.Simulation !");
        }

        //Simulation n°2
        monde = clientTwisk.ConstruMonde2();

        //Début de la simulation du second monde
        classLoaderPerso = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
        try {
            Class<?> clS = classLoaderPerso.loadClass("twisk.simulation.Simulation");
            Constructor<?> constructeur = clS.getDeclaredConstructor();
            Object instanceSim = constructeur.newInstance();
            Method fonctionSetNbClients = clS.getMethod("setNbClients", int.class);
            fonctionSetNbClients.invoke(instanceSim, 10);
            Method fonctionSimuler = clS.getMethod("simuler", Monde.class);
            fonctionSimuler.invoke(instanceSim, monde);
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la classe twisk.simulation.Simulation !");
        } catch (NoSuchMethodException e) {
            System.out.println("Erreur, fonction non trouvée dans la classe twisk.simulation.Simulation !");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            //System.out.println("Erreur lors de l'appel d'une fonction de la classe twisk.simulation.Simulation !");
        } catch (IllegalAccessException e) {
            System.out.println("Erreur, appel illegal d'une fonction de la classe twisk.simulation.Simulation !");
        } catch (InstantiationException e) {
            System.out.println("Erreur lors de l'instanciation de la classe twisk.simulation.Simulation !");
        }
        GestionnaireThreads.getInstance().detruireTout();
    }
}
