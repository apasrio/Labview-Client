import javax.swing.JComponent;


public interface HP54602bInterface {
	public void configExecutionButton(boolean status);			// Enable/disable configuration button
	public void setHP54602bControl(HP54602bControl control); 	// Enable the ActionListeners
	public void disableDevice();
	public void disableDataValidationMessage();	
	
	public JComponent getHP54602bPanel();	
	public void setDataValidationMessage(String Message);	
	
	// Elements for action commands
	static final String CONFIG = "HP54602b_Config";
}
