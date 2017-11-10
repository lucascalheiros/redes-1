import java.util.Scanner;

public class ServerReceiverThread implements Runnable {
	private Server server;
	private Scanner channel;
	private String message;
	private int id;

	public ServerReceiverThread(Server server, Scanner channel) {
		this.server = server;
		this.channel = channel;
	}

	public void run() {
		String[] split;
		while (channel.hasNextLine()) {
			message = channel.nextLine();
			split = message.split(" ");
			id = Integer.valueOf(split[0]);
			message = split[1];
			System.out.println("msg recebida id"+ id);
			TurnManager.mapTurnMessage.get(id).putMessage(message.charAt(0));

		}

	}
}
