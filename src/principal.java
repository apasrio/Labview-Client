
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class principal extends JFrame {
	

	static String SocketIp = "127.0.0.1";
	static int SocketPort = 5020;
	static TCPClient socketClient;

	private JPanel contentPane;
	private JTextField msgToSend;
	private JButton btnSend;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
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
	public principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEnviar = new JLabel("Enviar:");
		lblEnviar.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		msgToSend = new JTextField();
		msgToSend.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/** 
				 * Executed when Send button is pressed
				 */
				String message = msgToSend.getText();
				System.out.println(message);
				try {
					//socketClient = new TCPClient(SocketIp, SocketPort);
					socketClient.bidirectComm(message, 0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/**
				 *  Executed when Disconnect button is pressed
				 */
				try{
					socketClient.bidirectComm("Closing socket!", 1);
					socketClient.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnNewButton = new JButton("Connect");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/**
				 * Snippet of code to open a new connection
				 */
				try {
					socketClient = new TCPClient(SocketIp, SocketPort);
					// We should just execute a method to stablishComm();
					socketClient.establishComm(SocketIp);					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblEnviar, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(msgToSend, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnDisconnect)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
					.addComponent(btnSend))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnviar)
						.addComponent(msgToSend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSend)
						.addComponent(btnDisconnect)
						.addComponent(btnNewButton)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
