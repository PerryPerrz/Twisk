package twisk.outils;


import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * La classe KitC.
 */
public class KitC {
    /**
     * Creer l'environnement nécessaire à la simulation.
     */
    public void creerEnvironnement() {
        try {
            // création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà
            Path directories = Files.createDirectories(Paths.get("/tmp/twisk"));
            // copie des deux fichiers programmeC.o et def.h depuis le projet sous /tmp/twisk
            String[] liste = {"programmeC.o", "def.h", "codeNatif.o"};
            for (String nom : liste) {
                InputStream source = getClass().getResource("/twisk/ressources/codeC/" + nom).openStream();
                File destination = new File("/tmp/twisk/" + nom);
                copier(source, destination);
//                  Path source = Paths.get(getClass().getResource("/twisk/ressources/codeC/" + nom).getPath());
//                  Path newdir = Paths.get("/tmp/twisk/");
//                  Files.copy(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copier(InputStream source, File dest) {
        try {
            InputStream sourceFile = source;
            OutputStream destinationFile = new FileOutputStream(dest);
            // Lecture par segment de 0.5Mo
            byte[] buffer = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
            destinationFile.close();
            sourceFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creer le fichier client.c contenant le codeC généré par le monde.
     *
     * @param codeC le code c
     */
    public void creerFichier(String codeC) {
        try {
            // création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà
            FileWriter flot = new FileWriter("/tmp/twisk/client.c");
            PrintWriter flotFiltre = new PrintWriter(flot);
            flotFiltre.println(codeC);
            flotFiltre.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Compile le fichier client.c contenant le code c généré par le monde.
     */
    public void compiler() {
        try {
            Runtime runtime = Runtime.getRuntime();//Il faut récupérer l’environnement d’exécution de java
            Process p = runtime.exec("gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o");//On demande l’exécution de la compilation
            p.waitFor();
            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            // à reprendre éventuellement et à adapter à votre code
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne;
            while ((ligne = output.readLine()) != null) {
                System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Construit la librairie à partir des fichiers en c.
     */
    public void construireLaLibrairie() {
        FabriqueNumero fab = FabriqueNumero.getInstance();
        try {
            Runtime runtime = Runtime.getRuntime();//Il faut récupérer l’environnement d’exécution de java
            Process p = runtime.exec("gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/codeNatif.o /tmp/twisk/client.o -o /tmp/twisk/libTwisk" + fab.getNumeroLibrairie() + ".so");//On demande l’exécution de la compilation
            p.waitFor();
            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            // à reprendre éventuellement et à adapter à votre code
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne;
            while ((ligne = output.readLine()) != null) {
                System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Procédure qui tue tous les processus C
     *
     * @param gestCli le gestionnaire de clients
     */
    public void tuerLesProcessusC(GestionnaireClients gestCli) {
        //On s'occupe de tuer les processus C
        Runtime runtime = Runtime.getRuntime();//Il faut récupérer l’environnement d’exécution de java
        for (Client c : gestCli) {
            Process p = null;//On demande l’exécution de la compilation
            try {
                p = runtime.exec("kill " + c.getNumeroClient()); //Le numéro du client correspond à son numéro de processus
                System.out.println(c.getNumeroClient());
                p.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
