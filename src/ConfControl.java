import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ConfControl implements ActionListener{
		
	private ViewInterface view;
	private TCPClient tcpClient;
	private Frame frame;
	
	static int SocketPort = 5020;
	
	public ConfControl(ViewInterface view, TCPClient socketClient, Frame parent){
		this.view = view;
		this.tcpClient = socketClient;
		this.frame = parent;
		System.out.println(socketClient);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("Event Triggered");
		int connectionCode = 0;
		if(event.getActionCommand().equals(ViewInterface.CONNECT)){
			// Metodo para establecer conexión
			try {
				tcpClient = new TCPClient(Globals.IP, SocketPort);				
				connectionCode = TCPClient.establishComm(Globals.IP);
			} catch (IOException e) {
				e.printStackTrace();
				connectionCode = -1;
			}			
			if(connectionCode == 4){
				view.disableErrorLabel();
				// First of all we need to check what devices are active at this very moment!
				try {
					String[] receivedData;
					receivedData = TCPClient.bidirectComm("AVAILABLE_FIELD", Globals.AVAILABLE_DEVICES_QUERY);
					// receivedData[1] contains the list with available devices sorted as follows: 
					// AG33220A, HP33120A, HP34401A, HP54602B
					receivedData = decodeAvailableDevicesQuery(receivedData[1]);
					System.out.println("AG33220A is -> " + receivedData[0]);
					System.out.println("HP33120A is -> " + receivedData[1]);
					System.out.println("HP34401A is -> " + receivedData[2]);
					System.out.println("HP54602B is -> " + receivedData[3]);			

					frame.createMainView(receivedData);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (connectionCode == 5){
				// Call a method to show the user that the system is busy
				view.setErrorMsg("Server is busy! Try again later!!");
			} else if (connectionCode == -1){
				// Call a method to show the user that there has been a problem with the connection
				view.setErrorMsg("Unknown Error! Try again later!!");
			}
		}
	}	
	
	private String[] decodeAvailableDevicesQuery(String receivedMessage){
		String[] decodedQuery;
		decodedQuery = receivedMessage.split(",");
		return decodedQuery;
	}	
}
