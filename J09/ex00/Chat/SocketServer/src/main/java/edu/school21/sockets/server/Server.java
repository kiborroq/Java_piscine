package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {
	UsersService usersService;
	ServerSocket server;
	Socket client;
	DataInputStream dis;
	DataOutputStream dos;

	@Autowired
	public Server(UsersService usersService) {
		this.usersService = usersService;
	}

	public void run(int port) throws IOException {
		try {
			server = new ServerSocket(port);
			client = server.accept();

			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());

			dos.write("Hello from Server!\n".getBytes());
			dos.flush();

			String action = dis.readLine();

			dos.write("Enter username:\n".getBytes());
			dos.flush();

			String login = dis.readLine();

			dos.write("Enter password:\n".getBytes());
			dos.flush();

			String password = dis.readLine();

			dos.write("Successful!\n".getBytes());
			dos.flush();

			usersService.signUp(login, password);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			server.close();
			client.close();
			dis.close();
			dos.close();
		}
	}
}
