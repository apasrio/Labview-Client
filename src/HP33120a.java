
public class HP33120a {
	private String frame, dataValidationMessage;
	private int signalShape, typeOfSignal, unit, modType, modWfmShape, amDepth, burstCount, burstPhase;
	private float signalFreq, signalAmp, signalOff, deviationFM, hopFrequency, burstRate, modFreq;
	private int dutyCycleSq;
	private boolean dataValidationFlag;
	
	public HP33120a(){
		/*
		 *  Constructor to build a default instance of HP33120A Waveform Generator
		 *  Signal Mode, generating a 1000Hz Sine, without offset and 0.1Vpp
		 */
		
		/*
		 * SignalShape_value 	->		Signal Shape
		 * 		0							DC
		 * 		1							Sine
		 * 		2							Square
		 * 		3							Triangle
		 * 		4							Ramp
		 * 		6							Pulse (Not Allowed)
		 * 		7							Noise
		 * 		8							Sinc
		 * 		9							Neg. Ramp
		 * 		10							Exp. Rise
		 * 		11							Exp. Fall
		 * 		12							Cardiac (To be implemented)
		 * 		13							Volatile (To be implemented)		
		 */
		this.typeOfSignal = 0;		// Signal instead of modulation
		this.unit = 0;
		this.signalShape = 1;		// Sine Waveform
		this.signalFreq = 1000f;	// 1000Hz
		this.signalAmp = 0.1f;		// 0.1Vpp
		this.signalOff = 0f;		// 0V
		
		// Initialization of dutyCycleSq to 50% although
		// it is no necessary because we are generating a Sine
		this.dutyCycleSq = 50;
		
		// Modulation fields 
		// TODO: Initialize modulation fields
		
		// Frame Initialization to an empty value
		this.frame = "";		
	}

	/*
	 * Method to compose the frame that is going to be sent to the LabView Server. Based on CSV format
	 */	
	public void setFrame() {
		this.frame = String.valueOf(this.typeOfSignal) 
				+ "," + String.valueOf(this.signalShape)
				+ "," + String.valueOf(this.unit)
				+ "," + String.valueOf(this.signalFreq)
				+ "," + String.valueOf(this.signalAmp)
				+ "," + String.valueOf(this.signalOff)
				+ "," + String.valueOf(this.dutyCycleSq)
				+ "," + String.valueOf(this.modType)
				+ "," + String.valueOf(this.modWfmShape)
				+ "," + String.valueOf(this.modFreq)
				+ "," + String.valueOf(this.amDepth)
				+ "," + String.valueOf(this.deviationFM)
				+ "," + String.valueOf(this.hopFrequency)
				+ "," + String.valueOf(this.burstRate)
				+ "," + String.valueOf(this.burstCount)
				+ "," + String.valueOf(this.burstPhase);
	}
	
	public boolean dataValidation(){
		// TODO: Ensure that read fields has the appropiate format
		
		dataValidationFlag = false;
		dataValidationMessage = "";
		frequencyValidation();
		amplitudeValidation();
		offsetValidation();
		dutyCycleSqValidation();
		
		if(typeOfSignal == 1){
			amModulationValidation();
			burstRateValidation();
		}
		return dataValidationFlag;
	}
	
	private void frequencyValidation(){				
		System.out.println("Checking Frequency!!!!!!");
		if (signalShape == 1 || signalShape == 2){
			if(signalFreq > 15000000f || signalFreq < 0.0001f){
				System.out.println(dataValidationMessage);
				dataValidationMessage += "Frequency must be between 0.1mHz and 15MHz\n";
				System.out.println("Error in frequency!");
				System.out.println(dataValidationMessage);
				dataValidationFlag = true;
				return;
			}
		} else if (signalShape == 3 || signalShape == 4){
			if(signalFreq > 100000f || signalFreq < 0.0001f){
				System.out.println(dataValidationMessage);
				dataValidationMessage += "Frequency must be between 0.1mHz and 100KHz\n";
				dataValidationFlag = true;
				System.out.println("Error in frequency!");
				System.out.println(dataValidationMessage);
				return;
			}				
		}
		// TODO: Check Built-In Arbs way of working
	}
	
	private void amplitudeValidation(){
		System.out.println("Checking Amplitude!!!!!!");
		// Limits are fixed between 50mVpp and 10Vpp because we have a 50 ohms output impedance
		if(signalShape <= 6){
			if(signalAmp > 10f || signalAmp < 0.05f){
				System.out.println("Error in amplitude");
				System.out.println(dataValidationMessage);
				dataValidationMessage += "Amplitude must be between 20mVpp and 10Vpp\n";
				dataValidationFlag =  true;
				return;
			}
		}		
	}
	
