import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TargetFrameServer extends JFrame implements KeyListener {
	
	JLabel label;
	ImageIcon icon;
	ServerSocket serverSocket = null;
	Socket clientSocket = null;
	PrintWriter out;
	
	TargetFrameServer(String serverIP, int serverPort, int maxClientCount) throws IOException{		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Socket Server Page");
		this.setSize(700,500);
		this.setLayout(null);
		this.addKeyListener(this);
		this.getContentPane().setBackground(Color.black); //background color changes - black
		
		icon = new ImageIcon("image.png"); //set image
		
		label = new JLabel(); //box
		label.setBounds(250, 100, 200, 200);
		label.setIcon(icon);
		//label.setBackground(Color.red); //tested color
		//label.setOpaque(true);
		
		this.add(label);
		this.setVisible(true);
		
		Thread newThread = new Thread(() -> {
			
			try {
				InetAddress addr = InetAddress.getByName(serverIP);
	            serverSocket = new ServerSocket(serverPort, maxClientCount);
	            
	            clientSocket = serverSocket.accept();
	            out = new PrintWriter(clientSocket.getOutputStream(), true);
	            
	            /*
	            for (int i=0; i<maxClientCount; i++) {
	            	clientSocket = serverSocket.accept();
		            out = new PrintWriter(clientSocket.getOutputStream(), true);
		            
		            new TargetFrameClient(serverIP,serverPort);
	            }
	            */
	            
	            
	            
	            
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,
					    "Portu Kontrol Edin !!",
					    "Port Hatasi",
					    JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
			System.out.println("Server is ready !!");
			
		});
		newThread.start();
		
	}

	

	@Override
	public void keyPressed(KeyEvent e) {
		switch(Integer.toString(e.getKeyCode())) {
			case "38": {
				label.setLocation(label.getX(), label.getY()-10);
				//TargetFrameClient.updateBox("38");
				out.println("38");
				break;
			}
			case "39": {
				label.setLocation(label.getX()+10, label.getY());
				//TargetFrameClient.updateBox("38");
				out.println("39");
				break;
			}
			case "37": {
				label.setLocation(label.getX()-10, label.getY());
				//TargetFrameClient.updateBox("38");
				out.println("37");
				break;
			}
			case "40": {
				label.setLocation(label.getX(), label.getY()+10);
				//TargetFrameClient.updateBox("38");
				out.println("40");
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("key pressed : "+ e.getKeyChar());
		//System.out.println("key pressed code  : "+ e.getKeyCode());
		//next - 38
		//right - 39
		//left - 37
		//back - 40
		
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}
