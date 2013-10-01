import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JToggleButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class HP54602bView implements HP54602bInterface{
	private JPanel hp54602bPanel = new JPanel();	// Holds the HP54602B GUI and its components, it is raised in the main View
	private JTextField rangeCh1;
	private JTextField positionCh1;
	private JTextField rangeCh2;
	private JTextField positionCh2;
	public HP54602bView(int availableDevice){
		hp54602bPanel.setSize(1280, 480);
		GridBagLayout gbl_hp54602bPanel = new GridBagLayout();
		gbl_hp54602bPanel.columnWidths = new int[]{216, 225, 0};
		gbl_hp54602bPanel.rowHeights = new int[]{105, 0, 0};
		gbl_hp54602bPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_hp54602bPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		hp54602bPanel.setLayout(gbl_hp54602bPanel);
		
		JPanel channelPanel = new JPanel();
		channelPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_channelPanel = new GridBagConstraints();
		gbc_channelPanel.insets = new Insets(0, 0, 5, 5);
		gbc_channelPanel.fill = GridBagConstraints.BOTH;
		gbc_channelPanel.gridx = 0;
		gbc_channelPanel.gridy = 0;
		hp54602bPanel.add(channelPanel, gbc_channelPanel);
		channelPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:default"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblFunction = new JLabel("Function");
		channelPanel.add(lblFunction, "8, 2");
		
		JLabel lblCoupling = new JLabel("Coupling");
		channelPanel.add(lblCoupling, "10, 2, left, bottom");
		
		JLabel lblProbe = new JLabel("Probe");
		channelPanel.add(lblProbe, "12, 2");
		
		JLabel lblRange = new JLabel("Range");
		channelPanel.add(lblRange, "14, 2");
		
		JLabel lblPosition = new JLabel("Position");
		channelPanel.add(lblPosition, "16, 2, left, bottom");
		
		JLabel lblCh1 = new JLabel("Channel 1:");
		channelPanel.add(lblCh1, "2, 4");
		
		JToggleButton btnCh1 = new JToggleButton("CH 1");
		channelPanel.add(btnCh1, "4, 4");
		
		JToggleButton btnCh1BW = new JToggleButton("CH 1 BW Limit");
		channelPanel.add(btnCh1BW, "6, 4");
		
		JComboBox comboBox = new JComboBox();
		channelPanel.add(comboBox, "8, 4, fill, default");
		
		JComboBox comboBox_2 = new JComboBox();
		channelPanel.add(comboBox_2, "10, 4, fill, default");
		
		JComboBox comboBox_4 = new JComboBox();
		channelPanel.add(comboBox_4, "12, 4, fill, default");
		
		rangeCh1 = new JTextField();
		channelPanel.add(rangeCh1, "14, 4, fill, default");
		rangeCh1.setColumns(10);
		
		positionCh1 = new JTextField();
		channelPanel.add(positionCh1, "16, 4, fill, default");
		positionCh1.setColumns(10);
		
		JLabel lblCh2 = new JLabel("Channel 2:");
		channelPanel.add(lblCh2, "2, 6");
		
		JToggleButton btnCh2 = new JToggleButton("CH 2");
		channelPanel.add(btnCh2, "4, 6");
		
		JToggleButton btnCh2BW = new JToggleButton("CH 2 BW Limit");
		channelPanel.add(btnCh2BW, "6, 6");
		
		JComboBox comboBox_1 = new JComboBox();
		channelPanel.add(comboBox_1, "8, 6, fill, default");
		
		JComboBox comboBox_3 = new JComboBox();
		channelPanel.add(comboBox_3, "10, 6, fill, default");
		
		JComboBox comboBox_5 = new JComboBox();
		channelPanel.add(comboBox_5, "12, 6, fill, default");
		
		rangeCh2 = new JTextField();
		channelPanel.add(rangeCh2, "14, 6, fill, default");
		rangeCh2.setColumns(10);
		
		positionCh2 = new JTextField();
		channelPanel.add(positionCh2, "16, 6, fill, default");
		positionCh2.setColumns(10);
		
		JPanel configPanel = new JPanel();
		configPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_configPanel = new GridBagConstraints();
		gbc_configPanel.insets = new Insets(0, 0, 5, 0);
		gbc_configPanel.fill = GridBagConstraints.BOTH;
		gbc_configPanel.gridx = 1;
		gbc_configPanel.gridy = 0;
		hp54602bPanel.add(configPanel, gbc_configPanel);
		configPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JToggleButton autoSet = new JToggleButton("autoSet");
		configPanel.add(autoSet, "2, 2");
		
		JToggleButton slopeButton = new JToggleButton("Negative Slope");
		configPanel.add(slopeButton, "4, 2");
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_displayPanel = new GridBagConstraints();
		gbc_displayPanel.gridwidth = 2;
		gbc_displayPanel.fill = GridBagConstraints.BOTH;
		gbc_displayPanel.gridx = 0;
		gbc_displayPanel.gridy = 1;
		hp54602bPanel.add(displayPanel, gbc_displayPanel);
		
	}
	
	
	@Override
	public void configExecutionButton(boolean status) {
		// TODO Auto-generated method stub		
	}


	@Override
	public JComponent getHP54602bPanel() {		
		return hp54602bPanel;
	}
}
