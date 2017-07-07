import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class View {

	public JFrame frame;
	private JTextField textField_usr;
	private JTextField textField_passwd;

	/**
	 * Launch the application.
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		// la connessione al database va messa nel DataMapper (come ha fatto posenato) altrimenti nel model(credo)
		try {
			Model db = new Model();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
					View window = new View();
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
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/img/caduceus-symbol-128[1].png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setLocationRelativeTo(null);

		JPanel panelNorth = new JPanel();
		frame.getContentPane().add(panelNorth, BorderLayout.NORTH);

		JLabel lbl_title_application = new JLabel("HEALTHCARE MANAGEMENT SYSTEM");
		lbl_title_application.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelNorth.add(lbl_title_application);

		JPanel panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[] {0};
		gbl_panelCenter.rowHeights = new int[] {0};
		gbl_panelCenter.columnWeights = new double[]{0.0, 0.0};
		gbl_panelCenter.rowWeights = new double[]{0.0, 0.0};
		panelCenter.setLayout(gbl_panelCenter);

		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.BOTH;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		panelCenter.add(lblUsername, gbc_lblUsername);
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setAlignmentX(Component.RIGHT_ALIGNMENT);

		textField_usr = new JTextField(16);
		GridBagConstraints gbc_textField_usr = new GridBagConstraints();
		gbc_textField_usr.fill = GridBagConstraints.BOTH;
		gbc_textField_usr.insets = new Insets(0, 0, 5, 5);
		gbc_textField_usr.gridx = 1;
		gbc_textField_usr.gridy = 0;
		panelCenter.add(textField_usr, gbc_textField_usr);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		panelCenter.add(lblPassword, gbc_lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);

		textField_passwd = new JPasswordField(16);
		GridBagConstraints gbc_textField_passwd = new GridBagConstraints();
		gbc_textField_passwd.insets = new Insets(0, 0, 0, 5);
		gbc_textField_passwd.fill = GridBagConstraints.BOTH;
		gbc_textField_passwd.gridx = 1;
		gbc_textField_passwd.gridy = 1;
		panelCenter.add(textField_passwd, gbc_textField_passwd);


		JPanel panelSouth = new JPanel();
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		JButton btnLogin = new JButton("Login");
		panelSouth.add(btnLogin);
		

		// close the application
		JButton btnCancel = new JButton("Cancel");
		panelSouth.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		frame.pack();
		frame.setResizable(false);
	}
}
