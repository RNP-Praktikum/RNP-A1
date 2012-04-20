package multiClient;

import java.net.*;
import java.util.*;
import java.io.*;

public class MyMultiServerThread extends Thread {
	private static int overallMessageCount = 0;
	private int messageC;
	private long startTime = 0;
	// TODO: Warum funktioniert das mit der MAP obwohl in verschiedenen Threads gearbeitet wird? 
	private static Map<Socket, Integer> messageCount = new HashMap<Socket, Integer>();
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
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String inputLine, outputLine;

			while ((inputLine = in.readLine()) != null) {

				if (messageCount.get(socket) == null) {
					messageCount.put(socket, 1);
				} else {
					messageCount.put(socket, messageCount.get(socket) + 1);
				}

				overallMessageCount += 1;
				System.out.println("---------------------------------------");
				System.out.println("Client: " + socket.toString());
				System.out.println("aktive since " + ((System.currentTimeMillis() - startTime) / 1000) + " seconds");
				System.out.println(new Date(System.currentTimeMillis()));
				System.out.println("Overall Message Count: "+  overallMessageCount);
				System.out.println("Message Count from Client: " + messageCount.get(socket));
				System.out.println("Message " + inputLine);
				System.out.println("---------------------------------------");
				outputLine = inputLine.toUpperCase();

				if (outputLine.equals("END")) {
					out.println("abort");
					break;
				}
				out.println(outputLine);
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

}
