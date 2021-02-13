import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MainPage {

	private JFrame frmSimsoftsocket;
	private JTextField ipAddressText;
	private JTextField portText;
	
	private int MAX_CLIENT_COUNT = 10; // max 10 clients can join
	
	String ipAddress, portNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frmSimsoftsocket.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimsoftsocket = new JFrame();
		frmSimsoftsocket.setTitle("Socket Main Page");
		frmSimsoftsocket.setBounds(100, 100, 364, 174);
		frmSimsoftsocket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSimsoftsocket.getContentPane().setLayout(null);
		
		JButton serverButton = new JButton("Server");
		serverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//server button clicked.
				ipAddress = ipAddressText.getText().toString();
				portNumber = portText.getText().toString();
				
				//System.out.println("IP Server: "+ ipAddress);
				frmSimsoftsocket.dispose();
				//new FramePage("server").setVisible(true);
				//new TargetFrameServer();
				try {
					new TargetFrameServer(ipAddress, Integer.parseInt(portNumber), MAX_CLIENT_COUNT);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frmSimsoftsocket,
						    "Gecerli bir port numarasi giriniz !!",
						    "Port Hatasi",
						    JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		serverButton.setBounds(81, 87, 89, 23);
		frmSimsoftsocket.getContentPane().add(serverButton);
		
		JButton clientButton = new JButton("Client");
		clientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//client button clicked
				
				ipAddress = ipAddressText.getText().toString();
				portNumber = portText.getText().toString();
				
				//System.out.println("IP Client: "+ ipAddress);
				frmSimsoftsocket.dispose();
				//new FramePage("client").setVisible(true);
				
				new TargetFrameClient(ipAddress, Integer.parseInt(portNumber));
				
				/*
				try {
					TargetFrameClient client = new TargetFrameClient(Integer.parseInt(portNumber));
					//client.initializeClient();
				} catch (NumberFormatException | IOException e1) {
					e1.printStackTrace();
				}
				*/
				
			}
		});
		clientButton.setBounds(180, 87, 89, 23);
		frmSimsoftsocket.getContentPane().add(clientButton);
		
		JLabel lblNewLabel_1 = new JLabel("IP ADDRESS:");
		lblNewLabel_1.setBounds(40, 14, 78, 14);
		frmSimsoftsocket.getContentPane().add(lblNewLabel_1);
		
		ipAddressText = new JTextField();
		ipAddressText.setText("127.0.0.1");
		ipAddressText.setBounds(158, 11, 144, 20);
		frmSimsoftsocket.getContentPane().add(ipAddressText);
		ipAddressText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("PORT:");
		lblNewLabel.setBounds(40, 45, 78, 14);
		frmSimsoftsocket.getContentPane().add(lblNewLabel);
		
		portText = new JTextField();
		portText.setBounds(158, 42, 144, 20);
		frmSimsoftsocket.getContentPane().add(portText);
		portText.setColumns(10);
	}
}
