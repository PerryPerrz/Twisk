package twisk.vues;

import animatefx.animation.Pulse;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.Rotate;
import twisk.designPattern.Observateur;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.CouleurComposants;
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
        CouleurComposants cc = CouleurComposants.getInstance();

        ligne.setStroke(Paint.valueOf(cc.getCouleurLigneStroke()));
        ligne.setStrokeWidth(tc.getLargLigne());
        triangle.setStroke(Paint.valueOf(cc.getCouleurTriangleStroke()));
        triangle.setFill(Paint.valueOf(cc.getCouleurTriangleFill()));

        //Changement de curseur
        ligne.setCursor(Cursor.HAND);
        triangle.setCursor(Cursor.HAND);

        if (monde.isSelected(arc)) {
            ligne.setStroke(Color.valueOf(cc.getCouleurLigneStrokeIsSelected()));
            triangle.setStroke(Color.valueOf(cc.getCouleurTriangleStrokeIsSelected()));
            triangle.setFill(Color.valueOf(cc.getCouleurTriangleFillIsSelected()));
        }
        triangle.setStrokeWidth(tc.getLargLigne());
        this.apparitionDeLaLigne(this.arc.getPdcArrive(), this.arc.getPdcDepart());
        this.apparitionDuTriangle();
        this.getChildren().addAll(ligne, triangle);

        //Animation
        if (!monde.simulationACommencee()) {
            new Pulse(ligne).play();
            new Pulse(triangle).play();
        }

        ligne.setOnMouseClicked(MouseEvent -> monde.selectionArc(this.arc));
        triangle.setOnMouseClicked(MouseEvent -> monde.selectionArc(this.arc));
        this.setPickOnBounds(false);
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
//Fonction qui calcule l'orientation de la fl??che
    public void apparitionDuTriangle() {
        TailleComposants tc = TailleComposants.getInstance();
        double pointDepX = arc.getPdcDepart().getCentreX();
        double pointDepY = arc.getPdcDepart().getCentreY();
        double pointArrX = arc.getPdcArrive().getCentreX();
        double pointArrY = arc.getPdcArrive().getCentreY();

        //Cr??ation du triangle
        triangle.getPoints().addAll(pointArrX, pointArrY, pointArrX + tc.getLongTri(), pointArrY + tc.getLargTri(), pointArrX + tc.getLongTri(), pointArrY - tc.getLargTri(), pointArrX, pointArrY);

        //L'angle de la fl??che :

        //On calcule delta venant de l'??quation d'une droite form??e par deux points (y = delta * x + b)
        double del = (pointDepY - pointArrY) / (pointDepX - pointArrX);

        //Calcul correspondant ?? la tangente de l'angle entre la droite horizontale et la droite correspondant au corps de la fl??che.
        double tangAngle = Math.abs(del);

        double angleEnRadiant = Math.atan(tangAngle); //On sait que tan(Alpha) = |Delta| donc pour avoir Alpha, on fait arctan(|Delta|)
        double angleEnDegree = Math.toDegrees(angleEnRadiant);

        //On change l'angle en fonction de la position du point de d??part (Si on est dans la partie haut ?? gauche,bas ?? gauche ou bas ?? droite du graphique)
        //Si l'on est dans la partie en bas ?? droite du graphique (en math) donc en haut ?? droite dans un graph INFORMATIQUE :)
        if (pointDepX >= pointArrX && pointDepY < pointArrY) {
            angleEnDegree = -angleEnDegree;
        }
        //Si l'on est dans la partie en haut ?? gauche du graphique (en math) donc en bas ?? gauche dans un graph INFORMATIQUE :)
        else if (pointDepX < pointArrX && pointDepY >= pointArrY) {
            angleEnDegree = 180 - angleEnDegree;
        }
        //Si l'on est dans la partie en bas ?? gauche du graphiquee (en math) donc en haut ?? gauche dans un graph INFORMATIQUE :)
        else if (pointDepX < pointArrX && pointDepY < pointArrY) {
            angleEnDegree = -(180 - angleEnDegree);
        }

        //Maintenant, il faut s'occuper de la rotation de la t??te de la fl??che
        Rotate rotate = new Rotate();
        rotate.setAngle(angleEnDegree);
        rotate.setPivotX(pointArrX);
        rotate.setPivotY(pointArrY);

        triangle.getTransforms().add(rotate);
    }

    @Override
    public void reagir() {
    }
}
