import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Server server = new Server(12345);
		Scanner read = new Scanner(System.in);
		String message;
		while (read.hasNextLine()) {
			message = read.nextLine();
			server.sendMessage(message);
		}
		read.close();
	}
}
