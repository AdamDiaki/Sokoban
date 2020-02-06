package sokoban;

/**
 * Coordonnées sur le plateau de jeu
 */
public class Coord {

    private int x;
    private int y;

    public Coord(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Additionne deux coordonnées
     * @param coord à ajouter à l'objet courant
     * @return un nouvel objet Coord
     */
    public Coord add(Coord coord) {
        return new Coord(this.x + coord.x, this.y + coord.y);
    }
    
}
