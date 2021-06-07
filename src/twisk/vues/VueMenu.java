package twisk.vues;

import animatefx.animation.BounceIn;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
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
import twisk.outils.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

/**
 * La classe VueMenu.
 */
public class VueMenu extends MenuBar implements Observateur {
    private final MondeIG monde;
    private final Menu fichier;
    private final Menu edition;
    private final Menu accesAuMonde;
    private final Menu parametres;
    private final Menu style;
    private final Menu mondes;
    private final Menu lois;

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
        accesAuMonde = new Menu("Accès");
        parametres = new Menu("Paramètres");
        style = new Menu("Style");
        mondes = new Menu("Mondes");
        lois = new Menu("Lois");
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
        MenuItem ajouterMonde = new MenuItem("Ajouter");
        MenuItem supprimerMonde = new MenuItem("Supprimer");
        MenuItem loiUniforme = new MenuItem("Loi Uniforme");
        MenuItem loiGaussienne = new MenuItem("Loi Gaussienne");
        MenuItem loiExponentielle = new MenuItem("Loi Exponentielle");

        this.fichier.getItems().addAll(nouveau, ouvrir, sauvegarder, quitter);
        this.edition.getItems().addAll(supprimer, renommer, effacer);
        this.accesAuMonde.getItems().addAll(entree, sortie);
        this.parametres.getItems().addAll(delai, ecart, jetons, clients);
        this.style.getItems().addAll(jour, nuit, reset);
        this.mondes.getItems().addAll(ajouterMonde, supprimerMonde);
        this.lois.getItems().addAll(loiUniforme, loiGaussienne, loiExponentielle);

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
        ajouterMonde.setOnAction(actionEvent -> this.ajouter());
        supprimerMonde.setOnAction(actionEvent -> this.supprimerMonde());
        loiUniforme.setOnAction(actionEvent -> monde.setLoi("Uni"));
        loiGaussienne.setOnAction(actionEvent -> monde.setLoi("Gauss"));
        loiExponentielle.setOnAction(actionEvent -> monde.setLoi("Expo"));

        this.gestionDesImages(fichier, "file");
        this.gestionDesImages(edition, "edit");
        this.gestionDesImages(accesAuMonde, "porte");
        this.gestionDesImages(parametres, "settings");
        this.gestionDesImages(style, "brush");
        this.gestionDesImages(quitter, "exit");
        this.gestionDesImages(supprimer, "delete");
        supprimer.setDisable(true);
        this.gestionDesImages(renommer, "rename");
        renommer.setDisable(true);
        this.gestionDesImages(effacer, "select");
        effacer.setDisable(true);
        this.gestionDesImages(entree, "entree");
        entree.setDisable(true);
        this.gestionDesImages(sortie, "sortie");
        sortie.setDisable(true);
        this.gestionDesImages(delai, "hourglass");
        delai.setDisable(true);
        this.gestionDesImages(ecart, "hourglass");
        ecart.setDisable(true);
        this.gestionDesImages(jour, "day");
        this.gestionDesImages(nuit, "night");
        this.gestionDesImages(reset, "reset");
        this.gestionDesImages(jetons, "token");
        jetons.setDisable(true);
        this.gestionDesImages(clients, "client");
        this.gestionDesImages(nouveau, "new");
        this.gestionDesImages(ouvrir, "open");
        this.gestionDesImages(sauvegarder, "save");
        this.gestionDesImages(mondes, "world");
        this.gestionDesImages(ajouterMonde, "ajouterMonde");
        this.gestionDesImages(supprimerMonde, "supprimerMonde");
        this.gestionDesImages(lois, "probabilite");
        this.gestionDesImages(loiUniforme, "loi");
        this.gestionDesImages(loiGaussienne, "loi");
        this.gestionDesImages(loiExponentielle, "loi");

        this.gestionDuCheckmarkDesLois();
        ajouterMenuItemsMondes();

