package sokoban.action;

import sokoban.Coord;
import sokoban.TerrainSokoban;

/**
 * Enregistre les informations nécessaires pour un déplacement du joueur
 * poussant une pierre (qui n'est pas envoyée dans une sortie)
 */
public class DeplacementPierre extends AbstractAction {

    Coord coordCiblePierre;

    public DeplacementPierre(Coord coordDepart, Coord coordCible, Coord coordCiblePierre) {
        super(coordDepart, coordCible);
        this.coordCiblePierre = coordCiblePierre;
    }

    public void execute(TerrainSokoban t) {
        t.deplacePierre(coordCible, coordCiblePierre);
        t.deplaceJoueur(coordCible);
    }

    public void executeDefait(TerrainSokoban t) {
        t.deplaceJoueur(coordDepart);
        t.deplacePierre(coordCiblePierre, coordCible);
    }
}
