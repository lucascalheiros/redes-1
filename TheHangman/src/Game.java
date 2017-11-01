import java.util.Scanner;

public class Game {
	
	private String word;
	private String visibleWord;
	private int life;
	private String guesses = "";
	
	
	public Game(String word) {
		this.word = word;
		this.visibleWord = "";
		this.life = 6;
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
			
			guess = read.next().charAt(0); // Garantindo que pegue apenas o primeiro caractere
			this.tryLetter(guess);
			win = this.checkWin();
			
			if(win){
				System.out.println("YOU WIN!");
			}
			
			if(this.life == 0){
				System.out.println("YOU DIED!");
				break;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Game game = new Game("testando");
		game.run();
	}
	
}
