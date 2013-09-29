
public class AG33220a {
	
	private int signalShape, typeOfSignal, modType, modWfmShape, amDepth, burstCount, burstPhase;
	private float signalFreq, signalAmp, signalOff, deviationFM, hopFrequency, internalDeviation, phaseDeviation, burstRate, modFreq;
	private int rampSymm, dutyCycleSq, dutyCyclePuls, unit;
	private String frame, dataValidationMessage;
	private boolean dataValidationFlag;
	
	public AG33220a(){
		/*
		 *  Constructor to build a default instance of WaveformGenerator
		 *  Signal Mode, generating a 1000Hz Sine, without offset and 2 Vpp
		 */
		
		/*
		 * SignalShape_value 	->		Signal Shape
		 * 		0							DC
		 * 		1							Sine
		 * 		2							Square
		 * 		3							Triangle
		 * 		4							Ramp
		 * 		6							Pulse
		 * 		7							Noise
		 * 		8							Sinc ------> Built-in Arb. Waveforms
		 * 		9							Neg. Ramp
		 * 		10							Exp. Rise
		 * 		11							Exp. Fall
		 * 		12							Cardiac (To be implemented)
		 * 		13							Volatile (Is not going to be implemented -> outputs the waveform loaded in the volatile memory)		
		 */
		
		this.typeOfSignal = 0; 		// Type of signal3
		
		this.signalShape = 1; 		//Sine Signal
		this.unit = 0; 				// (0) Vpp Units, (1) Vrms, (2) dB
		this.signalFreq = 1000f; 	//1000Hz
		this.signalAmp = 2f; 		// 2Vpp
		this.signalOff = 0f; 		// Offset
		
		// Initialization of rampSymme, dutyCycleSq and dutyCyclePuls to 50% although
		// it is no necessary because we are generating a Sine 
		this.dutyCyclePuls = 50;
		this.dutyCycleSq = 50;
		this.rampSymm = 50;
		
		// Modulation fields 
		// TODO: Initialize modulation fields
		
		// Frame Initialization to null
		this.frame = "";
	}
	
	// Method to create the Frame that is going to be sent to LabVIEW Server. Based on CSV format
	public void setFrame() {
		this.frame = String.valueOf(this.typeOfSignal) 
				+ "," + String.valueOf(this.signalShape)
				+ "," + String.valueOf(this.unit)
				+ "," + String.valueOf(this.signalFreq)
				+ "," + String.valueOf(this.signalAmp)
				+ "," + String.valueOf(this.signalOff)
				+ "," + String.valueOf(this.rampSymm)
				+ "," + String.valueOf(this.dutyCycleSq)
				+ "," + String.valueOf(this.dutyCyclePuls)
				+ "," + String.valueOf(this.modType)
				+ "," + String.valueOf(this.modWfmShape)
				+ "," + String.valueOf(this.modFreq)
				+ "," + String.valueOf(this.amDepth)
				+ "," + String.valueOf(this.deviationFM)
				+ "," + String.valueOf(this.hopFrequency)
				+ "," + String.valueOf(this.internalDeviation)
				+ "," + String.valueOf(this.phaseDeviation)
				+ "," + String.valueOf(this.burstRate)
				+ "," + String.valueOf(this.burstCount)
				+ "," + String.valueOf(this.burstPhase);
	}
	
	public boolean dataValidation(String dataValMessage){
		// TODO: Ensure that read fields has the appropriate format
		
		dataValidationFlag = false;
		dataValidationMessage = dataValMessage;
		System.out.println("We are going to print the dataValMessage: " + dataValMessage);
		frequencyValidation();
		if(!amplitudeValidation()){
			// To avoid incorrect info, this method is only executed when amplitudeValidation() has passed without errors!
			offsetValidation();
		}
		
		dutyCycleSqValidation();
		
		if(typeOfSignal == 1){
			amModulationValidation();
			fmModulationValidation();
			modulatingFrequency();
			deviationFMValidation();
			downRampValidation();
			hopFrequencyValidation();
			burstRateValidation();
			burstPhaseValidation();
			burstCountValidation();
			
			 // TODO: Add a method because PWM and PM modulations are not allowed with this device
		}
		return dataValidationFlag;
	}
	
