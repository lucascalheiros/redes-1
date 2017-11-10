import java.io.IOException;

public class ServerAddClientThread implements Runnable {
	Server server;

	public ServerAddClientThread(Server server) {
		this.server = server;

	}

	public void run() {
		try {
			while (true) {
				server.acceptClient();
				System.out.println("Novo Cliente Conectado");
			}
		} catch (IOException e) {
			System.err.println("Erro ao adicionar cliente");
		}

	}

}
