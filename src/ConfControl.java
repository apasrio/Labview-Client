import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ConfControl implements ActionListener{
		
	private ViewInterface vista;
	private TCPClient tcpClient;
	private Frame frame;
	
	static String SocketIp = "127.0.0.1";
	static int SocketPort = 5020;
	
	public ConfControl(ViewInterface vista, TCPClient socketClient, Frame parent){
		this.vista = vista;
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
				tcpClient = new TCPClient(SocketIp, SocketPort);				
				connectionCode = TCPClient.establishComm(SocketIp);
			} catch (IOException e) {
				e.printStackTrace();
				connectionCode = -1;
			}			
			if(connectionCode == 4){
				vista.disableErrorLabel();
				frame.createMainView();
			} else if (connectionCode == 5){
				// Call a method to show the user that the system is busy
				vista.setErrorMsg("Server is busy! Try again later!!");
			} else if (connectionCode == -1){
				// Call a method to show the user that there has been a problem with the connection
				vista.setErrorMsg("Unknown Error! Try again later!!");
			}
		}
		/*
		else if(event.getActionCommand().equals(ViewInterface.DISCONNECT)){
			try {
				TCPClient.bidirectComm("Closing socket!", 1);
				TCPClient.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();				
			}			
		}
		else if(event.getActionCommand().equals(ViewInterface.SEND)){
			String msgToSend = vista.getMsgToSend();
			System.out.println("This is the message that is going to be sent: "+ msgToSend);
			try{
				TCPClient.bidirectComm(msgToSend, 0);
			} catch (Exception e){
				e.printStackTrace();
			}
		}*/
	}	
}
