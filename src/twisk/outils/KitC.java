package twisk.outils;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class KitC {
    public void creerEnvironnement() {
        try {
            // création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà
            System.out.println(Files.exists(Paths.get("/tmp/twisk")));
            Path directories = Files.createDirectories(Paths.get("/tmp/twisk"));
            System.out.println(Files.exists(Paths.get("/tmp/twisk")));
            // copie des deux fichiers programmeC.o et def.h depuis le projet sous /tmp/twisk
            String[] liste = {"programmeC.o", "def.h"};
            for (String nom : liste) {
                Path source = Paths.get(getClass().getResource("/twisk/ressources/codeC/" + nom).getPath());
                Path newdir = Paths.get("/tmp/twisk/");
                Files.copy(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creerFichier(String codeC){
        try {
            // création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà
            FileWriter flot = new FileWriter("/tmp/twisk/client.c");
            PrintWriter flotFiltre = new PrintWriter(flot) ;
            flotFiltre.println(codeC) ;
            flotFiltre.close() ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
