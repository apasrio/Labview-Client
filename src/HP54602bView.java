import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class HP54602bView implements HP54602bInterface{
	private JPanel hp54602bPanel = new JPanel();	// Holds the HP54602B GUI and its components, it is raised in the main View
	private JTextField rangeCh1, positionCh1, rangeCh2, positionCh2;
	private JTextField rangeTime, triggerLevel;
	private JTextField delay;
	private JComboBox<String> triggerSource, probeCh2, probeCh1, couplingCh2, couplingCh1;
	private JComboBox<String> functionCh2, functionCh1;
	private JToggleButton btnCh1, btnCh2, btnCh2BW, btnCh1BW;
	private JToggleButton autoSet, slopeButton;
	private JButton configButton;
	private JTextArea dataValidationMsg;
	private JTextField function1Measure;
	private JLabel lblFunction1;
	private JLabel lblFunction2;
	private JTextField function2Measure;
	private ChartPanel chartPanel;
	private XYSeries series = new XYSeries("XYGraph");
	private XYSeriesCollection dataset = new XYSeriesCollection();
	private JFreeChart objChart;
	private JPanel displayPanel;
	
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
		
		btnCh1 = new JToggleButton("CH 1");
		channelPanel.add(btnCh1, "4, 4");
		
		btnCh1BW = new JToggleButton("CH 1 BW Limit");
		channelPanel.add(btnCh1BW, "6, 4");
		
		functionCh1 = new JComboBox<String>();
		functionCh1.setModel(new DefaultComboBoxModel<String>(new String[]{
			Globals.VPP,
			Globals.VAVERAGE,
			Globals.VRMS,
			Globals.VMAX,
			Globals.VMIN,
			Globals.VTOP,
			Globals.VBASE,
			Globals.FREQUENCY,
			Globals.PERIOD,
			Globals.DUTY_CYCLE,
			Globals.POSITIVE_WIDTH,
			Globals.NEGATIVE_WIDTH,
			Globals.RISE_TIME,
			Globals.FALL_TIME
		}));
		channelPanel.add(functionCh1, "8, 4, fill, default");
		
		couplingCh1 = new JComboBox<String>();
		couplingCh1.setModel(new DefaultComboBoxModel<String>(new String[]{
			Globals.DC,	Globals.AC, Globals.GROUND }));
		channelPanel.add(couplingCh1, "10, 4, fill, default");
		
		probeCh1 = new JComboBox<String>();
		probeCh1.setModel(new DefaultComboBoxModel<String>(new String[] {
			Globals.X, Globals.XX, Globals.XXX }));
		channelPanel.add(probeCh1, "12, 4, fill, default");
		
		rangeCh1 = new JTextField();
		rangeCh1.setText("1");
		channelPanel.add(rangeCh1, "14, 4, fill, default");
		rangeCh1.setName(CH1_RANGE);
		rangeCh1.setColumns(10);
		
		positionCh1 = new JTextField();
		positionCh1.setText("0");
		channelPanel.add(positionCh1, "16, 4, fill, default");
		positionCh1.setName(CH1_POS);
		positionCh1.setColumns(10);
		
		JLabel lblCh2 = new JLabel("Channel 2:");
		channelPanel.add(lblCh2, "2, 6");
		
		btnCh2 = new JToggleButton("CH 2");
		channelPanel.add(btnCh2, "4, 6");
		
		btnCh2BW = new JToggleButton("CH 2 BW Limit");
		channelPanel.add(btnCh2BW, "6, 6");
		
		functionCh2 = new JComboBox<String>();
		functionCh2.setModel(new DefaultComboBoxModel<String>(new String[]{
				Globals.VPP,
				Globals.VAVERAGE,
				Globals.VRMS,
				Globals.VMAX,
				Globals.VMIN,
				Globals.VTOP,
				Globals.VBASE,
				Globals.FREQUENCY,
				Globals.PERIOD,
				Globals.DUTY_CYCLE,
				Globals.POSITIVE_WIDTH,
				Globals.NEGATIVE_WIDTH,
				Globals.RISE_TIME,
				Globals.FALL_TIME
			}));
		channelPanel.add(functionCh2, "8, 6, fill, default");
		
		couplingCh2 = new JComboBox<String>();
		couplingCh2.setModel(new DefaultComboBoxModel<String>(new String[]{
				Globals.DC,	Globals.AC, Globals.GROUND }));
		channelPanel.add(couplingCh2, "10, 6, fill, default");
		
		probeCh2 = new JComboBox<String>();
		probeCh2.setModel(new DefaultComboBoxModel<String>(new String[] {
				Globals.X, Globals.XX, Globals.XXX }));
		channelPanel.add(probeCh2, "12, 6, fill, default");
		
		rangeCh2 = new JTextField();
		rangeCh2.setText("1");
		channelPanel.add(rangeCh2, "14, 6, fill, default");
		rangeCh2.setName(CH2_RANGE);
		rangeCh2.setColumns(10);
		
		positionCh2 = new JTextField();
		positionCh2.setText("0");
		channelPanel.add(positionCh2, "16, 6, fill, default");
		positionCh2.setName(CH2_POS);
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
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				RowSpec.decode("default:grow"),}));
		
		autoSet = new JToggleButton("AUTOSET");
		configPanel.add(autoSet, "2, 2");
		
		slopeButton = new JToggleButton("Negative Slope");
		configPanel.add(slopeButton, "4, 2");
		
		JLabel lblRangeTime = new JLabel("Range:");
		configPanel.add(lblRangeTime, "6, 2, right, default");
		
		rangeTime = new JTextField();
		rangeTime.setText("1");
		configPanel.add(rangeTime, "8, 2, fill, default");
		rangeTime.setName(TIME_RANGE);
		rangeTime.setColumns(10);
		
		JLabel lblDelay = new JLabel("Delay:");
		configPanel.add(lblDelay, "10, 2, right, default");
		
		delay = new JTextField();
		delay.setText("1");
		configPanel.add(delay, "12, 2, fill, default");
		delay.setName(TIME_DELAY);
		delay.setColumns(10);
		
		JLabel lblTrigger = new JLabel("Trigger src:");
		configPanel.add(lblTrigger, "2, 4, right, default");
		
		triggerSource = new JComboBox<String>();
		triggerSource.setModel(new DefaultComboBoxModel<String>(new String[] {
			Globals.CHANNEL1, Globals.CHANNEL2 }));
		configPanel.add(triggerSource, "4, 4, fill, default");
		
		JLabel lblTriggerLevel = new JLabel("Trigger Level:");
		configPanel.add(lblTriggerLevel, "6, 4, right, default");
		
		triggerLevel = new JTextField();
		triggerLevel.setText("0");
		configPanel.add(triggerLevel, "8, 4, fill, default");
		triggerLevel.setName(TRIGGER_LEVEL);
		triggerLevel.setColumns(10);
		
		configButton = new JButton("Do it!");
		configButton.setActionCommand(CONFIG);
		configPanel.add(configButton, "12, 4");
		
		dataValidationMsg = new JTextArea();
		dataValidationMsg.setForeground(Color.RED);
		dataValidationMsg.setBackground(UIManager.getColor("Label.background"));
		configPanel.add(dataValidationMsg, "2, 6, 11, 1, fill, fill");
		
		displayPanel = new JPanel();
		displayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_displayPanel = new GridBagConstraints();
		gbc_displayPanel.gridwidth = 2;
		gbc_displayPanel.fill = GridBagConstraints.BOTH;
		gbc_displayPanel.gridx = 0;
		gbc_displayPanel.gridy = 1;
		hp54602bPanel.add(displayPanel, gbc_displayPanel);
		displayPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		lblFunction1 = new JLabel("Function 1:");
		displayPanel.add(lblFunction1, "2, 2, right, default");
		
		function1Measure = new JTextField();
		displayPanel.add(function1Measure, "4, 2, center, default");
		function1Measure.setColumns(10);
		
		lblFunction2 = new JLabel("Function 2:");
		displayPanel.add(lblFunction2, "2, 4, right, default");
		
		function2Measure = new JTextField();
		displayPanel.add(function2Measure, "4, 4, center, default");
		function2Measure.setColumns(10);
		
		
		
		if(availableDevice == 0){
			disableDevice();
		}
		
		dataset.addSeries(series);
		
		//Generate the graph		
		objChart = ChartFactory.createXYLineChart("Oscilloscope - HP54602B",
				"Time - ms",
				"Voltage - mv",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false);
				
		chartPanel = new ChartPanel(objChart);
		displayPanel.add(chartPanel, "6, 2, 1, 27, fill, fill");		
	}
	
	
	@Override
	public void configExecutionButton(boolean status) {
		configButton.setEnabled(status);
	}


	@Override
	public JComponent getHP54602bPanel() {		
		return hp54602bPanel;
	}


	@Override
	public void disableDevice() {
		rangeCh1.setEnabled(false);
		rangeCh2.setEnabled(false);
		rangeTime.setEnabled(false);
		positionCh1.setEnabled(false);
		positionCh2.setEnabled(false);
		triggerLevel.setEnabled(false);
		triggerSource.setEnabled(false);
		probeCh1.setEnabled(false);
		probeCh2.setEnabled(false);
		couplingCh1.setEnabled(false);
		couplingCh2.setEnabled(false);
		functionCh1.setEnabled(false);
		functionCh2.setEnabled(false);
		btnCh1.setEnabled(false);
		btnCh2.setEnabled(false);
		btnCh1BW.setEnabled(false);
		btnCh2BW.setEnabled(false);
		autoSet.setEnabled(false);
		slopeButton.setEnabled(false);
		configButton.setEnabled(false);
	}


	@Override
	public void setDataValidationMessage(String Message) {
		dataValidationMsg.setText(Message);
		dataValidationMsg.setVisible(true);
	}


	@Override
	public void disableDataValidationMessage() {
		dataValidationMsg.setVisible(false);
	}


	@Override
	public void setHP54602bControl(HP54602bControl control) {
		configButton.addActionListener(control);
		rangeCh2.addFocusListener(control);
		rangeCh1.addFocusListener(control);
		positionCh2.addFocusListener(control);
		positionCh1.addFocusListener(control);
		rangeTime.addFocusListener(control);
		triggerLevel.addFocusListener(control);
		delay.addFocusListener(control);
	}


	public JPanel getHp54602bPanel() {
		return hp54602bPanel;
	}


	public JTextField getRangeCh1() {
		return rangeCh1;
	}


	public JTextField getPositionCh1() {
		return positionCh1;
	}


	public JTextField getRangeCh2() {
		return rangeCh2;
	}


	public JTextField getPositionCh2() {
		return positionCh2;
	}


	public JTextField getRangeTime() {
		return rangeTime;
	}


	public JTextField getTriggerlevel() {
		return triggerLevel;
	}


	public JTextField getTimeDelay() {
		return delay;
	}


	public JComboBox<String> getTriggerSource() {
		return triggerSource;
	}


	public JComboBox<String> getProbeCh2() {
		return probeCh2;
	}


	public JComboBox<String> getProbeCh1() {
		return probeCh1;
	}


	public JComboBox<String> getCouplingCh2() {
		return couplingCh2;
	}


	public JComboBox<String> getCouplingCh1() {
		return couplingCh1;
	}


	public JComboBox<String> getFunctionCh2() {
		return functionCh2;
	}


	public JComboBox<String> getFunctionCh1() {
		return functionCh1;
	}


	public JToggleButton getBtnCh1() {
		return btnCh1;
	}


	public JToggleButton getBtnCh2() {
		return btnCh2;
	}


	public JToggleButton getBtnCh2BW() {
		return btnCh2BW;
	}


	public JToggleButton getBtnCh1BW() {
		return btnCh1BW;
	}


	public JToggleButton getAutoSet() {
		return autoSet;
	}


	public JToggleButton getSlopeButton() {
		return slopeButton;
	}


	public JButton getConfigButton() {
		return configButton;
	}


	@Override
	public void setFunc1MeasuredValue(String measuredValue) {
		function1Measure.setText(measuredValue);		
	}


	@Override
	public void setFunc2MeasuredValue(String measuredValue) {
		function2Measure.setText(measuredValue);
	}


	@Override
	public void setXYSeries(XYSeries trace1) {
		series = trace1;		
		dataset.addSeries(series);
		
		//Generate the graph		
		objChart = ChartFactory.createXYLineChart("Oscilloscope - HP54602B",
				"Time - ms",
				"Voltage - mv",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false);
				
		chartPanel = new ChartPanel(objChart);
		displayPanel.add(chartPanel, "6, 2, 1, 27, fill, fill");
	}	
}
