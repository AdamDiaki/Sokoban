package sokoban.elementjeu;

import sokoban.Coord;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class AbstractElementJeu {

    public static final int width = 20;
    public static final int itemWidth = width - 1;

    Coord coord;
    Node node;

    protected void positionnement(Pane pane) {
        node.setTranslateX(coord.getX() * width);
        node.setTranslateY(coord.getY() * width);
        pane.getChildren().add(node);
    }

    public boolean hasCoord(Coord coord) {
        return (this.coord != null)
                && this.coord.getX() == coord.getX()
                && this.coord.getY() == coord.getY();
    }

    final Duration DureeAnimation = Duration.millis(40);

    public void setCoord(Coord coord) {
        this.coord = coord;
        TranslateTransition tt = new TranslateTransition(DureeAnimation, node);
        tt.setToX(coord.getX() * width);
        tt.setToY(coord.getY() * width);
        tt.play();
    }
    
}
