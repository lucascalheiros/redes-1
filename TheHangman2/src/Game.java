import java.util.Scanner;

public class Game {
	
	private String word;
	private String visibleWord = "";
	private int life = 6;
	private String guesses = "";
	private Player players[];
	private int currentPlayer = 0;
	
	public Game(String word, Player players[]) {
		this.word = word;
		this.players = players;
		for(int i = 0; i < word.length(); i++) {
			this.visibleWord += "_";
		}
	}
	
	public void newWord(String word) {
		this.word = word;
		this.visibleWord = "";
		for(int i = 0; i < word.length(); i++) {
			this.visibleWord += "_";
		}
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int tryLetter(Character letter) throws Exception {
		int matches = 0;
		String tmpWord = "";
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == letter && visibleWord.charAt(i) == '_') {
				matches++;
				tmpWord += String.valueOf(word.charAt(i));
			}
			else {
				tmpWord += String.valueOf(visibleWord.charAt(i));
			}
		}
		
		if(matches == 0){ // Nenhuma letra igual na palavra
			this.life--;
			this.drawHangman();
		}
		
		this.guesses += letter+" ";
		this.visibleWord = tmpWord;
		return matches;
	}
	
	public String getVisibleWord() {
		return visibleWord;
	}
	
	public boolean checkWin(){
		if(this.word.equals(this.visibleWord)){
			return true; // Vitória
		}else{
			return false; // Jogo continua
		}
	}
	
	public void drawHangman(){
		switch(this.life){
		case 6:
			System.out.println("   ______________ \n"+
							   "   |         |    \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "___|______________");
			break;
		case 5:
			System.out.println("   ______________ \n"+
							   "   |         |    \n"+
							   "   |        ( )   \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "___|______________");
			break;
		case 4:
			System.out.println("   ______________ \n"+
							   "   |         |    \n"+
							   "   |        ( )   \n"+
							   "   |         |    \n"+
							   "   |         |    \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "___|______________");
			break;
		case 3:
			System.out.println("   ______________ \n"+
							   "   |         |    \n"+
							   "   |        ( )   \n"+
							   "   |        /|    \n"+
							   "   |         |    \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "___|______________");
			break;
		case 2:
			System.out.println("   ______________ \n"+
							   "   |         |    \n"+
							   "   |        ( )   \n"+
							   "   |        /|\\   \n"+
							   "   |         |    \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "___|______________");
			break;
		case 1:
			System.out.println("   ______________ \n"+
							   "   |         |    \n"+
							   "   |        ( )   \n"+
							   "   |        /|\\   \n"+
							   "   |         |    \n"+
							   "   |        /     \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "___|______________");
			break;
		case 0:
			System.out.println("   ______________ \n"+
							   "   |         |    \n"+
							   "   |        ( )   \n"+
							   "   |        /|\\   \n"+
							   "   |         |    \n"+
							   "   |        / \\   \n"+
							   "   |              \n"+
							   "   |              \n"+
							   "___|______________");
			break;
		}
		
	}
	
	public void printGuesses(){
		System.out.println("Guesses: " + this.guesses);
	}
	
	public void run() throws Exception{
		Scanner read = new Scanner(System.in);
		boolean win = false;
		Character guess;
		
		this.drawHangman();
		
		while(!win){
			System.out.println(this.visibleWord);
			this.printGuesses();
			
			System.out.println(players[currentPlayer].getNome()+"'s Turn:"); //Turno
			System.out.println("Matches: "+players[currentPlayer].getMatches()); //Pontuação
			
			guess = read.next().charAt(0); // Garantindo que pegue apenas o primeiro caractere
			this.players[currentPlayer].addMatches(this.tryLetter(guess)); // Adiciona a pontuação ao Jogador atual
			win = this.checkWin();
			
			if(win){
				String winner = players[0].getNome();
				int bestPonctuation = players[0].getMatches();
				
				for(int i = 0; i < players.length; i++){
					System.out.println(players[i].getNome()+"'s Matches: "+
									   players[i].getMatches());
					if(players[i].getMatches() > bestPonctuation){
						winner = players[i].getNome();
						bestPonctuation = players[i].getMatches();
					}
				}
				System.out.println(winner + " WINS!");
			}
			
			if(this.life == 0){
				System.out.println("YOU DIED!");
				break;
			}
			
			if(currentPlayer == 2){ // Resetar se o 3º jogador jogou
				currentPlayer = 0;
			}else{
				currentPlayer++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Player player1 = new Player("Joao", 0);
		Player player2 = new Player("Jose", 1);
		Player player3 = new Player("Pedro", 2);
		
		Player players[] = {player1, player2, player3};
		
		Game game = new Game("testando", players);
		game.run();
	}
	
}
