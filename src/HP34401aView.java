import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class HP34401aView {
	
	/**
	 * Class to develop the GUI for the HP34401A DMM
	 */
	
	private JPanel dmmPanel = new JPanel();	// Holds the HP34401 GUI and its components, it is raised in the main View
	
	public HP34401aView(){
		
		JLabel lblReadyToBe = new JLabel("Ready to be developed");
		dmmPanel.add(lblReadyToBe);
		
	
	}
	
	// get the HP34401 GUI and its components for display
	public JComponent getHP34401aPanel(){
		return dmmPanel;
	}
}
