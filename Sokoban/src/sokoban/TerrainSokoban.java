package sokoban;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import sokoban.action.AbstractAction;
import sokoban.action.DeplacementPierre;
import sokoban.action.DeplacementSimple;
import sokoban.action.EliminationPierre;
import sokoban.elementjeu.AbstractElementJeu;
import sokoban.elementjeu.Joueur;
import sokoban.elementjeu.Mur;
import sokoban.elementjeu.Pierre;
import sokoban.elementjeu.Sortie;
import javafx.scene.layout.Pane;

/**
 * GÃ¨re le plateau de jeu
 */
public class TerrainSokoban {

    private Pane gamePane;

    private Joueur joueur;
    private List<Mur> murs;
    private List<Sortie> sorties;
    private List<Pierre> pierres;
    private int nbrLignes ;
    private int maxX;
    private List<Integer> size = new ArrayList<Integer>();

    /**
     * Enregistre une rÃ©fÃ©rence vers le Pane devant afficher le terrain
     */
    public TerrainSokoban(Pane gamePane) {
        this.gamePane = gamePane;
    }
    
    public TerrainSokoban() {
    	
    }
    /**
     * PrÃ©pare le terrain d'aprÃ¨s un niveau de jeu
     */
    public void initNiveau(String niveau) {

        gamePane.getChildren().clear();
        murs = new ArrayList<>();
        sorties = new ArrayList<>();
        pierres = new ArrayList<>();

        int x = 0;
        int y = 0;
        int max =0;
        int index = 0;
        for(char c : niveau.toCharArray()) {
        	index++;
            switch (c) {
                case '|' :
                    y++;
                    x=0;
                    max =0;
                    maxX = index;
                    size.add(index);
                    index=0;
                    break;
                case '@':
                    joueur = new Joueur(x, y, gamePane);
                    x++;
                    max++;
                    break;
                case '#':
                    murs.add(new Mur(x, y, gamePane));
                    x++;
                    max++;
                    break;
                case '*':
                    sorties.add(new Sortie(x, y, gamePane));
                    x++;
                    max++;
                    break;
                case '0':
                    pierres.add(new Pierre(x, y, gamePane));
                    x++;
                    max++;
                    break;
                default:
                    x++;
            }
            
        }

        // dÃ©finition des dimensions du gamePane
        int w = x * AbstractElementJeu.width;
        int h = (y + 1) * AbstractElementJeu.width;
        gamePane.setMinSize(w, h);
        gamePane.setMaxSize(w, h);
        this.nbrLignes = y;
    }

    /**
     * Retourne le cas Ã©chÃ©ant un objet Action correspondant au dÃ©placement possible
     * selon le paramÃ¨tre depl et l'Ã©tat du plateau de jeu
     */
    Optional<AbstractAction> prepareDeplacement(Coord depl) {

        // carreau souhaitÃ©
        Coord coordJoueur = joueur.getCoord();
        Coord coordCible = coordJoueur.add(depl);
        Optional<AbstractElementJeu> cible = contenu(coordCible);

        if (!cible.isPresent()) {
            // dÃ©place le joueur
            return Optional.of(new DeplacementSimple(coordJoueur, coordCible));
        } else {
            if (cible.get() instanceof Pierre) {
                // le joueur est susceptible de pousser une pierre
                Coord coordCiblePierre = coordCible.add(depl);
                Optional<AbstractElementJeu> ciblePierre = contenu(coordCiblePierre);
                if (!ciblePierre.isPresent()) {
                    return Optional.of(new DeplacementPierre(coordJoueur, coordCible, coordCiblePierre));
                } else if (ciblePierre.get() instanceof Sortie) {
                    return Optional.of(new EliminationPierre(coordJoueur, coordCible));
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Recherche si un Ã©lÃ©ment du jeu (autre que le joueur) se trouve Ã  l'emplacement spÃ©cifiÃ© par les coordonnÃ©es
     */
    Optional<AbstractElementJeu> contenu(Coord coord) {
        for(Mur x: murs) {
            if (x.hasCoord(coord)) return Optional.of(x);
        }
        for(Sortie x: sorties) {
            if (x.hasCoord(coord)) return Optional.of(x);
        }
        for(Pierre x: pierres) {
            if (x.hasCoord(coord)) return Optional.of(x);
        }
        return Optional.empty();
    }
 
    public void deplaceJoueur(Coord cible) {
        joueur.setCoord(cible);
    }

    public void deplacePierre(Coord depart, Coord arrivee) {
        for(Pierre x: pierres) {
            if (x.hasCoord(depart)) {
                x.setCoord(arrivee);
                return;
            };
        }
    }

    public void cachePierre(Coord coord) {
        for(Pierre x: pierres) {
            if (x.hasCoord(coord)) {
                x.setCoord(null);
                return;
            };
        }
    }

    public void affichePierre(Coord coord) {
        for(Pierre x: pierres) {
            if (x.estCache()) {
                x.setCoord(coord);
                return;
            };
        }
    }
    
    Optional<AbstractElementJeu> contenu2(Coord coord){
    	for(Mur x: murs) {
            if (x.hasCoord(coord)) return Optional.of(x);
        }
        for(Sortie x: sorties) {
            if (x.hasCoord(coord)) return Optional.of(x);
        }
        for(Pierre x: pierres) {
            if (x.hasCoord(coord)) return Optional.of(x);
        }
        if(joueur.hasCoord(coord )) return Optional.of(joueur);
    	return Optional.empty();
}
    	public String getNiveau() {
    	String niv = "";
    	int x = 0;
    	int y =-1;
    	System.out.println("nombre de lignes :"+nbrLignes);
    	for(int i=0;i<=nbrLignes;i++) {
    		x = 0;
    		y = y+1;
	    	System.out.println("y:"+y);
    		for(int j=0;j<maxX;j++) {
    	    	Coord coord = new Coord(x,y);
    			Optional<AbstractElementJeu> cible = contenu2(coord);
    			if (j == maxX-1) {
    	    		niv += "|";
    	    		j = maxX;
    			}
    			else {
    			if (!cible.isPresent()) {
    				niv += " ";
    				x++;
    			}
    			else {
    			if (cible.get() instanceof Mur) {
    				niv += "#";
    				x++;
    			}
    			if (cible.get() instanceof Sortie) {
    				niv += "*";
    				x++;
    			}
    			if (cible.get() instanceof Pierre) {
    				niv += "0";
    				x++;
    			}
    			if (cible.get() instanceof Joueur) {
    				niv += "@";
    				x++;
    			}
    			
    			}
    	}
    		}
    }
    return niv;
    }
}
