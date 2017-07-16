import java.awt.EventQueue;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.UIManager;
 
public abstract class User extends JFrame { 
	protected String fiscalcode;
	protected String name;
	protected String surname;
	protected String company;
	
	protected abstract void initializeView();
	protected abstract void setParamenters(Map<String, Object> map);
	
	protected String capitalizeString(String s){
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	protected void OpenLoginWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Set System L&F
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
