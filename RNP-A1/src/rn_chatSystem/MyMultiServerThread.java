package rn_chatSystem;

import java.net.*;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import static rn_chatSystem.MyMultiServer.*;

public class MyMultiServerThread extends Thread {
	private static int overallMessageCount = 0;
	private int messageCount = 0;
	private long startTime = 0;
	private Socket socket = null;

	public MyMultiServerThread(Socket socket) {
		super("MyServerThread");
		this.socket = socket;
	}

	public void run() {

		try {
			//Benutzt für die aktive Zeit vom Client
			startTime = System.currentTimeMillis();

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String inputLine, outputLine;
			String name = "", ip = "";
			boolean loggedIn = false;
			
			while ((inputLine = in.readLine()) != null) {
				overallMessageCount += 1;
				messageCount +=1;
				System.out.println("---------------------------------------");
				System.out.println("Client: " + socket.toString());
				System.out.println("aktive since " + ((System.currentTimeMillis() - startTime) / 1000) + " seconds");
				System.out.println(new Date(System.currentTimeMillis()));
				System.out.println("Overall Message Count: "+  overallMessageCount);
				System.out.println("Message Count from Client: " + messageCount);
				System.out.println("---------------------------------------");
				
				if (inputLine.startsWith("NEW")) {
					try {
						if(!loggedIn) {
						userCount += 1;
						name = inputLine.split(" ")[1];
						ip = socket.getInetAddress().toString();
						if(userMap.containsKey(name)){
							out.println("ERROR chatname already assigned");
							break;
						} else {
							userMap.put(name, ip);
						}
						out.println("OK");
						out.flush();
						loggedIn = true;
						} else {
							out.println("ERROR user already logged in");
							break;
						}
					} catch (RuntimeException e) {
						out.println("ERROR cannot Add User");
						break;
					}
				} else if(inputLine.equals("INFO")) {
					try {
					out.println("LIST " + MyMultiServer.userCount);
					out.flush();
					for(Entry<String,String> e: userMap.entrySet()){
						out.println(e.getValue() + " " + e.getKey());
					}
					} catch (RuntimeException e) {
						out.println("ERROR Incorrect UserList");
						break;
					}
				} else if (inputLine.equals("BYE")) {
					out.println("BYE");
					userCount--;
					deleteUser(name, ip);
					loggedIn = false;
					break;
				} else {
					out.println("ERROR Incorrect Argument");
					deleteUser(name, ip);
					userCount--;
					loggedIn = false;
					break;
				}
			}
			out.close();
			in.close();
			socket.close();

			System.out.println("All Closed");
		} catch (IOException e) {
			System.out.println("Something went wrong!");
//			e.printStackTrace();
		}

	}
	
	
	//TODO Gleiche namen????
	private boolean deleteUser(String name, String ip) {
		boolean result = false;
		userMap.remove(name);
		
		return result;
	}

}
