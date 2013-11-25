package server;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class GUIServer extends JFrame 
{

    private JPanel contentPane;
    public JTextField textField;
    private JTextField textField_1;
    private static Controller con;
    public static JTextArea log;
    private static UDPServer server;
    private static DataHandler dh;
    
    private boolean isClosed;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
			con = new Controller();
//			server = new UDPServer();
		    GUIServer frame = new GUIServer(con);
		    
		    
		    
			/////////////////////////////MESSY/////////////////////////////////////////
			
			ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
			
//			con.console(con.getCurrentTime() + "First test");
			
			ses.scheduleAtFixedRate(new Runnable() 
			{
			    @Override
			    public void run() 
			    {
			    	log.revalidate();;
			    }
			}, 0, 5, TimeUnit.SECONDS);

			
//			con.console(con.getCurrentTime() + "Second test");
			
			// when finished
//			ses.shutdown();
			
			/////////////////////////////MESSY/////////////////////////////////////////
		    
		   
		    
		    frame.setVisible(true);
		    
		   

		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public GUIServer(final Controller con)
    {
    	
    	this.con = con;
    	isClosed = true;
    	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 666, 380);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblIpAdresse = new JLabel("IP Adresse:");
	lblIpAdresse.setBounds(39, 55, 77, 16);
	contentPane.add(lblIpAdresse);
	
	JLabel lblPort = new JLabel("Port:");
	lblPort.setBounds(39, 99, 61, 16);
	contentPane.add(lblPort);
	
	textField = new JTextField();
	textField.setBounds(121, 49, 134, 28);
	contentPane.add(textField);
	textField.setColumns(10);
	
	String ip = "";
	try {
		ip = InetAddress.getLocalHost().getHostAddress();
		textField.setText(ip);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	textField_1 = new JTextField();
	textField_1.setBounds(121, 93, 134, 28);
	contentPane.add(textField_1);
	textField_1.setColumns(10);
	
	final JButton btnStartServer = new JButton("Start Server");
	btnStartServer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
						
			String portString = textField_1.getText();
			final int port;
			
			port = Integer.parseInt(portString);
			
			if(portString.isEmpty())
			{
				update("Port is empty" + "\n");
			}
			
			

				if (port < 1024)
				{
					update("Port must be over 1024" + "\n");
				}
				else 
				{
					
// Making a controller call for method 'runServer' with the integer parsed port from 'textField_1'
// runServer method in controller will instaniate a NewServer object and start the
// thread associated to that object.
					
//			
						log.setText(con.getCurrentTime() + "test");
						System.out.println(log.getText());
						log.update(log.getGraphics());
//						log.
						server = new UDPServer(port, con);
//						server.startServer(port);
					
					System.out.println("In GUIServer.java - server should be started on port " + port);
					
					textField_1.setEditable(false);
					btnStartServer.setEnabled(false);
				}
			
	
		}
	});
	
	btnStartServer.setBounds(138, 132, 117, 29);
	contentPane.add(btnStartServer);
	
	JButton btnStopServer = new JButton("Stop Server");
	btnStopServer.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			String[] choice = { "Yes", "No" };

			int temp = JOptionPane.showOptionDialog(null,
					"Are you sure you want to disconnect and close the application?", "Shutdown",
					JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					choice, null);

			
			if(temp==0)
			{
				update("Stopping server - Disconnecting");
				
				if (isClosed) 
				{
					System.exit(0);
					isClosed = false;
				}
				con.getServerInstance().close();
			}
			
		
		
		}
	});
	
	btnStopServer.setBounds(21, 132, 117, 29);
	contentPane.add(btnStopServer);
	
	JLabel lblBrugere = new JLabel("Brugere:");
	lblBrugere.setBounds(23, 233, 61, 16);
	contentPane.add(lblBrugere);
	
	JList<String> users = new JList<String>();
	JScrollPane scrollPane = new JScrollPane(users);
	scrollPane.setBounds(121, 192, 134, 140);
	contentPane.add(scrollPane);
	
	log = new JTextArea();
	log.setEditable(false);
	con.passConsole(log);
	JScrollPane scrollPane_1 = new JScrollPane(log);
	scrollPane_1.setBounds(317, 41, 300, 290);
	contentPane.add(scrollPane_1);
	
	JLabel lblLog = new JLabel("Log:");
	lblLog.setBounds(454, 14, 61, 16);
	contentPane.add(lblLog);
    }
    
    
    public void update(final String upd) 
    {
		SwingUtilities.invokeLater(new Runnable() 
		{

			public void run() 
			{
				
				System.out.println(upd);
				log.append(upd);
				log.revalidate();
				log.validate();
			

			}
		});
	}
    
	public static void updateConsole()
	{	
		log.setText(con.getConsole().getText());
//		console.revalidate();
	}
    
    
    

	public String getTimeDate() { // if needed
		Date dataTime = new Date();

		SimpleDateFormat datFormat = new SimpleDateFormat(
				"HH:mm:ss\tdd-MM-yyyy");
		String dateTime = datFormat.format(dataTime);
		return dateTime;
	}

	public String getIp() {
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
	
    
}
