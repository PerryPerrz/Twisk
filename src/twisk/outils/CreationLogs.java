package twisk.outils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreationLogs {

    private CreationLogs() {
    }

    private static final CreationLogs instance = new CreationLogs();

    /**
     * Fonction qui donne une instance de OutilsSerializable.
     *
     * @return l'instance
     */
    public static CreationLogs getInstance() {
        return instance;
    }

    /**
     * Procédure qui crée le dossier contenant les fichiers de sauvegarde temporaires.
     *
     * @throws IOException Erreur envoyée si la création du dossier n'est pas réussie.
     */
    public void initializelogs() throws IOException {
        Files.createDirectories(Paths.get("/tmp/twisk/logs"));
    }

    /**
     * Procédure qui écris du texte dans un fichier log créé dans le dossier temporaire.
     *
     * @param numLog  Le numéro du log sur lequel on écrit
     * @param contenu Le contenu à écrire
     */
    public void ecrireContenuDansLog(int numLog, String contenu) {
        try {
            // création du répertoire logs sous /tmp/twisk. Ne déclenche pas d’erreur si le répertoire existe déjà
            FileWriter flot = new FileWriter("/tmp/twisk/logs/log" + numLog + ".txt", true);
            PrintWriter flotFiltre = new PrintWriter(flot);
            flotFiltre.print(contenu);
            flotFiltre.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
