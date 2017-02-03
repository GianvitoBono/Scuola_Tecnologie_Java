package Verifica_03022017;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Server1(){
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listen() {
		try {
			socket = serverSocket.accept();
			serverSocket.close();
			System.out.println("Client connesso");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
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

	public static void main(String args[]) {
		Server1 s = new Server1();
		System.out.println("Server in ascolto sulla porta 4444");
		s.listen();

		String str = s.receive();
		if(Integer.parseInt(str) <= 10)
			s.send(String.valueOf(Integer.parseInt(str)*20));
		else if(Integer.parseInt(str) > 10 && Integer.parseInt(str) < 50)
			s.send(String.valueOf((Integer.parseInt(str)*20)-(Integer.parseInt(str)*20)*0.1));
		else if(Integer.parseInt(str) > 50)
			s.send(String.valueOf((Integer.parseInt(str)*20)-(Integer.parseInt(str)*20)*0.2));
	}
}
