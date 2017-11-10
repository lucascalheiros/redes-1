import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Server server = new Server(12345);
		Scanner read = new Scanner(System.in);
		String message;
		new TurnManager(0);
		TurnManager.mapTurnMessage.get(0).setTurn();
		TurnManager.mapTurnMessage.get(0).putMessage('o');
		

		System.out.println(TurnManager.mapTurnMessage.get(0).readMessage());
	}
}
