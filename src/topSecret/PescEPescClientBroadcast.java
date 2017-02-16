package topSecret;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PescEPescClientBroadcast extends Thread {
	@Override
	public void run() {
		try {
			DatagramSocket socket = new DatagramSocket();
			socket.setBroadcast(true);
			InetAddress IPAddress = InetAddress.getByName("192.168.0.255");
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
		while (true)
			new PescEPescClientBroadcast().start();
	}
}