

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	Socket cliente;
	Scanner serverInput;
	PrintStream serverOutput;
	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket cliente = new Socket("127.0.0.1", 12345);
		Scanner serverInput = new Scanner(cliente.getInputStream());
		PrintStream serverOutput = new PrintStream(cliente.getOutputStream());
		
		
		System.out.println("O cliente se conectou ao servidor!");
		ClientSenderThread clientSender = new ClientSenderThread(serverOutput);
		Thread tClientSender = new Thread(clientSender);
		tClientSender.start();
		while(serverInput.hasNextLine()) {
			System.out.println(serverInput.nextLine());
			
		}
/*
		serverOutput.close();
		cliente.close();*/

	}
}
