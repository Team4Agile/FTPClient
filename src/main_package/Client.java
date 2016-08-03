package main_package;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Client {
	
	HashMap users = new HashMap();
	
	public Client() {
		users.put("prajyoth", "1234");
		users.put("sriharsha", "1234");
		users.put("rupa", "1234");
	}
	
	public Boolean ValidateUser(String username, String pass) {
		Set set = users.entrySet();
		Iterator i = set.iterator();
		
	   while(i.hasNext()) {
       Map.Entry me = (Map.Entry)i.next();
		   if (me.getKey().equals(username))
		   {
			   if (me.getValue().equals(pass)) {
				   return true;
			   }
		   }
	   }
		
		return false;
		
	}
	
	
	public void Success() throws IOException {
		FTPClientClass operation = new FTPClientClass();
		//TODO: this is a remote server implementation. If it does not work call the local server
		operation.connectServer();
		System.out.println("Welcome! Please select any option from the following menu");
		int quit = 0;
		while (quit == 0) {
		System.out.println("1. listFiles (remote), 2. listDirectories (remote), 3. listFiles (local), 4. listDirectories (local)"
				+ ", 5. download single file, 6. download multiple, 10. quit");
		Scanner in = new Scanner(System.in);
		int option = in.nextInt();
		switch (option) {
			case 1: operation.listRemoteFiles();
					break;
			case 2: operation.listDirectoriesRemote();
					break;
			case 3: operation.listFilesLocal();
					break;
			case 4: operation.listDirectoriesLocal();
					break;
			case 5: operation.downloadSingle();
					break;
			default: System.out.println("please enter a valid choice");
			         break;
			//TO be implemented
			case 10: quit = 1;
					 break;
		}
		}

		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Welcome");
		Scanner in = new Scanner(System.in);
		Client client = new Client();
		Integer attempt = 0;
		
		while (attempt <= 3) {
		System.out.println("Please enter your username :");
		String login = in.next();
		System.out.println("Please enter your password");
		String password = in.next();


		if (client.ValidateUser(login, password)) {
			System.out.println("Connection successful!!");
			attempt = 999;
			client.Success();
		}
		else {
			System.out.println("Oops! looks like your credentials are wrong :(");
			attempt++;
		}
		
		}
		
		System.out.println("You've exceeded your attempt limit");
		System.out.println("exiting....");
	}

}
