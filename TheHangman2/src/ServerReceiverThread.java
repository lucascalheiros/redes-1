import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerReceiverThread implements Runnable {
	private Server server;
	private Scanner channel;
	
	public ServerReceiverThread(Server server) {
		this.server = server;
	}

	public void run() {
		try{
			String message;
			int id;
			System.out.println("na frent");
			

			while (true) {	
				for (int i = 0; i < server.totalClients(); i++) {
					Socket actualClient = server.getClient(i);
					channel = new Scanner(actualClient.getInputStream());
					while (channel.hasNextLine()) {
					
						System.out.println(channel.nextLine());
					}
				}/*
				System.out.println("entrww");
				message = channel.nextLine();
				id = Integer.valueOf(message.substring(0, message.indexOf(' ')));
				message = message.substring(message.indexOf(' ') + 1, message.length());
				System.out.println(server.getPlayer(id));
				System.out.println(message);
				System.out.println("entrouwww");*/

	
			}
		} catch (Exception e) {
			
		}
	}
}