	private void offsetValidation(){		
		// System is developed to have a 50 Ohms output impedance then Vmax = 5Vdc, therefore next equations
		// must be satisfied: ABS(Voff) + 1/2 Vpp =< 5Vdc and
		// ABS(Voff) =< 2Vpp
		int Vmax = 5;
		float lowerLimit, upperLimit;
		float upperLimit_1, upperLimit_2, lowerLimit_1, lowerLimit_2;
		upperLimit_1 = Vmax - (signalAmp / 2);
		lowerLimit_1 = (signalAmp / 2) - Vmax;
		upperLimit_2 = 2 * signalAmp;
		lowerLimit_2 = -2 * signalAmp;
		if(!(lowerLimit_1<=signalOff && signalOff<=upperLimit_1) || !(lowerLimit_2<=signalOff && signalOff<=upperLimit_2)){
			// At least one of two equations is invalid, so we are going to tell user what are the more restrictive limits
			if(lowerLimit_1 > lowerLimit_2){
				lowerLimit = lowerLimit_1;
			}else{
				lowerLimit = lowerLimit_2;
			}
			
			if(upperLimit_1 < upperLimit_2){
				upperLimit = upperLimit_1;
			}
			else{
				upperLimit = upperLimit_2;
			}
			dataValidationMessage += "Offset must be between " + lowerLimit + " Vdc and " + upperLimit + " Vdc\n" ;
			dataValidationFlag = true;
		}
	}	
	
	private void dutyCycleSqValidation(){
		System.out.println("Checking Duty Cycle for Square signal");
		if(signalShape == 2 && signalFreq <= 5000000 ){
			if(dutyCycleSq < 20 || dutyCycleSq > 80){
				dataValidationMessage += "Duty Cycle must be between 20% and 80%\n";
				dataValidationFlag = true;
				return;
			}
		}else if (signalShape == 2 && signalFreq > 5000000){
			if(dutyCycleSq < 40 || dutyCycleSq > 60){
				dataValidationMessage += "Duty Cycle must be between 40% and 60%\n";
				dataValidationFlag = true;
				return;
			}
		}
	}
	
	private void burstCountValidation(){
		// TODO: Implement data Validation
		if(typeOfSignal == 1 && modType == 5){
			
		}
	}
	
	private void burstPhaseValidation(){
		// TODO: Complete this stub
	}
	
	private void burstRateValidation(){
		// TODO: Finish this method
		// This should be between 10mHz and 50kHz
		if(typeOfSignal == 1 && modType == 5){
			if(burstRate < 0.01 ||burstRate > 50000){
				dataValidationMessage += "Burst Rate must be between 10mHz and 50kHz";
				dataValidationFlag = true;
			}
		}
	}
	
	private void amModulationValidation(){
		System.out.println("Checking AM Modulation!!");
		if(typeOfSignal == 1 && modType == 0){
			if(amDepth < 0 || amDepth > 120){
				dataValidationMessage += "AM Depth must be an integer between 0% and 120%";
				dataValidationFlag = true;
			}
		}
	}	
	
	public String getFrame() {		
		return frame;
	}
	
	public String getDataValidationMessage() {
		return dataValidationMessage;
	}

	public int getSignalShape() {
		return signalShape;
	}

	public void setSignalShape(int signalShape) {
		if (signalShape > 5){
			// This is done because in the LabVIEW Driver signalShape numbers does not follow a sequence 
			this.signalShape = signalShape +1;
		} else {
			this.signalShape = signalShape;
		}
	}

	public int getTypeOfSignal() {
		return typeOfSignal;
	}

	public void setTypeOfSignal(int typeOfSignal) {
		this.typeOfSignal = typeOfSignal;
	}

	public int getModType() {
		return modType;
	}

	public void setModType(int modType) {
		this.modType = modType;
	}

	public int getModWfmShape() {
		return modWfmShape;
	}

	public void setModWfmShape(int modWfmShape) {
		this.modWfmShape = modWfmShape;
	}

	public int getAmDepth() {
		return amDepth;
	}

	public void setAmDepth(int amDepth) {
		this.amDepth = amDepth;
	}

	public int getBurstCount() {
		return burstCount;
	}

	public void setBurstCount(int burstCount) {
		this.burstCount = burstCount;
	}

	public int getBurstPhase() {
		return burstPhase;
	}

	public void setBurstPhase(int burstPhase) {
		this.burstPhase = burstPhase;
	}

	public float getSignalFreq() {
		return signalFreq;
	}

	public void setSignalFreq(float signalFreq) {
		this.signalFreq = signalFreq;
	}

	public float getSignalAmp() {
		return signalAmp;
	}

	public void setSignalAmp(float signalAmp) {
		this.signalAmp = signalAmp;
	}

	public float getSignalOff() {
		return signalOff;
	}

	public void setSignalOff(float signalOff) {
		this.signalOff = signalOff;
	}

	public float getDeviationFM() {
		return deviationFM;
	}

	public void setDeviationFM(float deviationFM) {
		this.deviationFM = deviationFM;
	}

	public float getHopFrequency() {
		return hopFrequency;
	}

	public void setHopFrequency(float hopFrequency) {
		this.hopFrequency = hopFrequency;
	}

	public float getBurstRate() {
		return burstRate;
	}

	public void setBurstRate(float burstRate) {
		this.burstRate = burstRate;
	}

	public float getModFreq() {
		return modFreq;
	}

	public void setModFreq(float modFreq) {
		this.modFreq = modFreq;
	}

	public int getDutyCycleSq() {
		return dutyCycleSq;
	}

	public void setDutyCycleSq(int dutyCycleSq) {
		this.dutyCycleSq = dutyCycleSq;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}
}
