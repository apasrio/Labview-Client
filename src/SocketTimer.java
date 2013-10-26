import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;


public class SocketTimer{
	private Timer timer;
	private Frame frame;
	
	public SocketTimer(Frame frame){
		timer = new Timer();
		this.frame = frame;
	}
	
	public void setTimer(){
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				try {
					TCPClient.bidirectComm("EmptyMessage", Globals.CLOSE_CONNECTION);
					JOptionPane.showMessageDialog(frame,
                            "Socket Closed by Timeout!", "Socket Closed!" ,JOptionPane.WARNING_MESSAGE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(frame,
                            "Close the program manually please!!", "Socket Closed!" ,JOptionPane.WARNING_MESSAGE);
				}				
			}			
		}, Globals.TIME);
	}
}
