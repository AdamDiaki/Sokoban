package sokoban.elementjeu;

import sokoban.Coord;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Mur extends AbstractElementJeu {

    public Mur(int x, int y, Pane pane) {
        coord = new Coord(x, y);
        node = new Rectangle(itemWidth, itemWidth, Color.FIREBRICK);
        positionnement(pane);
    }

}
