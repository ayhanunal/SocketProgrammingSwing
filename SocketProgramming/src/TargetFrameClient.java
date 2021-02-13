import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TargetFrameClient {
	
	//JLabel label;
	//static ImageIcon icon;
	
	private int UPDATE_TIME = 20; // update time is 20ms
	
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	String stateValue;
	
	int clientPort;
	String clientIP;
	
	private JFrame clientFrame;
	private JLabel label;
	private ImageIcon icon;

	
	public static void main(String[] args) {
		System.out.println("Main is running.....");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TargetFrameClient window = new TargetFrameClient();
					window.clientFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TargetFrameClient(String clientIP, int clientPort) {
		this.clientPort = clientPort;
		this.clientIP = clientIP;
		initialize();
		
		
	}
	
	public TargetFrameClient() {
		initialize();
	}
	
	private void initialize() {

		clientFrame = new JFrame();
		clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientFrame.setTitle("Socket Client Page");
		clientFrame.setSize(700,500);
		clientFrame.setLayout(null);
		clientFrame.getContentPane().setBackground(Color.black); //background color changes - black
		
		icon = new ImageIcon("image.png"); //set image
		
		label = new JLabel(); //box
		label.setBounds(250, 100, 200, 200);
		label.setIcon(icon);
		
		clientFrame.add(label);
		clientFrame.setVisible(true);
		
		
		Thread newThread = new Thread(() -> {
			
			try {
				InetAddress addr = InetAddress.getByName(clientIP);
	            socket = new Socket(addr, clientPort);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(clientFrame,
					    "Portu Kontrol Edin !!",
					    "Port Hatasi",
					    JOptionPane.ERROR_MESSAGE);
				clientFrame.dispose();
			}
			
			while(true) {
				try {
					Thread.sleep(UPDATE_TIME);
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    		//System.out.println("Server value= " + in.readLine());
					switch(in.readLine()){
						case "38": {
							label.setLocation(label.getX(), label.getY()-10);
							break;
						}
						case "39": {
							label.setLocation(label.getX()+10, label.getY());
							break;
						}
						case "37": {
							label.setLocation(label.getX()-10, label.getY());
							break;
						}
						case "40": {
							label.setLocation(label.getX(), label.getY()+10);
							break;
						}
					}
		    		
		    		clientFrame.repaint();
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(clientFrame,
						    "Sunucu Bulunamadi !!",
						    "Sunucu Hatasi",
						    JOptionPane.ERROR_MESSAGE);
					clientFrame.dispose();
					break;
				}
			}
			
		});
		newThread.start();
		
		
	}
	
	
	
	
	/*
	
	TargetFrameClient(int clientPort) throws IOException{
		
		clientFrame = new JFrame();
		clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientFrame.setSize(700,500);
		clientFrame.setLayout(null);
		clientFrame.getContentPane().setBackground(Color.black); //background color changes - black
		

		
		label = new JLabel(); //box
		label.setBounds(250, 100, 200, 200);
		label.setIcon(icon);
		
		clientFrame.add(label);
		clientFrame.setVisible(true);
		
		this.clientPort = clientPort;
		

	}
	
	public void initializeClient() {
		try {
            socket = new Socket("localhost", clientPort);
            
            out = new PrintWriter(socket.getOutputStream(), true);
    		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
            System.out.println("Port Error!");
		}	
	}
	
	
	
	public static void updateBox(String readingData) {
		System.out.println("Server value = " + readingData);
		
		JLabel label2;
		label2 = new JLabel(); //box
		label2.setBounds(250, 100, 200, 200);
		label2.setIcon(icon);
		
		switch(readingData) {
		case "38": label2.setLocation(100, 100);
			break;
		case "39": label2.setLocation(200, 100);
			break;
		case "37": label2.setLocation(300, 100);
			break;
		case "40": label2.setLocation(400, 100);
			break;
		}
		
		TargetFrameClient.clientFrame.add(label2);
		TargetFrameClient.clientFrame.setVisible(true);
		
		
	}
	*/
	

}
