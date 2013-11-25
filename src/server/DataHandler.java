package server;

import java.util.ArrayList;
import java.util.*;

public class DataHandler 

{

	private String timeStamp;
	private String prefix;
	private String user;
	private String message;
	private Controller controller;

	ArrayList userList;

	/**
	 * By crea
	 * @param user
	 */
	public DataHandler(String message, Controller controller)
	{
		this.controller = controller;
		this.message = message;
		
		controller = new Controller();



		//		prefix = message.

		StringTokenizer tokenizer = new StringTokenizer(message);
		
		// So important part is to remember that a message must be encoded by:
		// 1. a timeStamp (can be retrieved by calling user controller.getCurrentTime();
		// 2. a prefix - encoded either by <AL>, <CO>, <DC>, <MSG>
		// 3. a username 
		// 4. a message
		

		timeStamp = tokenizer.nextToken();
		prefix = tokenizer.nextToken();
		user = tokenizer.nextToken();
		
		String concatString = timeStamp+prefix+user;
		message = message.substring(concatString.length()+3);

		System.out.println(timeStamp);
		System.out.println(prefix);
		System.out.println(user);
		System.out.println(message);

		if(prefix.equals("<AL>"))
		{
			controller.getGuiInstance().log.append(timeStamp + "Attempted login from " + user);
		}
		else if(prefix.equals("<CO>"))
		{
			controller.getGuiInstance().log.append(timeStamp + "User " + user + " is connected.");
		}
		else if(prefix.equals("<DC>"))
		{
			controller.getGuiInstance().log.append(timeStamp + "User " + user + " is discconnected.");
		}
		else if(prefix.equals("<MSG>"))
		{
			controller.getGuiInstance().log.append(timeStamp + user + ": " + message);
		}
		else
		{
			System.out.println("UDP packet failure?");
			controller.getGuiInstance().log.append("UDP PACKET FAILURE");
			//TODO lots - notify user, retrieve last sent message.
		}

	}





	public String getMessage() {
		return message;
	}




	
	public void setMessage(String message) {
		this.message = message;
	}





	public String getPrefix() 
	{
		return prefix;
	}
	public void setPrefix(String prefix) 
	{
		this.prefix = prefix;
	}
	public String getUser() 
	{
		return user;
	}
	public void setUser(String user) 
	{
		this.user = user;
	}
	public ArrayList getUserList() 
	{
		return userList;
	}
	public void setUserList(ArrayList userList) 
	{
		this.userList = userList;
	}






}
