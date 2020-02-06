package sokoban.action;

import sokoban.Coord;
import sokoban.TerrainSokoban;

/**
 * Enregistre les informations nécessaires pour un déplacement du joueur
 * poussant une pierre dans une sortie
 */
public class EliminationPierre extends AbstractAction {

    public EliminationPierre(Coord coordDepart, Coord coordCible) {
        super(coordDepart, coordCible);
    }

    public void execute(TerrainSokoban t) {
        t.cachePierre(coordCible);
        t.deplaceJoueur(coordCible);
    }

    public void executeDefait(TerrainSokoban t) {
        t.deplaceJoueur(coordDepart);
        t.affichePierre(coordCible);
    }
}
