import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientVocaliConsonanti {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public boolean connect() {
		try {
			socket = new Socket(InetAddress.getLocalHost(), 6789);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
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
		try {
			ClientVocaliConsonanti c = new ClientVocaliConsonanti();
			c.connect();
			String str;
			boolean f = true;
			do {
				System.out.println("[+] Invio un messaggio");
				c.send(new BufferedReader(new InputStreamReader(System.in)).readLine());
				str = c.receive();

				if (!str.equals(".")){
					String[] str_split = str.split(":");
					if(str_split[0] != null){
						System.out.println("[+] Risposta ricevuta: " + str_split[2]);
						System.out.println("[+] Numero vocali: " + str_split[0] + "    Numero consonati: " + str_split[1]);
						if((Integer.parseInt(str_split[0])/2) == Integer.parseInt(str_split[1])){
							c.send(".");
							f = false;
						}
					}
				}				
			} while (str != null && !str.equals(".") && f);
			c.close();
			System.out.println("[+] Connessione col server chiusa");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
