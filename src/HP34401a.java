
public class HP34401a {
	private String frame, dataValidationMessage;
	private int function;
	private float resolution, range;
	
	
	public HP34401a(){
		/*
		 *  Constructor to build a default instance of HP34401A Digital Multimeter
		 *  Default configuration -> Function DC Voltage
		 */
		this.function = 0;
		this.resolution = 5.5f;
		this.range = 100f;
		
		
		// Frame Initialization to an empty value
		this.frame = "";
	}
	
	public void setFrame(){
		// TODO: Fill this method
	}

	public String getFrame() {
		return frame;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}

	public String getDataValidationMessage() {
		return dataValidationMessage;
	}

	public void setDataValidationMessage(String dataValidationMessage) {
		this.dataValidationMessage = dataValidationMessage;
	}

	public int getFunction() {
		return function;
	}

	public void setFunction(int function) {
		this.function = function;
	}

	public float getResolution() {
		return resolution;
	}

	public void setResolution(float resolution) {
		this.resolution = resolution;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}
	
	

}
