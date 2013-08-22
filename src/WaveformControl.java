import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;


public class WaveformControl implements ActionListener, FocusListener{
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
			wfmGen.setModType(((WaveFormInterface) vista).getModType().getSelectedIndex());
			wfmGen.setModWfmShape(((WaveFormInterface) vista).getModWfmShape().getSelectedIndex());
			wfmGen.setAmDepth(Integer.parseInt(((WaveFormInterface) vista).getAmDepth()));
			wfmGen.setDeviationFM(Float.parseFloat(((WaveFormInterface) vista).getFmDeviation()));
			wfmGen.setHopFrequency(Float.parseFloat(((WaveFormInterface) vista).getHopFrequency()));
			wfmGen.setInternalDeviation(Float.parseFloat(((WaveFormInterface) vista).getIntDeviationPWM()));
			wfmGen.setPhaseDeviation(Float.parseFloat(((WaveFormInterface) vista).getPhaseDeviationPM()));
			wfmGen.setBurstRate(Float.parseFloat(((WaveFormInterface) vista).getBurstRate()));
			wfmGen.setBurstCount(Integer.parseInt(((WaveFormInterface) vista).getBurstCount()));
			wfmGen.setBurstPhase(Integer.parseInt(((WaveFormInterface) vista).getBurstPhase()));
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent event) {
		// TODO Auto-generated method stub
		System.out.println("A system has lost its focus! ");
		final JTextComponent c = (JTextComponent) event.getSource();
		String name = c.getName();
		String text = c.getText();
		if (name.equals(WaveFormInterface.AMPLITUDE)){
			System.out.println("There has been a focus lost on Amplitude field");
		}else if (name.equals(WaveFormInterface.FREQUENCY)){
			System.out.println("There has been a focus lost on Frequency field");
		}
		System.out.println("Text : " +text);
	}
}
