package sokoban.elementjeu;

import sokoban.Coord;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pierre extends AbstractElementJeu {

    public Pierre(int x, int y, Pane pane) {
        coord = new Coord(x, y);
        node = new Circle(itemWidth / 2, Color.STEELBLUE);
        node.setTranslateX(x * width + itemWidth / 2);
        node.setTranslateY(y * width + itemWidth / 2);
        pane.getChildren().add(node);
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
        if (coord == null) {
            node.setVisible(false);
        } else {
            node.setVisible(true);
            TranslateTransition tt = new TranslateTransition(DureeAnimation, node);
            tt.setToX(coord.getX() * width + itemWidth / 2);
            tt.setToY(coord.getY() * width + itemWidth / 2);
            tt.play();
        }
    }

    public boolean estCache() {
        return this.coord == null;
    }

}
