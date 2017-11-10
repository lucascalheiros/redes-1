
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
	private ServerSocket server;
	private List<Player> players = new ArrayList<Player>();
	private List<Socket> clients = new ArrayList<Socket>();
	private List<PrintStream> outChannels = new ArrayList<PrintStream>();
	private List<Scanner> inChannels = new ArrayList<Scanner>();
	private int id = 0;
	
	public Server(int port) {
		try {
			server = new ServerSocket(port);	//inicializa o server
			ServerAddClientThread accepter = new ServerAddClientThread(this);	//thread para aceitar clientes
			Thread tClientAccepter = new Thread(accepter);
			tClientAccepter.start();
			
		
			
			System.out.println("Servidor criado com sucesso");
		} catch (IOException e) {
			System.err.println("Erro ao criar servidor");
		}
	}

	//envia mensagem para todos os clientes
	public void sendMessage(String message) {
		for(PrintStream client: outChannels) {
			client.println(message);
		}
	}
	
	//aceita clientes
	public void acceptClient() throws IOException {
		Socket client = server.accept();
		PrintStream output = new PrintStream(client.getOutputStream());
		Scanner input = new Scanner(client.getInputStream());
		Player player = new Player(input.nextLine(), ++id);
		clients.add(client);
		outChannels.add(output);
		inChannels.add(input);
		players.add(player);
		output.println(id);
		
		ServerReceiverThread receiver = new ServerReceiverThread(this, input);	
		Thread tClientReceiver = new Thread(receiver);
		tClientReceiver.start();

	}
	
	public int totalClients() {
		return clients.size();
	}
	
	public Socket getClient(int i) {
		return clients.get(i);
	}
	
	public Player getPlayer(int i){
		return players.get(i);
	}
	
	public Scanner getInChannel(int i){
		return inChannels.get(i);
	}
}
