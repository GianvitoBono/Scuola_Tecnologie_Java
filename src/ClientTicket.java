import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTicket {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Socket connect() {
		try {
			socket = new Socket(InetAddress.getLocalHost(), 4444);
			return socket;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
		ClientTicket c = new ClientTicket();
		c.connect();
		String str;
		do {
			str = c.receive();
			if (!str.equals("."))
				System.out.println("[+] Risposta ricevuta: " + str);
		} while (str != null && !str.equals("."));
		c.close();
		System.out.println("[+] Connessione col server chiusa");

	}
}
