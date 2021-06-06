package twisk.outils;

import twisk.mondeIG.MondeIG;

import java.io.*;

public class OutilsSerializable {

    private OutilsSerializable() {
    }

    private static final OutilsSerializable instance = new OutilsSerializable();

    /**
     * Fonction qui donne une instance de OutilsSerializable.
     *
     * @return l'instance
     */
    public static OutilsSerializable getInstance() {
        return instance;
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
     * @throws IOException erreur déclenchée si le fichier ne peut pas s'ouvrir
     */
    public MondeIG mondeFromSer(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        MondeIG monde = (MondeIG) objectIn.readObject();
        objectIn.close();
        return monde;
    }
}
