package twisk.vues;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.Rotate;
import twisk.designPattern.Observateur;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.TailleComposants;

/**
 * La classe VueArcIG.
 */
public class VueArcIG extends Pane implements Observateur {
    private final MondeIG monde;
    private final ArcIG arc;
    private final Line ligne;
    private final Polyline triangle;

    /**
     * Constructeur de la classe VueArcIG.
     *
     * @param monde le monde
     * @param arc   l'arc
     */
    public VueArcIG(MondeIG monde, ArcIG arc) {
        this.monde = monde;
        monde.ajouterObservateur(this);
        this.arc = arc;
        this.ligne = new Line();
        this.triangle = new Polyline();
        TailleComposants tc = TailleComposants.getInstance();
        ligne.setStroke(Color.BLUEVIOLET);
        ligne.setStrokeWidth(tc.getLargLigne());
        triangle.setStroke(Color.BLUEVIOLET);
        triangle.setFill(Color.BLUEVIOLET);
        triangle.setStrokeWidth(tc.getLargLigne());
        this.getChildren().addAll(ligne, triangle);

        ligne.setOnMouseClicked(MouseEvent -> monde.selectionArc(this.arc));
        triangle.setOnMouseClicked(MouseEvent -> monde.selectionArc(this.arc));
        this.setPickOnBounds(false);
    }

    public void reagir() {
        this.getChildren().clear();
        this.apparitionDeLaLigne(this.arc.getPdcArrive(), this.arc.getPdcDepart());
        this.apparitionDuTriangle();
        this.getChildren().addAll(ligne, triangle);
        if (monde.isSelectionned(arc)) {
            ligne.setStroke(Color.valueOf("#f4abc4"));
            triangle.setStroke(Color.valueOf("#f4abc4"));
            triangle.setFill(Color.valueOf("#f4abc4"));
        }
    }

    /**
     * Apparition de la ligne.
     *
     * @param pdc1 le pdc 1
     * @param pdc2 le pdc 2
     */
    public void apparitionDeLaLigne(PointDeControleIG pdc1, PointDeControleIG pdc2) {
        this.ligne.setStartX(pdc1.getCentreX());
        this.ligne.setStartY(pdc1.getCentreY());
        this.ligne.setEndX(pdc2.getCentreX());
        this.ligne.setEndY(pdc2.getCentreY());
    }

    /**
     * Apparition du triangle.
     */
    //Fonction qui calcule l'orientation de la flèche
    public void apparitionDuTriangle() {
        TailleComposants tc = TailleComposants.getInstance();
        double pointDepX = arc.getPdcDepart().getCentreX();
        double pointDepY = arc.getPdcDepart().getCentreY();
        double pointArrX = arc.getPdcArrive().getCentreX();
        double pointArrY = arc.getPdcArrive().getCentreY();

        //Création du triangle
        triangle.getPoints().addAll(pointArrX, pointArrY, pointArrX + tc.getLongTri(), pointArrY + tc.getLargTri(), pointArrX + tc.getLongTri(), pointArrY - tc.getLargTri(), pointArrX, pointArrY);

        //L'angle de la flèche :

        //On calcule delta venant de l'équation d'une droite formée par deux points (y = delta * x + b)
        double del = (pointDepY - pointArrY) / (pointDepX - pointArrX);

        //Calcul correspondant à la tangente de l'angle entre la droite horizontale et la droite correspondant au corps de la flèche.
        double tangAngle = Math.abs(del);

        double angleEnRadiant = Math.atan(tangAngle); //On sait que tan(Alpha) = |Delta| donc pour avoir Alpha, on fait arctan(|Delta|)
        double angleEnDegree = Math.toDegrees(angleEnRadiant);

        //On change l'angle en fonction de la position du point de départ (Si on est dans la partie haut à gauche,bas à gauche ou bas à droite du graphique)
        //Si l'on est dans la partie en bas à droite du graphique (en math) donc en haut à droite dans un graph INFORMATIQUE :)
        if (pointDepX >= pointArrX && pointDepY < pointArrY) {
            angleEnDegree = -angleEnDegree;
        }
        //Si l'on est dans la partie en haut à gauche du graphique (en math) donc en bas à gauche dans un graph INFORMATIQUE :)
        else if (pointDepX < pointArrX && pointDepY >= pointArrY) {
            angleEnDegree = 180 - angleEnDegree;
        }
        //Si l'on est dans la partie en bas à gauche du graphiquee (en math) donc en haut à gauche dans un graph INFORMATIQUE :)
        else if (pointDepX < pointArrX && pointDepY < pointArrY) {
            angleEnDegree = -(180 - angleEnDegree);
        }

        //Maintenant, il faut s'occuper de la rotation de la tête de la flèche
        Rotate rotate = new Rotate();
        rotate.setAngle(angleEnDegree);
        rotate.setPivotX(pointArrX);
        rotate.setPivotY(pointArrY);

        triangle.getTransforms().add(rotate);
    }
}
