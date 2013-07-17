
public class WaveformGenerator {
	
	private int signalType, signalShape;
	private float signalFreq, signalAmp, signalOff;
	private int rampSymm, dutyCycleSq, dutyCyclePuls;
	
	public WaveformGenerator(){
		/*
		 *  Constructor to build a default instance of WaveformGenerator
		 *  Signal Mode, generating a 1000Hz Sine, without offset and 2 Vpp
		 */
		this.signalType = 0; //Signal Mode
		this.signalShape = 1; //Sine Signal
		this.signalFreq = 1000f; //1000Hz
		this.signalAmp = 2f; // 2Vpp
		this.signalOff = 0f; // Offset
		
		// Initialization of rampSymme, dutyCycleSq and dutyCyclePuls to 50% although
		// it is no necessary because we are generating a Sine 
		this.dutyCyclePuls = 50;
		this.dutyCycleSq = 50;
		this.rampSymm = 50;
	}

}
