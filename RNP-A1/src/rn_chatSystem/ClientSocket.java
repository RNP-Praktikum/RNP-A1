package rn_chatSystem;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientSocket {

	String hostName;
	public static final int PORT = 50000;
	public static List<String> users = new LinkedList<String>();
	public static boolean loggedIn = false;
	String chatName;
	DatagramPacket packet = null;
	byte[] buf = null;

	Socket tcpSocket = null;
	PrintWriter tcpOut = null;
	BufferedReader tcpIn = null;
	String userInput;
	DatagramSocket clientSocket;
	Chat gui;
	public ClientSocket(String chatName,String hostName, Chat gui) {
		this.chatName = chatName;
		this.hostName = hostName;
		this.gui = gui;


		try {
			tcpSocket = new Socket(InetAddress.getByName(hostName), PORT);
			tcpOut = new PrintWriter(tcpSocket.getOutputStream(), true);
			tcpIn = new BufferedReader(new InputStreamReader(
					tcpSocket.getInputStream()));
			System.out.println("TCP Socket established");
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for " + "the connection to: "
					+ hostName);
			System.exit(1);
		}

		new ClientSocketListThread(chatName, tcpSocket, gui).start();
		
		//Waiting for SocketListThread to log in
		while(!loggedIn){try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			gui.getError().setText("ERROR cannot wait for Login");
		}};
		try {
			clientSocket = new DatagramSocket(50001);
			new ClientSocketReceiveThread(clientSocket, gui).start();
		} catch (SocketException e1) {
			gui.getError().setText("ERROR start Receive Thread");
		}
	
	}
	
	public void send(String userInput) {
		if (userInput.equals("BYE")) {
			tcpOut.println("BYE");
		
				try {
					if (tcpIn.readLine().equals("BYE")) {
						disconnect();
					} else {
						gui.getError().setText("ERROR Server didnt send disconnect message");
					}
				} catch (IOException e) {
					gui.getError().setText("ERROR cannot Disconnect");
				}
		

		} else {
		
		buf = new byte[83];
		if (userInput.length() > 60) {
			userInput = userInput.substring(0,60);
		} 
		userInput = chatName + ": " + userInput + "\n";
		buf = userInput.getBytes();

		for (int i = 0; i < users.size(); i = i + 2) {
			InetAddress ip;
			try {
				ip = InetAddress.getByName(users.get(i));
				packet = new DatagramPacket(buf, buf.length, ip, 50001);
				clientSocket.send(packet);
			} catch (UnknownHostException e) {
				gui.getError().setText("ERROR cannot SEND via UDP");
			} catch (IOException e) {
				gui.getError().setText("ERROR cannot SEND via UDP");
			}
			
			
		}}
	}
	
	private void disconnect(){
		loggedIn = false;
		tcpOut.close();
		try {
			tcpIn.close();
		} catch (IOException e) {
			gui.getError().setText("ERROR cannot close TCP in Stream");
		}
//	stdIn.close();

		clientSocket.close();
		try {
			tcpSocket.close();
		} catch (IOException e) {
			gui.getError().setText("ERROR cannot close TCP Socket");
		}
		System.exit(0);
	}
	
//	public static void main(String[] args) throws IOException {
//		Chat gui = new Chat();
//		DatagramSocket clientSocket = new DatagramSocket(50001);
//		DatagramPacket packet = null;
//		byte[] buf = null;
//		String chatName = "Test";
//
//		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
//				System.in));
//		String userInput;
//
//		Socket tcpSocket = null;
//		PrintWriter tcpOut = null;
//		BufferedReader tcpIn = null;
//
//		try {
//			tcpSocket = new Socket(HOST_NAME, PORT);
//			tcpOut = new PrintWriter(tcpSocket.getOutputStream(), true);
//			tcpIn = new BufferedReader(new InputStreamReader(
//					tcpSocket.getInputStream()));
//		} catch (UnknownHostException e) {
//			System.err.println("Don't know about host: " + HOST_NAME);
//			System.exit(1);
//		} catch (IOException e) {
//			System.err.println("Couldn't get I/O for " + "the connection to: "
//					+ HOST_NAME);
//			System.exit(1);
//		}
//		System.out.println("Start List Thread");
//		new ClientSocketListThread(chatName, tcpSocket, gui).start();
//		System.out.println("Start Receive Thread");
//		new ClientSocketReceiveThread(clientSocket, gui).start();
//
//		while ((userInput = stdIn.readLine()) != null) {
//			if (userInput.equals("BYE")) {
//				tcpOut.println("BYE");
//				if (tcpIn.readLine().equals("BYE")) {
//					loggedIn = false;
//					break;
//				} else {
//					System.out.println("ERROR Cannot Close Connection");
//				}
//
//			}
//			
//			if (userInput.startsWith("ERROR")) {
//				System.out.println(userInput);
//				System.out.println("logged out");
//				loggedIn = false;
//				break;
//			}
//			
//			buf = new byte[83];
//			if (userInput.length() > 60) {
//				userInput = userInput.substring(0,60);
//			} 
//			userInput = chatName + ": " + userInput + "\n";
//			buf = userInput.getBytes();
//
//			for (int i = 0; i < users.size(); i = i + 2) {
//				InetAddress ip = InetAddress.getByName(users.get(i)
//						.substring(1));
//				packet = new DatagramPacket(buf, buf.length, ip, 50001);
//				clientSocket.send(packet);
//			}
//
//		}
//
//		tcpOut.close();
//		tcpIn.close();
//		stdIn.close();
//
//		clientSocket.close();
//		tcpSocket.close();
//	}
}