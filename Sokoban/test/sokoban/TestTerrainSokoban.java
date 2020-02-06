package sokoban;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import sokoban.action.AbstractAction;
import sokoban.action.DeplacementSimple;
import javafx.scene.layout.Pane;

public class TestTerrainSokoban {

    @Test
    public void testDeplacementVersEmplacementVide() {
        Pane gamePane = new Pane();
        TerrainSokoban terrain = new TerrainSokoban(gamePane);
        terrain.initNiveau("####|#@ #|####");
        Optional<AbstractAction> action = terrain.prepareDeplacement(new Coord(1, 0));
        assertTrue(action.get() instanceof DeplacementSimple);
    }

    @Test
    public void testDeplacementVersMur() {
        Pane gamePane = new Pane();
        TerrainSokoban terrain = new TerrainSokoban(gamePane);
        terrain.initNiveau("####|#@ #|####");
        Optional<AbstractAction> action = terrain.prepareDeplacement(new Coord(-1, 0));
        assertEquals(action, Optional.empty());
    }
}
