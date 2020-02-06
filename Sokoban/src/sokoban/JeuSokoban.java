package sokoban;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;

import sokoban.action.AbstractAction;
import sokoban.action.EliminationPierre;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * Gère le déroulement du jeu
 */
public class JeuSokoban {

    private final Pane gamePane;
    private final Label label;

    private int niveau;
    private TerrainSokoban terrainSokoban;
    private int pierresRestantes;
    private int score;

    private LinkedList<AbstractAction> actions = new LinkedList<>();

    JeuSokoban(Pane gamePane, Label label, Button btnUndo, Button btnJeu, Button btnNiveau, Button btnSave) throws SQLException {
        niveau = 0;
        this.gamePane = gamePane;
        terrainSokoban = new TerrainSokoban(gamePane);
        this.label = label;
        Requete rqt = new Requete();
        btnUndo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!actions.isEmpty()) {
                    AbstractAction c = actions.removeLast();
                    c.executeDefait(terrainSokoban);
                    if (c instanceof EliminationPierre) {
                        pierresRestantes++;
                        majLabel();
                    }
                }
            }
        });
        btnJeu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                niveau = 0;
                initNiveau(niveau);
            }
        });
        btnNiveau.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initNiveau(niveau);
            }
        });
        btnSave.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent event) {
        			String save = terrainSokoban.getNiveau();
        			int nbrPierre = 0;
        			for(char c : save.toCharArray()) {
        				if(c == '0')
        					nbrPierre++;
        			}
					try {
						rqt.save(niveau, gamePane, save,score,nbrPierre);
					} catch (SQLException e) {
						e.printStackTrace();
					}
        	}
        });
        gamePane.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case LEFT:
                        deplacement(new Coord(-1, 0));
                        break;
                    case RIGHT:
                        deplacement(new Coord(1, 0));
                        break;
                    case UP:
                        deplacement(new Coord(0, -1));
                        break;
                    case DOWN:
                        deplacement(new Coord(0, 1));
                        break;
                    default:
                        break;
                }
            }
        });
        initNiveau(niveau);
    }
    
    JeuSokoban(Pane gamePane, Label label, Button btnUndo, Button btnJeu, Button btnNiveau, Button btnSave,int sc,int niv,int nbrPierre) throws SQLException {
        niveau = niv;
        this.score = sc;
        this.gamePane = gamePane;
        terrainSokoban = new TerrainSokoban(gamePane);
        this.label = label;
        Requete rqt = new Requete();
        btnUndo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!actions.isEmpty()) {
                    AbstractAction c = actions.removeLast();
                    c.executeDefait(terrainSokoban);
                    if (c instanceof EliminationPierre) {
                        pierresRestantes++;
                        majLabel();
                    }
                }
            }
        });
        btnJeu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                niveau = 0;
                initNiveau(niveau);
            }
        });
        btnNiveau.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initNiveau(niveau);
            }
        });
        btnSave.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent event) {
        			String save = terrainSokoban.getNiveau();
					try {
						rqt.save(niveau, gamePane, save,score,nbrPierre);
					} catch (SQLException e) {
						e.printStackTrace();
					}
        	}
        });
        gamePane.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case LEFT:
                        deplacement(new Coord(-1, 0));
                        break;
                    case RIGHT:
                        deplacement(new Coord(1, 0));
                        break;
                    case UP:
                        deplacement(new Coord(0, -1));
                        break;
                    case DOWN:
                        deplacement(new Coord(0, 1));
                        break;
                    default:
                        break;
                }
            }
        });
        UserAuth userAuth = new UserAuth();
        String pseudo = userAuth.getPseudo();
        String save = rqt.getSave(pseudo);
        Niveaux.changeNiveau(niveau, nbrPierre, save);
        initNiveau(niveau);
    }

    /**
     * Gère une commande de déplacement
     */
    public void deplacement(Coord depl) {
        Optional<AbstractAction> optionCommande = terrainSokoban.prepareDeplacement(depl);
        if (optionCommande.isPresent()) {
        	score++;
        	majLabel();
            AbstractAction c = optionCommande.get();
            actions.addLast(c);
            c.execute(terrainSokoban);
            if (c instanceof EliminationPierre) {
                pierresRestantes--;
                majLabel();
                if (pierresRestantes == 0) {
                    niveauSuivant();
                }
            }
        }
    }

    private void initNiveau(int niveau) {
        actions.clear();
        terrainSokoban.initNiveau(Niveaux.getNiveau(niveau).getTerrain());
        pierresRestantes = Niveaux.getNiveau(niveau).getPierres();
        majLabel();
    }

    private void majLabel() {
    	System.out.println(score);
        label.setText(String.format("Niveau %d-Pierres à éliminer: %d-Score: %d", niveau+1, pierresRestantes,score));
    }

    private void niveauSuivant() {
        if (niveau == Niveaux.nombreNiveau() - 1) {
            new Alert(Alert.AlertType.INFORMATION, "Vous avez terminé tous les niveaux").showAndWait();
        } else {
            niveau++;
            new Alert(Alert.AlertType.INFORMATION, "Vous avez terminé le niveau " + niveau).showAndWait();
            initNiveau(niveau);
        }
    }
    
}
