import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JSlider;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextPane;


public class Vista extends JApplet {
	
	static String SocketIp = "127.0.0.1";
	static int SocketPort = 5020;
	static TCPClient socketClient;
	private JTextField msgToSend;
	private JTextField frequency;
	private JTextField amplitude;
	private JTextField textField;
	private JTextField rampSymmetry;
	private JTextField dutyCycleSquare;
	private JTextField dutyCyclePulse;
	private JTextField modulatingFreq;
	private JTextField amDepth;
	private JTextField fmDeviation;
	private JTextField hopFrequency;
	private JTextField intDeviationPWM;
	private JTextField phaseDeviationPM;
	private JTextField burstRate;
	private JTextField burstCount;
	private JTextField burstPhase;

	/**
	 * Create the applet.
	 */
	public Vista() {
		this.setSize(1280,960);
		getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane_1);
		
		JPanel agilent_33220a = new JPanel();
		tabbedPane_1.addTab("Agilent 33220A", null, agilent_33220a, null);
		agilent_33220a.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		agilent_33220a.add(panel_2);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
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
		
		JLabel lblSignalConfiguration = new JLabel("Signal Configuration: ");
		lblSignalConfiguration.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_2.add(lblSignalConfiguration, "2, 2");
		
		JComboBox typeOfSignal = new JComboBox();
		typeOfSignal.setModel(new DefaultComboBoxModel(new String[] {"Simple Signal", "Modulation"}));
		panel_2.add(typeOfSignal, "4, 4, fill, center");
		
		JLabel wvfShapeLabel = new JLabel("Waveform Shape:");
		panel_2.add(wvfShapeLabel, "2, 6, right, default");
		
		JComboBox wvfShape_1 = new JComboBox();
		wvfShape_1.setModel(new DefaultComboBoxModel(new String[] {"DC", "Sine", "Square", "Triangle", "Ramp", "Pulse", "Noise", "Sinc", "Neg. Ramp", "Exp. Rise", "Exp. Fall"}));
		panel_2.add(wvfShape_1, "4, 6, fill, default");
		
		JLabel lblNewLabel_1 = new JLabel("Frequency (Hz): ");
		panel_2.add(lblNewLabel_1, "2, 8, right, center");
		
		frequency = new JTextField();
		panel_2.add(frequency, "4, 8, fill, default");
		frequency.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Amplitude (Vpp): ");
		panel_2.add(lblNewLabel_2, "2, 10, right, center");
		
		amplitude = new JTextField();
		panel_2.add(amplitude, "4, 10, fill, default");
		amplitude.setColumns(10);
		
		JLabel offsetLabel = new JLabel("Offset (Vdc): ");
		panel_2.add(offsetLabel, "2, 12, right, default");
		
		textField = new JTextField();
		panel_2.add(textField, "4, 12, fill, default");
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ramp Symmetry(%):");
		panel_2.add(lblNewLabel_3, "2, 14, right, default");
		
		rampSymmetry = new JTextField();
		panel_2.add(rampSymmetry, "4, 14, fill, default");
		rampSymmetry.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Duty Cycle (%) Square:");
		panel_2.add(lblNewLabel_4, "2, 16, right, default");
		
		dutyCycleSquare = new JTextField();
		panel_2.add(dutyCycleSquare, "4, 16, fill, default");
		dutyCycleSquare.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Duty Cycle (%) Pulse:");
		panel_2.add(lblNewLabel_5, "2, 18, right, default");
		
		dutyCyclePulse = new JTextField();
		panel_2.add(dutyCyclePulse, "4, 18, fill, default");
		dutyCyclePulse.setColumns(10);
		
