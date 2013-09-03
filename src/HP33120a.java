
public class HP33120a {
	private String frame;
	private int signalShape, typeOfSignal, modType, modWfmShape, amDepth, burstCount, burstPhase;
	private float signalFreq, signalAmp, signalOff, deviationFM, hopFrequency, internalDeviation, phaseDeviation, burstRate, modFreq;
	private int rampSymm, dutyCycleSq, dutyCyclePuls;
	private String dataValidationMessage;
	
	public HP33120a(){
		/*
		 *  Constructor to build a default instance of HP33120A Waveform Generator
		 *  Signal Mode, generating a 1000Hz Sine, without offset and 2 Vpp
		 */
		this.typeOfSignal = 0;		// Signal instead of modulation
		this.signalShape = 1;		// Sine Waveform
		this.signalFreq = 1000f;	// 1000Hz
		this.signalAmp = 2f;		// 2Vpp
		this.signalOff = 0f;		// 0V
		
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

	/*
	 * Method to compose the frame that is going to be sent to the LabView Server. Based on CSV format
	 */	
	public void setFrame() {
		this.frame = String.valueOf(this.typeOfSignal) 
				+ "," + String.valueOf(this.signalShape)
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
		this.signalShape = signalShape;
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

	public int getRampSymm() {
		return rampSymm;
	}

	public void setRampSymm(int rampSymm) {
		this.rampSymm = rampSymm;
	}

	public int getDutyCycleSq() {
		return dutyCycleSq;
	}

	public void setDutyCycleSq(int dutyCycleSq) {
		this.dutyCycleSq = dutyCycleSq;
	}

	public int getDutyCyclePuls() {
		return dutyCyclePuls;
	}

	public void setDutyCyclePuls(int dutyCyclePuls) {
		this.dutyCyclePuls = dutyCyclePuls;
	}
}
