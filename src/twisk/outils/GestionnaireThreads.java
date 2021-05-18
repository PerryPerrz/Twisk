package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class GestionnaireThreads {
    private final ArrayList<Thread> threads;

    private GestionnaireThreads() {
        threads = new ArrayList<>(10);
    }

    private static final GestionnaireThreads instance = new GestionnaireThreads();

    public static GestionnaireThreads getInstance() {
        return instance;
    }

    public void lancer(Task task) {
        Thread thread = new Thread(task);
        threads.add(thread);
        thread.start();
    }

    public void detruireTout() {
        for (Thread thread : threads)
            thread.interrupt();
    }
}
