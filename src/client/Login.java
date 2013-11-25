package client;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.util.TimerTask;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Login extends JFrame {

	private static JPanel contentPane, panel;
	private JTextField textField;
	private static Controller con;
	public static JFrame frame;
//	private GUIClient guiI = new GUIClient(con);
	
	private static JScrollPane scroller;
	private JTextField textField_1;
	private JTextField textField_2;
	private static JTextArea console;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args)
	{

					con = new Controller();
					buildConsole();
					Login loginFrame = new Login(con);
					loginFrame.setVisible(true);
		
					
					
					/////////////////////////////MESSY/////////////////////////////////////////
					
					ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
					
					con.console(con.getCurrentTime() + "First test");
					
					ses.scheduleAtFixedRate(new Runnable() 
					{
					    @Override
					    public void run() 
					    {
					    	updateConsole();
					    }
					}, 0, 5, TimeUnit.SECONDS);

					
					con.console(con.getCurrentTime() + "Second test");
					
					// when finished
//					ses.shutdown();
					
					/////////////////////////////MESSY/////////////////////////////////////////
					
//					directInputToConsole();
					
	}
	
	
	
	
	
	/**
	 * Create the frame.
	 */
	public Login(final Controller con)
	{

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		JButton btnStartChat = new JButton("Start Chat");
		btnStartChat.addActionListener(new ActionListener() 
		{
			
			
			
			public void actionPerformed(ActionEvent e) 
			{
				int intPort;

				String ip = textField_1.getText();
				String port = textField_2.getText();
				String username = textField.getText();

				if(ip.isEmpty() || port.isEmpty() || username.isEmpty())
				{
					System.out.println("Fields can't be empty");
				} 
				else

					try 
					{
						intPort = Integer.parseInt(textField_2.getText());

						{

							{
								con.console(con.getCurrentTime() + "Attempting to log in.");

								con.getClientInstance().connection(username, ip, intPort);
//								con.getClientInstance().sendMessage("Loggin in");
								
							
								
								System.out.println("In Login - logggin in");
								
//								con.console(con.getCurrentTime() + "Attempting to log in.");
								con.getGUIInstance().setVisible(true);
								con.console(con.getCurrentTime() + "Should now have fetched GUI");
								
							}

						}
				}
				catch (Exception e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnStartChat.setBounds(185, 193, 117, 29);
		contentPane.add(btnStartChat);

		JLabel lblChooseYourUsername = new JLabel("Choose your username:");
		lblChooseYourUsername.setBounds(185, 86, 206, 16);
		contentPane.add(lblChooseYourUsername);

		textField = new JTextField();
		textField.setBounds(189, 114, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(80, 20, 134, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_1.setText(getIp());
		textField_1.setEditable(false);

		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(38, 26, 61, 16);
		contentPane.add(lblIp);

		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(246, 26, 61, 16);
		contentPane.add(lblPort);

		textField_2 = new JTextField();
		textField_2.setBounds(311, 20, 134, 28);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
	}
	

	/**
	 * Primarily for test as it acccomplishes pretty much the exact same thing as console() method in Controller class
	 * Added JOptionPane for gui interface as-well as prefix
	 */
	
	public static void directInputToConsole()
	{
		String message = "Message:";
		String userInput = JOptionPane.showInputDialog(message);
		con.console(con.getCurrentTime() + "<root:> " + userInput);


	}
	
	public static void updateConsole()
	{	
		console.setText(con.getHistory().getText());
//		console.revalidate();
	}
	
	
	
	public static void buildConsole()
	{
		frame = new JFrame("Login");
		frame.setBounds(400, 200, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setBackground(Color.WHITE);
				
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		console = new JTextArea(con.getHistory().getText());
		console.setEditable(false);	

		
		scroller = new JScrollPane(console);
		scroller.setBounds(15, 15, 400, 375);
		contentPane.add(scroller);

		System.out.println(con.getHistory().getText());
			
//		frame.setContentPane(panel);
		frame.setContentPane(scroller);
		frame.setVisible(true);
	}
	
	public String getIp() 
	{
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}


}