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
			byte[] sendData = new byte[4128];
			String sentence = "C1asHdcLKvdFWABCtHCLqnIntpsX1qdQ1woxLq4hToLYVJE5aD1Mu5"
					+ "csA66wWVpsIErmKPvPGStnAGlMlrHKk27M5mZMGQFFmMvQ6upGSAslkQJQM3WQ"
					+ "MXWPVhkn9x8n8fL0uwF6mA8N9tkNnfj3ortiZoZQIVN7Lt7JhI3AKCtCtKn6NG"
					+ "5WTz337rdNAOpiNSxemR7KrFUGNCyJDVkMHkVKGiWCDMoq3RySYdakR1FslSod"
					+ "aSxHcYr7Ou70rH03HfK8JWVM3IZdcsXBE9GGeAb1xBBxAShv0NaiiTkn0Txoc4"
					+ "Lrz42GsUXrgiUonLD4BizazUW47kAAhUPrmA59q71exSfjG4rWlOiyNOOCyxNb"
					+ "T4YhqS7zNls1zc8N9tgK1xnIH7Csg5VTq0ketsaPeO0w4MMQCWnS7Ymk3ec5yE"
					+ "YgQaqTeYL7Q6QiKaYm08C5euZ4jwLiyZevoRKRJStYZqeUju4qRZCxxwePG05E"
					+ "QDKHPmpU8IbGESuam96P2Z6mpaDRV81hs2whVGj6AMqp0bVRDAp2yCVohDK9YD"
					+ "AOwUkaWXVkJahcgi5AAnFc1XkhmyZwfCChrGyaiaqrUDg38BMy0BLaVUzO2p80"
					+ "CskIIsKswIshMbeStOENlBosiCZL8uo4bErjYRnPWdyNBmdyV43Yxc9HIv65vZ"
					+ "kAtnVM8wZyv7ZpwOR7pTNINwyGKRLGBIaostj0PQcj8DaPDEqVij0v9cbxKKmw"
					+ "SoNniCRC4YctepHaSudjYKtzR4fzlfj6xvGq7fxOHcaK2OwBs0fJDRgjrCAs9r"
					+ "z9luJX6CEKoVJHxJm3d7F9BVobtguXUVYHze4tLGLI3IwaYPoJ13omM6uJZFI8"
					+ "cGemvrIyMsvHhLqVozH34kJCQjmdGeM7AprioxkY7YkZMI5asXiaWC1r0MOhjL"
					+ "RUyL0hAHOksLbq3GTSZL4xUyjBAaWyjq28f1YxrzeEGBAeCFE9vSyBOlwNzAiu"
					+ "VIXxKqvBLCSLatTIiCO26iVpKDnZZP1N4P8ALhHKz5arwLa1axNOUcMKUlbfHT"
					+ "7qqmOZf5MlXZvSF9iS59HrUsb8ZNRjbUxzYDbyycexyh9YuDh0TUYdWel82n4U";
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