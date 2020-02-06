package sokoban;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;



/**
 * Class permettant la connexion Ã  une base de donnÃ©e. 
 * @author vincenthardouin
 *
 */
public class Connect {

	private static Connection connexion = null; 
	private static int nbrConnexion = 0;
	
	/**
	 * Permets la connexion Ã  la BD, et trace les exceptions si nÃ©cessaire
	 */
	public Connect() {
	    /* Chargement du driver JDBC pour MySQL */
	    try {
	        Class.forName( "com.mysql.jdbc.Driver" );
	    } catch ( ClassNotFoundException e ) {
	    	System.out.println( "Erreur lors du chargement : le driver n'a pas Ã©tÃ© trouvÃ© dans le classpath ! <br/>"
	                + e.getMessage() );
	    }
		try {
			// Acces Ã  la base de donnÃ©es
			String url = "jdbc:mysql://localhost:3306/sokoban?useSSL=false";
			String user = "root";
			String passwd = "root";

			connexion = DriverManager.getConnection(url, user, passwd);
			nbrConnexion++;
			System.out.println("Connexion effective ! Connexion numÃ©ro " + nbrConnexion); 
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}    
	}
	
	/**
	 * Permets de retourner la connexion 
	 * @return la connexion Ã  la base de donnÃ©e
	 * @throws SQLException
	 */
	protected static Connection getConnexion() throws SQLException {
		return connexion; 
	}
	
	  
}
