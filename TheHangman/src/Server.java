
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private ServerSocket server;
	private List<Socket> clients = new ArrayList<Socket>();
	private List<PrintStream> outChannels = new ArrayList<PrintStream>();

	public Server(int port) {
		try {
			server = new ServerSocket(port);	//inicializa o server
			ServerAddClientThread accepter = new ServerAddClientThread(this);	//thread para aceitar clientes
			Thread tClientAccepter = new Thread(accepter);
			tClientAccepter.start();

			ServerReceiverThread receiver = new ServerReceiverThread(this);		//thread para receber mensagens dos clientes
			Thread tClientReceiver = new Thread(receiver);
			tClientReceiver.start();
			
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
		clients.add(client);
		outChannels.add(new PrintStream(client.getOutputStream()));
	}
	
	public int totalClients() {
		return clients.size();
	}
	
	public Socket getClient(int i) {
		return clients.get(i);
	}
	
}
