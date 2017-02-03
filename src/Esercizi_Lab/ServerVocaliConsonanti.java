package Esercizi_Lab;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerVocaliConsonanti {

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
			ServerVocaliConsonanti s = new ServerVocaliConsonanti();
			System.out.println("[+] Server creato");
			s.listen();
			String str = s.receive().toLowerCase();
			int voc = 0, cons = 0;
			while (!str.equals(".")) {
				System.out.println("[+] Risposta ricevuta: " + str);
				char str2[] = str.toCharArray();
				for(int i = 0; i < str2.length; i++){
					if(str2[i] == 'a' || str2[i] == 'e' || str2[i] == 'i' || str2[i] == 'o' || str2[i] == 'u')
						voc++;
					else
						cons++;
				}
				s.send(voc + ":" + cons + ":" + str.toUpperCase());
				voc=0; cons=0;
				str = s.receive();
			}
			s.send(".");
			s.close();
			System.out.println("[+] Connessione col client chiusa");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
