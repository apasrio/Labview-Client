import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class AG33220aView implements AG33220aInterface{
	private JPanel agilent_33220a = new JPanel();	// Holds the HP33120A GUI and its components, it is raised in the main View
	
	private JTextField frequency, amplitude, rampSymmetry, dutyCycleSquare, dutyCyclePulse, modulatingFreq;
	private JTextField offset;
	private JTextField amDepth, fmDeviation, hopFrequency, intDeviationPWM, phaseDeviationPM, burstRate, burstCount, burstPhase;	
	private JButton btnWfmConf;
	private JComboBox typeOfSignal, modType, modWfmShape, unit;
	private JPanel modConfiguration;
	private JComboBox wvfShape;
	private JLabel dataValidationMsg;
	
	
	public AG33220aView(int availableDevice){
		
		agilent_33220a.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel signalConfiguration = new JPanel();
		signalConfiguration.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		agilent_33220a.add(signalConfiguration);
		signalConfiguration.setLayout(new FormLayout(new ColumnSpec[] {
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
		signalConfiguration.add(lblSignalConfiguration, "2, 2");
		
		typeOfSignal = new JComboBox();
		typeOfSignal.setModel(new DefaultComboBoxModel(new String[] {SIGNAL, MODULATION}));
		typeOfSignal.setActionCommand(TYPE_OF_SIGNAL);
		signalConfiguration.add(typeOfSignal, "4, 4, fill, center");
		
		JLabel wvfShapeLabel = new JLabel("Waveform Shape:");
		signalConfiguration.add(wvfShapeLabel, "2, 6, right, default");
		
		wvfShape = new JComboBox();
		wvfShape.setModel(new DefaultComboBoxModel(new String[] {"DC", "Sine", "Square", "Triangle", "Ramp", "Pulse", "Noise", "Sinc", "Neg. Ramp", "Exp. Rise", "Exp. Fall"}));
		signalConfiguration.add(wvfShape, "4, 6, fill, default");
		
		JLabel lblUnit = new JLabel("Unit:");
		signalConfiguration.add(lblUnit, "2, 8, right, default");
		
		unit = new JComboBox();
		unit.setModel(new DefaultComboBoxModel(new String[] {VPP, VRMS, DB}));
		signalConfiguration.add(unit, "4, 8, fill, default");
		
		
		JLabel lblNewLabel_1 = new JLabel("Frequency (Hz): ");
		signalConfiguration.add(lblNewLabel_1, "2, 10, right, center");
		
		frequency = new JTextField();
		frequency.setText("1000");
		frequency.setName(FREQUENCY);
		signalConfiguration.add(frequency, "4, 10, fill, default");
		frequency.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Amplitude (Vpp): ");
		signalConfiguration.add(lblNewLabel_2, "2, 12, right, center");
		
		amplitude = new JTextField();
		amplitude.setText("1");
		amplitude.setName(AMPLITUDE);
		signalConfiguration.add(amplitude, "4, 12, fill, default");
		amplitude.setColumns(10);
		
		JLabel offsetLabel = new JLabel("Offset (Vdc): ");
		signalConfiguration.add(offsetLabel, "2, 14, right, default");
		
		offset = new JTextField();
		offset.setText("0");
		offset.setName(OFFSET);
		signalConfiguration.add(offset, "4, 14, fill, default");
		offset.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ramp Symmetry(%):");
		signalConfiguration.add(lblNewLabel_3, "2, 16, right, default");
		
		rampSymmetry = new JTextField();
		rampSymmetry.setText("50");
		rampSymmetry.setName(RAMP_SYMMETRY);
		signalConfiguration.add(rampSymmetry, "4, 16, fill, default");
		rampSymmetry.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Duty Cycle (%) Square:");
		signalConfiguration.add(lblNewLabel_4, "2, 18, right, default");
		
		dutyCycleSquare = new JTextField();
		dutyCycleSquare.setText("50");
		dutyCycleSquare.setName(DUTY_CYCLE_SQUARE);
		signalConfiguration.add(dutyCycleSquare, "4, 18, fill, default");
		dutyCycleSquare.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Duty Cycle (%) Pulse:");
		signalConfiguration.add(lblNewLabel_5, "2, 20, right, default");
		
		dutyCyclePulse = new JTextField();
		dutyCyclePulse.setText("50");
		dutyCyclePulse.setName(DUTY_CYCLE_PULSE);
		signalConfiguration.add(dutyCyclePulse, "4, 20, fill, default");
		dutyCyclePulse.setColumns(10);
		
		btnWfmConf = new JButton("Do it!");
		btnWfmConf.setActionCommand(CONFIG);
		signalConfiguration.add(btnWfmConf, "4, 30");
		
		dataValidationMsg = new JLabel("");
		dataValidationMsg.setForeground(Color.RED);
		signalConfiguration.add(dataValidationMsg, "2, 32, 5, 1, right, default");
		
		modConfiguration = new JPanel();
		modConfiguration.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		agilent_33220a.add(modConfiguration);
		FormLayout fl_modConfiguration = new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(80dlu;default)"),},
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
		phaseDeviationPM.setName(PHASE_DEVIATION_PM);
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
		
		if(availableDevice == 0){
			disableDevice();
		}
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

	@Override
	public void disableExecutionButton() {
		btnWfmConf.setEnabled(false);		
	}

	@Override
	public void enableExecutionButton() {
		btnWfmConf.setEnabled(true);		
	}

	@Override
	public void setAG33220aControl(AG33220aControl wfmc) {
		typeOfSignal.addActionListener(wfmc);
		btnWfmConf.addActionListener(wfmc);
		unit.addActionListener(wfmc);
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

	@Override
	public JComboBox getTypeOfSignal() {
		return typeOfSignal;
	}

	@Override
	public JComboBox getSignalShape() {
		return wvfShape;
	}

	@Override
	public JComboBox getModWfmShape() {		
		return modWfmShape;
	}

	@Override
	public JComboBox getModType() {
		return modType;
	}

	@Override
	public JComboBox getUnit() {
		return unit;
	}

	@Override
	public JPanel getModConfiguration() {
		return modConfiguration;
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
	public String getPhaseDeviationPM() {
		return phaseDeviationPM.getText();
	}

	@Override
	public String getIntDeviationPWM() {		
		return intDeviationPWM.getText();
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
	public String getDutyCyclePulse() {
		return dutyCyclePulse.getText();
	}

	@Override
	public String getDutyCycleSquare() {
		return dutyCycleSquare.getText();
	}

	@Override
	public String getRampSymmetry() {
		return rampSymmetry.getText();
	}

	@Override
	public String getAmplitude() {
		return amplitude.getText();
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
	public void setDataValidationMessage(String validationMessage) {
		dataValidationMsg.setText(validationMessage);
		dataValidationMsg.setVisible(true);		
	}

	@Override
	public void disableDataValidationLabel() {
		dataValidationMsg.setVisible(false);		
	}

	@Override
	public JComponent getAG33220aPanel() {
		return agilent_33220a;
	}

	@Override
	public void disableDevice() {
		// TODO: disable all the text fields and buttons because this device is not working right now
		
	}

}