		JPanel modConfiguration = new JPanel();
		modConfiguration.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		agilent_33220a.add(modConfiguration);
		FormLayout fl_modConfiguration = new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,});
		modConfiguration.setLayout(fl_modConfiguration);
		
		JLabel lblModulationConfig = new JLabel("Modulation Config:");
		lblModulationConfig.setFont(new Font("Dialog", Font.BOLD, 14));
		modConfiguration.add(lblModulationConfig, "2, 2");
		
		JComboBox modType = new JComboBox();
		modType.setMaximumRowCount(4);
		modType.setModel(new DefaultComboBoxModel(new String[] {"AM", "FM", "PWM", "PM", "FSK", "Burst Mode"}));
		modConfiguration.add(modType, "4, 4, fill, default");
		
		JLabel lblNewLabel_6 = new JLabel("Modulating wfm Shape:");
		modConfiguration.add(lblNewLabel_6, "2, 6, right, default");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Sine", "Square", "Triangle", "Up Ramp", "Down  Ramp", "Noise", "Sinc", "Neg. Ramp", "Exp. Rise", "Exp. Fall"}));
		modConfiguration.add(comboBox_1, "4, 6, fill, default");
		
		JLabel lblNewLabel_7 = new JLabel("Modulating Frequency (Hz):");
		modConfiguration.add(lblNewLabel_7, "2, 8, right, default");
		
		modulatingFreq = new JTextField();
		modConfiguration.add(modulatingFreq, "4, 8, fill, default");
		modulatingFreq.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("AM Depth (%):");
		modConfiguration.add(lblNewLabel_8, "2, 10, right, default");
		
		amDepth = new JTextField();
		modConfiguration.add(amDepth, "4, 10, fill, default");
		amDepth.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("FM Deviation (Hz):");
		modConfiguration.add(lblNewLabel_9, "2, 12, right, default");
		
		fmDeviation = new JTextField();
		modConfiguration.add(fmDeviation, "4, 12, fill, default");
		fmDeviation.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Hop Frequency (Hz):");
		modConfiguration.add(lblNewLabel_10, "2, 14, right, default");
		
		hopFrequency = new JTextField();
		modConfiguration.add(hopFrequency, "4, 14, fill, default");
		hopFrequency.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Int. Deviation PWM (%):");
		modConfiguration.add(lblNewLabel_11, "2, 16, right, default");
		
		intDeviationPWM = new JTextField();
		modConfiguration.add(intDeviationPWM, "4, 16, fill, default");
		intDeviationPWM.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Phase Deviation PM (deg):");
		modConfiguration.add(lblNewLabel_12, "2, 18, right, default");
		
		phaseDeviationPM = new JTextField();
		modConfiguration.add(phaseDeviationPM, "4, 18, fill, default");
		phaseDeviationPM.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Burst Rate (Hz):");
		modConfiguration.add(lblNewLabel_13, "2, 20, right, default");
		
		burstRate = new JTextField();
		modConfiguration.add(burstRate, "4, 20, fill, default");
		burstRate.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Burst Count:");
		modConfiguration.add(lblNewLabel_14, "2, 22, right, default");
		
		burstCount = new JTextField();
		modConfiguration.add(burstCount, "4, 22, fill, default");
		burstCount.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Burst Phase (deg):");
		modConfiguration.add(lblNewLabel_15, "2, 24, right, default");
		
		burstPhase = new JTextField();
		modConfiguration.add(burstPhase, "4, 24, fill, default");
		burstPhase.setColumns(10);
		
		JPanel hp_33120a = new JPanel();
		tabbedPane_1.addTab("HP 33120A", null, hp_33120a, null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane_2);
		
		JPanel dmm = new JPanel();
		tabbedPane_2.addTab("DMM", null, dmm, null);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane);
		
		JPanel hp_54602b = new JPanel();
		tabbedPane.addTab("HP 54602B", null, hp_54602b, null);
		hp_54602b.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton connectButton = new JButton("Connect");
		connectButton.addMouseListener(new MouseAdapter() {
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
		hp_54602b.add(connectButton);
		
		JButton btnNewButton_1 = new JButton("Disconnect");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
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
		hp_54602b.add(btnNewButton_1);
		
		JButton sendButton = new JButton("Send");
		sendButton.addMouseListener(new MouseAdapter() {
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
		hp_54602b.add(sendButton);
		
		JLabel lblNewLabel = new JLabel("To Send: ");
		hp_54602b.add(lblNewLabel);
		
		msgToSend = new JTextField();
		msgToSend.setToolTipText("Introduce Text To Send\r\n");
		hp_54602b.add(msgToSend);
		msgToSend.setColumns(50);
		
		this.setVisible(true);

	}
}
