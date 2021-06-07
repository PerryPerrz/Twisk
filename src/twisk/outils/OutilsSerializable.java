package twisk.outils;

import twisk.exceptions.FichierNullException;
import twisk.exceptions.MondeNullException;
import twisk.mondeIG.MondeIG;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * La classe OutilsSerializable
 */
public class OutilsSerializable {

    private OutilsSerializable() {
    }

    private static final OutilsSerializable instance = new OutilsSerializable();

    /**
     * Fonction qui donne une instance de OutilsSerializable.
     *
     * @return l 'instance
     */
    public static OutilsSerializable getInstance() {
        return instance;
    }

    /**
     * Procédure qui crée le dossier contenant les fichiers de sauvegarde temporaires.
     *
     * @throws IOException Erreur envoyée si la création du dossier n'est pas réussie.
     */
    public void initializeSer() throws IOException {
        Files.createDirectories(Paths.get("/tmp/twisk/mondes"));
    }

    /**
     * Procédure qui crée une sauvegarde d'un mondeIG au format serializable.
     *
     * @param monde Le mondeIG à sauvegarder
     * @param file  Le dossier de sauvegarde
     * @param nom   Le nom du fichier de sauvegarde
     * @throws IOException erreur déclenchée si l'écriture du fichier ne fonctionne pas
     */
    public void mondeToSer(MondeIG monde, File file, String nom) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file + "/" + nom + ".ser");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(monde);
        objectOut.close();
    }

    /**
     * Fonction qui retourne un MondeIG crée à partir d'un fichier de sauvegarde.
     *
     * @param file le fichier de sauvegarde
     * @return Le mondeIG
     * @throws IOException            erreur déclenchée si le fichier ne peut pas s'ouvrir
     * @throws ClassNotFoundException l'exception ClassNotFound
     * @throws MondeNullException     l'exception MondeNull
     */
    public MondeIG mondeFromSer(File file) throws IOException, ClassNotFoundException, MondeNullException {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        MondeIG monde = (MondeIG) objectIn.readObject();
        if (monde == null)
            throw new MondeNullException("Monde récupéré de la sauvegarde null !");
        objectIn.close();
        return monde;
    }

    /**
     * Fonction qui cherche si il existe des mondes prédéterminés temporaires et qui les retourne.
     *
     * @return Un tableau contenant les mondes ou null si il n'y a pas de fichiers dans le dossier de sauvegardetemporaire
     * @throws IOException            l'exception E/S
     * @throws ClassNotFoundException Erreur renvoyée quand le fichier ne correspond pas à un mondeIG.
     * @throws MondeNullException     l'exception MondeNull
     */
    public MondeIG[] mondesPredeterminesTemp() throws IOException, ClassNotFoundException, MondeNullException {
        File dossierTmp = new File("/tmp/twisk/mondes/");
        File[] fichiersTemp = dossierTmp.listFiles((dir, name) -> name.endsWith(".ser"));
        MondeIG[] mondes = null;
        if (fichiersTemp != null && fichiersTemp.length != 0) {
            mondes = new MondeIG[fichiersTemp.length];
            for (int i = 0; i < fichiersTemp.length; i++) {
                if (fichiersTemp[i] != null) {
                    mondes[i] = mondeFromSer(fichiersTemp[i]);
                    mondes[i].setNom(fichiersTemp[i].getName().replace(".ser", ""));
                }
            }
        }
        return mondes;
    }

    /**
     * Procédure qui crée une sauvegarde d'un mondeIG au format serializable dans le dossier temporaire.
     *
     * @param monde Le mondeIG à sauvegarder
     * @param nom   Le nom du fichier de sauvegarde
     * @throws IOException erreur déclenchée si l'écriture du fichier ne fonctionne pas
     */
    public void mondeToSerInMondesPredetermines(MondeIG monde, String nom) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("/tmp/twisk/mondes/" + nom + ".ser");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(monde);
        objectOut.close();
    }

    /**
     * Procédure qui supprime le fichier ser donné en paramètre.
     *
     * @param nom le nom du fichier .ser sans extension
     * @throws IOException          Erreur envoyée si la suppression du fichier à échoué.
     * @throws FichierNullException l'exception FichierNull
     */
    public void supprimerSer(String nom) throws IOException, FichierNullException {
        File dossierTmp = new File("/tmp/twisk/mondes/");
        File[] fichiersTemp = dossierTmp.listFiles((dir, name) -> name.endsWith(".ser"));
        if (fichiersTemp == null)
            throw new FichierNullException("La liste de fichiers de sauvegarde est nulle !");
        for (File file : fichiersTemp) {
            if (file.getName().equals(nom + ".ser"))
                if (!file.delete())
                    throw new IOException();
        }
    }

    /**
     * Fonction qui retourne le monde prédéterminé liés au fichier donné en paramètre.
     *
     * @param file le fichier
     * @return le mondeIG
     */
    public MondeIG chargerMondePredetermine(InputStream file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(file);
            return (MondeIG) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
