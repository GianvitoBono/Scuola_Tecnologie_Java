package topSecret;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PescEPescClient extends Thread {
	@Override
	public void run() {
		try {
		DatagramSocket socket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		String sentence = "Vuò fa pesc' e pesc'?";
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		socket.send(sendPacket);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) {
		while(true)
			new PescEPescClient().start();
	}
}