import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;


public interface HP33120aInterface {
	
	void enableModulationButtons(); // Method to enable buttons if normal signal is selected	
	void disableModulationbuttons(); // Method to disable buttons if normal signal is selected
	void disableExecutionButton();	// Method to disable the Execution Button if there is any problem with data validation
	void enableExecutionButton();	// Method to enable the Execution Button
	void setHP33120aControl(HP33120aControl wfmc); //Enable the ActionListeners
	
	public JComboBox<String> getTypeOfSignal();	
	public JComponent getHP33120aPanel();	
	public JComboBox<String> getSignalShape();
	public JComboBox<String> getModWfmShape();
	public JComboBox<String> getModType();
	public JComboBox<String> getUnit();
	public JButton getConnectButton();
	public String getBurstPhase();
	public String getBurstCount();
	public String getBurstRate();
	public String getHopFrequency();
	public String getFmDeviation();
	public String getAmDepth();
	public String getModulatingFreq();
	public String getDutyCycleSquare();
	public String getAmplitude();
	public String getFrequency();
	public String getOffset();
	
	public void setDataValidationMessage(String validationMessage);
	public void disableDataValidationLabel();
	public void disableDevice();
	
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
	
	public void configForFSK();
	public void configForAM();
	public void configForFM();
	public void configForBurstMode();
	
	
	
	static final int QUERY_MESSAGE_TYPE = 10;
	
	
	// Constants for HP33120
	static final String FREQUENCY = "HP33120a_Frequency";
	static final String TYPE_OF_SIGNAL = "HP33120a_Type_of_Signal";	
	static final String MODULATION_TYPE = "HP33120a_Modulation_Type";
	static final String CONFIG = "HP33120a_Config";
	static final String WAVEFORM_SHAPE = "HP33120a_Waveform_Shape";	
	static final String MOD_WAVEFORM_SHAPE = "HP33120a_Mod_Waveform_Shape";
	static final String AMPLITUDE ="HP33120a_Amplitude";
	static final String OFFSET = "HP33120a_Offset";
	static final String DUTY_CYCLE_SQUARE = "HP33120a_Duty_Cycle_Square";
	static final String MODULATING_FREQUENCY = "HP33120a_Modulating_Frequency";
	static final String AM_DEPTH = "HP33120a_AM_Depth";
	static final String FM_DEVIATION = "HP33120a_FM_Deviation";
	static final String HOP_FREQUENCY = "HP33120a_Hop_Frequency";
	static final String BURST_RATE = "HP33120a_Burst_Rate";
	static final String BURST_COUNT = "HP33120a_Burst_Count";
	static final String BURST_PHASE = "HP33120a_Burst_Phase";
}
