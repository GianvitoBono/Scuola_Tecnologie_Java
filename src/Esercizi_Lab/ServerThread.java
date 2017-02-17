package Esercizi_Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public ServerThread(Socket socket) {
		try {
			this.socket = socket;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String receive() {
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void send(String message) {
		try {
			out.println(message);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void run() {
		try {
			String str = in.readLine();
			
			while (!str.equals(".")) {
				System.out.println("Ricevuto: " + str);
				out.println(str);
				str = in.readLine();
			}
			
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
