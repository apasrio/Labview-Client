import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.EtchedBorder;


public class HP34401aView implements HP34401aInterface{
	
	/**
	 * Class to develop the GUI for the HP34401A DMM
	 */
	
	private JPanel dmmPanel = new JPanel();	// Holds the HP34401 GUI and its components, it is raised in the main View
	private JTextField textField;
	private JTextField measure;
	
	public HP34401aView(){
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Digital Multimeter");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		dmmPanel.add(lblNewLabel, "2, 2, center, default");
		
		JLabel functionLabel = new JLabel("Function:");
		dmmPanel.add(functionLabel, "2, 4, right, default");
		
		JComboBox function = new JComboBox();
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
				VDC_RATIO,
				TEMPERATURE,
				CAPACITANCE}));
		dmmPanel.add(function, "4, 4, fill, default");
		
		JLabel resolutionLabel = new JLabel("Resolution:");
		dmmPanel.add(resolutionLabel, "2, 6, right, default");
		
		JComboBox resolution = new JComboBox();
		resolution.setModel(new DefaultComboBoxModel(new String[] {FOUR_DIGITS, FIVE_DIGITS, SIX_DIGITS }));
		dmmPanel.add(resolution, "4, 6, fill, default");
		
		JLabel triggerLabel = new JLabel("Trigger Source:");
		dmmPanel.add(triggerLabel, "2, 8, right, top");
		
		JComboBox triggerSource = new JComboBox();
		triggerSource.setModel(new DefaultComboBoxModel(new String[] {IMMEDIATE, SOFTWARE, EXTERNAL, INTERNAL}));
		dmmPanel.add(triggerSource, "4, 8, fill, default");
		
		JLabel lblNewLabel_1 = new JLabel("Manual Range:");
		dmmPanel.add(lblNewLabel_1, "2, 10, right, default");
		
		textField = new JTextField();
		dmmPanel.add(textField, "4, 10, fill, default");
		textField.setColumns(10);
		
		JLabel resultLabel = new JLabel("Measure: ");
		dmmPanel.add(resultLabel, "2, 16, right, default");
		
		measure = new JTextField();
		measure.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		measure.setEditable(false);
		dmmPanel.add(measure, "4, 16, fill, default");
		measure.setColumns(10);
		
		JButton btnAutozero = new JButton("Auto-Zero");
		dmmPanel.add(btnAutozero, "2, 22, fill, top");
		
		JButton btnNewButton = new JButton("Auto-Range");
		dmmPanel.add(btnNewButton, "4, 22, fill, default");
		
		JButton configButton = new JButton("Do it! ");
		dmmPanel.add(configButton, "6, 22");
		
	
	}
	
	// get the HP34401 GUI and its components for display
	public JComponent getHP34401aPanel(){
		return dmmPanel;
	}
}
