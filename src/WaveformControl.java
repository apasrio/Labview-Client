import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;


public class WaveformControl implements ActionListener{
	private ViewInterface vista;
	private TCPClient tcpClient;
	private JComboBox combo;
	private WaveformGenerator wfmGen;
	
	public WaveformControl(ViewInterface vista, TCPClient socketClient, WaveformGenerator wfmGen){
		this.tcpClient = socketClient;
		this.vista = vista;
		this.wfmGen = wfmGen;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("Event Triggered");
		if(event.getActionCommand().equals(WaveFormInterface.TYPE_OF_SIGNAL)){	
			combo = ((WaveFormInterface) vista).getTypeOfSignal();
			if (combo.getSelectedItem().equals(WaveFormInterface.SIGNAL)){
				System.out.println("Disabling Buttons");
				((WaveFormInterface) vista).disableModulationbuttons();
			} else if (combo.getSelectedItem().equals(WaveFormInterface.MODULATION)){
				((WaveFormInterface) vista).enableModulationButtons();
				System.out.println("Enabling Buttons");
			}
		}
		if(event.getActionCommand().equals(WaveFormInterface.CONFIG)){
			// Configuration Button has been pressed, we have to read all the fields
			System.out.println("Do it!! Button has been presed");	
			readFields();
		}
	}
	
	public void readFields(){
		combo = ((WaveFormInterface) vista).getTypeOfSignal();
		// Start reading signal fields
		System.out.println(((WaveFormInterface) vista).getTypeOfSignal().getSelectedItem());
		wfmGen.setTypeOfSignal(((WaveFormInterface) vista).getTypeOfSignal().getSelectedIndex());
		
		// TODO: We need a catalog to know what each number means
		wfmGen.setSignalShape(((WaveFormInterface) vista).getSignalShape().getSelectedIndex());
		wfmGen.setSignalFreq(Float.parseFloat(((WaveFormInterface) vista).getFrequency()));
		wfmGen.setSignalAmp(Float.parseFloat(((WaveFormInterface) vista).getAmplitude()));
		wfmGen.setSignalOff(Float.parseFloat(((WaveFormInterface) vista).getOffset()));
		wfmGen.setRampSymm(Integer.parseInt(((WaveFormInterface) vista).getRampSymmetry()));
		wfmGen.setDutyCycleSq(Integer.parseInt(((WaveFormInterface) vista).getDutyCycleSquare()));
		wfmGen.setDutyCyclePuls(Integer.parseInt(((WaveFormInterface) vista).getDutyCyclePulse()));
		
		if(combo.getSelectedItem().equals(WaveFormInterface.MODULATION)){
			// We also read the modulation fields
		}
	}
}
