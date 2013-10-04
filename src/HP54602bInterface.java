import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


public interface HP54602bInterface {
	public void configExecutionButton(boolean status);			// Enable/disable configuration button
	public void setHP54602bControl(HP54602bControl control); 	// Enable the ActionListeners
	public void disableDevice();
	public void disableDataValidationMessage();	
	
	public JComponent getHP54602bPanel();	
	public void setDataValidationMessage(String Message);	
	
	// Getters
	public JTextField getRangeCh1();
	public JTextField getPositionCh1();
	public JTextField getRangeCh2();
	public JTextField getPositionCh2();
	public JTextField getRangeTime();
	public JTextField getTriggerlevel();
	public JTextField getTimeDelay();
	public JComboBox<String> getTriggerSource();
	public JComboBox<String> getProbeCh2(); 
	public JComboBox<String> getProbeCh1();
	public JComboBox<String> getCouplingCh2();
	public JComboBox<String> getCouplingCh1();
	public JComboBox<String> getFunctionCh2();
	public JComboBox<String> getFunctionCh1();
	public JToggleButton getBtnCh1(); 
	public JToggleButton getBtnCh2();
	public JToggleButton getBtnCh2BW();
	public JToggleButton getBtnCh1BW();
	public JToggleButton getAutoSet(); 
	public JToggleButton getSlopeButton();
	public JButton getConfigButton(); 
	
	// Elements for action commands
	static final String CONFIG = "HP54602b_Config";
}
