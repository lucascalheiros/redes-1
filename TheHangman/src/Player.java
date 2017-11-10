import java.util.ArrayList;
import java.util.HashMap;

public class Player {
	public static ArrayList<Player> players = new ArrayList<Player>();
	private String nome;
	private int id;
	private int matches = 0;
	private int life = 6;

<<<<<<< HEAD
=======
	
>>>>>>> 802097ae90735bf9f041303e219ac32f5f662c01
	public Player(String nome, int id){
		this.nome = nome;
		this.id = id;
	}
	
	public static void addPlayer(int keyId, Player player) {
		players.add(player);
	}
	
	public static Player getPlayer(int keyId) {
		return players.get(keyId);
	}
	
	public static void clrPlayer() {
		players.clear();
	}
	
	public void addMatches(int matches){this.matches += matches;}
	
	public String getNome(){return this.nome;}
	
	public int getId(){return this.id;}
	
	public int getMatches(){return this.matches;}
	
	public int getLife(){return this.life;}

	public void wrongAnswer(){this.life--;}
		
	public void newGame() {
		this.matches = 0;
		this.life = 6;
	}
	
	@Override
	public String toString() {
		return 	"Player: " + this.nome + "\tID: " + this.id + "\n" + 
				"Vidas: " + this.life + "\n" + 
				"Pontos: " + this.matches;
	}

}
