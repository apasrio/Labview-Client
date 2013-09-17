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
					readFields();
					System.out.println("Data Validation Method is going to be called! ");					
					dataValidation();									
				}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("HP33120a Event Triggered");
		JComboBox<String> combo;
		// Changes according to the selected type of signal
		if(event.getActionCommand().equals(HP33120aInterface.TYPE_OF_SIGNAL)){	
			readFields();
			dataValidation();
			combo = view.getTypeOfSignal();
			if (combo.getSelectedItem().equals(Globals.SIGNAL)){
				System.out.println("Disabling Buttons");
				view.disableModulationbuttons();
			} else if (combo.getSelectedItem().equals(Globals.MODULATION)){
				view.enableModulationButtons();
				System.out.println("Enabling Buttons");
			}
		}
		// Changes according to the selected waveform shape
		if(event.getActionCommand().equals(HP33120aInterface.WAVEFORM_SHAPE)){
			System.out.println("HP33120a WAVEFORMSHAPE CHANGED!!!!");
			readFields();
			dataValidation();
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
	private void dataValidation(){
		VALIDATION_FLAG = hp33120a.dataValidation();
		if(VALIDATION_FLAG){
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
	
	private void readFields(){
		JComboBox<String> combo = view.getTypeOfSignal();
		// Start reading signal fields
		System.out.println(view.getTypeOfSignal().getSelectedItem());
		hp33120a.setTypeOfSignal(view.getTypeOfSignal().getSelectedIndex());
		
		// TODO: We need a catalog to know what each number means
		hp33120a.setSignalShape(view.getSignalShape().getSelectedIndex());
		hp33120a.setUnit(view.getUnit().getSelectedIndex());
		hp33120a.setSignalFreq(Float.parseFloat(view.getFrequency()));
		hp33120a.setSignalAmp(Float.parseFloat(view.getAmplitude()));
		hp33120a.setSignalOff(Float.parseFloat(view.getOffset()));
		hp33120a.setDutyCycleSq(Integer.parseInt(view.getDutyCycleSquare()));
		
		if(combo.getSelectedItem().equals(WaveFormInterface.MODULATION)){
			// We also read the modulation fields
			hp33120a.setModType(view.getModType().getSelectedIndex());
			hp33120a.setModWfmShape(view.getModWfmShape().getSelectedIndex());
			hp33120a.setAmDepth(Integer.parseInt(view.getAmDepth()));
			hp33120a.setDeviationFM(Float.parseFloat(view.getFmDeviation()));
			hp33120a.setHopFrequency(Float.parseFloat(view.getHopFrequency()));
			hp33120a.setBurstRate(Float.parseFloat(view.getBurstRate()));
			hp33120a.setBurstCount(Integer.parseInt(view.getBurstCount()));
			hp33120a.setBurstPhase(Integer.parseInt(view.getBurstPhase()));
		}
	}
}
