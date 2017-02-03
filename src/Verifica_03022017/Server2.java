package Verifica_03022017;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Server2(){
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
		Server2 s = new Server2();
		boolean campo[][] = new boolean[3][3];
		campo = s.init_matrice(3, 3);
		System.out.println("Server in ascolto sulla porta 4444");
		s.listen();
		String x, y;
		String str;
		int navi = 0;
		do{
			x = s.receive();
			y = s.receive();
			
			if(campo[Integer.parseInt(x)][Integer.parseInt(y)])
				s.send("Nave colpita");
			else
				s.send("Nave non colpita");
			
			for(int i = 0; i < 3; i++){
				for(int c = 0; c < 3; c++){
					if(campo[c][i])
						navi++;
					
				}
			}
			
			if(navi > 0){
				s.send("nv");
				navi = 0;
			} else {
				s.send("vinto");
				navi = 0;
			}
			
			System.out.println("Invia x: ");
			s.send(new BufferedReader(new InputStreamReader(System.in)).readLine());
			System.out.println("Invia y: ");
			s.send(new BufferedReader(new InputStreamReader(System.in)).readLine());
			
			str = s.receive();
			System.out.println("Risposta dal client: " + str);
			if(s.receive().equals("vinto"))
				System.out.println("Hai vinto");
			
		} while(!x.equals(".") && !y.equals(".") && !str.equals("."));
		
		
		
		
	}
}
