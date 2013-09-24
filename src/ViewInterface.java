
public interface ViewInterface {
	// Aquí colocaremos métodos para la vista
	void setConfControl(ConfControl c); // Set the Configuration Controller (general issues)
	
	
	// Getters and Setters needed
	void setErrorMsg(String errorMsg);
	void disableErrorLabel();
	

	// Constantes que definen las posibles operaciones
	static final String CONNECT = "Stablishing connection";
	static final String DISCONNECT = "Disconnecting from server";	
	static final String SEND = "Sending message";
}
