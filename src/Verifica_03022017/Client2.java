package Verifica_03022017;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
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

	public boolean[][] init_matrice(int x, int y){
		boolean[][] mat = new boolean[x][y];
		for(int i = 0; i < x; i++){
			for(int c = 0; c < y; c++){
				if((int)(Math.random()*100)%2 == 0)
					mat[i][c] = false;
				else
					mat[i][c] = true;
			}
		}
		return mat;
	}
	
	public static void main(String args[]) throws IOException {
		Client2 c = new Client2();
		boolean campo[][] = new boolean[3][3];
		campo = c.init_matrice(3, 3);
		c.connect();
		String x, y;
		String str;
		int navi = 0;
		System.out.println("Connesso al server, per chiudere la connessione mandare il punto '.' \n Invia x: ");
		do{
			System.out.println("Invia x: ");
			c.send(new BufferedReader(new InputStreamReader(System.in)).readLine());
			System.out.println("Invia y: ");
			c.send(new BufferedReader(new InputStreamReader(System.in)).readLine());
			str = c.receive();
			System.out.println("Risposta dal server: " + str);
			
			if(c.receive().equals("vinto"))
				System.out.println("Hai vinto");
			
			x = c.receive();
			y = c.receive();
			
			if(campo[Integer.parseInt(x)][Integer.parseInt(y)])
				c.send("Nave colpita");
			else
				c.send("Nave non colpita");
			
			for(int i = 0; i < 3; i++){
				for(int k = 0; k < 3; k++){
					if(campo[k][i])
						navi++;
					
				}
			}
			
			if(navi > 0){
				c.send("nv");
				navi = 0;
			} else {
				c.send("vinto");
				navi = 0;
			}
			
		} while(str != null);
	}
}
