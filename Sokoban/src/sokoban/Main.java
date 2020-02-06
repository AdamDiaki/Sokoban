package sokoban;

import sokoban.elementjeu.AbstractElementJeu;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Initialisation de l'interface graphique et de la classe JeuSokoban
 */
public class Main extends Application {

	private static int w = Niveaux.gameWidth * AbstractElementJeu.width;
    private static int h = Niveaux.gameHeight * AbstractElementJeu.width;
    
	public void JoueurExiste(final Stage stage) {
		GridPane choix = new GridPane();
		choix.setAlignment(Pos.CENTER);
		choix.setHgap(10);
		choix.setVgap(10);
		choix.setPadding(new Insets(10));
		Button BtnContinue = new Button();
		Button BtnRestart = new Button();
		BtnRestart.setText("Recommencer");
		BtnContinue.setText("Continuer la partie");
		choix.add(BtnRestart,1,1);
		choix.add(BtnContinue,1,2);
		Scene scene = new Scene(choix,450,250);
		stage.setTitle("choix");
		stage.setScene(scene);
		stage.show();
		BtnRestart.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			try {
				restart(stage);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		});
		
		BtnContinue.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				Requete rqt;
				UserAuth userAuth = new UserAuth();
				String pseudo = userAuth.getPseudo();
				try {
					rqt = new Requete();
					int niveau = rqt.getNiveau(pseudo);
					int score = rqt.getScore(pseudo);
					int nbrPierre = rqt.getNbrPierre(pseudo);
					Continue(stage,niveau,score,nbrPierre);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
		public void JoueurExistePas(final Stage stage, String pseudo) throws SQLException {
			UserAuth userAuth = new UserAuth();
			Requete rqt = new Requete();
			rqt.addUser(pseudo);
			rqt.createSave();
			restart(stage);
		}
		
		public void restart(final Stage stage) throws SQLException {
			Pane gamePane = new Pane();
			
	        StackPane stackPane = new StackPane(gamePane);
	        
	        stackPane.setMinSize(w+100, h);
	        stackPane.setMaxSize(w+100, h);
	
	        Button btnUndo = new Button("Annuler dépl.");
	        btnUndo.setFocusTraversable(false);
	        Button btnJeu = new Button("Réinit. jeu");
	        btnJeu.setFocusTraversable(false);
	        Button btnNiveau = new Button("Réinit. niveau");
	        btnNiveau.setFocusTraversable(false);
	        HBox topPane = new HBox(5, btnUndo, btnJeu, btnNiveau); 
	        Button btnSave = new Button("Sauvegarder");
	        btnSave.setFocusTraversable(false);
	        Label label = new Label();
	        HBox botPane = new HBox(1,label,btnSave);
	
	        BorderPane borderPane = new BorderPane();
	        borderPane.setTop(topPane);
	        borderPane.setBottom(botPane);
	        borderPane.setCenter(stackPane);
	        borderPane.setBottom(botPane);
	
	        Scene scene = new Scene(borderPane);
	
	        JeuSokoban jeuSokoban = new JeuSokoban(gamePane, label, btnUndo, btnJeu, btnNiveau,btnSave);
	
	        stage.setScene(scene);
	        stage.setTitle("Sokoban");
	        stage.show();
		}
		
		public void Continue(final Stage stage,int niveau,int score,int nbrPierre) throws SQLException {
			Pane gamePane = new Pane();	
	        StackPane stackPane = new StackPane(gamePane);
	        
	        stackPane.setMinSize(w+100, h);
	        stackPane.setMaxSize(w+100, h);
	
	        Button btnUndo = new Button("Annuler dépl.");
	        btnUndo.setFocusTraversable(false);
	        Button btnJeu = new Button("Réinit. jeu");
	        btnJeu.setFocusTraversable(false);
	        Button btnNiveau = new Button("Réinit. niveau");
	        btnNiveau.setFocusTraversable(false);
	        HBox topPane = new HBox(5, btnUndo, btnJeu, btnNiveau); 
	        Button btnSave = new Button("Sauvegarder");
	        btnSave.setFocusTraversable(false);
	        Label label = new Label();
	        HBox botPane = new HBox(1,label,btnSave);
	
	        BorderPane borderPane = new BorderPane();
	        borderPane.setTop(topPane);
	        borderPane.setBottom(botPane);
	        borderPane.setCenter(stackPane);
	        borderPane.setBottom(botPane);
	
	        Scene scene = new Scene(borderPane);
	
	        JeuSokoban jeuSokoban = new JeuSokoban(gamePane, label, btnUndo, btnJeu, btnNiveau,btnSave,score,niveau,nbrPierre);
	        
	        stage.setScene(scene);
	        stage.setTitle("Sokoban");
	        stage.show();
		}
			
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws SQLException {
    	Requete rqt = new Requete();
    	TextField Pseudo = new TextField();
		Button bouton = new Button();
		TextField password = new TextField();
		bouton.setText("Jouer");

		
	    GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10));
		Text TextPseudo = new Text("Pseudo : ");
		grid.add(TextPseudo,0,1);
		grid.add(Pseudo,1,1);
		grid.add(bouton,1,2);
		grid.add(password,2,2);
		Scene scene = new Scene(grid,450,250);
		bouton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String pseudo = Pseudo.getText();
				try {
					UserAuth currentPlayer = new UserAuth(pseudo);
					System.out.println(currentPlayer.getPseudo());
					if (rqt.isInDb(pseudo) == true) {
						JoueurExiste(stage);			
					}
					else {
						JoueurExistePas(stage,pseudo);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			});
			/*
			
			*/

		
		stage.setTitle("Connexion");
		stage.setScene(scene);
		stage.show();
    }

}
