import java.io.PrintStream;
import java.util.Scanner;

public class ClientSenderThread implements Runnable {
	PrintStream serverOutput;
	int id;
	public ClientSenderThread(PrintStream serverOutput, int id) {
		this.serverOutput = serverOutput;
		this.id = id;
	}

	public void run() {
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
		

			serverOutput.println(id+" "+input.nextLine());

		}
		input.close();
	}
}
