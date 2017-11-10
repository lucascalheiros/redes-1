
public class Player {
	
	private String nome;
	private int id;
	private int matches = 0;
	private int life = 6;

	
	public Player(String nome, int id){
		this.nome = nome;
		this.id = id;
	}
	
	public void addMatches(int matches){this.matches += matches;}
	
	public String getNome(){return this.nome;}
	
	public int getId(){return this.id;}
	
	public int getMatches(){return this.matches;}
	
	public String toString(){
		return "Player ID: " + id +"\t\tNome: " + nome + "\nPontos: " + matches;
	}
}
