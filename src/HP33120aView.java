import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;


import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class HP33120aView implements HP33120aInterface{

	private JPanel wfmGenPanel = new JPanel();	// Holds the HP33120A GUI and its components, it is raised in the main View
	private JTextField frequency, amplitude, dutyCycleSquare, modulatingFreq, offset;
	private JTextField amDepth, fmDeviation, hopFrequency, burstRate, burstCount, burstPhase;
	private JComboBox<String> typeOfSignal, modType, modWfmShape, wfmShape, unit;
	private JButton btnWfmConf;
	private JTextArea dataValidationMsg;
	
	public HP33120aView(int availableDevice) {
		wfmGenPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel signalConf = new JPanel();
		signalConf.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		wfmGenPanel.add(signalConf);
		signalConf.setLayout(new FormLayout(new ColumnSpec[] {
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
		signalConf.add(lblSignalConfiguration, "2, 2");
		
		typeOfSignal = new JComboBox<String>();
		typeOfSignal.setModel(new DefaultComboBoxModel<String>(new String[] {Globals.SIGNAL, Globals.MODULATION}));
		typeOfSignal.setActionCommand(TYPE_OF_SIGNAL);
		signalConf.add(typeOfSignal, "4, 4, fill, center");
		
		JLabel wvfShapeLabel = new JLabel("Waveform Shape:");
		signalConf.add(wvfShapeLabel, "2, 6, right, default");
		
		wfmShape = new JComboBox<String>();
		wfmShape.setModel(new DefaultComboBoxModel<String>(new String[] {Globals.DC, 
				Globals.SINE, 
				Globals.SQUARE, 
				Globals.TRIANGLE, 
				Globals.RAMP, 
				Globals.PULSE, 
				Globals.NOISE,
				Globals.SINC,
				Globals.NEG_RAMP,
				Globals.EXP_RISE,
				Globals.EXP_FALL}));
		wfmShape.setActionCommand(WAVEFORM_SHAPE);
		signalConf.add(wfmShape, "4, 6, fill, default");		
		
		JLabel lblUnit = new JLabel("Unit:");
		signalConf.add(lblUnit, "2, 8, right, default");
		
		unit = new JComboBox<String>();
		signalConf.add(unit, "4, 8, fill, default");
		unit.setModel(new DefaultComboBoxModel<String>(new String[] {Globals.VPP, Globals.VRMS, Globals.DB}));
		
		
		JLabel lblFrequency = new JLabel("Frequency (Hz): ");
		signalConf.add(lblFrequency, "2, 10, right, center");
		
		frequency = new JTextField();
		frequency.setEnabled(false);
		frequency.setText("1000");
		frequency.setName(FREQUENCY);
		signalConf.add(frequency, "4, 10, fill, default");
		frequency.setColumns(10);
		
		JLabel lblAmplitude = new JLabel("Amplitude (Vpp): ");
		signalConf.add(lblAmplitude, "2, 12, right, center");
		
		amplitude = new JTextField();
		amplitude.setEnabled(false);
		amplitude.setText("1");
		amplitude.setName(AMPLITUDE);
		signalConf.add(amplitude, "4, 12, fill, default");
		amplitude.setColumns(10);
		
		JLabel lblOffset = new JLabel("Offset (Vdc): ");
		signalConf.add(lblOffset, "2, 14, right, default");
		
		offset = new JTextField();
		offset.setText("0");
		offset.setName(OFFSET);
		signalConf.add(offset, "4, 14, fill, default");
		offset.setColumns(10);
		
		JLabel lblDutyCycleSqr = new JLabel("Duty Cycle (%) Square:");
		signalConf.add(lblDutyCycleSqr, "2, 16, right, default");
		
		dutyCycleSquare = new JTextField();
		dutyCycleSquare.setEnabled(false);
		dutyCycleSquare.setText("50");
		dutyCycleSquare.setName(DUTY_CYCLE_SQUARE);
		signalConf.add(dutyCycleSquare, "4, 16, fill, default");
		dutyCycleSquare.setColumns(10);
		
		btnWfmConf = new JButton("Do it!");
		btnWfmConf.setActionCommand(CONFIG);
		signalConf.add(btnWfmConf, "4, 30");
		
		dataValidationMsg = new JTextArea("");
		dataValidationMsg.setBackground(UIManager.getColor("Label.background"));
		dataValidationMsg.setForeground(Color.RED);
		signalConf.add(dataValidationMsg, "2, 32, 5, 1, right, default");
		
		JPanel modConf = new JPanel();
		wfmGenPanel.add(modConf);
		modConf.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
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
		modConf.setLayout(fl_modConfiguration);
		
		JLabel lblModulationConfig = new JLabel("Modulation Config:");
		lblModulationConfig.setFont(new Font("Dialog", Font.BOLD, 14));
		modConf.add(lblModulationConfig, "2, 2");
		
		JLabel lblModulationType = new JLabel("Modulation Type:");
		modConf.add(lblModulationType, "2, 4, right, default");
		
		modType = new JComboBox<String>();
		modType.setEnabled(false);
		modType.setMaximumRowCount(4);
		modType.setActionCommand(HP33120aInterface.MODULATION_TYPE);
		modType.setModel(new DefaultComboBoxModel<String>(new String[] {Globals.AM,
				Globals.FM,
				Globals.PWM,
				Globals.PM,
				Globals.FSK,
				Globals.BURST_MODE}));
		modConf.add(modType, "4, 4, fill, default");
		// TODO: fix the next line
		//modType.setRenderer(new CustomListCellRenderer());
		// When this method works we can disable Modulations that are not supported by the device
		
		JLabel lblModWfmShape = new JLabel("Modulating wfm Shape:");
		modConf.add(lblModWfmShape, "2, 6, right, default");
		
		modWfmShape = new JComboBox<String>();
		modWfmShape.setEnabled(false);
		modWfmShape.setModel(new DefaultComboBoxModel<String>(new String[] {Globals.SINE,
				Globals.SQUARE,
				Globals.TRIANGLE,
				Globals.UP_RAMP,
				Globals.DOWN_RAMP,
				Globals.NOISE,
				Globals.SINC,
				Globals.NEG_RAMP,
				Globals.EXP_RISE,
				Globals.EXP_FALL}));
		modConf.add(modWfmShape, "4, 6, fill, default");
		modWfmShape.setActionCommand(MOD_WAVEFORM_SHAPE);
		
		JLabel lblModFreq = new JLabel("Modulating Frequency (Hz):");
		modConf.add(lblModFreq, "2, 8, right, default");
		
		modulatingFreq = new JTextField();
		modulatingFreq.setText("100");
		modulatingFreq.setName(MODULATING_FREQUENCY);
		modulatingFreq.setEnabled(false);
		modConf.add(modulatingFreq, "4, 8, fill, default");
		modulatingFreq.setColumns(10);
		
		JLabel lblAMDepth = new JLabel("AM Depth (%):");
		modConf.add(lblAMDepth, "2, 10, right, default");
		
		amDepth = new JTextField();
		amDepth.setText("100");
		amDepth.setEnabled(false);
		amDepth.setName(AM_DEPTH);
		modConf.add(amDepth, "4, 10, fill, default");
		amDepth.setColumns(10);
		
		JLabel lblFMDeviation = new JLabel("FM Deviation (Hz):");
		modConf.add(lblFMDeviation, "2, 12, right, default");
		
		fmDeviation = new JTextField();
		fmDeviation.setText("100");
		fmDeviation.setEnabled(false);
		fmDeviation.setName(FM_DEVIATION);
		modConf.add(fmDeviation, "4, 12, fill, default");
		fmDeviation.setColumns(10);		
		
		JLabel lblHopFreq = new JLabel("Hop Frequency (Hz):");
		modConf.add(lblHopFreq, "2, 14, right, default");
		
		hopFrequency = new JTextField();
		hopFrequency.setText("100");
		hopFrequency.setEnabled(false);
		hopFrequency.setName(HOP_FREQUENCY);
		modConf.add(hopFrequency, "4, 14, fill, default");
		hopFrequency.setColumns(10);
		
		JLabel lblBurstRate = new JLabel("Burst Rate (Hz):");
		modConf.add(lblBurstRate, "2, 16, right, default");
		
		burstRate = new JTextField();
		burstRate.setText("100");
		burstRate.setEnabled(false);
		burstRate.setName(BURST_RATE);
		modConf.add(burstRate, "4, 16, fill, default");
		burstRate.setColumns(10);
		
		JLabel lblBurstCount = new JLabel("Burst Count:");
		modConf.add(lblBurstCount, "2, 18, right, default");
		
		burstCount = new JTextField();
		burstCount.setText("1");
		burstCount.setEnabled(false);
		burstCount.setName(BURST_COUNT);
		modConf.add(burstCount, "4, 18, fill, default");
		burstCount.setColumns(10);
		
		JLabel lblBurstPhase = new JLabel("Burst Phase (deg):");
		modConf.add(lblBurstPhase, "2, 20, right, default");
		
		burstPhase = new JTextField();
		burstPhase.setText("0");
		burstPhase.setEnabled(false);
		burstPhase.setName(BURST_PHASE);
		modConf.add(burstPhase, "4, 20, fill, default");
		burstPhase.setColumns(10);
		
	}
	// get the HP34401 GUI and its components for display
	public JComponent getHP33120aPanel(){
		return wfmGenPanel;
	}

	@Override
	public void enableModulationButtons() {
		modType.setEnabled(true);
		modWfmShape.setEnabled(true);
		modulatingFreq.setEnabled(true);
		amDepth.setEnabled(true);
		fmDeviation.setEnabled(true);
		hopFrequency.setEnabled(true);
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
		burstRate.setEnabled(false);
		burstCount.setEnabled(false);
		burstPhase.setEnabled(false);		
	}

	@Override
	public void disableExecutionButton() {
		btnWfmConf.setEnabled(false);		
	}

	@Override
	public void enableExecutionButton() {
		btnWfmConf.setEnabled(true);		
	}

	@Override
	public void setDataValidationMessage(String validationMessage) {
		dataValidationMsg.setText(validationMessage);
		dataValidationMsg.setVisible(true);
	}

	@Override
	public void disableDataValidationLabel() {
		dataValidationMsg.setVisible(false);		
	}

	@Override
	public void setHP33120aControl(HP33120aControl wfmc) {
		typeOfSignal.addActionListener(wfmc);
		wfmShape.addActionListener(wfmc);
		btnWfmConf.addActionListener(wfmc);
		frequency.addFocusListener(wfmc);
		amplitude.addFocusListener(wfmc);
		unit.addActionListener(wfmc);
		offset.addFocusListener(wfmc);
		dutyCycleSquare.addFocusListener(wfmc);
		modType.addActionListener(wfmc);
		modWfmShape.addActionListener(wfmc);
		modulatingFreq.addFocusListener(wfmc);
		amDepth.addFocusListener(wfmc);
		fmDeviation.addFocusListener(wfmc);
		hopFrequency.addFocusListener(wfmc);
		burstRate.addFocusListener(wfmc);
		burstCount.addFocusListener(wfmc);
		burstPhase.addFocusListener(wfmc);
	}

	@Override
	public JComboBox<String> getTypeOfSignal() {
		return typeOfSignal;
	}

	@Override
	public String getAmplitude() {		
		return amplitude.getText();
	}

	@Override
	public JComboBox<String> getSignalShape() {
		return wfmShape;
	}

	@Override
	public JComboBox<String> getModWfmShape() {
		return modWfmShape;
	}

	@Override
	public JComboBox<String> getModType() {
		return modType;
	}	

	@Override
	public JButton getConnectButton() {
		return btnWfmConf;
	}

	@Override
	public String getBurstPhase() {
		return burstPhase.getText();
	}

	@Override
	public String getBurstCount() {
		return burstCount.getText();
	}

	@Override
	public String getBurstRate() {
		return burstRate.getText();
	}
	
	@Override
	public String getHopFrequency() {
		return hopFrequency.getText();
	}

	@Override
	public String getFmDeviation() {
		return fmDeviation.getText();
	}

	@Override
	public String getAmDepth() {
		return amDepth.getText();
	}

	@Override
	public String getModulatingFreq() {
		return modulatingFreq.getText();
	}

	@Override
	public String getDutyCycleSquare() {
		return dutyCycleSquare.getText();
	}

	@Override
	public String getFrequency() {
		return frequency.getText();
	}

	@Override
	public String getOffset() {
		return offset.getText();
	}

	@Override
	public JComboBox<String> getUnit() {
		return unit;
	}
	
	// TODO: Make it work right! 
	/*class CustomListCellRenderer extends JLabel implements ListCellRenderer{
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			setText(value.toString());
			if(index ==  2 || index == 3){
				setEnabled(false);
				setFocusable(false);
			}else{
				setEnabled(true);
				setFocusable(true);
			}
			return this;
		}		
	}*/
	
	@Override
	public void configForSine() {
		amplitude.setEnabled(true);
		frequency.setEnabled(true);
		offset.setEnabled(true);
		dutyCycleSquare.setEnabled(false);		
	}
	@Override
	public void configForSquare() {
		amplitude.setEnabled(true);
		frequency.setEnabled(true);
		offset.setEnabled(true);
		dutyCycleSquare.setEnabled(true);			
	}
	@Override
	public void configForTriangle() {
		amplitude.setEnabled(true);
		frequency.setEnabled(true);
		offset.setEnabled(true);
		dutyCycleSquare.setEnabled(false);		
	}
	@Override
	public void configForRamp() {
		amplitude.setEnabled(true);
		frequency.setEnabled(true);
		offset.setEnabled(true);
		dutyCycleSquare.setEnabled(false);			
	}
	@Override
	public void configForPulse() {
		// TODO: Does the HP33120a the Pulse waveform shape? We have to disable this option!!!!
		amplitude.setEnabled(true);
		frequency.setEnabled(true);
		offset.setEnabled(true);
		dutyCycleSquare.setEnabled(false);				
	}
	@Override
	public void configForNoise() {
		amplitude.setEnabled(true);
		frequency.setEnabled(true);
		offset.setEnabled(true);
		dutyCycleSquare.setEnabled(false);				
	}
	@Override
	public void configForSinc() {
		amplitude.setEnabled(true);
		frequency.setEnabled(true);
		offset.setEnabled(true);
		dutyCycleSquare.setEnabled(false);			
	}
	@Override
	public void configForNegRamp() {
		amplitude.setEnabled(true);
		frequency.setEnabled(true);
		offset.setEnabled(true);
		dutyCycleSquare.setEnabled(false);			
	}
	@Override
	public void configForExpRise() {
		amplitude.setEnabled(true);
		frequency.setEnabled(true);
		offset.setEnabled(true);
		dutyCycleSquare.setEnabled(false);		
	}
	@Override
	public void configForExpFall() {
		amplitude.setEnabled(true);
		frequency.setEnabled(true);
		offset.setEnabled(true);
		dutyCycleSquare.setEnabled(false);		
	}
	@Override
	public void configForDC() {
		amplitude.setEnabled(false);
		frequency.setEnabled(false);
		offset.setEnabled(true);
		dutyCycleSquare.setEnabled(false);
	}
	@Override
	public void configForFSK() {
		// TODO: Check if it is right! 
		modulatingFreq.setEnabled(true);		
		amDepth.setEnabled(false);
		fmDeviation.setEnabled(false);
		hopFrequency.setEnabled(true);
		burstPhase.setEnabled(false);
		burstCount.setEnabled(false);
		burstRate.setEnabled(false);
		
	}
	@Override
	public void configForAM() {
		modulatingFreq.setEnabled(true);
		amDepth.setEnabled(true);
		fmDeviation.setEnabled(false);
		hopFrequency.setEnabled(false);
		burstPhase.setEnabled(false);
		burstCount.setEnabled(false);
		burstRate.setEnabled(false);
		
		// Choose as default carrier Sine
		wfmShape.setSelectedIndex(1);
	}
	@Override
	public void configForFM() {
		// TODO Auto-generated method stub
		modulatingFreq.setEnabled(true);
		amDepth.setEnabled(false);
		fmDeviation.setEnabled(true);
		hopFrequency.setEnabled(false);
		burstPhase.setEnabled(false);
		burstCount.setEnabled(false);
		burstRate.setEnabled(false);
	}
	@Override
	public void configForBurstMode() {
		// TODO Auto-generated method stub
		modulatingFreq.setEnabled(false);
		amDepth.setEnabled(false);
		fmDeviation.setEnabled(false);
		hopFrequency.setEnabled(false);
		burstPhase.setEnabled(true);
		burstCount.setEnabled(true);
		burstRate.setEnabled(true);
	}	
}
