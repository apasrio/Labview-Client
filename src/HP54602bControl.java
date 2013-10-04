import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HP54602bControl implements ActionListener{
	private TCPClient tcpClient;
	private HP54602bInterface view;
	private HP54602b hp54602b;
	private String auxDataValidationMessage;
	
	public HP54602bControl(HP54602bInterface view, TCPClient socketClient, HP54602b hp54602b){
		this.hp54602b = hp54602b;
		this.view = view;
		this.tcpClient = socketClient;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("HP54602B has triggered an event");
		if(event.getActionCommand().equals(HP54602bInterface.CONFIG)){
			System.out.println("Do it! Button has been pressed!");
			readFields();
			hp54602b.setFrame();
			System.out.println(hp54602b.getFrame());
		}
	}
	
	private boolean readFields(){
		boolean formatError = false;
		hp54602b.setAutoset(view.getAutoSet().isSelected());
		hp54602b.setCh1(view.getBtnCh1().isSelected());
		hp54602b.setCh2(view.getBtnCh2().isSelected());
		hp54602b.setCh1BW(view.getBtnCh1BW().isSelected());
		hp54602b.setCh2BW(view.getBtnCh2BW().isSelected());
		hp54602b.setPositiveNegativeSlope(view.getSlopeButton().isSelected());
		hp54602b.setCh1Function(view.getFunctionCh1().getSelectedIndex());
		hp54602b.setCh2Function(view.getFunctionCh2().getSelectedIndex());
		hp54602b.setCh1Coupling(view.getCouplingCh1().getSelectedIndex());
		hp54602b.setCh2Coupling(view.getCouplingCh2().getSelectedIndex());
		hp54602b.setCh1Probe(view.getProbeCh1().getSelectedIndex());
		hp54602b.setCh2Probe(view.getProbeCh2().getSelectedIndex());
		
		if(ch1RangeDataValidation())
			formatError = true;
		if(ch2RangeDataValidation())
			formatError = true;
		if(ch1PositionDataValidation())
			formatError = true;
		if(ch2PositionDataValidation())
			formatError = true;
		if(timeRangeDataValidation())
			formatError = true;
		if(timeDelayDataValidation())
			formatError = true;
		if(triggerDataValidation())
			formatError = true;
		return formatError;
	}
	
	private boolean ch1RangeDataValidation(){
		boolean flag = false;
		try{
			hp54602b.setCh1Range(Float.parseFloat(view.getRangeCh1().getText()));
		} catch (NumberFormatException e){
			flag = true;
			auxDataValidationMessage += "Ch1 Range must be a float\n";
		}
		return flag;
	}
	
	private boolean ch2RangeDataValidation(){
		boolean flag = false;
		try{
			hp54602b.setCh2Range(Float.parseFloat(view.getRangeCh2().getText()));
		} catch (NumberFormatException e){
			flag = true;
			auxDataValidationMessage += "Ch2 Range must be a float.\n";
		}
		return flag;
	}
	
	private boolean ch1PositionDataValidation(){
		boolean flag = false;
		try{
			hp54602b.setCh1Pos(Float.parseFloat(view.getPositionCh1().getText()));
		} catch (NumberFormatException e){
			flag = true;
			auxDataValidationMessage += "Ch1 Position must be a float\n";
		}
		return flag;
	}
	
	private boolean ch2PositionDataValidation(){
		boolean flag = false;
		try{
			hp54602b.setCh2Pos(Float.parseFloat(view.getPositionCh2().getText()));
		} catch (NumberFormatException e){
			flag = true;
			auxDataValidationMessage += "Ch2 Position must be a float\n";			
		}
		return flag;
	}
	
	private boolean timeRangeDataValidation(){
		boolean flag = false;
		try{
			hp54602b.setTimeRange(Float.parseFloat(view.getRangeTime().getText()));
		} catch (NumberFormatException e){
			flag = true;
			auxDataValidationMessage += "Time Range must be a float";
		}
		return flag;
	}
	
	private boolean timeDelayDataValidation(){
		boolean flag = false;
		try{
			hp54602b.setTimeDelay(Float.parseFloat(view.getTimeDelay().getText()));
		} catch (NumberFormatException e){
			flag = true;
			auxDataValidationMessage += "Time Delay must be a float";
		}
		return flag;
	}
	
	private boolean triggerDataValidation(){
		boolean flag = false;
		try{
			hp54602b.setTriggerLevel(Float.parseFloat(view.getTriggerlevel().getText()));
		} catch (NumberFormatException e){
			flag = true;
			auxDataValidationMessage += "Trigger level must be a float";
		}
		return flag;
	}
}
