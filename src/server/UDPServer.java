package server;

import java.io.*;
import java.net.*;

public class UDPServer 
{

	private int port, sendersPort;
	private DatagramSocket serverSocket;
	private boolean isRunning;
	private Controller con;

	
	public byte[] receiveData, sendData;
	

	public UDPServer(int port, Controller con)
	{
		super();
		isRunning= true;
		this.con = con;
		this.port = port;
	}

	public UDPServer() {
		// TODO Auto-generated constructor stub
	}

	public static void getIP()
	{

	}

	public void close()
	{
		isRunning = false;
		serverSocket.close();
		System.exit(0);
		
	}
	
	public void updateLog(String message)
	{
		
		con.getGuiInstance().update(message);
	}
    
	
	public void startServer(int port)
	{
		try
		{
			
		// This socket should run on same port as the clientSocket in UDPClient's method connection?
			serverSocket = new DatagramSocket(port+1);
			con.getConsole().append("Starting the server socket..." + "\n");

			while(isRunning)
			{
				
				receiveData = new byte[1024];
				sendData = new byte[1024];
				
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				try 
				{
					serverSocket.receive(receivePacket);
				} 
				catch (IOException e)
				{
					// TODO Auto-generated catch block
				System.out.println("There is nothing to recieve - still listening though!");
				}
				String sentence = new String( receivePacket.getData());
				updateLog(sentence);
				System.out.println("RECEIVED: " + sentence);
				
				InetAddress SendersIPAddress = receivePacket.getAddress();
				int sendersPort = receivePacket.getPort();
				
				String capitalizedSentence = sentence.toUpperCase();
				sendData = capitalizedSentence.getBytes();

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, SendersIPAddress, sendersPort);
				try 
				{
					serverSocket.send(sendPacket);
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		}

		catch(Exception e)
		{
			con.getConsole().append("Could not connect to port" + port + "\n");
		}
	}

//
//	public static void main(String args[]) throws Exception
//	{
//
//		
//	}
}