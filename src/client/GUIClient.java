package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class GUIClient extends JFrame {

	private JPanel contentPane;
	public JTextArea textField, userList, txt;
	private Controller con;
	private UDPClient client;
	
//	private UDPServer server;

	/**
	 * Launch the application.
	 */
	//    public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	//	    public void run() {
	//		try {
	//		    GUI frame = new GUI(con);
	//		    frame.setVisible(true);
	//		} catch (Exception e) {
	//		    e.printStackTrace();
	//		}
	//	    }
	//	});
	//    }


	// Methods





	/**
	 * Create the frame.
	 * @return 
	 */
	public GUIClient(final Controller con) 
	{
				
		client = new UDPClient(con);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txt = new JTextArea();
		txt.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txt);
		scrollPane.setBounds(26, 24, 421, 342);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 372, 346, 68);
		contentPane.add(panel);
		panel.setLayout(null);

		textField = new JTextArea();
		textField.setBounds(10, 16, 329, 41);
		panel.add(textField);
		textField.setColumns(10);


//		textField.addKeyListener(new KeyListener()
//		{
//			//When any key is pressed and released then the 
//			//keyPressed and keyReleased methods are called respectively.
//			//The keyTyped method is called when a valid character is typed.
//			//The getKeyChar returns the character for the key used. If the key
//			//is a modifier key (e.g., SHIFT, CTRL) or action key (e.g., DELETE, ENTER)
//			//then the character will be a undefined symbol.
//			@Override 
//			public void keyPressed(KeyEvent e)
//			{
//				boolean isSent = false;
//
//
//				if(e.getKeyChar() == KeyEvent.VK_ENTER)
//				{
//					System.out.println("Enter has been pressed");        		  
//
//					String input = textField.getText() + "\n";
//					appendText(input);
//					try 
//					{
//						sendMessage(input);
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					textField.setText("");
//
//					isSent = true;
//
//				}
//
//
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e)
//			{
//			}
//
//			@Override
//			public void keyTyped(KeyEvent e)
//			{
//			}
//		});
//
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener()
		
		{
			public void actionPerformed(ActionEvent e) 
			{
				String input = textField.getText();
				try 
				{
					sendMessage(input);
					appendText(input);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textField.setText("");
			}
		});

		btnSend.setBounds(372, 393, 117, 29);
		contentPane.add(btnSend);

		JList<String> users = new JList<String>();
		JScrollPane scrollPane_1 = new JScrollPane(users);
		scrollPane_1.setBounds(477, 24, 117, 296);
		contentPane.add(scrollPane_1);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				logoutRequest();
				int temp = logoutRequest();
				System.out.println(temp);
				if(temp==0)
				{
					System.exit(0);
				}
			}
		});

		btnExit.setBounds(499, 393, 95, 29);
		contentPane.add(btnExit);
	}


	public void sendMessage(String message)
	{
		System.out.println("Arrived with message: " + message);
		client.sendMessage(message);
	}


	private void appendText(String input) 
	{
		txt.append(con.getCurrentTime() + input);
		txt.validate();
		txt.repaint();
	}
	
	private int logoutRequest() 
	{

		String[] choice = { "Yes", "No" };

		int temp = JOptionPane.showOptionDialog(null,
				"Are you sure you want to logout?", "Logout",
				JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				choice, null);

		return temp;

	}



}
