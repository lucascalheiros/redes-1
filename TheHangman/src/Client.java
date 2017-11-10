import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private static Socket cliente;
	private static Scanner serverInput;
	private static PrintStream serverOutput;
	private static final String ipDefault = "127.0.0.1";
	private static final int portDefault = 12345;
	private static int id = -1;
	private static String nome;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String ip;
		String message = "";
		int port;
		System.out.println("\t\t\tThe Hangmam");
		System.out.println("\n\n");
		System.out.println("Digite seu nome por favor:");
		nome = input.nextLine();
		
		while(true) {
			try {
				
				System.out.println("Olá, digite o IP do computador onde se encontra o servidor do jogo(caso 127.0.0.1 digite 0): ");
				ip = input.nextLine();
				if(ip.equals("0")) {
					ip = ipDefault;
				}
				
				System.out.println("\n");
				
				System.out.println("Olá, digite a porta do computador onde se encontra o servidor do jogo(caso 12345 digite 0): ");
				port = input.nextInt();
				if(port == 0) {
					port = portDefault;
				}
				
				cliente = new Socket(ip, port);
				serverInput = new Scanner(cliente.getInputStream());
				serverOutput = new PrintStream(cliente.getOutputStream());
				
				break;
				
			} catch(Exception e) {
				
				System.err.println("ERRO: Verifique se as informações acima estão corretas");
				
			}
			
		}		
		
		serverOutput.println(nome);
		System.out.println("O cliente se conectou ao servidor!");

		
		ClientSenderThread clientSender = new ClientSenderThread(serverOutput);
		Thread tClientSender = new Thread(clientSender);
		tClientSender.start();
		

		while(serverInput.hasNextLine()) {
			if(id == -1) {
				message = serverInput.nextLine();
				id = Integer.valueOf(message.substring(0, message.indexOf(' ')));
				clientSender.setId(id);
				message = message.substring(message.indexOf(' ') - 1, message.length() );
			}
			else {
				message = serverInput.nextLine();
			}
			System.out.println(message);
			
		}

		try {
			input.close();
			serverOutput.close();
			cliente.close();
		} catch (Exception e) {
			System.err.println("ERRO AO FECHAR CONEXÃO");

		}

	}
	
	
}
