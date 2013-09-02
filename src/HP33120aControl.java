import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;


public class HP33120aControl implements ActionListener, FocusListener{
	private HP33120aInterface view;
	private TCPClient tcpClient;
	private JComboBox combo;
	private HP33120a hp33120a;
	private String test;
	
	public HP33120aControl(HP33120aInterface view, TCPClient socketClient, HP33120a hp33120a){
		this.tcpClient = socketClient;
		this.view = view;
		this.hp33120a = hp33120a;
	}
	
	public void readFields(){
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("HP33120a Event Triggered");
		if(event.getActionCommand().equals(HP33120aInterface.TYPE_OF_SIGNAL)){	
			combo = view.getTypeOfSignal();
			if (combo.getSelectedItem().equals(HP33120aInterface.SIGNAL)){
				System.out.println("Disabling Buttons");
				view.disableModulationbuttons();
			} else if (combo.getSelectedItem().equals(HP33120aInterface.MODULATION)){
				view.enableModulationButtons();
				System.out.println("Enabling Buttons");
			}
		}
		if(event.getActionCommand().equals(WaveFormInterface.CONFIG)){
			// Configuration Button has been pressed, we have to read all the fields
			// create a request and send it to the server. We are going to use the CSV format
			System.out.println("Do it!! Button has been presed");	
			readFields();
			// TODO: Fix the next three lines:
			// wfmGen.setFrame();
			// Test print for WaveformGen
			// System.out.println(wfmGen.getFrame());
			}
		if(event.getActionCommand().equals(WaveFormInterface.FREQUENCY)){
			System.out.println("A system has lost its focus! ");
		}		
	}
}
