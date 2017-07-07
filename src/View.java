import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.AbstractListModel;

public class View {

	public JFrame frame;
	private JTextField textField_usr;
	private JTextField textField_passwd;
	private JTextField textFieldFiscalCode;
	private JPasswordField passwordFieldPIN;

	/**
	 * Launch the application.
	 * 
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {

		/*
		 * // la connessione al database va messa nel DataMapper (come ha fatto
		 * // posenato) altrimenti nel model(credo) try { Model db = new
		 * Model(); } catch (ClassNotFoundException e1) { // TODO Auto-generated
		 * catch block e1.printStackTrace(); } catch (SQLException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */

		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}

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
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(View.class.getResource("/img/caduceus-symbol-128[1].png")));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel, "name_11222847950945");
		loginPanel.setLayout(new BorderLayout(0, 0));

		JPanel panelNorthLogin = new JPanel();
		loginPanel.add(panelNorthLogin, BorderLayout.NORTH);
		panelNorthLogin.setLayout(new BorderLayout(0, 0));

		JLabel lbl_title_application = new JLabel("HEALTHCARE MANAGEMENT SYSTEM");
		lbl_title_application.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title_application.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelNorthLogin.add(lbl_title_application, BorderLayout.NORTH);

		JTabbedPane tabbedPaneLogin = new JTabbedPane(JTabbedPane.TOP);
		loginPanel.add(tabbedPaneLogin, BorderLayout.CENTER);

		JPanel patientLogin = new JPanel();
		tabbedPaneLogin.addTab("Patient", null, patientLogin, null);
		patientLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterPatient = new JPanel();
		patientLogin.add(panelCenterPatient, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenterPatient = new GridBagLayout();
		gbl_panelCenterPatient.columnWidths = new int[] { 0, 0 };
		gbl_panelCenterPatient.rowHeights = new int[] { 0 };
		panelCenterPatient.setLayout(gbl_panelCenterPatient);

		JLabel lblFiscalCode = new JLabel("Fiscal Code:");
		lblFiscalCode.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFiscalCode.setAlignmentX(1.0f);
		GridBagConstraints gbc_lblFiscalCode = new GridBagConstraints();
		gbc_lblFiscalCode.anchor = GridBagConstraints.EAST;
		gbc_lblFiscalCode.fill = GridBagConstraints.VERTICAL;
		gbc_lblFiscalCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiscalCode.gridx = 0;
		gbc_lblFiscalCode.gridy = 0;
		panelCenterPatient.add(lblFiscalCode, gbc_lblFiscalCode);

		textFieldFiscalCode = new JTextField(16);
		GridBagConstraints gbc_textFieldFiscalCode = new GridBagConstraints();
		gbc_textFieldFiscalCode.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFiscalCode.fill = GridBagConstraints.BOTH;
		gbc_textFieldFiscalCode.gridx = 1;
		gbc_textFieldFiscalCode.gridy = 0;
		panelCenterPatient.add(textFieldFiscalCode, gbc_textFieldFiscalCode);

		JLabel lblPin = new JLabel("PIN:");
		lblPin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPin.setAlignmentX(1.0f);
		GridBagConstraints gbc_lblPin = new GridBagConstraints();
		gbc_lblPin.anchor = GridBagConstraints.EAST;
		gbc_lblPin.insets = new Insets(0, 0, 0, 5);
		gbc_lblPin.fill = GridBagConstraints.VERTICAL;
		gbc_lblPin.gridx = 0;
		gbc_lblPin.gridy = 1;
		panelCenterPatient.add(lblPin, gbc_lblPin);

		passwordFieldPIN = new JPasswordField(16);
		GridBagConstraints gbc_passwordFieldPIN = new GridBagConstraints();
		gbc_passwordFieldPIN.fill = GridBagConstraints.BOTH;
		gbc_passwordFieldPIN.gridx = 1;
		gbc_passwordFieldPIN.gridy = 1;
		panelCenterPatient.add(passwordFieldPIN, gbc_passwordFieldPIN);

		JPanel panelSouthPatient = new JPanel();
		patientLogin.add(panelSouthPatient, BorderLayout.SOUTH);
		panelSouthPatient.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnLoginPatient = new JButton("Login");
		btnLoginPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelSouthPatient.add(btnLoginPatient);

		JPanel employeeLogin = new JPanel();
		tabbedPaneLogin.addTab("Employee", null, employeeLogin, null);
		employeeLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterEmployee = new JPanel();
		employeeLogin.add(panelCenterEmployee, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenterEmployee = new GridBagLayout();
		gbl_panelCenterEmployee.columnWidths = new int[] { 0, 0 };
		gbl_panelCenterEmployee.rowHeights = new int[] { 0 };
		panelCenterEmployee.setLayout(gbl_panelCenterEmployee);

		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.BOTH;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		panelCenterEmployee.add(lblUsername, gbc_lblUsername);
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setAlignmentX(Component.RIGHT_ALIGNMENT);

		textField_usr = new JTextField(16);
		GridBagConstraints gbc_textField_usr = new GridBagConstraints();
		gbc_textField_usr.fill = GridBagConstraints.BOTH;
		gbc_textField_usr.insets = new Insets(0, 0, 5, 5);
		gbc_textField_usr.gridx = 1;
		gbc_textField_usr.gridy = 0;
		panelCenterEmployee.add(textField_usr, gbc_textField_usr);

		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		panelCenterEmployee.add(lblPassword, gbc_lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);

		textField_passwd = new JPasswordField(16);
		GridBagConstraints gbc_textField_passwd = new GridBagConstraints();
		gbc_textField_passwd.insets = new Insets(0, 0, 0, 5);
		gbc_textField_passwd.fill = GridBagConstraints.BOTH;
		gbc_textField_passwd.gridx = 1;
		gbc_textField_passwd.gridy = 1;
		panelCenterEmployee.add(textField_passwd, gbc_textField_passwd);

		JPanel panelSouthEmployee = new JPanel();
		employeeLogin.add(panelSouthEmployee, BorderLayout.SOUTH);
		JButton btnLoginEmployee = new JButton("Login");
		panelSouthEmployee.add(btnLoginEmployee);

		JPanel panelSouthLogin = new JPanel();
		loginPanel.add(panelSouthLogin, BorderLayout.SOUTH);

		JButton btnViewClinicAnd = new JButton("View clinics and services");
		panelSouthLogin.add(btnViewClinicAnd);

		JPanel patientPanel = new JPanel();
		frame.getContentPane().add(patientPanel, "name_11222960382586");

		JButton btnNewButton = new JButton("New button");
		patientPanel.add(btnNewButton);

		JButton btnBookVisit = new JButton("book visit");
		patientPanel.add(btnBookVisit);

		JPanel employeePanel = new JPanel();
		frame.getContentPane().add(employeePanel, "name_11222990618502");

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.3);
		frame.getContentPane().add(splitPane, "name_18581128151280");

		JPanel ClinicsPanel = new JPanel();
		splitPane.setLeftComponent(ClinicsPanel);
		ClinicsPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblClinics = new JLabel("Clinics");
		lblClinics.setHorizontalAlignment(SwingConstants.CENTER);
		ClinicsPanel.add(lblClinics, BorderLayout.NORTH);

		JList<String> list = new JList<String>();
		list.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 2626387453652530474L;
			String[] values = new String[] {"Clinic_1", "Clinic_2", "Clinic_3"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		ClinicsPanel.add(list, BorderLayout.CENTER);

		JPanel ServicesPanel = new JPanel();
		splitPane.setRightComponent(ServicesPanel);
		ServicesPanel.setLayout(new BorderLayout(0, 0));

		JTextPane txtpnclinicsOfJesus = new JTextPane();
		txtpnclinicsOfJesus.setContentType("text/html");
		txtpnclinicsOfJesus.setText(
				"<html>\r\n\t<head></head>\r\n\t<body>\r\n\t\t<h1>Clinics of Jesus</h1>\r\n\t\t<h3>Services</h3>\r\n\t\t.....Prova html.....\r\n\t</body>\r\n</html>\r\n");
		ServicesPanel.add(txtpnclinicsOfJesus, BorderLayout.CENTER);

		frame.setSize(500, 400);
		// frame.pack();
		// frame.setResizable(false);
	}
}
