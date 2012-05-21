package rn_chatSystem;

import java.io.*;
import java.net.*;
import java.util.*;


public class MyMultiServer {

	
	//Port for communication, 
	//must be same as Client
	public static final int PORT = 4446;
	public static List<String> users = new LinkedList<String>();
	public static int userCount = 0;
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
