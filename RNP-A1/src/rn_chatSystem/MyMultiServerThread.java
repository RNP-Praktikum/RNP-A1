package rn_chatSystem;

import java.net.*;
import java.util.*;
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
						MyMultiServer.userCount += 1;
						MyMultiServer.users.add(socket.getInetAddress().toString());
						MyMultiServer.users.add(inputLine.split(" ")[1]);
						name = inputLine.split(" ")[1];
						ip = socket.getInetAddress().toString();
						out.println("OK");
						out.flush();
						loggedIn = true;
						} else {
							out.println("ERROR user already logged in");
						}
					} catch (RuntimeException e) {
						out.println("ERROR cannot Add User");
					}
				} else if(inputLine.equals("INFO")) {
					try {
					out.println("LIST " + MyMultiServer.userCount);
					out.flush();
					for(int i = 0; i < users.size(); i = i + 2) {
						out.println(users.get(i) + " " + users.get(i + 1));
					}
					} catch (RuntimeException e) {
						out.println("ERROR Incorrect UserList");
					}
				} else if (inputLine.equals("BYE")) {
					out.println("BYE");
//					userCount--;
//					deleteUser(name, ip);
//					loggedIn = false;
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
			e.printStackTrace();
		}

	}
	
	
	//TODO Gleiche namen????
	private boolean deleteUser(String name, String ip) {
		boolean result = false;
		for(int i = 0; i < users.size(); i = i + 2) {
			if(users.get(i).equals(ip) && users.get(i + 1).equals(name)) {
				users.remove(i + 1);
				users.remove(i);
				result = true;
			}
		}
		
		return result;
	}

}
