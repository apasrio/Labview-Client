import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class Vista extends JFrame implements ViewInterface, WaveFormInterface {
	
	static String SocketIp = "127.0.0.1";
	static int SocketPort = 5020;
	private JTextField msgToSend, frequency, amplitude, rampSymmetry, dutyCycleSquare, dutyCyclePulse, modulatingFreq;
	private JTextField offset;
	private JTextField amDepth, fmDeviation, hopFrequency, intDeviationPWM, phaseDeviationPM, burstRate, burstCount, burstPhase;
	private JButton connectButton, disconnectButton, sendButton;
	private JButton btnWfmConf;
	private JComboBox typeOfSignal, modType, modWfmShape;
	private JPanel modConfiguration;
	private JComboBox wvfShape;

	/**
	 * Create the applet.
	 */
	public Vista(final TCPClient socketClient) {
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
		
		typeOfSignal = new JComboBox();
		typeOfSignal.setModel(new DefaultComboBoxModel(new String[] {SIGNAL, MODULATION}));
		typeOfSignal.setActionCommand(TYPE_OF_SIGNAL);
		panel_2.add(typeOfSignal, "4, 4, fill, center");
		
		JLabel wvfShapeLabel = new JLabel("Waveform Shape:");
		panel_2.add(wvfShapeLabel, "2, 6, right, default");
		
		wvfShape = new JComboBox();
		wvfShape.setModel(new DefaultComboBoxModel(new String[] {"DC", "Sine", "Square", "Triangle", "Ramp", "Pulse", "Noise", "Sinc", "Neg. Ramp", "Exp. Rise", "Exp. Fall"}));
		panel_2.add(wvfShape, "4, 6, fill, default");
		
		JLabel lblNewLabel_1 = new JLabel("Frequency (Hz): ");
		panel_2.add(lblNewLabel_1, "2, 8, right, center");
		
		frequency = new JTextField();
		frequency.setText("1000");
		frequency.setName(FREQUENCY);
		panel_2.add(frequency, "4, 8, fill, default");
		frequency.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Amplitude (Vpp): ");
		panel_2.add(lblNewLabel_2, "2, 10, right, center");
		
		amplitude = new JTextField();
		amplitude.setText("1");
		amplitude.setName(AMPLITUDE);
		panel_2.add(amplitude, "4, 10, fill, default");
		amplitude.setColumns(10);
		
		JLabel offsetLabel = new JLabel("Offset (Vdc): ");
		panel_2.add(offsetLabel, "2, 12, right, default");
		
		offset = new JTextField();
		offset.setText("0");
		offset.setName(OFFSET);
		panel_2.add(offset, "4, 12, fill, default");
		offset.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ramp Symmetry(%):");
		panel_2.add(lblNewLabel_3, "2, 14, right, default");
		
		rampSymmetry = new JTextField();
		rampSymmetry.setText("50");
		rampSymmetry.setName(RAMP_SYMMETRY);
		panel_2.add(rampSymmetry, "4, 14, fill, default");
		rampSymmetry.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Duty Cycle (%) Square:");
		panel_2.add(lblNewLabel_4, "2, 16, right, default");
		
		dutyCycleSquare = new JTextField();
		dutyCycleSquare.setText("50");
		dutyCycleSquare.setName(DUTY_CYCLE_SQUARE);
		panel_2.add(dutyCycleSquare, "4, 16, fill, default");
		dutyCycleSquare.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Duty Cycle (%) Pulse:");
		panel_2.add(lblNewLabel_5, "2, 18, right, default");
		
		dutyCyclePulse = new JTextField();
		dutyCyclePulse.setText("50");
		dutyCyclePulse.setName(DUTY_CYCLE_PULSE);
		panel_2.add(dutyCyclePulse, "4, 18, fill, default");
		dutyCyclePulse.setColumns(10);
		
		btnWfmConf = new JButton("Do it!");
		btnWfmConf.setActionCommand(CONFIG);
		panel_2.add(btnWfmConf, "4, 30");
		
		modConfiguration = new JPanel();
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
		
		JLabel lblModulationType = new JLabel("Modulation Type:");
		modConfiguration.add(lblModulationType, "2, 4, right, default");
		
		modType = new JComboBox();
		modType.setEnabled(false);
		modType.setMaximumRowCount(4);
		modType.setModel(new DefaultComboBoxModel(new String[] {"AM", "FM", "PWM", "PM", "FSK", "Burst Mode"}));
		modConfiguration.add(modType, "4, 4, fill, default");
		
		JLabel lblNewLabel_6 = new JLabel("Modulating wfm Shape:");
		modConfiguration.add(lblNewLabel_6, "2, 6, right, default");
		
		modWfmShape = new JComboBox();
		modWfmShape.setEnabled(false);
		modWfmShape.setModel(new DefaultComboBoxModel(new String[] {"Sine", "Square", "Triangle", "Up Ramp", "Down  Ramp", "Noise", "Sinc", "Neg. Ramp", "Exp. Rise", "Exp. Fall"}));
		modConfiguration.add(modWfmShape, "4, 6, fill, default");
		
		JLabel lblNewLabel_7 = new JLabel("Modulating Frequency (Hz):");
		modConfiguration.add(lblNewLabel_7, "2, 8, right, default");
		
		modulatingFreq = new JTextField();
		modulatingFreq.setText("1000");
		modulatingFreq.setName(MODULATING_FREQUENCY);
		modulatingFreq.setEnabled(false);
		modConfiguration.add(modulatingFreq, "4, 8, fill, default");
		modulatingFreq.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("AM Depth (%):");
		modConfiguration.add(lblNewLabel_8, "2, 10, right, default");
		
		amDepth = new JTextField();
		amDepth.setText("50");
		amDepth.setEnabled(false);
		amDepth.setName(AM_DEPTH);
		modConfiguration.add(amDepth, "4, 10, fill, default");
		amDepth.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("FM Deviation (Hz):");
		modConfiguration.add(lblNewLabel_9, "2, 12, right, default");
		
		fmDeviation = new JTextField();
		fmDeviation.setText("50");
		fmDeviation.setEnabled(false);
		fmDeviation.setName(FM_DEVIATION);
		modConfiguration.add(fmDeviation, "4, 12, fill, default");
		fmDeviation.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Hop Frequency (Hz):");
		modConfiguration.add(lblNewLabel_10, "2, 14, right, default");
		
		hopFrequency = new JTextField();
		hopFrequency.setText("500");
		hopFrequency.setEnabled(false);
		hopFrequency.setName(HOP_FREQUENCY);
		modConfiguration.add(hopFrequency, "4, 14, fill, default");
		hopFrequency.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Int. Deviation PWM (%):");
		modConfiguration.add(lblNewLabel_11, "2, 16, right, default");
		
		intDeviationPWM = new JTextField();
		intDeviationPWM.setText("0");
		intDeviationPWM.setEnabled(false);
		intDeviationPWM.setName(DEVIATION_PWM);
		modConfiguration.add(intDeviationPWM, "4, 16, fill, default");
		intDeviationPWM.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Phase Deviation PM (deg):");
		modConfiguration.add(lblNewLabel_12, "2, 18, right, default");
		
		phaseDeviationPM = new JTextField();
		phaseDeviationPM.setText("0");
		phaseDeviationPM.setEnabled(false);
		phaseDeviationPM.setText(PHASE_DEVIATION_PM);
		modConfiguration.add(phaseDeviationPM, "4, 18, fill, default");
		phaseDeviationPM.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Burst Rate (Hz):");
		modConfiguration.add(lblNewLabel_13, "2, 20, right, default");
		
		burstRate = new JTextField();
		burstRate.setText("100");
		burstRate.setEnabled(false);
		burstRate.setName(BURST_RATE);
		modConfiguration.add(burstRate, "4, 20, fill, default");
		burstRate.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Burst Count:");
		modConfiguration.add(lblNewLabel_14, "2, 22, right, default");
		
		burstCount = new JTextField();
		burstCount.setText("1");
		burstCount.setEnabled(false);
		burstCount.setName(BURST_COUNT);
		modConfiguration.add(burstCount, "4, 22, fill, default");
		burstCount.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Burst Phase (deg):");
		modConfiguration.add(lblNewLabel_15, "2, 24, right, default");
		
		burstPhase = new JTextField();
		burstPhase.setText("0");
		burstPhase.setEnabled(false);
		burstPhase.setName(BURST_PHASE);
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
		
		connectButton = new JButton("Connect");
		connectButton.setActionCommand(CONNECT);
		hp_54602b.add(connectButton);
		
		disconnectButton = new JButton("Disconnect");
		disconnectButton.setActionCommand(DISCONNECT);
		hp_54602b.add(disconnectButton);
		
		sendButton = new JButton("Send");
		sendButton.setActionCommand(SEND);
		hp_54602b.add(sendButton);
		
		JLabel lblNewLabel = new JLabel("To Send: ");
		hp_54602b.add(lblNewLabel);
		
		msgToSend = new JTextField();
		msgToSend.setToolTipText("Introduce Text To Send\r\n");
		hp_54602b.add(msgToSend);
		msgToSend.setColumns(50);
		
		this.setVisible(true);

	}

	@Override
	public void start() {		
	}

	@Override
	public void setConfControl(ConfControl c) {
		// Adding conf control
		connectButton.addActionListener(c);
		disconnectButton.addActionListener(c);
		sendButton.addActionListener(c);
	}

	public String getMsgToSend() {
		return msgToSend.getText();
	}

	@Override
	public void setWfmControl(WaveformControl wfmc) {
		typeOfSignal.addActionListener(wfmc);
		btnWfmConf.addActionListener(wfmc);
		frequency.addFocusListener(wfmc);
		amplitude.addFocusListener(wfmc);
		offset.addFocusListener(wfmc);
		rampSymmetry.addFocusListener(wfmc);
		dutyCycleSquare.addFocusListener(wfmc);
		dutyCyclePulse.addFocusListener(wfmc);
		modulatingFreq.addFocusListener(wfmc);
		amDepth.addFocusListener(wfmc);
		fmDeviation.addFocusListener(wfmc);
		hopFrequency.addFocusListener(wfmc);
		intDeviationPWM.addFocusListener(wfmc);
		phaseDeviationPM.addFocusListener(wfmc);
		burstRate.addFocusListener(wfmc);
		burstCount.addFocusListener(wfmc);
		burstPhase.addFocusListener(wfmc);
	}

	public JComboBox getTypeOfSignal() {
		return typeOfSignal;
	}

	@Override
	public void enableModulationButtons() {	
		modType.setEnabled(true);
		modWfmShape.setEnabled(true);
		modulatingFreq.setEnabled(true);
		amDepth.setEnabled(true);
		fmDeviation.setEnabled(true);
		hopFrequency.setEnabled(true);
		intDeviationPWM.setEnabled(true);
		phaseDeviationPM.setEnabled(true);
		burstRate.setEnabled(true);
		burstCount.setEnabled(true);
		burstPhase.setEnabled(true);
	}

	@Override
	public void disableModulationbuttons() {
		modType.setEnabled(false);
		modWfmShape.setEnabled(false);
		modulatingFreq.setEnabled(false);
		amDepth.setEnabled(false);
		fmDeviation.setEnabled(false);
		hopFrequency.setEnabled(false);
		intDeviationPWM.setEnabled(false);
		phaseDeviationPM.setEnabled(false);
		burstRate.setEnabled(false);
		burstCount.setEnabled(false);
		burstPhase.setEnabled(false);
	}

	public String getFrequency() {
		return frequency.getText();
	}

	public String getAmplitude() {
		return amplitude.getText();
	}

	public String getRampSymmetry() {
		return rampSymmetry.getText();
	}

	public String getDutyCycleSquare() {
		return dutyCycleSquare.getText();
	}

	public String getDutyCyclePulse() {
		return dutyCyclePulse.getText();
	}

	public String getModulatingFreq() {
		return modulatingFreq.getText();
	}

	public String getAmDepth() {
		return amDepth.getText();
	}

	public String getFmDeviation() {
		return fmDeviation.getText();
	}

	public String getHopFrequency() {
		return hopFrequency.getText();
	}

	public String getIntDeviationPWM() {
		return intDeviationPWM.getText();
	}

	public String getPhaseDeviationPM() {
		return phaseDeviationPM.getText();
	}

	public String getBurstRate() {
		return burstRate.getText();
	}

	public String getBurstCount() {
		return burstCount.getText();
	}

	public String getBurstPhase() {
		return burstPhase.getText();
	}

	public JButton getConnectButton() {
		return connectButton;
	}

	public JComboBox getModType() {
		return modType;
	}

	public JComboBox getModWfmShape() {
		return modWfmShape;
	}

	public JPanel getModConfiguration() {
		return modConfiguration;
	}
	
	public JComboBox getSignalShape() {
		return wvfShape;
	}
	
	public String getOffset() {
		return offset.getText();
	}
}
