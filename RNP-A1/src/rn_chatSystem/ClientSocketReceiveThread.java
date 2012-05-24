package rn_chatSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;
import static rn_chatSystem.ClientSocket.*;

public class ClientSocketReceiveThread extends Thread {
	Chat gui;
	DatagramSocket socket;
	public ClientSocketReceiveThread(DatagramSocket socket, Chat gui) {
		this.socket = socket;
		this.gui = gui;
	}
	
	public void run() {
		
		 DatagramPacket packet = null;
		 byte[] buf = new byte[83];
		 
		while(loggedIn) {
			System.out.println("Starting Receive Thread");
			packet = new DatagramPacket(buf, buf.length);
			try {
				System.out.println("try receive package");
				socket.receive(packet);
//				System.out.println("Packet received");
			} catch (IOException e) {
				gui.getError().setText("ERROR cannot receive udp Package");
				break;
			}
			
			String message = new String(packet.getData());
			int endIndex = message.indexOf('\n');
			String messages = gui.getMessages().getText();
			gui.getMessages().setText(messages.concat(message.substring(0, endIndex) + "\n"));
			System.out.println(message.substring(0, endIndex));
		}
		this.interrupt();
		
	}
}
