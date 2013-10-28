import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class View{	
		
	private JPanel mainView = new JPanel();
	
	/**
	 * Create the applet.
	 */
	public View(final TCPClient socketClient, HP33120a hp33120a, HP34401a hp34401a, AG33220a ag33220a, HP54602b hp54602b,String[] availableDevices) {
		// TODO: Check if a refactor is needed -> Maybe we can initialize models here instead of in Programa
		/* availableDevices is a string array with the next format 
		 * -> AG33220aFlag, HP32120aFlag, HP34401aFlag, HP54602bFlag
		 * The flag is going to be given to the proper View Constructor
		 */
					
		mainView.setSize(1280,960);
		mainView.setLayout(new GridLayout(2, 0, 0, 0));
				
		JPanel topPanel = new JPanel();
		mainView.add(topPanel);
		topPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JTabbedPane waveformGenerators = new JTabbedPane(JTabbedPane.TOP);
		topPanel.add(waveformGenerators);
				
		
		// Start defining AG33220a Components
		int ag33220aFlag = Integer.parseInt(availableDevices[0]);
		AG33220aInterface ag33220aView = new AG33220aView(ag33220aFlag);
		waveformGenerators.addTab("Agilent 33220A", null, ag33220aView.getAG33220aPanel(), null);
		
		if(ag33220aFlag == 1){
			AG33220aControl ag33220aControl = new AG33220aControl(ag33220aView, socketClient, ag33220a);
			ag33220aView.setAG33220aControl(ag33220aControl);
		}
		
		// Start defining HP33120a Components	
		int hp33120aFlag = Integer.parseInt(availableDevices[1]);
		HP33120aInterface hp33120aView = new HP33120aView(hp33120aFlag);
		waveformGenerators.addTab("HP 33120A", null, hp33120aView.getHP33120aPanel(), null);
		if(hp33120aFlag == 1){
			HP33120aControl hp33120aControl = new HP33120aControl(hp33120aView, socketClient, hp33120a);
			hp33120aView.setHP33120aControl(hp33120aControl);	
		}
			
		
		JTabbedPane digitalMultimeters = new JTabbedPane(JTabbedPane.TOP);
		topPanel.add(digitalMultimeters);		
		
		// Start defining HP34401a Components 
		int hp34401aFlag = Integer.parseInt(availableDevices[2]);
		HP34401aView hp34401aView = new HP34401aView(hp34401aFlag);
		digitalMultimeters.addTab("HP34401A", null, hp34401aView.getHP34401aPanel(), null);
		if(hp34401aFlag == 1){
			HP34401aControl hp34401aControl = new HP34401aControl(hp34401aView,socketClient, hp34401a);
			hp34401aView.setHP34401aControl(hp34401aControl);
		}
		
		
		JPanel bottomPanel = new JPanel();
		mainView.add(bottomPanel);
		bottomPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane oscilloscopesPanel = new JTabbedPane(JTabbedPane.TOP);
		bottomPanel.add(oscilloscopesPanel);
		
		// Start defining HP54602b Components
		int hp54602bFlag = Integer.parseInt(availableDevices[3]);
		HP54602bView hp54602bView = new HP54602bView(hp54602bFlag);
		oscilloscopesPanel.addTab("HP 54602B", null, hp54602bView.getHP54602bPanel(), null);
		if(hp54602bFlag == 1){
			HP54602bControl hp54602bControl = new HP54602bControl(hp54602bView, socketClient, hp54602b);
			hp54602bView.setHP54602bControl(hp54602bControl);
		}
				
		mainView.setVisible(true);
		SocketTimer socketTimer = new SocketTimer((Frame) mainView.getParent());
		socketTimer.setTimer();
	}	
	
	public JComponent getMainView(){
		return mainView;
	}	
}
