import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserMainPage extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 101L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public UserMainPage() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel panel_Patient = new JPanel();
		contentPane.add(panel_Patient, "PATIENT");
		panel_Patient.setLayout(new BorderLayout(0, 0));

		JPanel panelPatient_north = new JPanel();
		panel_Patient.add(panelPatient_north, BorderLayout.NORTH);

		JLabel lblWelcomePatient = new JLabel("WELCOME PATIENT:");
		lblWelcomePatient.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelPatient_north.add(lblWelcomePatient);

		JPanel panel = new JPanel();
		panel_Patient.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnViewClinicsAnd = new JButton("View clinics and services");
		panel.add(btnViewClinicsAnd);

		JButton btnBookAVisit = new JButton("Book a visit");
		panel.add(btnBookAVisit);

		JButton btnViewLastVisit = new JButton("View last visit results");
		panel.add(btnViewLastVisit);

		JPanel panelEmployee = new JPanel();
		contentPane.add(panelEmployee, "EMPLOYEE");
		//setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		addWindowListener(this);
	}



	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {
		setVisible(true);
		ApplicationMain.instance.start();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}

}
