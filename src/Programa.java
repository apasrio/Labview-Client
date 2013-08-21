import java.awt.EventQueue;


public class Programa{

	public static void main(String[] args) {
		// Initialize the model and Socket
		final TCPClient clientSocket = null;
		// Instance of Agilent 33220A Arbitrary Waveform Generator
		final WaveformGenerator ag33220a = new WaveformGenerator();
		
		// View initializing
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Imprimiendo Vista");
					ViewInterface vista = new Vista(clientSocket);
					ConfControl confControl = new ConfControl(vista, clientSocket);
					WaveformControl wfmControl = new WaveformControl(vista, clientSocket, ag33220a);
					vista.setConfControl(confControl);
					((WaveFormInterface) vista).setWfmControl(wfmControl);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
