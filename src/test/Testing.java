package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import server.*;
import client.*;
import client.Controller;

public class Testing 
{
	
	private static UDPClient client;
	private static UDPServer server;
	private static Controller con;
	private static Login login;
	
	
	
	public static void main(String args[] )
	{
		con = new Controller();
		con.getClientInstance().sendMessage("Loggin in");

		
		
//		clientController = new Controller();
//		String ip = clientController.getIp();

		
//		server = new UDPServer(9999);
//		System.out.println("Server started");
//		client = new UDPClient("Anton", ip, 9999);
		
//		client.
		
		
//		testConsole();
//		System.out.println(getCurrentTime());
//		System.out.println(clientController.getHistory());
	
	}
	
	
	public String getCurrentTime()
	{
		
		String pattern = "HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = new Date();
		
		return format.format(date).toString();
	}
	
	public static void testConsole()
	{
//		clientController = new Controller();
//		clientController.console(clientController.getCurrentTime() + "Attempting a connection to");
	}
	
}
