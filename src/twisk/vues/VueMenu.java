package twisk.vues;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import twisk.designPattern.Observateur;
import twisk.exceptions.UncorrectSettingsException;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

import java.util.Optional;

/**
 * La classe VueMenu.
 */
public class VueMenu extends MenuBar implements Observateur {
    private final MondeIG monde;
    private final Menu fichier;
    private final Menu edition;
    private final Menu mondeMenu;
    private final Menu parametres;
    private final Menu style;

    /**
     * Constructeur de la classe VueMenu.
     *
     * @param monde the monde
     */
    public VueMenu(MondeIG monde) {
        this.monde = monde;
        monde.ajouterObservateur(this);

        fichier = new Menu("Fichier");
        edition = new Menu("Edition");
        mondeMenu = new Menu("Monde");
        parametres = new Menu("Paramètres");
        style = new Menu("Style");
        MenuItem quitter = new MenuItem("Quitter");
        MenuItem supprimer = new MenuItem("Supprimer");
        MenuItem renommer = new MenuItem("Renommer");
        MenuItem effacer = new MenuItem("Effacer");
        MenuItem entree = new MenuItem("Entrée");
        MenuItem sortie = new MenuItem("Sortie");
        MenuItem delai = new MenuItem("Délai");
        MenuItem ecart = new MenuItem("Écart");
        MenuItem jour = new MenuItem("Jour");
        MenuItem nuit = new MenuItem("Nuit");
        MenuItem reset = new MenuItem("Reset");

        this.fichier.getItems().add(quitter);
        this.edition.getItems().add(supprimer);
        this.edition.getItems().add(renommer);
        this.edition.getItems().add(effacer);
        this.mondeMenu.getItems().add(entree);
        this.mondeMenu.getItems().add(sortie);
        this.parametres.getItems().add(delai);
        this.parametres.getItems().add(ecart);
        this.style.getItems().add(jour);
        this.style.getItems().add(nuit);
        this.style.getItems().add(reset);

        quitter.setOnAction(actionEvent -> Platform.exit());
        supprimer.setOnAction(actionEvent -> monde.supprimerLaSelection());
        renommer.setOnAction(actionEvent -> this.rename());
        effacer.setOnAction(actionEvent -> monde.effacerLaSelection());
        entree.setOnAction(actionEvent -> monde.setEntree());
        sortie.setOnAction(actionEvent -> monde.setSortie());
        delai.setOnAction(actionEvent -> this.delai());
        ecart.setOnAction(actionEvent -> this.ecart());
        jour.setOnAction(actionEvent -> monde.setStyle(0));
        nuit.setOnAction(actionEvent -> monde.setStyle(1));
        reset.setOnAction(actionEvent -> monde.setStyle(2));

        TailleComposants tc = TailleComposants.getInstance();
        Image image1 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/file.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon1 = new ImageView(image1);
        fichier.setGraphic(icon1);

        Image image2 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/edit.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon2 = new ImageView(image2);
        edition.setGraphic(icon2);

        Image image3 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/world.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon3 = new ImageView(image3);
        mondeMenu.setGraphic(icon3);

        Image image4 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/settings.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon4 = new ImageView(image4);
        parametres.setGraphic(icon4);

        Image image5 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/brush.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon5 = new ImageView(image5);
        style.setGraphic(icon5);

        Image image6 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/exit.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon6 = new ImageView(image6);
        quitter.setGraphic(icon6);

        Image image7 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/delete.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon7 = new ImageView(image7);
        supprimer.setGraphic(icon7);

        Image image8 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/rename.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon8 = new ImageView(image8);
        renommer.setGraphic(icon8);

        renommer.setDisable(true);

        Image image9 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/select.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon9 = new ImageView(image9);
        effacer.setGraphic(icon9);

        Image image10 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/entree.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon10 = new ImageView(image10);
        entree.setGraphic(icon10);

        Image image11 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/sortie.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon11 = new ImageView(image11);
        sortie.setGraphic(icon11);

        Image image12 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/hourglass.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon12 = new ImageView(image12);
        delai.setGraphic(icon12);

        delai.setDisable(true);

        Image image13 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/hourglass.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon13 = new ImageView(image13);
        ecart.setGraphic(icon13);

        ecart.setDisable(true);

        Image image14 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/day.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon14 = new ImageView(image14);
        jour.setGraphic(icon14);

        Image image15 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/night.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon15 = new ImageView(image15);
        nuit.setGraphic(icon15);

        Image image16 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/reset.png"), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon16 = new ImageView(image16);
        reset.setGraphic(icon16);

        this.getMenus().addAll(fichier, edition, mondeMenu, parametres, style);
    }

    @Override
    public void reagir() {
        edition.getItems().get(1).setDisable(monde.nbEtapesSelectionnees() != 1); //On set le disable à false lorsque le nombre d'étapes est égale à 1
        parametres.getItems().get(0).setDisable(monde.nbEtapesSelectionnees() != 1);//On disable délai
        parametres.getItems().get(1).setDisable(monde.nbEtapesSelectionnees() != 1);//On disable écart
    }

    /**
     * Rename.
     */
    public void rename() {
        TextInputDialog dialog = new TextInputDialog("Balançoire");
        dialog.setTitle("Renommer la sélection");
        dialog.setHeaderText("Entrez votre nouveau nom :");
        dialog.setContentText("Nom :");

        TailleComposants tc = TailleComposants.getInstance();
        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/tools.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dialog.setGraphic(icon);

        Optional<String> result = dialog.showAndWait();
        //Ce qu'on à donné à result est stocké dans name et on fait un appel un la fonction avec la variable name
        result.ifPresent(name -> this.monde.renommerLaSelection(name));
    }

    /**
     * Delai.
     */
    public void delai() {
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Délai d'une activité");
        dialog.setHeaderText("Entrez votre délai :");
        dialog.setContentText("Délai :");

        TailleComposants tc = TailleComposants.getInstance();
        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/tools.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dialog.setGraphic(icon);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(d -> {
            try {
                this.monde.setDelai(d);
            } catch (UncorrectSettingsException uSE) {
                Alert dia = new Alert(Alert.AlertType.ERROR);
                dia.setTitle("UncorrectSettingsException");
                dia.setHeaderText("Impossible de saisir ce délai");
                dia.setContentText("Erreur : La saisie du délai est incorrecte\n" +
                        "Veuillez ré-essayer");
                Image image2 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/warning.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon2 = new ImageView(image2);
                dia.setGraphic(icon2);
                dia.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dia.close());
                pt.play();
            }
        });
    }

    /**
     * Ecart.
     */
    public void ecart() {
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Écart d'une activité");
        dialog.setHeaderText("Entrez votre écart :");
        dialog.setContentText("Écart :");

        TailleComposants tc = TailleComposants.getInstance();
        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/tools.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dialog.setGraphic(icon);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(e -> {
            try {
                this.monde.setEcart(e);
            } catch (UncorrectSettingsException uSE) {
                Alert dia = new Alert(Alert.AlertType.ERROR);
                dia.setTitle("UncorrectSettingsException");
                dia.setHeaderText("Impossible de saisir cette écart");
                dia.setContentText("Erreur : La saisie de l'écart est incorrecte\n" +
                        "Veuillez ré-essayer");
                Image image2 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/warning.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon2 = new ImageView(image2);
                dia.setGraphic(icon2);
                dia.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dia.close());
                pt.play();
            }
        });
    }
}
