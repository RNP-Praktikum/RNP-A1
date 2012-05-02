package rn_multiClient;

import java.io.*;
import java.net.*;


public class MyMultiServer {

	
	//Port for communication, 
	//must be same as Client
	public static final int PORT = 4444;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		boolean listening = true;	
		
		try{
		serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + PORT);
			System.exit(-1);
		} 
		
		//Starts new Thread for every new Client
		while (listening) {
			new MyMultiServerThread(serverSocket.accept()).start();
		}
		
		serverSocket.close();
	}

}
