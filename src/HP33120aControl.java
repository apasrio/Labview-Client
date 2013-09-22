import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;


public class HP33120aControl implements ActionListener, FocusListener{
	private HP33120aInterface view;
	private TCPClient tcpClient;	
	private HP33120a hp33120a;
	private String auxDataValidationMessage;
	private boolean VALIDATION_FLAG = false;
	
	public HP33120aControl(HP33120aInterface view, TCPClient socketClient, HP33120a hp33120a){
		this.tcpClient = socketClient;
		this.view = view;
		this.hp33120a = hp33120a;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void focusLost(FocusEvent event) {
		// TODO Change the system
				System.out.println("A system has lost its focus! ");				
				final JTextComponent c = (JTextComponent) event.getSource();
				String name = c.getName();
				String text = c.getText();

				System.out.println("Text : " +text);
				if (name.equals(HP33120aInterface.AMPLITUDE) ||
						name.equals(HP33120aInterface.FREQUENCY) ||
						name.equals(HP33120aInterface.OFFSET) ||
						name.equals(HP33120aInterface.DUTY_CYCLE_SQUARE) ||
						name.equals(HP33120aInterface.MODULATING_FREQUENCY) ||
						name.equals(HP33120aInterface.AM_DEPTH) ||
						name.equals(HP33120aInterface.FM_DEVIATION) || 
						name.equals(HP33120aInterface.HOP_FREQUENCY) || 
						name.equals(HP33120aInterface.BURST_RATE) || 
						name.equals(HP33120aInterface.BURST_COUNT) ||
						name.equals(HP33120aInterface.BURST_PHASE)
						){		
					System.out.println("Data Validation Method is going to be called! ");					
					dataValidation(readFields());									
				}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("HP33120a Event Triggered");
		JComboBox<String> combo;
		// Changes according to the selected type of signal
		if(event.getActionCommand().equals(HP33120aInterface.TYPE_OF_SIGNAL)){	
			dataValidation(readFields());
			combo = view.getTypeOfSignal();
			if (combo.getSelectedItem().equals(Globals.SIGNAL)){
				System.out.println("Disabling Buttons");
				view.disableModulationbuttons();
			} else if (combo.getSelectedItem().equals(Globals.MODULATION)){
				view.enableModulationButtons();
				view.configForAM();
				System.out.println("Enabling Buttons");
			}
		}
		// Changes according to the selected waveform shape
		if(event.getActionCommand().equals(HP33120aInterface.WAVEFORM_SHAPE)){
			System.out.println("HP33120a WAVEFORMSHAPE CHANGED!!!!");			
			dataValidation(readFields());
			combo = view.getSignalShape();
			String command = (String) combo.getSelectedItem();			
			if(command.equals(Globals.DC)){
				view.configForDC();
			}else if (command.equals(Globals.SINE)){
				view.configForSine();
			}else if (command.equals(Globals.SQUARE)){
				view.configForSquare();
			}else if (command.equals(Globals.TRIANGLE)){
				view.configForTriangle();
			}else if (command.equals(Globals.RAMP)){
				view.configForRamp();
			}else if (command.equals(Globals.PULSE)){
				view.configForPulse();
			}else if (command.equals(Globals.NOISE)){
				view.configForNoise();
			}else if (command.equals(Globals.SINC)){
				view.configForSinc();
			}else if (command.equals(Globals.NEG_RAMP)){
				view.configForNegRamp();
			}else if (command.equals(Globals.EXP_RISE)){
				view.configForExpRise();
			}else if (command.equals(Globals.EXP_FALL)){
				view.configForExpFall();
			}
		}
		if (event.getActionCommand().equals(HP33120aInterface.MODULATION_TYPE)){
			System.out.println("HP33120a MODULATION TYPE CHANGED!!!!");			
			// We should do data validation! 
			dataValidation(readFields());
			combo = view.getModType();
			String command = (String) combo.getSelectedItem();
			if(command.equals(Globals.AM)){
				view.configForAM();
			}else if(command.equals(Globals.FM)){
				view.configForFM();
			}else if (command.equals(Globals.FSK)){
				view.configForFSK();
			}else if (command.equals(Globals.BURST_MODE)){
				view.configForBurstMode();
			}			
		}
		if(event.getActionCommand().equals(HP33120aInterface.MOD_WAVEFORM_SHAPE)){
			dataValidation(readFields());
		}
		
		if(event.getActionCommand().equals(HP33120aInterface.CONFIG)){
			// Configuration Button has been pressed, we have to read all the fields
			// create a request and send it to the server. We are going to use the CSV format
			System.out.println("Do it!! Button has been presed");	
			readFields();
			hp33120a.setFrame();
			// Test print for HP33120A
			System.out.println(hp33120a.getFrame());
			try {
				TCPClient.bidirectComm(hp33120a.getFrame(), HP33120aInterface.QUERY_MESSAGE_TYPE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	private void dataValidation(boolean formatError){
		VALIDATION_FLAG = hp33120a.dataValidation(auxDataValidationMessage);
		if(VALIDATION_FLAG || formatError){
			// There is some error in the frequency
			System.out.println("Called dataValidation()");
			view.disableExecutionButton();
			view.setDataValidationMessage(hp33120a.getDataValidationMessage());
			System.out.println(hp33120a.getDataValidationMessage());
		}
		else{
			view.enableExecutionButton();
			view.disableDataValidationLabel();
		}									
	}
		
	
	private boolean readFields(){
		JComboBox<String> combo = view.getTypeOfSignal();
		boolean formatError = false;
		auxDataValidationMessage = "";
		// Start reading signal fields
		
		System.out.println(view.getTypeOfSignal().getSelectedItem());
		hp33120a.setTypeOfSignal(view.getTypeOfSignal().getSelectedIndex());		
		hp33120a.setSignalShape(view.getSignalShape().getSelectedIndex());
		hp33120a.setUnit(view.getUnit().getSelectedIndex());
		if(frequencyFormatValidation())
			formatError = true;
		if(amplitudeFormatValidation())
			formatError = true;
		if(offsetFormatValidation())
			formatError = true;
		if(dutyCycleFormatValidation())
			formatError = true;		
		if(combo.getSelectedItem().equals(AG33220aInterface.MODULATION)){
			// We also read the modulation fields
			hp33120a.setModType(view.getModType().getSelectedIndex());
			hp33120a.setModWfmShape(view.getModWfmShape().getSelectedIndex());
			if(modFrequencyFormatValidation())
				formatError = true;			
			if(amDepthFormatValidation())
				formatError = true;
			if(fmDeviationFormatValidation())
				formatError = true;
			if(hopFrequencyFormatValidation())
				formatError = true;
			if(burstRateFormatValidation())
				formatError = true;
			if(burstCountFormatValidation())
				formatError = true;
			if(burstPhaseFormatValidation())
				formatError = true;
		}
		return formatError;
	}
	
	private boolean frequencyFormatValidation(){
		boolean flag = false;
		try{
			hp33120a.setSignalFreq(Float.parseFloat(view.getFrequency()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "Frequency must be a float\n";
			flag = true;
		}		
		return flag;
	}
	
	private boolean amplitudeFormatValidation(){
		boolean flag = false;
		try{
			hp33120a.setSignalAmp(Float.parseFloat(view.getAmplitude()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "Amplitude must be a float\n";
			flag = true;
		}				
		return flag;
	}
	
	private boolean offsetFormatValidation(){
		boolean flag = false;
		try{
			hp33120a.setSignalOff(Float.parseFloat(view.getOffset()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "Offset must be a float\n";
			flag = true;
		}				
		return flag;
	}
	
	private boolean dutyCycleFormatValidation(){
		boolean flag = false;
		try{
			hp33120a.setDutyCycleSq(Integer.parseInt(view.getDutyCycleSquare()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "Duty Cycle must be an integer\n";
			flag = true;
		}				
		return flag;
	}
	
	private boolean modFrequencyFormatValidation(){
		boolean flag = false;
		try{
			hp33120a.setModFreq(Float.parseFloat(view.getModulatingFreq()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "Modulating Frequency must be a float\n";
			flag = true;
		}				
		return flag;
	}
	
	private boolean amDepthFormatValidation(){
		boolean flag = false;
		try{
			hp33120a.setAmDepth(Integer.parseInt(view.getAmDepth()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "AM Depth must be an integer\n";
			flag = true;
		}				
		return flag;
	}
	
	private boolean fmDeviationFormatValidation(){
		boolean flag = false;
		try{
			hp33120a.setDeviationFM(Float.parseFloat(view.getFmDeviation()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "FM Deviation must be a float\n";
			flag = true;
		}				
		return flag;
	}
	
	private boolean hopFrequencyFormatValidation(){
		boolean flag = false;
		try{
			hp33120a.setHopFrequency(Float.parseFloat(view.getHopFrequency()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "Hop Frequency must be a float\n";
			flag = true;
		}				
		return flag;
	}
	
	private boolean burstRateFormatValidation(){
		boolean flag = false;
		try{
			hp33120a.setBurstRate(Float.parseFloat(view.getBurstRate()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "Burst Rate must be a float\n";
			flag = true;
		}				
		return flag;
	}
	
	private boolean burstCountFormatValidation(){
		boolean flag = false;
		try{
			hp33120a.setBurstCount(Integer.parseInt(view.getBurstCount()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "Burst Count must be an integer\n";
			flag = true;
		}				
		return flag;
	}
	
	private boolean burstPhaseFormatValidation(){
		boolean flag = false;
		try{
			hp33120a.setBurstPhase(Integer.parseInt(view.getBurstPhase()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "Burst Phase must be an integer\n";
			flag = true;
		}				
		return flag;
	}
}
