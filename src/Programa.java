import java.awt.EventQueue;


public class Programa{

	public static void main(String[] args) {
		// Initialize the model and Socket
		final TCPClient clientSocket = null;
		// Instance of Agilent 33220A Arbitrary Waveform Generator
		final AG33220a ag33220a = new AG33220a();
		// Instance of HP 33120A Arbitrary Waveform Generator
		final HP33120a hp33120a = new HP33120a();
		// Instance of HP 34401A Digital Multimeter
		final HP34401a hp34401a = new HP34401a();
		
		// View initializing
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Imprimiendo Vista");
					ViewInterface vista = new View(clientSocket, hp33120a, hp34401a, ag33220a);
					ConfControl confControl = new ConfControl(vista, clientSocket);
					vista.setConfControl(confControl);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
