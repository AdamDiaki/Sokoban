package sokoban.elementjeu;

import sokoban.Coord;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sortie extends AbstractElementJeu {

    public Sortie(int x, int y, Pane pane) {
        coord = new Coord(x, y);
        node = new Rectangle(itemWidth, itemWidth, Color.BLACK);
        positionnement(pane);
    }

}
