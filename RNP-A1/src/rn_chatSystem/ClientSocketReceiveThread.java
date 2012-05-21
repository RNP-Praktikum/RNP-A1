package rn_chatSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ClientSocketReceiveThread extends Thread {
	
	DatagramSocket socket;
	public ClientSocketReceiveThread(DatagramSocket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		 BufferedReader in = null;
		 DatagramPacket packet = null;
		 byte[] buf = new byte[60];
		 
		while(true) {
			
			packet = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				this.interrupt();
			}
			
			//TODO Ausgabe in GUI
			
			
			System.out.println(packet.getData().toString());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
