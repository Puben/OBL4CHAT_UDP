package server;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class Controller 
{
	
	private Thread t;
	private UDPServer serverInstance;
	private GUIServer guiInstance;
	private int port;
	private JTextArea console;
	
	public void runServer(int port) 
	{
// Crafting an object of the UDPServer class with arguments 'port' and 
// this controller.
		System.out.println(port);
		serverInstance = new UDPServer(port, this);
// Will now start an instance of the object created above
		serverInstance.startServer(port);
		

	}
	
	
	public String getCurrentTime()
	{
		
		String pattern = "HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = new Date();
		
		return format.format(date).toString()+" : ";
	}
	
	
	public GUIServer getGuiInstance() 
	{
		guiInstance = new GUIServer(this);
		return guiInstance;
	}



	public void setGuiInstace(GUIServer guiInstace) {
		this.guiInstance = guiInstace;
	}
	
	
	public UDPServer getServerInstance() 
	{
		return serverInstance;
	}

	
	public void passConsole(JTextArea console)
	{
		this.console = console;
//		guiInstace.
	}
	
	public JTextArea getConsole()
	{
		return console;
	}
	
	public void setServerInstance(UDPServer serverInstance) {
		this.serverInstance = serverInstance;
	}

	
}
