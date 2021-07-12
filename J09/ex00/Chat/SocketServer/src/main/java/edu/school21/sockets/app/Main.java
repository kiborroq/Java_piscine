package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
		Server server = context.getBean("server", Server.class);
		try {
			server.run(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
