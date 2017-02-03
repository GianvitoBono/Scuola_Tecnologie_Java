package Verifica_03022017;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public void connect() {
		try {
			socket = new Socket(InetAddress.getLocalHost(), 4444);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String receive() {
		try {
			return in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void send(String message) {
		out.println(message);
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws IOException {
		Client1 c = new Client1();
		c.connect();
		
		String str;
		System.out.println("Inserire quantità da acquistare: ");
		c.send(new BufferedReader(new InputStreamReader(System.in)).readLine());
		str = c.receive();
		System.out.println("Prezzo: " + str);
	}
}
