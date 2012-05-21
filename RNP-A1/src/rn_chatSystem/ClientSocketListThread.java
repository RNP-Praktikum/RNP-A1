package rn_chatSystem;

import static rn_chatSystem.ClientSocket.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
public class ClientSocketListThread extends Thread {
	
	String chatName;
	Socket socket;
	  PrintWriter out = null;
	    BufferedReader in = null;
	
	public ClientSocketListThread(String chatName, Socket socket) {
//		super("MyServerThread");
		this.chatName = chatName;
		this.socket = socket;
	}

	public void disconnect() {
		
	}
	
	public void run() {
		 try {
			out = new PrintWriter(socket.getOutputStream(), true);
			 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    
		
		
		String input = "";
		boolean loggedIn = false;
		int userCount;
		 
	        
	        out.println("NEW " + chatName);
	        try {
				input = in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        if(input.equals("OK")) {
	        	loggedIn = true;
	        } else {
	        	System.out.println("ERROR cannot log in");
	        }
	        
		while(loggedIn) {
			  out.println("INFO");
			  try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		        if(input.startsWith("LIST")) {
		    		userCount = Integer.parseInt(input.split(" ")[1]);
//		    		System.out.println(input);
		    		users.clear();
		    		for(int i = 0; i < userCount; i++) {
		    			try {
							input = in.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    			String[] entry = input.split(" ");
		    			users.add(entry[0]);
		    			users.add(entry[1]);
//		    			System.out.println(users);
		    		}
		   
			}
		    try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
			
		   	
		
			
	}
	
}