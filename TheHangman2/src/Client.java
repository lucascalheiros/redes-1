

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private Socket cliente;
	private Scanner serverInput;
	private PrintStream serverOutput;
	private int id;
	private String nome;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		//
		String nome;
		Scanner input = new Scanner(System.in);
		System.out.println("Olá, diga-me seu nome:");
		nome = input.nextLine();
		//
		
		//
		Socket cliente = new Socket("127.0.0.1", 12345);
		Scanner serverInput = new Scanner(cliente.getInputStream());
		PrintStream serverOutput = new PrintStream(cliente.getOutputStream());
		serverOutput.println(nome);
		int id = serverInput.nextInt();
		//
		
		System.out.println("O cliente se conectou ao servidor!");
		
		//
		ClientSenderThread clientSender = new ClientSenderThread(serverOutput, id);
		Thread tClientSender = new Thread(clientSender);
		tClientSender.start();
		//
		
		while(serverInput.hasNextLine()) {
			System.out.println(serverInput.nextLine());
			
		}
		
		input.close();
		serverInput.close();
		serverOutput.close();
		cliente.close();

	}
}
