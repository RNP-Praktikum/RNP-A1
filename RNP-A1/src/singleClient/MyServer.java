package singleClient;


import java.net.*;
import java.util.*;
import java.io.*;

public class MyServer {
	public static final int PORT = 444;
	private static int overallMessageCount = 0;
	private static long startTime = 0;
	
	//Not really useful in single client mode ;)
	private static Map<Socket, Integer> messageCount = new HashMap<Socket,Integer>();
	
	
    public static void main(String[] args) throws IOException {

    	
    	
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: "+PORT+".");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            startTime = System.currentTimeMillis();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
				new InputStreamReader(
				clientSocket.getInputStream()));
        String inputLine, outputLine;
        
        while ((inputLine = in.readLine()) != null) {
        	
        	if (messageCount.get(clientSocket) == null) {
        		messageCount.put(clientSocket, 1);
        	} else {
        		messageCount.put(clientSocket, messageCount.get(clientSocket) + 1);
        	}
        		
        	overallMessageCount += 1;
        	System.out.println("---------------------------------------");
        	System.out.println("Client: " + clientSocket.toString());
        	System.out.println("aktive since " + ((System.currentTimeMillis() - startTime) / 1000) + " seconds");
        	System.out.println(new Date(System.currentTimeMillis()));
        	System.out.println("Overall Message Count: " + overallMessageCount);
        	System.out.println("Message Count from Client: " + messageCount.get(clientSocket));
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
        clientSocket.close();
        serverSocket.close();
        System.out.println("All Closed");
    }
}