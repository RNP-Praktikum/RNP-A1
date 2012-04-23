package multiClient;


import java.io.*;
import java.net.*;

public class ClientSocket {
	
	public static final String HOST_NAME = "Kaddi-PC";
	public static final int PORT = 4444;
	
	
    public static void main(String[] args) throws IOException {

        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
        	
        
            clientSocket = new Socket(HOST_NAME, PORT);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + HOST_NAME);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + HOST_NAME);
            System.exit(1);
        }

	BufferedReader stdIn = new BufferedReader(
                                   new InputStreamReader(System.in));
	String userInput;

	while ((userInput = stdIn.readLine()) != null) {
	    out.println(userInput);
	    System.out.println("echo: " + in.readLine());
	}

	out.close();
	in.close();
	stdIn.close();
	clientSocket.close();
    }
}