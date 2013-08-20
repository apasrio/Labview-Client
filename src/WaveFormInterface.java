import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;


public interface WaveFormInterface {
	void enableModulationButtons(); // Method to enable buttons if normal signal is selected	
	void disableModulationbuttons(); // Method to disable buttons if normal signal is selected
	void setWfmControl(WaveformControl wfmc);
	
	// Needed getters and setters
	public JComboBox getTypeOfSignal();
	public JComboBox getSignalShape();
	public JComboBox getModWfmShape();
	public JComboBox getModType();
	public JPanel getModConfiguration();
	public JButton getConnectButton();
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
		
	static final String TYPE_OF_SIGNAL = "Type of Signal";
	static final String MODULATION = "Modulation";
	static final String SIGNAL = "Simple Signal";
	static final String CONFIG = "Config";
}
