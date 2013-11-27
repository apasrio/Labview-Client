import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Frame extends JFrame{
	private TCPClient tcpClient;
	private HP33120a hp33120a;
	private HP34401a hp34401a;
	private AG33220a ag33220a;
	private HP54602b hp54602b;
	private WelcomeWindow welcomeWindow;
	private View view;
	
	public Frame(final TCPClient clientSocket, HP33120a hp33120a, HP34401a hp34401a, AG33220a ag33220a, HP54602b hp54602b){		
		this.setSize(1280,960);		
		this.tcpClient = clientSocket;
		this.ag33220a = ag33220a;
		this.hp33120a = hp33120a;
		this.hp34401a = hp34401a;
		this.hp54602b = hp54602b;
		
		JPanel panel = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int)screenSize.getHeight();
					
		panel.setSize(width,height);
		
		welcomeWindow = new WelcomeWindow();
		getContentPane().add(panel, BorderLayout.CENTER);		
		panel.add(welcomeWindow.getWelcomePanel());
		
		ConfControl confControl = new ConfControl(welcomeWindow, clientSocket, this);		
		welcomeWindow.setConfControl(confControl);
		this.setVisible(true);
	}	
	
	public void createMainView(String[] activeDevicesFlags){
		view = new View(tcpClient, hp33120a, hp34401a, ag33220a, hp54602b,activeDevicesFlags);
		getContentPane().removeAll();
		getContentPane().add(view.getMainView());
		getContentPane().revalidate();
		getContentPane().repaint();
		System.out.println("Re-painting the whole window!");
	}
}
