package Esercizi_Lab;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/*
 *
 * Creato da Bono Gianvito 20/01/2017
 * 
 */
public class ServerMultiThread {

	private ServerSocket serverSocket;
	public ArrayList<String> sharedPool;
	
	public ServerMultiThread(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	public void listen() {
		try {
			while (true) {
				new ServerThread(serverSocket.accept(), sharedPool).start();
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
