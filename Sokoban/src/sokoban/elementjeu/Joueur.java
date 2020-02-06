package sokoban.elementjeu;

import java.io.ByteArrayInputStream;

import sokoban.Coord;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Joueur extends AbstractElementJeu {

    private static String imageJoueurAsHexBinary = "47494638376114001400a102000000007f7f7fffffffffffff2c000000001400140000022f948fa90bbd2fc09110d159b1cde676fe8560e48c9a29a25ed6b4aeb98e3138b3a51c0841ad00b9b1e3f8124114a400003b";

    private static Image imageJoueur = new Image(
        new ByteArrayInputStream(hexStringToByteArray(imageJoueurAsHexBinary))
    );

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len/2];

        for(int i = 0; i < len; i+=2){
            data[i/2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }

        return data;
    }

    public Joueur(int x, int y, Pane pane) {
        coord = new Coord(x, y);
        node = new ImageView(imageJoueur);
        node.setId("joueur");
        positionnement(pane);
    }
    public Coord getCoord() {
        return new Coord(coord.getX(), coord.getY());
    }

}
