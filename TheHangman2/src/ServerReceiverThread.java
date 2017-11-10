import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerReceiverThread implements Runnable {
	private Server server;
	private Scanner channel;

	public ServerReceiverThread(Server server, Scanner channel) {
		this.server = server;
		this.channel = channel;
	}

	public void run() {

		String message;
		int id = 0;

		while (true) {
			System.out.println(server.totalClients());
			message = channel.nextLine();
			id = Integer.valueOf(message.substring(0, message.indexOf(' ')));
			message = message.substring(message.indexOf(' ') + 1, message.length());
			System.out.println(server.getPlayer(id - 1));
			System.out.println(message);

		}
	}
}
