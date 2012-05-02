package rn_multiClient;

import java.net.*;
import java.util.*;
import java.io.*;

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
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String inputLine, outputLine;

			while ((inputLine = in.readLine()) != null) {
				overallMessageCount += 1;
				messageCount +=1;
				System.out.println("---------------------------------------");
				System.out.println("Client: " + socket.toString());
				System.out.println("aktive since " + ((System.currentTimeMillis() - startTime) / 1000) + " seconds");
				System.out.println(new Date(System.currentTimeMillis()));
				System.out.println("Overall Message Count: "+  overallMessageCount);
				System.out.println("Message Count from Client: " + messageCount);
				System.out.println("Message: " + inputLine);
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
