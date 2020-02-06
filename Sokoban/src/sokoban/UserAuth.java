package sokoban;

public class UserAuth {

	private static String pseudo;
	private static Boolean set = false;
	
	public UserAuth(String pseudo) {
	if (!set) {
		this.pseudo = pseudo;
		set = true;
	}
	}
	
	public UserAuth() {
		
	}
	
	public String getPseudo() {
		return pseudo;
	}
}
