
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server implements Runnable {
	ServerSocket server;
	List<Socket> clients = new ArrayList<Socket>();
	List<Scanner> channels = new ArrayList<Scanner>();

	Server(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("Servidor criado com sucesso");
		} catch (IOException e) {
			System.err.println("Erro ao criar servidor");
		}
	}

	// adiciona clientes ao servidor
	public void run() {
		while (true) {
			try {
				while (true) {
					Socket client = server.accept();
					clients.add(client);
					channels.add(new Scanner(client.getInputStream()));
					System.out.println("Novo cliente conectado");
				}
			} catch (IOException e) {
				System.err.println("Erro ao adicionar um cliente");
			}
		}
	}

	// recebe mensagens intercalando clientes em ordem de entrada ao servidor
	public void receiveMsg() throws IOException {
		boolean verif;
		Scanner channel;
		while (true) {
			// parece que o compilador otimiza o codigo se não tiver o comando abaixo
			System.out.print("");

			// turnos entre clientes
			for (int i = 0; i < channels.size(); i++) {
				channel = channels.get(i);
				verif = true;
				while (verif && channel.hasNextLine()) {
					verif = false;
					System.out.println(channel.nextLine());
				}
			}
		}
	}
}
