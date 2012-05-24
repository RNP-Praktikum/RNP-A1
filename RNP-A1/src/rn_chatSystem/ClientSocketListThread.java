package rn_chatSystem;

import static rn_chatSystem.ClientSocket.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketListThread extends Thread {

	String chatName;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in = null;
	Chat gui;

	public ClientSocketListThread(String chatName, Socket socket, Chat gui) {
		// super("MyServerThread");
		this.chatName = chatName;
		this.socket = socket;
		this.gui = gui;
	}

	public void disconnect() {

	}

	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e2) {
			gui.getError().setText("ERROR cannot create BUFFER");
		}

		String input = "";
		// boolean loggedIn = false;
		int userCount;

		out.println("NEW " + chatName);
		try {
			input = in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (input.equals("OK")) {
			loggedIn = true;
//			System.out.println(chatName + " is logged in");
		} else {
			gui.getError().setText("ERROR cannot LOGIN");
		}

		while (loggedIn) {
			out.println("INFO");
			try {
				input = in.readLine();
			} catch (IOException e) {
				gui.getError().setText("ERROR cannot READ");
			}

			if (input.startsWith("LIST")) {
//				 System.out.println(input);
				users.clear();
				String allClients = "";
				String[] entry = input.split(" ");
				userCount = Integer.parseInt(entry[1]);
//				System.out.println(userCount);
				for (int i = 2; i < userCount * 2 + 2; i = i+2) {
					
					users.add(entry[i]);
					users.add(entry[i + 1]);
//					System.out.println(users);
					allClients = allClients + entry[i + 1] + "\n";
					// System.out.println(users);
				}
				gui.getMember().setText(allClients);
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				gui.getError().setText("ERROR cannot Wait to update List");
			}

		}
		
		this.interrupt();

	}

}
