package rn_chatSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import static rn_chatSystem.ClientSocket.*;

public class ClientSocketReceiveThread extends Thread {
	
	DatagramSocket socket;
	public ClientSocketReceiveThread(DatagramSocket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		 BufferedReader in = null;
		 DatagramPacket packet = null;
		 byte[] buf = new byte[83];
		 
		while(loggedIn) {
			packet = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);
//				System.out.println("Packet received");
			} catch (IOException e) {
				break;
			}
			
			//TODO Ausgabe in GUI
			
			
			String message = new String(packet.getData());
			int endIndex = message.indexOf('\n');
			System.out.println(message.substring(0, endIndex));
		}
		this.interrupt();
		
	}
}
