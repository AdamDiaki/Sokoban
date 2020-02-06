package sokoban.action;

import sokoban.Coord;
import sokoban.TerrainSokoban;

/**
 * Enregistre les informations nécessaires pour un déplacement du joueur,
 * sans déplacement ni élimination de pierre
 */
public class DeplacementSimple extends AbstractAction {

    public DeplacementSimple(Coord coordDepart, Coord coordCible) {
        super(coordDepart, coordCible);
    }

    /**
     * Execute ce déplacement sur un objet TerrainSokoban
     */
    public void execute(TerrainSokoban t) {
        t.deplaceJoueur(coordCible);
    }

    /**
     * Annule ce déplacement sur un objet TerrainSokoban
     */
    public void executeDefait(TerrainSokoban t) {
        t.deplaceJoueur(coordDepart);
    }

}
