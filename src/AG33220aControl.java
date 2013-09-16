import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;


public class AG33220aControl implements ActionListener, FocusListener{
	private AG33220aInterface view;
	private TCPClient tcpClient;
	private JComboBox combo;
	private AG33220a wfmGen;
	
	private boolean VALIDATION_FLAG = false;
	
	public AG33220aControl(AG33220aInterface view, TCPClient socketClient, AG33220a wfmGen){
		this.tcpClient = socketClient;
		this.view = view;
		this.wfmGen = wfmGen;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("Event Triggered");
		if(event.getActionCommand().equals(AG33220aInterface.TYPE_OF_SIGNAL)){	
			combo = ((AG33220aInterface) view).getTypeOfSignal();
			if (combo.getSelectedItem().equals(AG33220aInterface.SIGNAL)){
				System.out.println("Disabling Buttons");
				((AG33220aInterface) view).disableModulationbuttons();
			} else if (combo.getSelectedItem().equals(AG33220aInterface.MODULATION)){
				((AG33220aInterface) view).enableModulationButtons();
				System.out.println("Enabling Buttons");
			}
		}
		if(event.getActionCommand().equals(AG33220aInterface.CONFIG)){
			// Configuration Button has been pressed, we have to read all the fields
			// create a request and send it to the server. We are going to use the CSV format
			System.out.println("Do it!! Button has been presed");	
			readFields();
			wfmGen.setFrame();
			// Test print for WaveformGen
			System.out.println(wfmGen.getFrame());
			
			try {
				TCPClient.bidirectComm(wfmGen.getFrame(), AG33220aInterface.QUERY_MESSAGE_TYPE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		if(event.getActionCommand().equals(AG33220aInterface.FREQUENCY)){
			System.out.println("A system has lost its focus! ");
		}
	}
	
	private void readFields(){
		combo = ((AG33220aInterface) view).getTypeOfSignal();
		// Start reading signal fields
		System.out.println(((AG33220aInterface) view).getTypeOfSignal().getSelectedItem());
		wfmGen.setTypeOfSignal(((AG33220aInterface) view).getTypeOfSignal().getSelectedIndex());
		
		// TODO: We need a catalog to know what each number means
		wfmGen.setSignalShape(((AG33220aInterface) view).getSignalShape().getSelectedIndex());
		wfmGen.setUnit(((AG33220aInterface) view).getUnit().getSelectedIndex());
		wfmGen.setSignalFreq(Float.parseFloat(((AG33220aInterface) view).getFrequency()));
		wfmGen.setSignalAmp(Float.parseFloat(((AG33220aInterface) view).getAmplitude()));
		wfmGen.setSignalOff(Float.parseFloat(((AG33220aInterface) view).getOffset()));
		wfmGen.setRampSymm(Integer.parseInt(((AG33220aInterface) view).getRampSymmetry()));
		wfmGen.setDutyCycleSq(Integer.parseInt(((AG33220aInterface) view).getDutyCycleSquare()));
		wfmGen.setDutyCyclePuls(Integer.parseInt(((AG33220aInterface) view).getDutyCyclePulse()));
		
		if(combo.getSelectedItem().equals(AG33220aInterface.MODULATION)){
			// We also read the modulation fields
			wfmGen.setModType(((AG33220aInterface) view).getModType().getSelectedIndex());
			wfmGen.setModWfmShape(((AG33220aInterface) view).getModWfmShape().getSelectedIndex());
			wfmGen.setAmDepth(Integer.parseInt(((AG33220aInterface) view).getAmDepth()));
			wfmGen.setDeviationFM(Float.parseFloat(((AG33220aInterface) view).getFmDeviation()));
			wfmGen.setHopFrequency(Float.parseFloat(((AG33220aInterface) view).getHopFrequency()));
			wfmGen.setInternalDeviation(Float.parseFloat(((AG33220aInterface) view).getIntDeviationPWM()));
			wfmGen.setPhaseDeviation(Float.parseFloat(((AG33220aInterface) view).getPhaseDeviationPM()));
			wfmGen.setBurstRate(Float.parseFloat(((AG33220aInterface) view).getBurstRate()));
			wfmGen.setBurstCount(Integer.parseInt(((AG33220aInterface) view).getBurstCount()));
			wfmGen.setBurstPhase(Integer.parseInt(((AG33220aInterface) view).getBurstPhase()));
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// Just maintained for debugging reasons 
		System.out.println("A Field has gained the main focus");		
	}

	@Override
	public void focusLost(FocusEvent event) {
		// TODO Change the if... for a case structure
		System.out.println("A system has lost its focus! ");
		final JTextComponent c = (JTextComponent) event.getSource();
		String name = c.getName();
		String text = c.getText();
		if (name.equals(AG33220aInterface.AMPLITUDE)){
			System.out.println("Here should be called the data-validation method for Amplitude");
		}else if (name.equals(AG33220aInterface.FREQUENCY)){
			System.out.println("Here should be called the data-validation method for Frequency");
			VALIDATION_FLAG = wfmGen.frequencyValidation(text);
			if(VALIDATION_FLAG){
				// There is some error in the frequency
				System.out.println("Frequency is higher than expected");
				((AG33220aInterface) view).disableExecutionButton();
				((AG33220aInterface) view).setDataValidationMessage(wfmGen.getDataValidationMessage());
			}
			else{
				((AG33220aInterface) view).enableExecutionButton();
				((AG33220aInterface) view).disableDataValidationLabel();
			}				
		} else if (name.equals(AG33220aInterface.OFFSET)){
			System.out.println("Here should be called the data-validation method for Offset");
		}else if (name.equals(AG33220aInterface.RAMP_SYMMETRY)){
			System.out.println("Here should be called the data-validation method for Ramp Symmetry");
		}else if (name.equals(AG33220aInterface.DUTY_CYCLE_SQUARE)){
			System.out.println("Here should be called the data-validation method for Duty Cycle Square");
		}else if (name.equals(AG33220aInterface.DUTY_CYCLE_PULSE)){
			System.out.println("Here should be called the data-validation method for Duty Cycle Pulse");
		}else if (name.equals(AG33220aInterface.MODULATING_FREQUENCY)){
			System.out.println("Here should be called the data-validation method for Modulating Frequency");
		}else if (name.equals(AG33220aInterface.AM_DEPTH)){
			System.out.println("Here should be called the data-validation method for AM Depth");
		}else if (name.equals(AG33220aInterface.FM_DEVIATION)){
			System.out.println("Here should be called the data-validation method for FM Deviation");
		}else if (name.equals(AG33220aInterface.HOP_FREQUENCY)){
			System.out.println("Here should be called the data-validation method for Hop Frequency");
		}else if (name.equals(AG33220aInterface.DEVIATION_PWM)){
			System.out.println("Here should be called the data-validation method for Int Deviation PWM");
		}else if (name.equals(AG33220aInterface.PHASE_DEVIATION_PM)){
			System.out.println("Here should be called the data-validation method for Phase Deviation");
		}else if (name.equals(AG33220aInterface.BURST_RATE)){
			System.out.println("Here should be called the data-validation method for Burst Rate");
		}else if (name.equals(AG33220aInterface.BURST_COUNT)){
			System.out.println("Here should be called the data-validation method for Burst Count");
		}else if (name.equals(AG33220aInterface.BURST_PHASE)){
			System.out.println("Here should be called the data-validation method for Burst Phase");
		}
		System.out.println("Text : " +text);
	}
}
