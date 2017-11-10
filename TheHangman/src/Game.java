import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private String word;
	private String visibleWord = "";
	private String guesses = "";
	private ArrayList<Player> players = new ArrayList<Player>();
	private Server server;
	
	public Game(Server server, String word, ArrayList<Player> players) {
		this.server = server;
		this.word = word;
		this.players = players;
		for (int i = 0; i < word.length(); i++) {
			this.visibleWord += "_";
		}
	}

	public void newWord(String word) {
		this.word = word;
		this.visibleWord = "";
		for (int i = 0; i < word.length(); i++) {
			this.visibleWord += "_";
		}
	}

	public String getWord() {
		return this.word;
	}

	public void tryLetter(Player player, Character letter) throws Exception {
		int matches = 0;
		String tmpWord = "";
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == letter && visibleWord.charAt(i) == '_') {
				matches++;
				tmpWord += String.valueOf(word.charAt(i));
			} else {
				tmpWord += String.valueOf(visibleWord.charAt(i));
			}
		}

		if (matches == 0) { // Nenhuma letra igual na palavra
			player.wrongAnswer();
			System.out.println(player.getNome() + "'s wrong play");
			server.sendMessage(player.getNome() + "'s wrong play");
			Thread.sleep(3000);
		} else {
			System.out.println(player.getNome() + "'s right play. Matches: " + matches);
			server.sendMessage(player.getNome() + "'s right play. Matches: " + matches);
			Thread.sleep(3000);
		}

		this.guesses += letter + " ";
		this.visibleWord = tmpWord;
		player.addMatches(matches);
	}

	public String getVisibleWord() {
		return visibleWord;
	}

	public boolean checkWin() {
		if (this.word.equals(this.visibleWord)) {
			return true; // Vit�ria
		} else {
			return false; // Jogo continua
		}
	}

	public void drawHangman(Player player) {
		System.out.println(player.getNome() + "'s");
		server.sendMessage(player.getNome() + "'s");
		switch (player.getLife()) {
		case 6:
			System.out.println("   ______________ \n" + "   |         |    \n" + "   |              \n"
					+ "   |              \n" + "   |              \n" + "   |              \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			server.sendMessage("   ______________ \n" + "   |         |    \n" + "   |              \n"
					+ "   |              \n" + "   |              \n" + "   |              \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			break;
		case 5:
			System.out.println("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |              \n" + "   |              \n" + "   |              \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			server.sendMessage("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |              \n" + "   |              \n" + "   |              \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			break;
		case 4:
			System.out.println("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |         |    \n" + "   |         |    \n" + "   |              \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			server.sendMessage("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |         |    \n" + "   |         |    \n" + "   |              \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			break;
		case 3:
			System.out.println("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |        /|    \n" + "   |         |    \n" + "   |              \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			server.sendMessage("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |        /|    \n" + "   |         |    \n" + "   |              \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			break;
		case 2:
			System.out.println("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |        /|\\   \n" + "   |         |    \n" + "   |              \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			server.sendMessage("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |        /|\\   \n" + "   |         |    \n" + "   |              \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			break;
		case 1:
			System.out.println("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |        /|\\   \n" + "   |         |    \n" + "   |        /     \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			server.sendMessage("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |        /|\\   \n" + "   |         |    \n" + "   |        /     \n" + "   |              \n"
					+ "   |              \n" + "___|______________");
			break;
		case 0:
			System.out.println("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |        /|\\   \n" + "   |         |    \n" + "   |        / \\   \n"
					+ "   |              \n" + "   |              \n" + "___|______________");
			server.sendMessage("   ______________ \n" + "   |         |    \n" + "   |        ( )   \n"
					+ "   |        /|\\   \n" + "   |         |    \n" + "   |        / \\   \n"
					+ "   |              \n" + "   |              \n" + "___|______________");
			break;
		}

	}

	public void printGuesses() {
		System.out.println("Guesses: " + this.guesses);
		server.sendMessage("Guesses: " + this.guesses);
	}

	public void run() throws Exception {
		Scanner read = new Scanner(System.in);
		boolean win = false;
		Character guess;
		int currentPlayerId = 0;
		Player player = null;
		// this.drawHangman();

		while (!win) {

			player = players.get(currentPlayerId);

			this.drawHangman(player);

			System.out.println(this.visibleWord);
			server.sendMessage(this.visibleWord);
			this.printGuesses();

			System.out.println(player.getNome() + "'s Turn:"); // Turno
			server.sendMessage(player.getNome() + "'s Turn:");
			System.out.println("Matches: " + player.getMatches()); // Pontua��o
			server.sendMessage("Matches: " + player.getMatches());
			if (player.getLife() != 0) {
				guess = ' ';
				TurnManager.mapTurnMessage.get(currentPlayerId).setTurn();
				while(guess == ' ') {
					System.out.print("");
					Thread.sleep(500);
					guess = TurnManager.mapTurnMessage.get(currentPlayerId).readMessage();
				}
				this.tryLetter(player, guess); // Adiciona a pontua��o ao Jogador atual
				win = this.checkWin();

				if (win) {
					String winner = players.get(0).getNome();
					int bestPonctuation = players.get(0).getMatches();

					for (int i = 0; i < players.size(); i++) {
						System.out.println(players.get(i).getNome() + "'s Matches: " + players.get(i).getMatches());
						if (players.get(i).getMatches() > bestPonctuation) {
							winner = players.get(i).getNome();
							bestPonctuation = players.get(i).getMatches();
						}
					}
					System.out.println(winner + " WINS!");
					server.sendMessage(winner + " WINS!");
				}
			} else {
				System.out.println(player.getNome() + " YOU DIED!");
				server.sendMessage(player.getNome() + " YOU DIED!");
				Thread.sleep(3000);
			}
			System.out.println("\n\n");
			server.sendMessage("\n\n");
			currentPlayerId++;
			if (currentPlayerId >= players.size()) {
				currentPlayerId = 0;
			}
		}
	}

	public static void main(String[] args) throws Exception {
	
		Server server = new Server(12345);
		Scanner read = new Scanner(System.in);
		System.out.println("Aperte enter para começar o jogo");
		read.nextLine();
		
		Game game = new Game( server, "testando", Player.players);
		game.run();
	}

}