        this.getMenus().addAll(fichier, edition, accesAuMonde, parametres, style, mondes, lois);
    }

    /**
     * Procédure supprimer
     */
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
        TextInputDialog dialog = new TextInputDialog("Tobbogan");
        dialog.setTitle("Renommer la sélection");
        dialog.setHeaderText("Entrez votre nouveau nom (pas de caractères spéciaux) :");
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
        dialog.setHeaderText("Entrez votre nombre de client(s) (max : 49) :");
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
        try {
            OutilsSerializable.getInstance().initializeSer();
            CreationLogs.getInstance().initializelogs();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            ouvrirFenetreNouveauMonde(monde);
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

    /**
     * Procédure qui ajoute les menuItems aux mondes
     */
    private void ajouterMenuItemsMondes() {
        mondes.getItems().removeIf(menuItem -> !(menuItem.getText().equals("Ajouter") || menuItem.getText().equals("Supprimer")));
        //On s'occupe des menuItem correspondant aux mondes prédéterminés
        try {
            MondeIG[] mondesPredetermines = OutilsSerializable.getInstance().mondesPredetermines();
            if (!(mondesPredetermines == null)) {
                for (MondeIG mondeIG : mondesPredetermines) {
                    MenuItem menuItem = new MenuItem(mondeIG.getNom());
                    mondes.getItems().add(menuItem);
                    if (monde.simulationACommencee())
                        menuItem.setDisable(true);
                    menuItem.setOnAction(actionEvent -> {
                        try {
                            ClassLoader loader = Thread.currentThread().getContextClassLoader();
                            URL url = loader.getResource("mondes");
                            assert url != null;
                            String path = url.getPath();
                            File dossier = new File(path);
                            File[] fichiers = dossier.listFiles((dir, name) -> name.endsWith(".ser"));
                            assert fichiers != null;
                            File dossierTmp = new File("/tmp/");
                            File[] fichiersTemp = dossierTmp.listFiles((dir, name) -> name.endsWith(".ser"));
                            File file = null;
                            for (File f : fichiers) {
                                if (f.getName().equals(mondeIG.getNom() + ".ser"))
                                    file = f;
                            }
                            if (fichiersTemp != null) {
                                for (File f : fichiersTemp) {
                                    if (f.getName().equals(mondeIG.getNom() + ".ser"))
                                        file = f;
                                }
                            }
                            assert file != null;
                            ouvrirFenetreNouveauMonde(OutilsSerializable.getInstance().mondeFromSer(file));
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/mondesDef.png")), TailleComposants.getInstance().getTailleIcons2(), TailleComposants.getInstance().getTailleIcons2(), true, true);
                    ImageView icon = new ImageView(image);
                    menuItem.setGraphic(icon);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Procédure qui permet d'ajouter des mondes prédefini temporairement, ils sont stockés dans le repértoire tmp de la machine. (impossible des les stocker sans un jar)
     */
    private void ajouter() {
        TextInputDialog dialog = new TextInputDialog("sauvegardeMonde");
        dialog.setTitle("Nommez le monde à sauvegarder");
        dialog.setHeaderText("Entrez le nouveau nom :");
        dialog.setContentText("Nom :");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(s -> {
            try {
                OutilsSerializable.getInstance().mondeToSerInMondesPredetermines(monde, s);
            } catch (IOException e) {
                Alert dia = new Alert(Alert.AlertType.ERROR);
                dia.setTitle("IOException");
                dia.setHeaderText(e.getMessage());
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
        monde.notifierObservateurs();
    }

    /**
     * Procédure qui supprimer un monde
     */
    private void supprimerMonde() {
        try {
            MondeIG[] mondesPredeterminesTemp = OutilsSerializable.getInstance().mondesPredeterminesTemp();
            if (mondesPredeterminesTemp != null) {
                String[] choices = new String[mondesPredeterminesTemp.length];
                for (int i = 0; i < mondesPredeterminesTemp.length; i++)
                    choices[i] = mondesPredeterminesTemp[i].getNom();
                ChoiceDialog<String> dialog = new ChoiceDialog<>(choices[0], choices);
                dialog.setTitle("Une fenêtre de Choix");
                dialog.setHeaderText("Choisissez le monde à supprimer");
                dialog.setContentText("Monde:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(s -> {
                    try {
                        OutilsSerializable.getInstance().supprimerSer(s);
                    } catch (IOException e) {
                        Alert dia = new Alert(Alert.AlertType.ERROR);
                        dia.setTitle("IOException");
                        dia.setHeaderText("Impossible de supprimer le monde");
                        dia.setContentText("Erreur : Le monde ne peut pas être supprimé \n" +
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
                monde.notifierObservateurs();
            } else {
                Alert dia = new Alert(Alert.AlertType.WARNING);
                dia.setTitle("Attention !");
                dia.setHeaderText("Impossible de supprimer un monde prédefini !");
                dia.setContentText("Attention, il n'existe aucun monde à supprimer !");
                Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), TailleComposants.getInstance().getTailleIcons(), TailleComposants.getInstance().getTailleIcons(), true, true);
                ImageView icon2 = new ImageView(image2);
                dia.setGraphic(icon2);
                dia.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dia.close());
                pt.play();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Procédure qui ouvre une fenetre d'un nouveau monde
     *
     * @param monde le monde sur lequel on veut ouvrir la fenetre
     */
    private void ouvrirFenetreNouveauMonde(MondeIG monde) {
        try {
            OutilsSerializable.getInstance().initializeSer();
            CreationLogs.getInstance().initializelogs();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * Procédure qui permet de gérer les images, plus facilement et plus proprement
     *
     * @param menuItem                  le menuItem sur lequel je veux accrocher une image
     * @param nomDeLImageSansLExtension le nom de l'image sans son extension
     */
    public void gestionDesImages(MenuItem menuItem, String nomDeLImageSansLExtension) {
        TailleComposants tc = TailleComposants.getInstance();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/" + nomDeLImageSansLExtension + ".png")), tc.getTailleIcons2(), tc.getTailleIcons2(), true, true);
        ImageView icon = new ImageView(image);
        menuItem.setGraphic(icon);
    }

    /**
     * Procédure qui gère le checkmark des lois, lorsque l'utilisateur selectionne une lois, un checkmark s'ajoute dans le menu
     */
    public void gestionDuCheckmarkDesLois() {
        if (monde.getLoi().equals("Uni"))
            this.lois.getItems().get(0).setText("Loi Uniforme ◄");
        else
            this.lois.getItems().get(0).setText("Loi Uniforme");
        if (monde.getLoi().equals("Gauss"))
            this.lois.getItems().get(1).setText("Loi Gaussienne ◄");
        else
            this.lois.getItems().get(1).setText("Loi Gaussienne");
        if (monde.getLoi().equals("Expo"))
            this.lois.getItems().get(2).setText("Loi Exponentielle ◄");
        else
            this.lois.getItems().get(2).setText("Loi Exponentielle");
    }

    @Override
    public void reagir() {
        Runnable command = () -> {
            if (!monde.simulationACommencee()) {
                edition.getItems().get(1).setDisable(monde.nbEtapesSelectionnees() != 1); //On set le disable à false lorsque le nombre d'étapes est égale à 1
                parametres.getItems().get(0).setDisable(monde.nbEtapesSelectionnees() != 1);//On disable délai
                parametres.getItems().get(1).setDisable(monde.nbEtapesSelectionnees() != 1);//On disable écart
                //Si aucune étape n'est selectionnée, on disable le bouton supprimer
                edition.getItems().get(0).setDisable(monde.nbEtapesSelectionnees() == 0 && monde.getNbArcsSelectionnes() == 0);
                edition.getItems().get(2).setDisable(monde.nbEtapesSelectionnees() == 0 && monde.getNbArcsSelectionnes() == 0);
                //Si aucune étape n'est seléctionnée, on ne peut pas donner d'entrée ou de sortie
                accesAuMonde.getItems().get(0).setDisable(monde.nbEtapesSelectionnees() == 0);
                accesAuMonde.getItems().get(1).setDisable(monde.nbEtapesSelectionnees() == 0 || monde.etapesSelectionneesContientUnGuichet());
                if (!monde.etapesSelectionneesSontDesGuichets())//Si l'étape concernée est une activité, on laisse le bouton "Nombre de jeton(s)" disable.
                    parametres.getItems().get(2).setDisable(true);
                else { //Si l'étape concernée est un guichet
                    parametres.getItems().get(0).setDisable(true);
                    parametres.getItems().get(1).setDisable(true);
                    parametres.getItems().get(2).setDisable(monde.nbEtapesSelectionnees() != 1); //Si plus d'un guichet est selectionné on disable jetons
                }
                fichier.getItems().get(0).setDisable(false);
                fichier.getItems().get(1).setDisable(false);
                parametres.getItems().get(3).setDisable(false);
                mondes.getItems().get(0).setDisable(false);
                mondes.getItems().get(1).setDisable(false);
                lois.getItems().get(0).setDisable(false);
                lois.getItems().get(1).setDisable(false);
                lois.getItems().get(2).setDisable(false);
            } else {
                fichier.getItems().get(0).setDisable(true);
                fichier.getItems().get(1).setDisable(true);
                edition.getItems().get(0).setDisable(true);
                edition.getItems().get(1).setDisable(true);
                edition.getItems().get(2).setDisable(true);
                accesAuMonde.getItems().get(0).setDisable(true);
                accesAuMonde.getItems().get(1).setDisable(true);
                parametres.getItems().get(0).setDisable(true);
                parametres.getItems().get(1).setDisable(true);
                parametres.getItems().get(2).setDisable(true);
                parametres.getItems().get(3).setDisable(true);
                mondes.getItems().get(0).setDisable(true);
                mondes.getItems().get(1).setDisable(true);
                lois.getItems().get(0).setDisable(true);
                lois.getItems().get(1).setDisable(true);
                lois.getItems().get(2).setDisable(true);
            }
            ajouterMenuItemsMondes();
            this.gestionDuCheckmarkDesLois();
        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }
    }
}
