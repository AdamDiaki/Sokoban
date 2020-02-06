package sokoban.action;

import sokoban.Coord;
import sokoban.TerrainSokoban;

/**
 * Base des différentes actions de jeu
 */
public abstract class AbstractAction {

    abstract public void execute(TerrainSokoban t);
    abstract public void executeDefait(TerrainSokoban t);

    Coord coordDepart;
    Coord coordCible;

    public AbstractAction(Coord coordDepart, Coord coordCible) {
        this.coordDepart = coordDepart;
        this.coordCible = coordCible;
    }
}
