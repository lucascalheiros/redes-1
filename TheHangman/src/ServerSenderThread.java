import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ServerSenderThread implements Runnable {
	private Server server;

	public ServerSenderThread(Server server) {
		this.server = server;

	}
	
	public void run() {
	/*	try {
			Socket client;
			PrintStream channel;
			for(int i = 0; i < server.totalClients(); i++) {
				client = server.getClient(i);
				channel = new PrintStream(client.getOutputStream());
				channel
			}
			
			
		} catch (IOException e) {
			System.err.println("Erro ao enviar mensagem");
		}*/
	}
}
