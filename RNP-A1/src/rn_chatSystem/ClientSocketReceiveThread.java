package rn_chatSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ClientSocketReceiveThread extends Thread {

	
	public void run() {
		DatagramSocket socket = null;
		 BufferedReader in = null;
		 DatagramPacket packet = null;
		 byte[] buf = new byte[60];
		 
		try {
			socket = new DatagramSocket(50001);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			
			packet = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//TODO Ausgabe in GUI
			System.out.println(packet.getData());
		}
		
		
	}
}
