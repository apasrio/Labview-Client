import javax.swing.JComboBox;
import javax.swing.JToggleButton;


public interface HP34401aInterface {
	
	public JComboBox getFunction();
	public JComboBox getResolution();
	public JComboBox getTriggerSource();
	public JToggleButton getAutoRange();
	public JToggleButton getAutoZero();
	public String getRange();
	
	public void configManualRange(boolean value);
	public void setMeasure(String measure);
	public void setHP34401aControl(HP34401aControl control); 	// Enable the ActionListeners
	
	// Definitions for ActionCommands
	static final String AUTOZERO = "HP34401a_Autozero";
	static final String AUTORANGE = "HP34401a_Autorange";
	static final String CONFIG = "HP34401a_Config";
	
	// Elements for the TriggerSource JComboBox
	static final String IMMEDIATE = "Immediate";
	static final String SOFTWARE = "Software (Bus)";
	static final String EXTERNAL = "External";
	static final String INTERNAL = "Internal";
	
	// Elements for the Resolution JComboBox
	static final String FOUR_DIGITS = "4.5";
	static final String FIVE_DIGITS = "5.5";
	static final String SIX_DIGITS = "6.6";
	
	// Elements for the Function JComboBox
	static final String DC_VOLTAGE = "DC Voltage";
	static final String AC_VOLTAGE = "AC Voltage";
	static final String WIRE_2_RESISTANCE = "2 - Wire Resistance";
	static final String WIRE_4_RESISTANCE = "4 - Wire Resistance";
	static final String DC_CURRENT = "DC Current";
	static final String AC_CURRENT = "AC Current";
	static final String FREQUENCY = "Frequency";
	static final String PERIOD = "Period";
	static final String CONTINUITY = "Continuity";
	static final String DIODE_CHECKING = "Diode Checking";
	static final String VDC_RATIO = "VDC Ratio";
	static final String TEMPERATURE = "Temperature";
	static final String CAPACITANCE = "Capacitance";
}