	private boolean amplitudeValidation(){
		boolean aux = false;
		System.out.println("Checking Amplitude!!!!!!");
		// Limits are fixed between 10mVpp and 10Vpp because we have a 50 ohms output impedance
		if(signalShape <= 6){
			if(signalAmp > 10f || signalAmp < 0.01f){
				System.out.println("Error in amplitude");
				System.out.println(dataValidationMessage);
				dataValidationMessage += "Amplitude must be between 20mVpp and 10Vpp\n";
				dataValidationFlag =  true;
				aux = true;
			}
		}
		return aux;
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
	
	private void frequencyValidation(){				
		System.out.println("Checking Frequency!!!!!!");
		if (signalShape == 1 || signalShape == 2){
			if(signalFreq > 20000000f || signalFreq < 0.000001f){
				System.out.println(dataValidationMessage);
				dataValidationMessage += "Frequency must be between 1uHz and 20MHz\n";
				System.out.println("Error in frequency!");
				System.out.println(dataValidationMessage);
				dataValidationFlag = true;
				return;
			}
		} else if (signalShape == 3 || signalShape == 4){
			if(signalFreq > 200000f || signalFreq < 0.000001f){
				System.out.println(dataValidationMessage);
				dataValidationMessage += "Frequency must be between 1uHz and 200KHz\n";
				dataValidationFlag = true;
				System.out.println("Error in frequency!");
				System.out.println(dataValidationMessage);
				return;
			}				
		} else if (signalShape == 6){
			if(signalFreq > 5000000 || signalFreq < 0.0005f){
				System.out.println(dataValidationMessage);
				dataValidationMessage += "Frequency must be between 500uHz and 5MHz";
			}
		}
		// TODO: Check the code below! 
		if(typeOfSignal == 1 && modType == 1){
			// FM Modulation 
			if(signalShape == 3 || signalShape == 4){
				if(signalFreq < 0.01f || signalFreq > 100000){
					dataValidationMessage += "Frequency must be between 0.1mHz and 100kHz\n";
					dataValidationFlag = true;
				}
			} else {
				if (signalFreq < 0.01f || signalFreq > 15000000){
					dataValidationMessage += "Frequency must be between 0.1mHz and 15MHz\n";
					dataValidationFlag = true;
				}
			}
		}		
		if(typeOfSignal == 1 && modType == 5){
			// Burst Mode
			if(signalShape == 3 || signalShape == 4){
				if(signalFreq < 0.01f || signalFreq > 100000){
					dataValidationMessage += "Frequency must be between 0.1mHz and 100kHz\n";
					dataValidationFlag = true;
				}
			}else{
				if(signalFreq < 0.01f || signalFreq > 5000000){
				dataValidationMessage += "Frequency must be between 0.1mHz and 5MHz\n";
				dataValidationFlag = true;
				}
			}
		}
	}
	
	private void dutyCycleSqValidation(){
		System.out.println("Checking Duty Cycle for Square signal");
		if(signalShape == 2 && signalFreq <= 10000000 ){
			if(dutyCycleSq < 20 || dutyCycleSq > 80){
				dataValidationMessage += "Duty Cycle must be between 20% and 80%\n";
				dataValidationFlag = true;
				return;
			}
		}else if (signalShape == 2 && signalFreq > 10000000){
			if(dutyCycleSq < 40 || dutyCycleSq > 60){
				dataValidationMessage += "Duty Cycle must be between 40% and 60%\n";
				dataValidationFlag = true;
				return;
			}
		}
	}
	
	private void burstCountValidation(){
		if(typeOfSignal == 1 && modType == 5){
			if (burstCount > 50000){
				dataValidationMessage += "Burst Count must be lower than 50000 cycles\n";
				dataValidationFlag = true;
			}else {
				if(signalFreq > 0.01 && signalFreq <= 100){
					int max_count;
					max_count = (int) (500 * signalFreq);
					if(burstCount > max_count || burstCount < 1){
						dataValidationMessage += "Burst Count must be between 1 and " + max_count + "\n";
						dataValidationFlag = true;
					}
				} else if (signalFreq > 100 && signalFreq <= 1000000 && burstCount < 1){
					dataValidationMessage += "Burst Count must be at least 1\n";
					dataValidationFlag = true;
				} else if (signalFreq > 1000000 && signalFreq <= 2000000 && burstCount < 2){
					dataValidationMessage += "Burst Count must be at least 2\n";
					dataValidationFlag = true;
				}else if (signalFreq > 2000000 && signalFreq <= 3000000 && burstCount < 3){
					dataValidationMessage += "Burst Count must be at least 3\n";
					dataValidationFlag = true;
				}else if (signalFreq > 3000000 && signalFreq <= 4000000 && burstCount < 4){
					dataValidationMessage += "Burst Count must be at least 4\n";
					dataValidationFlag = true;
				}else if (signalFreq > 4000000 && signalFreq <= 5000000 && burstCount < 5){
					dataValidationMessage += "Burst Count must be at least 5\n";
					dataValidationFlag = true;
				}
			}
		}
	}
	
	private void burstPhaseValidation(){
		if(typeOfSignal == 1 && modType == 5){
			if(burstPhase < -360 || burstPhase > 360){
				dataValidationMessage += "Burst Phase must be between -360 and 360 degress\n";
				dataValidationFlag = true;
			}
		}
	}
	
	private void burstRateValidation(){
		// This should be between 10mHz and 50kHz
		if(typeOfSignal == 1 && modType == 5){
			if(burstRate < 0.002 ||burstRate > 1000000){
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
			
			// You cannot use noise function or dc volts as the carrier waveform
			if(signalShape == 0){
				dataValidationMessage += "Cannot use DC Volts as Carrier Waveform\n";
				dataValidationFlag = true;
			}
			if(signalShape == 7){
				dataValidationMessage += "Cannot use Noise as Carrier Waveform\n";
				dataValidationFlag = true;
			}
		}
	}	
	
	private void fmModulationValidation(){		
		if(typeOfSignal == 1 && modType == 1){
			float result = signalFreq + deviationFM;
			if((signalShape == 1 || signalShape == 2) && result > 15100000){
				dataValidationMessage += "Carrier freq. + FM Deviation must be lower than 15.1MHz\n"; 
				dataValidationFlag = true;
			} else if ((signalShape == 3 || signalShape == 4) && result > 200000){
				dataValidationMessage += "Carrier freq. + FM Deviation must be lower than 200kHz\n"; 
				dataValidationFlag = true;
			}else if (result > 5100000){
				dataValidationMessage += "Carrier freq. + FM Deviation must be lower than 5.1MHz\n"; 
				dataValidationFlag = true;
			}			
			if(signalFreq < deviationFM){
				dataValidationMessage += "Carrier freq. must be greater than or equal to FM Deviation\n"; 
				dataValidationFlag = true;
			}
			
			// You cannot use noise function or dc volts as the carrier waveform
			if(signalShape == 0){
				dataValidationMessage += "Cannot use DC Volts as Carrier Waveform\n";
				dataValidationFlag = true;
			}
			if(signalShape == 7){
				dataValidationMessage += "Cannot use Noise as Carrier Waveform\n";
				dataValidationFlag = true;
			}
		}
	}

	public void setSignalShape(int signalShape) {
		this.signalShape = signalShape;
	}

	public void setSignalFreq(float signalFreq) {
		this.signalFreq = signalFreq;
	}

	public void setSignalAmp(float signalAmp) {
		this.signalAmp = signalAmp;
	}

	public void setSignalOff(float signalOff) {
		this.signalOff = signalOff;
	}

	public void setRampSymm(int rampSymm) {
		this.rampSymm = rampSymm;
	}

	public void setDutyCycleSq(int dutyCycleSq) {
		this.dutyCycleSq = dutyCycleSq;
	}

	public void setDutyCyclePuls(int dutyCyclePuls) {
		this.dutyCyclePuls = dutyCyclePuls;
	}

	public void setTypeOfSignal(int typeOfSignal) {
		this.typeOfSignal = typeOfSignal;
	}

	public int getSignalShape() {
		return signalShape;
	}

	public int getTypeOfSignal() {
		return typeOfSignal;
	}

	public float getSignalFreq() {
		return signalFreq;
	}

	public float getSignalAmp() {
		return signalAmp;
	}

	public float getSignalOff() {
		return signalOff;
	}

	public int getRampSymm() {
		return rampSymm;
	}

	public int getDutyCycleSq() {
		return dutyCycleSq;
	}

	public int getDutyCyclePuls() {
		return dutyCyclePuls;
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
		this.modWfmShape = modWfmShape + 1;
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

	public float getInternalDeviation() {
		return internalDeviation;
	}

	public void setInternalDeviation(float internalDeviation) {
		this.internalDeviation = internalDeviation;
	}

	public float getPhaseDeviation() {
		return phaseDeviation;
	}

	public void setPhaseDeviation(float phaseDeviation) {
		this.phaseDeviation = phaseDeviation;
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
	// Method to retrieve the Frame that is going to be sent to LabVIEW Server. Based on CSV format
	public String getFrame() {		
		return frame;
	}

	public String getDataValidationMessage() {
		return dataValidationMessage;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}
}
