import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HP54602bControl implements ActionListener{
	private TCPClient tcpClient;
	private HP54602bInterface view;
	private HP54602b hp54602b;
	
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
		}
	}
	
	private boolean readFields(){
		boolean formatError = false;
		return false;
	}
}
