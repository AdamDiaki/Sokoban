package sokoban;

/**
 * Définition d'un niveau du jeu
 */
public class Niveau {

    private int pierres;
    private String terrain;

    public Niveau(int pierres, String terrain) {
        this.pierres = pierres;
        this.terrain = terrain;
    }

    public int getPierres() {
        return pierres;
    }

    public String getTerrain() {
        return terrain;
    }
}
