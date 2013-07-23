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
	public JPanel getModConfiguration();
	public JComboBox getModWfmShape();
	public JComboBox getModType();
	public JButton getConnectButton();
	public JTextField getBurstPhase();
	public JTextField getBurstCount();
	public JTextField getBurstRate();
	public JTextField getPhaseDeviationPM();
	public JTextField getIntDeviationPWM();
	public JTextField getHopFrequency();
	public JTextField getFmDeviation();
	public JTextField getAmDepth();
	public JTextField getModulatingFreq();
	public JTextField getDutyCyclePulse();
	public JTextField getDutyCycleSquare();
	public JTextField getRampSymmetry();
	public JTextField getAmplitude();
	public JTextField getFrequency();
		
	static final String TYPE_OF_SIGNAL = "Type of Signal";
	static final String MODULATION = "Modulation";
	static final String SIGNAL = "Simple Signal";
	static final String CONFIG = "Config";
}
