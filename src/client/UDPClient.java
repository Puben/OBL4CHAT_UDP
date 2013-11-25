package client;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;


public class UDPClient implements Runnable
{
	
    private String name, address, sentence;
    private static int port;
    private JTextField txtMessage;
    private JTextArea history;
    private DefaultCaret caret;
    private String message;
    
    private DatagramPacket sendPacket;    
    private PrintWriter writer;
        
    private InetAddress ip;

    private byte[] data;
    private Controller controller;
    private static DatagramSocket clientSocket;
    
    
    public UDPClient(final Controller controller)
    {
    	this.controller = controller;
		
    }
    
   
    
    public DatagramSocket connection(String name, String address, int port) 
    {
        this.name = name;
        this.address = address;
        this.port = port;
        
        System.out.println("In method 'Connection' - here");
        
//        boolean connect = initiateConnection(address, port);
        
         
        try 
        {
        	
        	if(clientSocket == null)
        	 clientSocket = new DatagramSocket(port);
             ip = InetAddress.getByName(address);

             
         	controller.console(controller.getCurrentTime() + "Attempting a connection to " + address + ":" + port + ", user: " + name + "\n");
         	System.out.println("History: \n" + controller.getHistory().getText());
         	
         	if(port > 1024)
         	{
         		
         		try 
         		{
             		dataHandler(controller.getCurrentTime() + "SYSTEM " + name + " has logged in.", controller.getIp());

 				} 
         		catch (Exception e) 
 				{
 				
 					controller.console(controller.getCurrentTime() + "Unknow host!");
 					e.printStackTrace();
 				}
         		
         	}
         	else
         	{
         		controller.console(controller.getCurrentTime() + "An unknow error have occured.");
                controller.console(controller.getCurrentTime() + "Connection failed!");
         	}
             

         	return clientSocket;
        }
        catch (UnknownHostException e) 
        {
                e.printStackTrace();
                
        } 
        catch (SocketException e) 
        {
                e.printStackTrace();
               
        }
        
        return clientSocket;
   
        	
}
    
    /**
     * This method will send a given message to a PrintWriter object.
     * @throws UnknownHostException 
     */
    public void sendMessage(String message) 
	{
//		message = controller.getLoggedInUsername()+": "+message;
    	System.out.println("In UDPClient - a message have been requested to be sent: " + message);

			dataHandler(message, controller.getIp());
	}
    
    /**
     * This method will try to initiate a connection to the address and port specified.
     */
    private boolean initiateConnection(String address, int port) 
    {
    	
        try 
        {
        	 clientSocket = new DatagramSocket(port);
             ip = InetAddress.getByName(address);

             return true;

        }
        catch (UnknownHostException e) 
        {
                e.printStackTrace();
                
        } 
        catch (SocketException e) 
        {
                e.printStackTrace();
               
        }
        System.out.println("returning false - connection not established.");
        
        return false;
        
    }  
    
    
    /**
     *  This method will get the users input and parse that into a ByteArray that ultimately will be sent to the server.
     *  It is furthermore able to receive input from the server.
     */
    
    public void dataHandler(String message, String address)
    {
//    	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    	
    	this.message = message;
    	this.address = address;
    	
//    	clientSocket = connection(message, address, port);

		try 
		{
			ip = InetAddress.getByName(address);
			
			
			
			message += "\n";
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			
			// Will try to store the users input in a sentence of type String
			try 
			{	
				clear(sendData);
				System.out.println("In Datahandler " + message);					
				sendData = message.getBytes();
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

				

			sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
			try 
			{
				// will try to send the byteArray to server containing the user input.
				System.out.println("Length: " + sendData.length + "IP: "+ ip.toString() + " port: "+port);
				clientSocket.send(sendPacket);
				System.out.println("Data sent");
				clear(sendData);
				

			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// recieve data part
			
			
//			try
//			{
//
//				// will try to fetch packet from server
//				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//				clientSocket.receive(receivePacket);
//				String modifiedSentence = new String(receivePacket.getData());
//				System.out.println("FROM SERVER:" + modifiedSentence.toUpperCase());
//				
//			}
//			catch (IOException e) 
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("Intet at modtage");
//			}
					
//			clientSocket.close();
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	


    }

    
	  private void clear(byte[] data)
      { 
	      	data = new byte[1024]; 
		  	for (int i=0; i<data.length; i++)
    			data[i] = '\0';
		  	System.out.println("Buffer should be cleared");
      }
    
	@Override
	public void run() 
	{
		connection(name, address, port);
	}
	

}