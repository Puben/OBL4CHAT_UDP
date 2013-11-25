package client;

import java.net.InetAddress;
import java.awt.Color;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class Controller 
{

	private JTextArea history;
	private UDPClient clientInstance;
	private GUIClient GUIInstance;
	private Thread t;
	
	private InetAddress ina;
	private String address;
	private int port;
	
	public Controller()
	{
		
		
		history = new JTextArea();
		
//		new Login(this);
		GUIInstance = new GUIClient(this);
		clientInstance = new UDPClient(this);
		   
        
	}


	
	
	public synchronized void startClient()
	{
		t = new Thread(clientInstance);
		t.start();
	}

	
	
	public String getIp() 
	{
		String ip = "";
		try 
		{
			ip = InetAddress.getLocalHost().getHostAddress();
		} 
		catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
	
	public JTextArea getHistory() 
	{
		return history;
	}

	public void setHistory(JTextArea history) 
	{
		this.history = history;
	}

	public String getCurrentTime()
	{
		
		String pattern = "HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = new Date();
		
		return format.format(date).toString()+" : ";
	}
	
	   public void console(String message)
	    {
//		   	history = new JTextArea();
//		    
	        history.append(message + "\n");
//	        history.setCaretPosition(history.getDocument().getLength());
	    }
	   
	   
	   public UDPClient getClientInstance() 
	   	{
		   System.out.println("An intance of the UDPClient have been fetched - Link between controller and client is success");
		   return clientInstance;
		}

		public GUIClient getGUIInstance() {
			return GUIInstance;
		}

	
}
