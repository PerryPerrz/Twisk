package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

/**
 * The type Gestionnaire threads.
 */
public class GestionnaireThreads {
    private final ArrayList<Thread> threads;

    private GestionnaireThreads() {
        threads = new ArrayList<>(10);
    }

    private static final GestionnaireThreads instance = new GestionnaireThreads();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static GestionnaireThreads getInstance() {
        return instance;
    }

    /**
     * Fonction qui lance un nouveau Thread à partir d'une Task et qui stocke ce Thread dans le gestionnaire
     *
     * @param task La Task à lancer
     * @return l 'indice du Thread pour pouvoir le réutiliser plus tard
     */
    public int lancer(Task task) {
        Thread thread = new Thread(task);
        threads.add(thread);
        thread.start();
        //On stocke l'indice pour pouvoir réutiliser le Thread lors de l'arrêt de la simulation
        return threads.indexOf(thread);
    }

    /**
     * Procédure qui détruit tous les Threads crées auparavant.
     */
    public void detruireTout() {
        for (Thread thread : threads)
            thread.interrupt();
        threads.clear();
    }
}
