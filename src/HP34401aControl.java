import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.text.JTextComponent;


public class HP34401aControl implements ActionListener, FocusListener{
	private TCPClient tcpClient;
	private HP34401aInterface view;
	private HP34401a hp34401a;
	private String auxDataValidationMessage;
	private boolean VALIDATION_FLAG = false;
	
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
		final JTextComponent c = (JTextComponent) event.getSource();
		String name = c.getName();
		if (name.equals(HP34401aInterface.RANGE)){
			// Check if Range has been properly filled
			dataValidation(readFields());
		}
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
		if(event.getActionCommand().equals(HP34401aInterface.CONFIG)){
			// Config button has been pressed
			String[] receivedData = null;
			System.out.println("Do it!! Button has been presed");	
			readFields();
			hp34401a.setFrame();
			try {
				receivedData = TCPClient.bidirectComm(hp34401a.getFrame(), Globals.HP34401_QUERY_MESSAGE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			decodeHP34401aResponse(receivedData);
		}		
	}
	
	private void dataValidation(boolean formatError){
		VALIDATION_FLAG = hp34401a.dataValidation(auxDataValidationMessage);
		if(VALIDATION_FLAG || formatError){
			// There is some kind of error
			view.configExecutionButton(false);
			view.setDataValidationMessage(hp34401a.getDataValidationMessage());
		} else {
			view.configExecutionButton(true);
			view.disableDataValidationLabel();
		}
	}
	
	private boolean readFields(){
		boolean formatError = false;
		auxDataValidationMessage = "";
		
		hp34401a.setFunction(view.getFunction().getSelectedIndex());
		hp34401a.setResolution(view.getResolution().getSelectedIndex());
		hp34401a.setTriggerSource(view.getTriggerSource().getSelectedIndex());
		if(rangeFormatValidation())
			formatError = true;
		hp34401a.setAutoZero(view.getAutoZero().isSelected());
		hp34401a.setAutoRange(view.getAutoRange().isSelected());
		return formatError;
	}
	
	private boolean rangeFormatValidation(){
		boolean flag = false;
		try{
			hp34401a.setRange(Float.parseFloat(view.getRange()));
		} catch (NumberFormatException nfe){
			auxDataValidationMessage += "Frequency must be a float\n";
			flag = true;
		}
		return flag;
	}
	
	// Method to decode HP34401A response
	private void decodeHP34401aResponse(String[] receivedData){
		int messageType = Integer.parseInt(receivedData[0]);
		String receivedMeasure = receivedData[1];
		switch(messageType){
			case 31:
				// Measuring success
				// If it is needed to do some calculations with the measured value, method should be called here
				view.setMeasure(receivedMeasure);
				break;
			case 33:
				// Measuring failure
				view.setMeasure(receivedMeasure);
				break;			
		}
	}
}
