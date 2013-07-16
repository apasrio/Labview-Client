
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
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import java.awt.Component;


public class principal extends JFrame{
	

	static String SocketIp = "127.0.0.1";
	static int SocketPort = 5020;
	static TCPClient socketClient;

	private JPanel contentPane;
	private JTextField msgToSend;
	private JButton btnSend;
	private JButton btnNewButton;
	private JTabbedPane tabbedPane;
	private JTabbedPane tabbedPane_1;
	private JTabbedPane tabbedPane_2;
	private JPanel agilent_33220A;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel hp33120A;

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
		setBounds(100, 100, 1200, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEnviar = new JLabel("Enviar:");
		lblEnviar.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblEnviar.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		msgToSend = new JTextField();
		msgToSend.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		msgToSend.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setAlignmentY(Component.BOTTOM_ALIGNMENT);
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
		btnNewButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
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
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnDisconnect)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 555, Short.MAX_VALUE)
					.addComponent(lblEnviar, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(msgToSend, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSend))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
				.addComponent(tabbedPane_2, GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tabbedPane)
						.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane_2, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDisconnect)
						.addComponent(btnNewButton)
						.addComponent(msgToSend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnviar)
						.addComponent(btnSend)))
		);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		tabbedPane_1.addTab("New tab", null, panel_2, null);
		
		panel_1 = new JPanel();
		tabbedPane_2.addTab("New tab", null, panel_1, null);
		
		agilent_33220A = new JPanel();
		FlowLayout fl_agilent_33220A = (FlowLayout) agilent_33220A.getLayout();
		tabbedPane.addTab("Agilent 33220A", null, agilent_33220A, null);
		
		hp33120A = new JPanel();
		tabbedPane.addTab("HP 33120A", null, hp33120A, null);
		contentPane.setLayout(gl_contentPane);
	}
}
