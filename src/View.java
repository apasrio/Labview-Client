import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class View{	
		
	private JPanel mainView = new JPanel();
	private JTextField fromTextField;
	private JTextArea bodyTextArea;
	
	/**
	 * Create the applet.
	 */
	public View(final TCPClient socketClient, HP33120a hp33120a, HP34401a hp34401a, AG33220a ag33220a, HP54602b hp54602b,String[] availableDevices) {
		// TODO: Check if a refactor is needed -> Maybe we can initialize models here instead of in Programa
		/* availableDevices is a string array with the next format 
		 * -> AG33220aFlag, HP32120aFlag, HP34401aFlag, HP54602bFlag
		 * The flag is going to be given to the proper View Constructor
		 */
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int)screenSize.getHeight();
					
		mainView.setSize(width,height);
		mainView.setLayout(new GridLayout(2, 0, 0, 0));
		
				
		JPanel topPanel = new JPanel();
		mainView.add(topPanel);
		topPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("426px:grow"),
				ColumnSpec.decode("426px:grow"),
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("480px"),}));
		
		JTabbedPane waveformGenerators = new JTabbedPane(JTabbedPane.TOP);
		topPanel.add(waveformGenerators, "2, 1, fill, fill");
				
		
		// Start defining AG33220a Components
		int ag33220aFlag = Integer.parseInt(availableDevices[0]);
		AG33220aInterface ag33220aView = new AG33220aView(ag33220aFlag);
		waveformGenerators.addTab("Agilent 33220A", null, ag33220aView.getAG33220aPanel(), null);
		
		if(ag33220aFlag == 1){
			AG33220aControl ag33220aControl = new AG33220aControl(ag33220aView, socketClient, ag33220a);
			ag33220aView.setAG33220aControl(ag33220aControl);
		}
		
		// Start defining HP33120a Components	
		int hp33120aFlag = Integer.parseInt(availableDevices[1]);
		HP33120aInterface hp33120aView = new HP33120aView(hp33120aFlag);
		waveformGenerators.addTab("HP 33120A", null, hp33120aView.getHP33120aPanel(), null);
		if(hp33120aFlag == 1){
			HP33120aControl hp33120aControl = new HP33120aControl(hp33120aView, socketClient, hp33120a);
			hp33120aView.setHP33120aControl(hp33120aControl);	
		}
			
		
		JTabbedPane digitalMultimeters = new JTabbedPane(JTabbedPane.TOP);
		topPanel.add(digitalMultimeters, "3, 1, fill, fill");		
		
		// Start defining HP34401a Components 
		int hp34401aFlag = Integer.parseInt(availableDevices[2]);
		HP34401aView hp34401aView = new HP34401aView(hp34401aFlag);
		digitalMultimeters.addTab("HP34401A", null, hp34401aView.getHP34401aPanel(), null);
		
		JPanel controlPanel = new JPanel();
		topPanel.add(controlPanel, "4, 1, fill, fill");
		
		JButton btnDisconnectButton = new JButton("Disconnect!");
		btnDisconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					TCPClient.bidirectComm("EmptyMessage", Globals.CLOSE_CONNECTION);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		controlPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("59px:grow"),
				ColumnSpec.decode("89px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		controlPanel.add(btnDisconnectButton, "1, 2, 2, 1, center, top");
		
		JLabel lblFrom = new JLabel("From:");
		controlPanel.add(lblFrom, "1, 4, center, default");
		
		fromTextField = new JTextField();
		controlPanel.add(fromTextField, "1, 6, 2, 1, fill, default");
		fromTextField.setColumns(10);
		
		JLabel lblResults = new JLabel("Results:");
		controlPanel.add(lblResults, "1, 8, center, default");
		
		bodyTextArea = new JTextArea();
		controlPanel.add(bodyTextArea, "1, 10, 2, 15, fill, fill");
		
		JButton btnSubmit = new JButton("Submit!");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmailUtility email = new EmailUtility();
				email.sendEmail(fromTextField.getText(), bodyTextArea.getText());
				JOptionPane.showMessageDialog(mainView,
                        "Email properly Sent!!", "Email properly Sent!!" ,JOptionPane.INFORMATION_MESSAGE);
			}
		});
		controlPanel.add(btnSubmit, "1, 26, 2, 1, center, default");
		if(hp34401aFlag == 1){
			HP34401aControl hp34401aControl = new HP34401aControl(hp34401aView,socketClient, hp34401a);
			hp34401aView.setHP34401aControl(hp34401aControl);
		}
		
		
		JPanel bottomPanel = new JPanel();
		mainView.add(bottomPanel);
		bottomPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane oscilloscopesPanel = new JTabbedPane(JTabbedPane.TOP);
		bottomPanel.add(oscilloscopesPanel);
		
		// Start defining HP54602b Components
		int hp54602bFlag = Integer.parseInt(availableDevices[3]);
		HP54602bView hp54602bView = new HP54602bView(hp54602bFlag);
		oscilloscopesPanel.addTab("HP 54602B", null, hp54602bView.getHP54602bPanel(), null);
		if(hp54602bFlag == 1){
			HP54602bControl hp54602bControl = new HP54602bControl(hp54602bView, socketClient, hp54602b);
			hp54602bView.setHP54602bControl(hp54602bControl);
		}
				
		mainView.setVisible(true);
		SocketTimer socketTimer = new SocketTimer((Frame) mainView.getParent());
		socketTimer.setTimer();
	}	
	
	public JComponent getMainView(){
		return mainView;
	}	
}
