import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerReceiverThread implements Runnable {
	private Server server;

	public ServerReceiverThread(Server server) {
		this.server = server;

	}

	// alterar***********************************
	public void run() {
		try {
			boolean verif;
			Scanner channel;
			Socket actualClient;
			while (true) {
				// parece que o compilador otimiza o codigo se não tiver o comando abaixo
				System.out.print("");

				// função que envia a mensagem dos clientes ao servidor de forma alternada em
				// ordem de entrada ao servidor do cliente
				for (int i = 0; i < server.totalClients(); i++) {
					actualClient = server.getClient(i);
					channel = new Scanner(actualClient.getInputStream());
					verif = true;
					while (verif && channel.hasNextLine()) {
						verif = false;
						System.out.println(channel.nextLine());
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Erro ao receber mensagem");
		}
	}
}
