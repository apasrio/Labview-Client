import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class HP34401aView implements HP34401aInterface{
	
	/**
	 * Class to develop the GUI for the HP34401A DMM
	 */
	
	private JPanel dmmPanel = new JPanel();	// Holds the HP34401 GUI and its components, it is raised in the main View
	private JComboBox function, resolution, triggerSource;
	private JTextField manualRange, measure;
	private JToggleButton btnAutozero, btnAutoRange;
	private JButton configButton;
	private JTextArea dataValidationMsg;
	
	public HP34401aView(int availableDevice){
		dmmPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:max(79dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(75dlu;default)"),},
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
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Digital Multimeter");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		dmmPanel.add(lblNewLabel, "2, 2, center, default");
		
		JLabel functionLabel = new JLabel("Function:");
		dmmPanel.add(functionLabel, "2, 4, right, default");
		
		function = new JComboBox();
		function.setModel(new DefaultComboBoxModel(new String[] {DC_VOLTAGE,
				AC_VOLTAGE,
				WIRE_2_RESISTANCE,
				WIRE_4_RESISTANCE,
				DC_CURRENT,
				AC_CURRENT,
				FREQUENCY,
				PERIOD,
				CONTINUITY,
				DIODE_CHECKING,
				VDC_RATIO}));
		dmmPanel.add(function, "4, 4, fill, default");
		
		JLabel resolutionLabel = new JLabel("Resolution:");
		dmmPanel.add(resolutionLabel, "2, 6, right, default");
		
		resolution = new JComboBox();
		resolution.setModel(new DefaultComboBoxModel(new String[] {FOUR_DIGITS, FIVE_DIGITS, SIX_DIGITS }));
		dmmPanel.add(resolution, "4, 6, fill, default");
		
		JLabel triggerLabel = new JLabel("Trigger Source:");
		dmmPanel.add(triggerLabel, "2, 8, right, top");
		
		triggerSource = new JComboBox();
		triggerSource.setModel(new DefaultComboBoxModel(new String[] {IMMEDIATE, SOFTWARE, EXTERNAL, INTERNAL}));
		dmmPanel.add(triggerSource, "4, 8, fill, default");
		
		JLabel lblNewLabel_1 = new JLabel("Manual Range:");
		dmmPanel.add(lblNewLabel_1, "2, 10, right, default");
		
		manualRange = new JTextField();
		dmmPanel.add(manualRange, "4, 10, fill, default");
		manualRange.setColumns(10);
		manualRange.setName(HP34401aInterface.RANGE);
		
		btnAutozero = new JToggleButton("Auto-Zero");
		btnAutozero.setActionCommand(AUTOZERO);
		dmmPanel.add(btnAutozero, "2, 12, fill, top");
		
		btnAutoRange = new JToggleButton("Auto-Range");
		btnAutoRange.setActionCommand(AUTORANGE);
		dmmPanel.add(btnAutoRange, "4, 12, fill, default");
		
		JLabel resultLabel = new JLabel("Measure: ");
		dmmPanel.add(resultLabel, "2, 14, right, default");
		
		measure = new JTextField();
		measure.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		measure.setEditable(false);
		dmmPanel.add(measure, "4, 14, fill, default");
		measure.setColumns(10);
		
		configButton = new JButton("Do it! ");
		configButton.setActionCommand(CONFIG);
		dmmPanel.add(configButton, "4, 16, fill, default");
		
		dataValidationMsg = new JTextArea();
		dataValidationMsg.setBackground(UIManager.getColor("Label.background"));
		dataValidationMsg.setForeground(Color.RED);
		dataValidationMsg.setEditable(false);
		dmmPanel.add(dataValidationMsg, "2, 18, 5, 1, center, center");	
		
		if(availableDevice == 0){
			disableDevice();
		}
	}
	
	// get the HP34401 GUI and its components for display
	public JComponent getHP34401aPanel(){
		return dmmPanel;
	}

	@Override
	public JComboBox getFunction() {		
		return function;
	}

	@Override
	public JComboBox getResolution() {
		return resolution;
	}

	@Override
	public JComboBox getTriggerSource() {
		return triggerSource;
	}

	@Override
	public String getRange() {
		return manualRange.getText();
	}

	@Override
	public void setMeasure(String measure) {
		this.measure.setText(measure);
	}

	@Override
	public void setHP34401aControl(HP34401aControl control) {
		manualRange.addFocusListener(control);
		btnAutozero.addActionListener(control);
		btnAutoRange.addActionListener(control);
		configButton.addActionListener(control);
	}
	
	@Override
	public JToggleButton getAutoRange() {
		return btnAutoRange;
	}

	@Override
	public JToggleButton getAutoZero() {
		return btnAutozero;
	}

	@Override
	public void configManualRange(boolean value) {
		manualRange.setEditable(value);
	}

	@Override
	public void configExecutionButton(boolean status) {
		configButton.setEnabled(status);		
	}

	@Override
	public void setDataValidationMessage(String message) {
		dataValidationMsg.setVisible(true);
		dataValidationMsg.setText(message);
	}

	@Override
	public void disableDataValidationLabel() {
		dataValidationMsg.setVisible(false);		
	}

	@Override
	public void disableDevice() {
		// TODO: disable all the text fields and buttons because this device is not working right now 		
	}
}
