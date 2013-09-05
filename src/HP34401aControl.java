import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.text.JTextComponent;


public class HP34401aControl implements ActionListener, FocusListener{
	private TCPClient tcpClient;
	private HP34401aInterface view;
	private HP34401a hp34401a;
	
	
	public HP34401aControl(HP34401aInterface view, TCPClient socketClient, HP34401a hp34401a){
		this.hp34401a = hp34401a;
		this.view = view;
		this.tcpClient = socketClient;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
	}

	@Override
	public void focusLost(FocusEvent event) {
		System.out.println("An HP34401a field has lost its focus! ");
		// TODO: Fill this method
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("HP34401a has triggered an event");
		if(event.getActionCommand().equals(HP34401aInterface.AUTORANGE)){
			if(view.getAutoRange().isSelected()){
				// We have to disable ManualRange JTextField
				view.configManualRange(false);
			}else{
				view.configManualRange(true);
			}
		}
	}
}
