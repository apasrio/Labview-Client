import java.awt.EventQueue;

import javax.swing.JFrame;


public class Main extends JFrame{	

	public static void main(String[] args) {
		// Initialize the model and Socket
		final TCPClient clientSocket = null;
		// Instance of Agilent 33220A Arbitrary Waveform Generator
		final AG33220a ag33220a = new AG33220a();
		// Instance of HP 33120A Arbitrary Waveform Generator
		final HP33120a hp33120a = new HP33120a();
		// Instance of HP 34401A Digital Multimeter
		final HP34401a hp34401a = new HP34401a();	
		// Instance of HP 54602B Digital Oscilloscope
		final HP54602b hp54602b = new HP54602b();
		
		// View initializing
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Imprimiendo Vista");
					Frame frame = new Frame(clientSocket, hp33120a, hp34401a, ag33220a, hp54602b);				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
