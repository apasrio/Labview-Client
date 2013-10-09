import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import org.jfree.data.xy.XYSeries;


public interface HP54602bInterface {
	public void configExecutionButton(boolean status);			// Enable/disable configuration button
	public void setHP54602bControl(HP54602bControl control); 	// Enable the ActionListeners
	public void disableDevice();
	public void disableDataValidationMessage();	
	
	public JComponent getHP54602bPanel();	
	public void setDataValidationMessage(String Message);
	public void setFunc1MeasuredValue(String measuredValue);
	public void setFunc2MeasuredValue(String measuredValue);
	public void setXYSeries(XYSeries trace1);
	
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
	static final String CH1_POS = "HP54602b_CH1_Pos";
	static final String CH1_RANGE = "HP54602b_CH1_Range";
	static final String CH2_POS = "HP54602b_CH2_Pos";
	static final String CH2_RANGE = "HP54602b_CH2_Range";
	static final String TIME_RANGE = "HP54602b_Time_Range";
	static final String TIME_DELAY = "HP54602b_Time_Delay";
	static final String TRIGGER_LEVEL = "HP54602b_Triger_Level";
}
