import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;


public interface AG33220aInterface {
	void enableModulationButtons(); // Method to enable buttons if normal signal is selected	
	void disableModulationbuttons(); // Method to disable buttons if normal signal is selected
	void disableExecutionButton();	// Method to disable the Execution Button if there is any problem with data validation
	void enableExecutionButton();	// Method to enable the Execution Button
	void setAG33220aControl(AG33220aControl wfmc);
	
	// Needed getters and setters
	public JComboBox getTypeOfSignal();
	public JComboBox getSignalShape();
	public JComboBox getModWfmShape();
	public JComboBox getModType();
	public JComboBox getUnit();
	public JComponent getAG33220aPanel();
	public JPanel getModConfiguration();
	public String getBurstPhase();
	public String getBurstCount();
	public String getBurstRate();
	public String getPhaseDeviationPM();
	public String getIntDeviationPWM();
	public String getHopFrequency();
	public String getFmDeviation();
	public String getAmDepth();
	public String getModulatingFreq();
	public String getDutyCyclePulse();
	public String getDutyCycleSquare();
	public String getRampSymmetry();
	public String getAmplitude();
	public String getFrequency();
	public String getOffset();
	
	public void setDataValidationMessage(String validationMessage);
	public void disableDataValidationLabel();
	public void disableDevice();
	
	static final int QUERY_MESSAGE_TYPE = 20;
	
	// Methods to enable/disable buttons 
	public void configForDC();
	public void configForSine();
	public void configForSquare();
	public void configForTriangle();
	public void configForRamp();
	public void configForPulse();
	public void configForNoise();
	public void configForSinc();
	public void configForNegRamp();
	public void configForExpRise();
	public void configForExpFall();
	
	static final String VPP = "Vpp";
	static final String WAVEFORM_SHAPE = "AG33220A_Waveform_Shape";	
	static final String VRMS = "Vrms";
	static final String DB = "dB";		
	static final String TYPE_OF_SIGNAL = "Type of Signal";
	static final String MODULATION = "Modulation";
	static final String SIGNAL = "Simple Signal";
	static final String CONFIG = "Config";
	static final String FREQUENCY = "Frequency";
	static final String AMPLITUDE ="Amplitude";
	static final String OFFSET = "Offset";
	static final String RAMP_SYMMETRY = "Ramp_Symmetry";
	static final String DUTY_CYCLE_SQUARE = "Duty_Cycle_Square";
	static final String DUTY_CYCLE_PULSE = "Duty_Cycle_Pulse";
	static final String MODULATING_FREQUENCY = "Modulating_Frequency";
	static final String AM_DEPTH = "AM_Depth";
	static final String FM_DEVIATION = "FM_Deviation";
	static final String HOP_FREQUENCY = "Hop_Frequency";
	static final String DEVIATION_PWM = "Deviation_PWM";
	static final String PHASE_DEVIATION_PM = "Phase_Deviation";
	static final String BURST_RATE = "Burst_Rate";
	static final String BURST_COUNT = "Burst_Count";
	static final String BURST_PHASE = "Burst_Phase";
}
