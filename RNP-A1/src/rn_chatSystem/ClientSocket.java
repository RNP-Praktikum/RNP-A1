package rn_chatSystem;

import static rn_chatSystem.ClientSocket.HOST_NAME;
import static rn_chatSystem.ClientSocket.PORT;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientSocket {

	public static final String HOST_NAME = "Kathrin-PC";
	public static final int PORT = 50000;
	public static List<String> users = new LinkedList<String>();
	public static boolean loggedIn = false;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		DatagramSocket clientSocket = new DatagramSocket(50001);
		DatagramPacket packet = null;
		byte[] buf = null;
		String chatName = "Test";

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));
		String userInput;

		Socket tcpSocket = null;
		PrintWriter tcpOut = null;
		BufferedReader tcpIn = null;

		try {
			tcpSocket = new Socket(HOST_NAME, PORT);
			tcpOut = new PrintWriter(tcpSocket.getOutputStream(), true);
			tcpIn = new BufferedReader(new InputStreamReader(
					tcpSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + HOST_NAME);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for " + "the connection to: "
					+ HOST_NAME);
			System.exit(1);
		}

		new ClientSocketListThread(chatName, tcpSocket).start();
		new ClientSocketReceiveThread(clientSocket).start();

		while ((userInput = stdIn.readLine()) != null) {
			if (userInput.equals("BYE")) {
				tcpOut.println("BYE");
				if (tcpIn.readLine().equals("BYE")) {
					loggedIn = false;
					break;
				} else {
					System.out.println("ERROR Cannot Close Connection");
				}

			}
			
			if (userInput.startsWith("ERROR")) {
				System.out.println(userInput);
				System.out.println("logged out");
				loggedIn = false;
				break;
			}
			
			buf = new byte[83];
			if (userInput.length() > 60) {
				userInput = userInput.substring(0,60);
			} 
			userInput = chatName + ": " + userInput + "\n";
			buf = userInput.getBytes();

			for (int i = 0; i < users.size(); i = i + 2) {
				InetAddress ip = InetAddress.getByName(users.get(i)
						.substring(1));
				packet = new DatagramPacket(buf, buf.length, ip, 50001);
				clientSocket.send(packet);
			}

		}

		tcpOut.close();
		tcpIn.close();
		stdIn.close();

		clientSocket.close();
		tcpSocket.close();
	}
}