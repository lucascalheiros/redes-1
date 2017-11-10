
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Server {
	private ServerSocket server;
	private HashMap<Integer, Socket> clients = new HashMap<Integer, Socket>();
	private List<PrintStream> outChannels = new ArrayList<PrintStream>();
	private List<Scanner> inChannels = new ArrayList<Scanner>();
	private int id = 0;

	public Server(int port) {
		try {
			server = new ServerSocket(port); // inicializa o server
			ServerAddClientThread accepter = new ServerAddClientThread(this); // thread para aceitar clientes
			Thread tClientAccepter = new Thread(accepter);
			tClientAccepter.start();

			System.out.println("Servidor criado com sucesso");
		} catch (IOException e) {
			System.err.println("Erro ao criar servidor");
		}
	}

	// envia mensagem para todos os clientes
	public void sendMessage(String message) {
		for (PrintStream client : outChannels) {
			client.println(message);
		}
	}

	// aceita clientes
	public void acceptClient() throws IOException {
		Socket client = server.accept();
		PrintStream output = new PrintStream(client.getOutputStream());
		Scanner input = new Scanner(client.getInputStream());
		
		
		if (input.hasNextLine()) {
			Player player = new Player(input.nextLine(), id);
			Player.addPlayer(id, player);
			clients.put(id, client);
			outChannels.add(output);
			inChannels.add(input);
			new TurnManager(id);
			output.println(id + " Player conectado\n" + "Player: " + player.getNome() + "\tID: " + player.getId());
			id++;
		}

		ServerReceiverThread receiver = new ServerReceiverThread(this, input);
		Thread tClientReceiver = new Thread(receiver);
		tClientReceiver.start();
	}

	public int totalClients() {
		return clients.size();
	}

	public Socket getClient(int id) {
		return clients.get(id);
	}

}
