package twisk.vues;

import animatefx.animation.BounceIn;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import twisk.designPattern.Observateur;
import twisk.exceptions.ChargementSauvegardeException;
import twisk.exceptions.PasUnGuichetException;
import twisk.exceptions.UncorrectSettingsException;
import twisk.mondeIG.MondeIG;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.GestionnaireThreads;
import twisk.outils.OutilsSerializable;
import twisk.outils.TailleComposants;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
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
        MenuItem nouveau = new MenuItem("Nouveau");
        MenuItem ouvrir = new MenuItem("Ouvrir");
        MenuItem sauvegarder = new MenuItem("Sauvegarder");
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
        MenuItem jetons = new MenuItem("Nombre de jeton(s)");
        MenuItem clients = new MenuItem("Nombre de client(s)");

        this.fichier.getItems().add(nouveau);
        this.fichier.getItems().add(ouvrir);
        this.fichier.getItems().add(sauvegarder);
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
        this.parametres.getItems().add(jetons);
        this.parametres.getItems().add(clients);

        nouveau.setOnAction(actionEvent -> this.nouveau());
        ouvrir.setOnAction(actionEvent -> this.restaurer());
        sauvegarder.setOnAction(actionEvent -> this.enregistrer());
        quitter.setOnAction(actionEvent -> {
            GestionnaireThreads.getInstance().detruireTout();
            Platform.exit();
        });
        supprimer.setOnAction(actionEvent -> this.supprimer());
        renommer.setOnAction(actionEvent -> this.rename());
        effacer.setOnAction(actionEvent -> monde.effacerLaSelection());
        entree.setOnAction(actionEvent -> monde.setEntree());
        sortie.setOnAction(actionEvent -> monde.setSortie());
        delai.setOnAction(actionEvent -> this.delai());
        ecart.setOnAction(actionEvent -> this.ecart());
        jour.setOnAction(actionEvent -> monde.setStyle(0));
        nuit.setOnAction(actionEvent -> monde.setStyle(1));
        reset.setOnAction(actionEvent -> monde.setStyle(2));
        jetons.setOnAction(actionEvent -> this.jetons());
        clients.setOnAction(actionEvent -> this.clients());

        TailleComposants tc = TailleComposants.getInstance();
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/file.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon1 = new ImageView(image1);
        fichier.setGraphic(icon1);

        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/edit.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon2 = new ImageView(image2);
        edition.setGraphic(icon2);

        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/world.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon3 = new ImageView(image3);
        mondeMenu.setGraphic(icon3);

        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/settings.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon4 = new ImageView(image4);
        parametres.setGraphic(icon4);

        Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/brush.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon5 = new ImageView(image5);
        style.setGraphic(icon5);

        Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/exit.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon6 = new ImageView(image6);
        quitter.setGraphic(icon6);

        Image image7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/delete.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon7 = new ImageView(image7);
        supprimer.setGraphic(icon7);

        Image image8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/rename.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon8 = new ImageView(image8);
        renommer.setGraphic(icon8);

        renommer.setDisable(true);

        Image image9 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/select.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon9 = new ImageView(image9);
        effacer.setGraphic(icon9);

        Image image10 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/entree.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon10 = new ImageView(image10);
        entree.setGraphic(icon10);

        Image image11 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/sortie.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon11 = new ImageView(image11);
        sortie.setGraphic(icon11);

        Image image12 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/hourglass.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon12 = new ImageView(image12);
        delai.setGraphic(icon12);

        delai.setDisable(true);

        Image image13 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/hourglass.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon13 = new ImageView(image13);
        ecart.setGraphic(icon13);

        ecart.setDisable(true);

        Image image14 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/day.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon14 = new ImageView(image14);
        jour.setGraphic(icon14);

        Image image15 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/night.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon15 = new ImageView(image15);
        nuit.setGraphic(icon15);

        Image image16 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/reset.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon16 = new ImageView(image16);
        reset.setGraphic(icon16);

        Image image17 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/token.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon17 = new ImageView(image17);
        jetons.setGraphic(icon17);

        jetons.setDisable(true);

        Image image18 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/client.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon18 = new ImageView(image18);
        clients.setGraphic(icon18);

        Image image19 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/open.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon19 = new ImageView(image19);
        nouveau.setGraphic(icon19);

        Image image20 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/open.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon20 = new ImageView(image20);
        ouvrir.setGraphic(icon20);

        Image image21 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/save.png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon21 = new ImageView(image21);
        sauvegarder.setGraphic(icon21);

        this.getMenus().addAll(fichier, edition, mondeMenu, parametres, style);
    }

    private void supprimer() {
        try {
            monde.supprimerLaSelection();
        } catch (PasUnGuichetException e) {
            TailleComposants tc = TailleComposants.getInstance();
            Alert dia = new Alert(Alert.AlertType.ERROR);
            dia.setTitle("PasUnGuichetException");
            dia.setHeaderText("Impossible de changer le sens d'une activité !");
            dia.setContentText("Erreur : L'étape choisie n'est pas un guichet\n" +
                    "Veuillez ré-essayer");
            Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
            ImageView icon2 = new ImageView(image2);
            dia.setGraphic(icon2);
            dia.show();
            //Le chronomètre
            PauseTransition pt = new PauseTransition(Duration.seconds(5));
            pt.setOnFinished(Event -> dia.close());
            pt.play();
        }
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
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/tools.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dialog.setGraphic(icon);

        Optional<String> result = dialog.showAndWait();
        //Ce qu'on à donné à result est stocké dans name et on fait un appel un la fonction avec la variable name
        result.ifPresent(this.monde::renommerLaSelection);
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
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/tools.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
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
                Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
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
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/tools.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
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
                Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
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
     * Jetons.
     */
    public void jetons() {
        TextInputDialog dialog = new TextInputDialog("2");
        dialog.setTitle("Nombre de jeton(s) d'un guichet");
        dialog.setHeaderText("Entrez votre nombre de jeton(s) :");
        dialog.setContentText("Jeton(s) :");

        TailleComposants tc = TailleComposants.getInstance();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/tools.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dialog.setGraphic(icon);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nbJet -> {
            try {
                this.monde.setTokens(Integer.parseInt(nbJet));
            } catch (UncorrectSettingsException uSE) {
                Alert dia = new Alert(Alert.AlertType.ERROR);
                dia.setTitle("UncorrectSettingsException");
                dia.setHeaderText("Impossible de saisir ce nombre de jeton(s)");
                dia.setContentText("Erreur : La saisie du nombre de jeton(s) est incorrecte\n" +
                        "Veuillez ré-essayer");
                Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon2 = new ImageView(image2);
                dia.setGraphic(icon2);
                dia.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dia.close());
                pt.play();
            } catch (PasUnGuichetException e) {
                Alert dia = new Alert(Alert.AlertType.ERROR);
                dia.setTitle("PasUnGuichetException");
                dia.setHeaderText("Impossible de changer le nombre de jetons d'une activité !");
                dia.setContentText("Erreur : L'étape choisie n'est pas un guichet\n" +
                        "Veuillez ré-essayer");
                Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
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
     * Clients.
     */
    public void clients() {
        TextInputDialog dialog = new TextInputDialog("5");
        dialog.setTitle("Nombre de client(s) dans la simulation");
        dialog.setHeaderText("Entrez votre nombre de client(s) :");
        dialog.setContentText("Client(s) :");

        TailleComposants tc = TailleComposants.getInstance();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/tools.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dialog.setGraphic(icon);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nbJet -> {
            try {
                this.monde.setNbClients(Integer.parseInt(nbJet));
            } catch (UncorrectSettingsException uSE) {
                Alert dia = new Alert(Alert.AlertType.ERROR);
                dia.setTitle("UncorrectSettingsException");
                dia.setHeaderText("Impossible de saisir ce nombre de client(s)");
                dia.setContentText("Erreur : La saisie du nombre de client(s) est incorrecte\n" +
                        "Veuillez ré-essayer");
                Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
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
     * Procédure qui ouvre un nouveau monde
     */
    public void nouveau() {
        //On reset le compteur des étapes
        FabriqueIdentifiant.getInstance().reset();

        MondeIG monde = new MondeIG();
        BorderPane root = new BorderPane();
        VueOutils viewO = new VueOutils(monde);
        VueMondeIG viewM = new VueMondeIG(monde);
        VueMenu viewMe = new VueMenu(monde);
        TailleComposants tc = TailleComposants.getInstance();
        root.setBottom(viewO);
        root.setCenter(viewM);
        root.setTop(viewMe);
        //Animation
        new BounceIn(root).play();
        Stage primaryStage = (Stage) this.getScene().getWindow();
        primaryStage.setTitle("twisk | Iopeti & Yvoz");
        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/twisk/ressources/images/icon.png"))));
        primaryStage.setScene(new Scene(root, tc.getWindowX(), tc.getWindowY()));
        primaryStage.show();
        monde.notifierObservateurs();
    }

    /**
     * Procédure qui demande à l'utilisateur quel monde sauvegardé ouvrir et qui l'ouvre.
     */
    @FXML
    void restaurer() {
        //On crée une nouvelle fenêtre avec le nouveau monde
        try {
            Window mainStage = this.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Ouvrez le fichier de sauvegarde");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Save Files", "*.ser"));
            File selectedFile = fileChooser.showOpenDialog(mainStage);
            if (selectedFile == null)
                throw new ChargementSauvegardeException("Fichier non sélectionné ou introuvable");
            MondeIG monde = OutilsSerializable.getInstance().mondeFromSer(selectedFile);

            BorderPane root = new BorderPane();
            VueOutils viewO = new VueOutils(monde);
            VueMondeIG viewM = new VueMondeIG(monde);
            VueMenu viewMe = new VueMenu(monde);
            TailleComposants tc = TailleComposants.getInstance();
            root.setBottom(viewO);
            root.setCenter(viewM);
            root.setTop(viewMe);
            //Animation
            new BounceIn(root).play();
            Stage primaryStage = (Stage) this.getScene().getWindow();
            primaryStage.setTitle("twisk | Iopeti & Yvoz");
            primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/twisk/ressources/images/icon.png"))));
            primaryStage.setScene(new Scene(root, tc.getWindowX(), tc.getWindowY()));
            primaryStage.show();
            monde.notifierObservateurs();
        } catch (ChargementSauvegardeException | IOException | ClassNotFoundException e) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle(e.getClass().getSimpleName());
            dialog.setHeaderText("Erreur lors du chargement d'une sauvegarde !");
            dialog.setContentText("Erreur : " + e.getMessage());
            dialog.show();
            //Le chronomètre
            PauseTransition pt = new PauseTransition(Duration.seconds(5));
            pt.setOnFinished(Event -> dialog.close());
            pt.play();
        }
    }

    /**
     * Procédure qui demande à l'utilisateur des informations sur la façon de sauvegarder le monde et qui le sauvegarde.
     */
    private void enregistrer() {
        Window mainStage = this.getScene().getWindow();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisissez le répertoire de sauvegarde");
        File selectedDir = directoryChooser.showDialog(mainStage);

        if (selectedDir != null) {
            TextInputDialog dialog = new TextInputDialog("sauvegardeMonde");
            dialog.setTitle("Nommez le fichier de sauvegarde");
            dialog.setHeaderText("Entrez le nouveau nom :");
            dialog.setContentText("Nom :");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(s -> {
                try {
                    OutilsSerializable.getInstance().mondeToSer(monde, selectedDir, s);
                } catch (IOException e) {
                    Alert dia = new Alert(Alert.AlertType.ERROR);
                    dia.setTitle("IOException");
                    dia.setHeaderText("Impossible de sauvegarder le monde");
                    dia.setContentText("Erreur : Le monde ne peut pas être sauvegarder \n" +
                            "Veuillez ré-essayer");
                    Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), TailleComposants.getInstance().getTailleIcons(), TailleComposants.getInstance().getTailleIcons(), true, true);
                    ImageView icon2 = new ImageView(image2);
                    dia.setGraphic(icon2);
                    dia.show();
                    //Le chronomètre
                    PauseTransition pt = new PauseTransition(Duration.seconds(5));
                    pt.setOnFinished(Event -> dia.close());
                    pt.play();
                }
            });
        } else {
            Alert dia = new Alert(Alert.AlertType.ERROR);
            dia.setTitle("IOException");
            dia.setHeaderText("Impossible de sauvegarder le monde");
            dia.setContentText("Erreur : Le monde ne peut pas être sauvegarder \n" +
                    "Veuillez ré-essayer");
            Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), TailleComposants.getInstance().getTailleIcons(), TailleComposants.getInstance().getTailleIcons(), true, true);
            ImageView icon2 = new ImageView(image2);
            dia.setGraphic(icon2);
            dia.show();
            //Le chronomètre
            PauseTransition pt = new PauseTransition(Duration.seconds(5));
            pt.setOnFinished(Event -> dia.close());
            pt.play();
        }
    }

    @Override
    public void reagir() {
        Runnable command = () -> {
            edition.getItems().get(1).setDisable(monde.nbEtapesSelectionnees() != 1); //On set le disable à false lorsque le nombre d'étapes est égale à 1
            parametres.getItems().get(0).setDisable(monde.nbEtapesSelectionnees() != 1);//On disable délai
            parametres.getItems().get(1).setDisable(monde.nbEtapesSelectionnees() != 1);//On disable écart
            if (!monde.etapesSelectionneesSontDesGuichets()) //Si l'étape concernée est une activité, on laisse le bouton "Nombre de jeton(s)" disable.
                parametres.getItems().get(2).setDisable(true);
            else //Si l'étape concernée est un guichet
                parametres.getItems().get(2).setDisable(monde.nbEtapesSelectionnees() != 1);//On disable jeton
        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }
    }
}
