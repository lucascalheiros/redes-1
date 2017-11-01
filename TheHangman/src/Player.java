
public class Player {
	
	private String nome;
	private int id;
	private int matches = 0;
	
	public Player(String nome, int id){
		this.nome = nome;
		this.id = id;
	}
	
	public void addMatches(int matches){this.matches += matches;}
	
	public String getNome(){return this.nome;}
	
	public int getId(){return this.id;}
	
	public int getMatches(){return this.matches;}

}
