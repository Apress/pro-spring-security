package com.apress.pss.terrormovies.standalone;

import java.io.Console;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.pss.terrormovies.access.AccessOperations;

public class Main {

	private AccessOperations operations;

	public static void main(String[] args) {
		Main main = new Main();
		main.loadContext();
		main.initCommandInput();
	}

	private void initCommandInput() {
		Console c = System.console();
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		String command = "";
		do {
			try {
				command = c.readLine("Insert command: ");
				interpretAndExecute(command);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (!command.equals("exit"));
	}

	private void interpretAndExecute(String command) {
		if (command.equals("logout")) {
			operations.logout();
		} else if (command.startsWith("login")) {
			String userAndPassword = command.split(" ")[1];
			String user = userAndPassword.split(":")[0];
			String password = userAndPassword.split(":")[1];
			operations.login(user, password);
		} else {
			String[] beanAndMethod = command.split(" ");
			String returned = operations.executeOperation(beanAndMethod[0], beanAndMethod[1]);
			System.out.println("Returned: "+returned);
		}
	}

	private void loadContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext-security.xml");
		operations = context
				.getBean("accessOperations", AccessOperations.class);
	}

}
