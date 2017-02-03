package Esercizi_Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<String> sharedPool;

	public ServerThread(Socket socket, ArrayList<String> sharedPool) {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.sharedPool = sharedPool;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		try {
			String str;
			do {
				str = in.readLine();
				synchronized (sharedPool) { sharedPool.add(str); }
				out.println(str);
			} while (!str.equals("."));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
