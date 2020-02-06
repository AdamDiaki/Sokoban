package sokoban;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;


public class Requete extends Connect {

	private Connection connexion;
	private TerrainSokoban terrainSokoban;
	
	public Requete() throws SQLException {
		try {
			this.connexion = Connect.getConnexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean isInDb(String pseudo) throws SQLException{
		Boolean dedans = false;
		PreparedStatement ps = null;
		ResultSet resultat = null;
		String query = "SELECT * FROM user WHERE pseudo =?";
		try {
			ps = connexion.prepareStatement(query);
			ps.setString(1,pseudo);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				dedans = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			resultat.close();
		}
		return dedans;
	}
	
	public void addUser(String pseudo) throws SQLException {
		PreparedStatement ps = null;
		String query = "INSERT INTO user(Pseudo) VALUES (?)";
		try {
			ps = connexion.prepareStatement(query);
			ps.setString(1,pseudo);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
	}
	
	public void createSave() throws SQLException {
		PreparedStatement ps = null;
		String query = "INSERT INTO sauvegarde(idUser) VALUES(?)";
		try {
			ps = connexion.prepareStatement(query);
			UserAuth currentPlayer = new UserAuth();
			String currentPseudo = currentPlayer.getPseudo();
			int idUser = getIdUser(currentPseudo);
			ps.setInt(1,idUser);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
	}
	public int getIdUser(String pseudo) throws SQLException {
		PreparedStatement ps = null;
		ResultSet resultat = null;
		int idUser = 0;
		String query = "SELECT * FROM user WHERE pseudo =?";
		try {
			UserAuth UA = new UserAuth();
			System.out.println(UA.getPseudo());
			ps = connexion.prepareStatement(query);
			ps.setString(1,pseudo);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				idUser = resultat.getInt("idUser");
				System.out.println(idUser);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			resultat.close();
		}
		return idUser;
	}
	
	public void save(int niveau,Pane gamePane,String save,int score,int nbrPierre) throws SQLException {
		PreparedStatement ps = null;
		System.out.println("save:"+save);
		UserAuth userAuth = new UserAuth();
		String pseudo = userAuth.getPseudo();
		int idUser = getIdUser(pseudo);
		System.out.println(save);
		String query = "UPDATE sauvegarde set sauvegarde=?, niveau=?,score=?,nbrPierre=? WHERE idUser =?";
		try {
			ps = connexion.prepareStatement(query);
			ps.setString(1,save);
			ps.setInt(2,niveau);
			ps.setInt(3,score);
			ps.setInt(4,nbrPierre);
			ps.setInt(5,idUser);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
	}
	
	public int getNiveau(String pseudo) throws SQLException {
		PreparedStatement ps = null;
		ResultSet resultat = null;
		int idUser = getIdUser(pseudo);
		int niveau =0;
		String query = "SELECT * FROM sauvegarde where idUser=?";
		try {
			ps = connexion.prepareStatement(query);
			ps.setInt(1,idUser);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				niveau = resultat.getInt("niveau");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			resultat.close();
		}
		System.out.println("niv: "+niveau);
		return niveau;
	}
	
	public int getScore(String pseudo) throws SQLException {
		PreparedStatement ps = null;
		ResultSet resultat = null;
		int idUser = getIdUser(pseudo);
		int score =0;
		String query = "SELECT * FROM sauvegarde where idUser=?";
		try {
			ps = connexion.prepareStatement(query);
			ps.setInt(1,idUser);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				score = resultat.getInt("score");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			resultat.close();
		}
		System.out.println("score: "+score);
		return score;
	}
	
	public String getSave(String pseudo) throws SQLException {
		PreparedStatement ps = null;
		ResultSet resultat = null;
		int idUser = getIdUser(pseudo);
		String save = "";
		String query = "SELECT * FROM sauvegarde where idUser=?";
		try {
			ps = connexion.prepareStatement(query);
			ps.setInt(1,idUser);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				save = resultat.getString("sauvegarde");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			resultat.close();
		}
		System.out.println("save: "+save);
		return save;
	}
	
	public int getNbrPierre(String pseudo) throws SQLException {
		PreparedStatement ps = null;
		ResultSet resultat = null;
		int idUser = getIdUser(pseudo);
		int nbrPierre = 0;
		String query = "SELECT * FROM sauvegarde where idUser=?";
		try {
			ps = connexion.prepareStatement(query);
			ps.setInt(1,idUser);
			resultat = ps.executeQuery();
			if(resultat.next()) {
				nbrPierre = resultat.getInt("nbrPierre");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			resultat.close();
		}
		System.out.println("nbrPierre: "+nbrPierre);
		return nbrPierre;
	}
}
