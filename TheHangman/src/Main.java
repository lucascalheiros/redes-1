import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		Server server = new Server(12345);
		Thread t = new Thread(server);
		t.start();
		try {
			server.receiveMsg();
		} catch(IOException e) {
			
		}
		
	}
	
}
