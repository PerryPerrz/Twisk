package twisk;

import animatefx.animation.BounceIn;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;
import twisk.outils.CreationLogs;
import twisk.outils.OutilsSerializable;
import twisk.outils.TailleComposants;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

/**
 * La classe MainTwisk.
 */
public class MainTwisk extends Application {
    /**
     * Procédure main qui permet de lancer l'application
     *
     * @param args les arguments d'entrée
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        OutilsSerializable.getInstance().initializeSer();
        CreationLogs.getInstance().initializelogs();
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

        primaryStage.setTitle("twisk | Iopeti & Yvoz");
        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("ressources/images/icon.png"))));
        primaryStage.setScene(new Scene(root, tc.getWindowX(), tc.getWindowY()));
        primaryStage.show();
    }
}
