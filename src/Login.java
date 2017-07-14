import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Login extends JFrame {

	private static final long serialVersionUID = 1234L;
	private CardLayout clLogin;
	private ArrayList<String[]> companiesList; // forse da mettere locale
	private JComboBox<String> comboBoxSelectCompany; // forse da mettere locale
	private JList<String> listClinics; // forse da mettere locale
	private JButton btnLoginPatient;
	private JButton btnLoginEmployee;
	private JLabel lblMaiuscActive;
	private JPasswordField textField_password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Login() throws ClassNotFoundException, SQLException {

		Database db = Database.getInstance();
		setTitle("HEALTHCARE MANAGEMENT SYSTEM - Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/img/healthcare-icon.png")));
		setSize(700, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		clLogin = (CardLayout) this.getContentPane().getLayout();

		JPanel panelLogin = new JPanel();
		getContentPane().add(panelLogin, "panelLogin");
		panelLogin.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPaneLogin = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneLogin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabbedPaneLogin.getSelectedIndex() == 0)
					getRootPane().setDefaultButton(btnLoginPatient);
				else
					getRootPane().setDefaultButton(btnLoginEmployee);
			}
		});
		
		panelLogin.add(tabbedPaneLogin, BorderLayout.CENTER);

		JPanel panelPatientLogin = new JPanel();
		tabbedPaneLogin.addTab("Area paziente", null, panelPatientLogin, null);
		panelPatientLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterPatientLogin = new JPanel();
		panelCenterPatientLogin.setBorder(new EmptyBorder(0, 20, 0, 0));
		panelPatientLogin.add(panelCenterPatientLogin, BorderLayout.WEST);
		GridBagLayout gbl_panelCenterPatientLogin = new GridBagLayout();
		gbl_panelCenterPatientLogin.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panelCenterPatientLogin.columnWidths = new int[] { 100, 0 };
		gbl_panelCenterPatientLogin.rowHeights = new int[] { 0, 0, 0, 0 };
		panelCenterPatientLogin.setLayout(gbl_panelCenterPatientLogin);

		JLabel lblFiscalCode = new JLabel("Codice Fiscale:");
		lblFiscalCode.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFiscalCode.setAlignmentX(1.0f);
		GridBagConstraints gbc_lblFiscalCode = new GridBagConstraints();
		gbc_lblFiscalCode.anchor = GridBagConstraints.EAST;
		gbc_lblFiscalCode.fill = GridBagConstraints.VERTICAL;
		gbc_lblFiscalCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiscalCode.gridx = 0;
		gbc_lblFiscalCode.gridy = 0;
		panelCenterPatientLogin.add(lblFiscalCode, gbc_lblFiscalCode);

		JFormattedTextField formattedTextFieldFiscalCode = new JFormattedTextField("MTBMHM93M51D251I");
		GridBagConstraints gbc_formattedTextFieldFiscalCode = new GridBagConstraints();
		gbc_formattedTextFieldFiscalCode.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldFiscalCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldFiscalCode.gridx = 1;
		gbc_formattedTextFieldFiscalCode.gridy = 0;
		panelCenterPatientLogin.add(formattedTextFieldFiscalCode, gbc_formattedTextFieldFiscalCode);

		JLabel lblPin = new JLabel("PIN:");
		lblPin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPin.setAlignmentX(1.0f);
		GridBagConstraints gbc_lblPin = new GridBagConstraints();
		gbc_lblPin.anchor = GridBagConstraints.EAST;
		gbc_lblPin.insets = new Insets(0, 0, 5, 5);
		gbc_lblPin.fill = GridBagConstraints.VERTICAL;
		gbc_lblPin.gridx = 0;
		gbc_lblPin.gridy = 1;
		panelCenterPatientLogin.add(lblPin, gbc_lblPin);

		JPasswordField passwordFieldPIN = new JPasswordField(16);
		GridBagConstraints gbc_passwordFieldPIN = new GridBagConstraints();
		gbc_passwordFieldPIN.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldPIN.fill = GridBagConstraints.BOTH;
		gbc_passwordFieldPIN.gridx = 1;
		gbc_passwordFieldPIN.gridy = 1;
		panelCenterPatientLogin.add(passwordFieldPIN, gbc_passwordFieldPIN);

		btnLoginPatient = new JButton("Login");
		GridBagConstraints gbc_btnLoginPatient = new GridBagConstraints();
		gbc_btnLoginPatient.gridx = 1;
		gbc_btnLoginPatient.gridy = 3;
		panelCenterPatientLogin.add(btnLoginPatient, gbc_btnLoginPatient);

		btnLoginPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fiscalCode = formattedTextFieldFiscalCode.getText();
				String PIN = new String(passwordFieldPIN.getPassword());

				// CHECK LOGIN

				ResultSet patientInfo = Database.getInstance().checkPatient(fiscalCode, PIN);
				if (patientInfo != null) {

					new Patient(patientInfo);
					dispose(); 																														// valutare un possibile set visible false
				} else
					JOptionPane.showMessageDialog(null, "Codice fiscale o PIN errati o mancanti", "Errore accesso",
							JOptionPane.WARNING_MESSAGE);
			}
		});

		JLabel lblBackGroundImageMainPage = new JLabel();
		lblBackGroundImageMainPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackGroundImageMainPage.setIcon(new ImageIcon(View.class.getResource("/img/healthcare-icon.png")));
		panelPatientLogin.add(lblBackGroundImageMainPage, BorderLayout.CENTER);

		// ----------------- FINE PATIENT ------------------------------------

		JPanel panelEmployeeLogin = new JPanel();
		tabbedPaneLogin.addTab("Area dipendente", null, panelEmployeeLogin, null);
		panelEmployeeLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterEmployeeLogin = new JPanel();
		panelCenterEmployeeLogin.setBorder(new EmptyBorder(0, 20, 0, 0));
		panelEmployeeLogin.add(panelCenterEmployeeLogin, BorderLayout.WEST);
		GridBagLayout gbl_panelCenterEmployeeLogin = new GridBagLayout();
		gbl_panelCenterEmployeeLogin.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_panelCenterEmployeeLogin.columnWidths = new int[] { 100, 0 };
		gbl_panelCenterEmployeeLogin.rowHeights = new int[] { 0, 0, 0, 0 };
		panelCenterEmployeeLogin.setLayout(gbl_panelCenterEmployeeLogin);

		JLabel lblUsername = new JLabel("Nome utente:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.BOTH;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		panelCenterEmployeeLogin.add(lblUsername, gbc_lblUsername);
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JTextField textField_username = new JTextField(16);
		textField_username.setText("JOBIT0001");
		GridBagConstraints gbc_textField_usr = new GridBagConstraints();
		gbc_textField_usr.fill = GridBagConstraints.BOTH;
		gbc_textField_usr.insets = new Insets(0, 0, 5, 0);
		gbc_textField_usr.gridx = 1;
		gbc_textField_usr.gridy = 0;
		panelCenterEmployeeLogin.add(textField_username, gbc_textField_usr);

		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		panelCenterEmployeeLogin.add(lblPassword, gbc_lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);

		textField_password = new JPasswordField(16);
		GridBagConstraints gbc_textField_passwd = new GridBagConstraints();
		gbc_textField_passwd.insets = new Insets(0, 0, 5, 0);
		gbc_textField_passwd.fill = GridBagConstraints.BOTH;
		gbc_textField_passwd.gridx = 1;
		gbc_textField_passwd.gridy = 1;
		textField_password.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK))
					lblMaiuscActive.setVisible(true);
				else
					lblMaiuscActive.setVisible(false);
			}
		});
		
		panelCenterEmployeeLogin.add(textField_password, gbc_textField_passwd);
		btnLoginEmployee = new JButton("Login");

		btnLoginEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = textField_username.getText();
				String password = new String(textField_password.getPassword());

				// CHECK LOGIN
				ResultSet employeeInfo = Database.getInstance().checkEmployee(username, password);
				
				if (employeeInfo != null) {

					new Employee(employeeInfo);
					dispose();																												 // valutare un possibile set visible false

				} else {
					JOptionPane.showMessageDialog(null, "Nome utente o password errati o mancanti", "Errore accesso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		lblMaiuscActive = new JLabel("MAIUSC ATTIVO");
		lblMaiuscActive.setForeground(UIManager.getColor("TextField.selectionBackground"));
		lblMaiuscActive.setVisible(false);
		GridBagConstraints gbc_lblMaiuscActive = new GridBagConstraints();
		gbc_lblMaiuscActive.fill = GridBagConstraints.VERTICAL;
		gbc_lblMaiuscActive.insets = new Insets(0, 0, 5, 0);
		gbc_lblMaiuscActive.gridx = 1;
		gbc_lblMaiuscActive.gridy = 2;
		panelCenterEmployeeLogin.add(lblMaiuscActive, gbc_lblMaiuscActive);

		GridBagConstraints gbc_btnLoginEmployee = new GridBagConstraints();
		gbc_btnLoginEmployee.gridx = 1;
		gbc_btnLoginEmployee.gridy = 3;
		panelCenterEmployeeLogin.add(btnLoginEmployee, gbc_btnLoginEmployee);

		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(View.class.getResource("/img/healthcare-icon.png")));
		panelEmployeeLogin.add(label, BorderLayout.CENTER);

		JPanel panelSouthLogin = new JPanel();
		panelLogin.add(panelSouthLogin, BorderLayout.SOUTH);

		// ------------------------- FINE LOGIN EMPLOYEE ----------------------------------

		JButton btnViewClinicAnd = new JButton("Ambulatori & Servizi");
		btnViewClinicAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				companiesList = db.getCompanies();

				for (String[] o : companiesList) {
					comboBoxSelectCompany.addItem(o[1]);
				}

				clLogin.next(getContentPane());
			}
		});
		panelSouthLogin.add(btnViewClinicAnd);

		JPanel panelClinicsAndServices = new JPanel();
		getContentPane().add(panelClinicsAndServices, "panelClinicsAndServices");
		panelClinicsAndServices.setLayout(new BorderLayout(0, 0));

		// sezione nord, combo box companies
		JPanel panelClinicsAndServicesNorth = new JPanel();
		FlowLayout fl_panelClinicsAndServicesNorth = (FlowLayout) panelClinicsAndServicesNorth.getLayout();
		fl_panelClinicsAndServicesNorth.setAlignment(FlowLayout.LEFT);
		panelClinicsAndServices.add(panelClinicsAndServicesNorth, BorderLayout.NORTH);

		JLabel lblSelectCompany = new JLabel("Seleziona azienda:");
		panelClinicsAndServicesNorth.add(lblSelectCompany);

		comboBoxSelectCompany = new JComboBox<String>();
		comboBoxSelectCompany.setPreferredSize(new Dimension(150, 27));
		listClinics = new JList<String>();

		comboBoxSelectCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int comboBoxIndex = comboBoxSelectCompany.getSelectedIndex();
				String companyID = companiesList.get(comboBoxIndex)[0];
				ArrayList<Clinic> clinics = db.getClinics(companyID);
				listClinics.setModel(new AbstractListModel<String>() {
					private static final long serialVersionUID = 1L;

					@Override
					public String getElementAt(int index) {
						return clinics.get(index).getName();
					}

					@Override
					public int getSize() {
						return clinics.size();
					}
				});
				listClinics.setSelectedIndex(0);
			}
		});
		panelClinicsAndServicesNorth.add(comboBoxSelectCompany);

		JSplitPane splitPaneClinics = new JSplitPane();
		panelClinicsAndServices.add(splitPaneClinics, BorderLayout.CENTER);
		splitPaneClinics.setResizeWeight(0.3);

		JPanel ClinicsPanel = new JPanel();
		splitPaneClinics.setLeftComponent(ClinicsPanel);
		ClinicsPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblClinics = new JLabel("Ambulatori");
		lblClinics.setHorizontalAlignment(SwingConstants.CENTER);
		ClinicsPanel.add(lblClinics, BorderLayout.NORTH);

		ClinicsPanel.add(listClinics, BorderLayout.CENTER);

		JPanel ServicesPanel = new JPanel();
		splitPaneClinics.setRightComponent(ServicesPanel);
		ServicesPanel.setLayout(new BorderLayout(0, 0));

		JTextPane txtClinic = new JTextPane();
		txtClinic.setContentType("text/html");

		listClinics.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int comboBoxIndex = comboBoxSelectCompany.getSelectedIndex();
				String companyID = companiesList.get(comboBoxIndex)[0]; 

				ArrayList<Clinic> clinics = db.getClinics(companyID);
				int selectedIndex = listClinics.getSelectedIndex();

				if (selectedIndex >= 0) { // quando cambio contenuto in combobox mi ritorna -1 e mi fa segfault in clinics.get
					txtClinic.setText(clinics.get(selectedIndex).getCompleteDescription(
							db.getClinicServices(companyID, clinics.get(selectedIndex).getName())));
					ServicesPanel.add(txtClinic, BorderLayout.CENTER);
				}
			}
		});

		JPanel panelClinicAndServicesButton = new JPanel();
		FlowLayout fl_panelClinicAndServicesButton = (FlowLayout) panelClinicAndServicesButton.getLayout();
		fl_panelClinicAndServicesButton.setAlignment(FlowLayout.RIGHT);
		panelClinicsAndServices.add(panelClinicAndServicesButton, BorderLayout.SOUTH);

		JButton btnBackToLogin = new JButton("Indietro");
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clLogin.previous(getContentPane());
			}
		});
		panelClinicAndServicesButton.add(btnBackToLogin);
		getRootPane().setDefaultButton(btnLoginPatient);

	}

}
