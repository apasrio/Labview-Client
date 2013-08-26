
public interface ViewInterface {
	// Aqu� colocaremos m�todos para la vista
	void start(); //Initialize Visualization
	void setConfControl(ConfControl c); // Set the Configuration Controller (general issues)
	
	// Getters and Setters needed
	String getMsgToSend();
	

	// Constantes que definen las posibles operaciones
	static final String CONNECT = "Stablishing connection";
	static final String DISCONNECT = "Disconnecting from server";	
	static final String SEND = "Sending message";
}
