import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	public View view;
	
	
	public Controller(View view){
		this.view = view;
		setUpViewEvents();
	}
	
	public void setUpViewEvents(){
		view.btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// check credentials
				// ...
				
				new UserMainPage(frame);
				frame.setVisible(false);
			}
		});
	}

}
