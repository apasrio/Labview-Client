import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;


public class WaveformControl implements ActionListener, FocusListener{
	private ViewInterface view;
	private TCPClient tcpClient;
	private JComboBox combo;
	private WaveformGenerator wfmGen;
	
	private boolean VALIDATION_FLAG = false;
	
	public WaveformControl(ViewInterface view, TCPClient socketClient, WaveformGenerator wfmGen){
		this.tcpClient = socketClient;
		this.view = view;
		this.wfmGen = wfmGen;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("Event Triggered");
		if(event.getActionCommand().equals(WaveFormInterface.TYPE_OF_SIGNAL)){	
			combo = ((WaveFormInterface) view).getTypeOfSignal();
			if (combo.getSelectedItem().equals(WaveFormInterface.SIGNAL)){
				System.out.println("Disabling Buttons");
				((WaveFormInterface) view).disableModulationbuttons();
			} else if (combo.getSelectedItem().equals(WaveFormInterface.MODULATION)){
				((WaveFormInterface) view).enableModulationButtons();
				System.out.println("Enabling Buttons");
			}
		}
		if(event.getActionCommand().equals(WaveFormInterface.CONFIG)){
			// Configuration Button has been pressed, we have to read all the fields
			// create a request and send it to the server. We are going to use the CSV format
			System.out.println("Do it!! Button has been presed");	
			readFields();
			wfmGen.setFrame();
			// Test print for WaveformGen
			System.out.println(wfmGen.getFrame());
			}
		if(event.getActionCommand().equals(WaveFormInterface.FREQUENCY)){
			System.out.println("A system has lost its focus! ");
		}
	}
	
	private void readFields(){
		combo = ((WaveFormInterface) view).getTypeOfSignal();
		// Start reading signal fields
		System.out.println(((WaveFormInterface) view).getTypeOfSignal().getSelectedItem());
		wfmGen.setTypeOfSignal(((WaveFormInterface) view).getTypeOfSignal().getSelectedIndex());
		
		// TODO: We need a catalog to know what each number means
		wfmGen.setSignalShape(((WaveFormInterface) view).getSignalShape().getSelectedIndex());
		wfmGen.setUnit(((WaveFormInterface) view).getUnit().getSelectedIndex());
		wfmGen.setSignalFreq(Float.parseFloat(((WaveFormInterface) view).getFrequency()));
		wfmGen.setSignalAmp(Float.parseFloat(((WaveFormInterface) view).getAmplitude()));
		wfmGen.setSignalOff(Float.parseFloat(((WaveFormInterface) view).getOffset()));
		wfmGen.setRampSymm(Integer.parseInt(((WaveFormInterface) view).getRampSymmetry()));
		wfmGen.setDutyCycleSq(Integer.parseInt(((WaveFormInterface) view).getDutyCycleSquare()));
		wfmGen.setDutyCyclePuls(Integer.parseInt(((WaveFormInterface) view).getDutyCyclePulse()));
		
		if(combo.getSelectedItem().equals(WaveFormInterface.MODULATION)){
			// We also read the modulation fields
			wfmGen.setModType(((WaveFormInterface) view).getModType().getSelectedIndex());
			wfmGen.setModWfmShape(((WaveFormInterface) view).getModWfmShape().getSelectedIndex());
			wfmGen.setAmDepth(Integer.parseInt(((WaveFormInterface) view).getAmDepth()));
			wfmGen.setDeviationFM(Float.parseFloat(((WaveFormInterface) view).getFmDeviation()));
			wfmGen.setHopFrequency(Float.parseFloat(((WaveFormInterface) view).getHopFrequency()));
			wfmGen.setInternalDeviation(Float.parseFloat(((WaveFormInterface) view).getIntDeviationPWM()));
			wfmGen.setPhaseDeviation(Float.parseFloat(((WaveFormInterface) view).getPhaseDeviationPM()));
			wfmGen.setBurstRate(Float.parseFloat(((WaveFormInterface) view).getBurstRate()));
			wfmGen.setBurstCount(Integer.parseInt(((WaveFormInterface) view).getBurstCount()));
			wfmGen.setBurstPhase(Integer.parseInt(((WaveFormInterface) view).getBurstPhase()));
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
		if (name.equals(WaveFormInterface.AMPLITUDE)){
			System.out.println("Here should be called the data-validation method for Amplitude");
		}else if (name.equals(WaveFormInterface.FREQUENCY)){
			System.out.println("Here should be called the data-validation method for Frequency");
			VALIDATION_FLAG = wfmGen.frequencyValidation(text);
			if(VALIDATION_FLAG){
				// There is some error in the frequency
				System.out.println("Frequency is higher than expected");
				((WaveFormInterface) view).disableExecutionButton();
				((WaveFormInterface) view).setDataValidationMessage(wfmGen.getDataValidationMessage());
			}
			else{
				((WaveFormInterface) view).enableExecutionButton();
				((WaveFormInterface) view).disableDataValidationLabel();
			}				
		} else if (name.equals(WaveFormInterface.OFFSET)){
			System.out.println("Here should be called the data-validation method for Offset");
		}else if (name.equals(WaveFormInterface.RAMP_SYMMETRY)){
			System.out.println("Here should be called the data-validation method for Ramp Symmetry");
		}else if (name.equals(WaveFormInterface.DUTY_CYCLE_SQUARE)){
			System.out.println("Here should be called the data-validation method for Duty Cycle Square");
		}else if (name.equals(WaveFormInterface.DUTY_CYCLE_PULSE)){
			System.out.println("Here should be called the data-validation method for Duty Cycle Pulse");
		}else if (name.equals(WaveFormInterface.MODULATING_FREQUENCY)){
			System.out.println("Here should be called the data-validation method for Modulating Frequency");
		}else if (name.equals(WaveFormInterface.AM_DEPTH)){
			System.out.println("Here should be called the data-validation method for AM Depth");
		}else if (name.equals(WaveFormInterface.FM_DEVIATION)){
			System.out.println("Here should be called the data-validation method for FM Deviation");
		}else if (name.equals(WaveFormInterface.HOP_FREQUENCY)){
			System.out.println("Here should be called the data-validation method for Hop Frequency");
		}else if (name.equals(WaveFormInterface.DEVIATION_PWM)){
			System.out.println("Here should be called the data-validation method for Int Deviation PWM");
		}else if (name.equals(WaveFormInterface.PHASE_DEVIATION_PM)){
			System.out.println("Here should be called the data-validation method for Phase Deviation");
		}else if (name.equals(WaveFormInterface.BURST_RATE)){
			System.out.println("Here should be called the data-validation method for Burst Rate");
		}else if (name.equals(WaveFormInterface.BURST_COUNT)){
			System.out.println("Here should be called the data-validation method for Burst Count");
		}else if (name.equals(WaveFormInterface.BURST_PHASE)){
			System.out.println("Here should be called the data-validation method for Burst Phase");
		}
		System.out.println("Text : " +text);
	}
}
