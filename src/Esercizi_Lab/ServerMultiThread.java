package Esercizi_Lab;

import java.io.IOException;
import java.net.ServerSocket;

/*
 *
 * Creato da Bono Gianvito 20/01/2017
 * 
 */
public class ServerMultiThread {

	private ServerSocket serverSocket;
	private int port;
	
	public ServerMultiThread(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
	}

	public void listen() {
		try {
			while (true) {
				System.out.println("Server in attesa sulla porta " + port);
				new ServerThread(serverSocket.accept()).start();
				System.out.println("[+] Client connesso");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		try {
			new ServerMultiThread(4444).listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
