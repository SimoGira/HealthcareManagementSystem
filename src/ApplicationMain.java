import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ApplicationMain {

	private JFrame frame;
	public static ApplicationMain instance;
	private JTextField textField_usr;
	private JTextField textField_passwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationMain window = new ApplicationMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationMain() {
		instance = this;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ApplicationMain.class.getResource("/img/caduceus-symbol-128[1].png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setLocationRelativeTo(null);

		JPanel panel_north = new JPanel();
		frame.getContentPane().add(panel_north, BorderLayout.NORTH);

		JLabel lbl_title_application = new JLabel("HEALTHCARE MANAGEMENT SYSTEM");
		lbl_title_application.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_north.add(lbl_title_application);

		JPanel panel_center = new JPanel();
		frame.getContentPane().add(panel_center, BorderLayout.CENTER);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_center.add(lblUsername);

		textField_usr = new JTextField();
		panel_center.add(textField_usr);
		textField_usr.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_center.add(lblPassword);

		textField_passwd = new JTextField();
		panel_center.add(textField_passwd);
		textField_passwd.setColumns(10);

		JPanel panel_btns = new JPanel();
		frame.getContentPane().add(panel_btns, BorderLayout.SOUTH);
		JButton btnLogin = new JButton("Login");
		panel_btns.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UserMainPage();
				frame.setVisible(false);
			}
		});

		JButton btnCancel = new JButton("Cancel");
		panel_btns.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

	}

	public void start() {
		frame.setVisible(true);	
	}

}
