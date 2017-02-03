import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTicket {

	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public void listen() throws IOException {
		serverSocket = new ServerSocket(4444);
		socket = serverSocket.accept();
		serverSocket.close();
		System.out.println("[+] Client connesso al server");
	}

	public String receive() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			return in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void send(String message) {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean close() {
		try {
			socket.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("[+] Creo il server");
			ServerTicket s = new ServerTicket();
			System.out.println("[+] Server creato");
			s.listen();
			int i = 0;
			while (!s.receive().equals(".")) {
				System.out.println("[+] Invio " + i++);
				s.send(String.valueOf(i++));
			}			
			
			s.close();
			System.out.println("[+] Connessione col client chiusa");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
