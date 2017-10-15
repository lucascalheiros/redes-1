import java.io.PrintStream;
import java.util.Scanner;

public class ClientSenderThread implements Runnable {
	PrintStream serverOutput;
	
	public ClientSenderThread(PrintStream serverOutput) {
		this.serverOutput = serverOutput;

	}

	public void run() {
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {

			serverOutput.println(input.nextLine());

		}
		input.close();
	}
}
