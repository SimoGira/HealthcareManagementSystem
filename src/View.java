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

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class View {

	public JFrame frmHealthcareManagementSystem;
	private JTextField textField_usr;
	private JTextField textField_passwd;
	private JTextField textFieldFiscalCode;
	private JPasswordField passwordFieldPIN;
	private JTable table;

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
					window.frmHealthcareManagementSystem.setVisible(true);
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
		frmHealthcareManagementSystem = new JFrame();
		frmHealthcareManagementSystem.setTitle("HEALTHCARE MANAGEMENT SYSTEM");
		frmHealthcareManagementSystem.setIconImage(
				Toolkit.getDefaultToolkit().getImage(View.class.getResource("/img/caduceus-symbol-128[1].png")));
		frmHealthcareManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHealthcareManagementSystem.setLocationRelativeTo(null);
		frmHealthcareManagementSystem.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel loginPanel = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(loginPanel, "name_11222847950945");
		loginPanel.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPaneLogin = new JTabbedPane(JTabbedPane.TOP);
		loginPanel.add(tabbedPaneLogin, BorderLayout.CENTER);

		JPanel patientLogin = new JPanel();
		tabbedPaneLogin.addTab("Patient", null, patientLogin, null);
		patientLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterPatientLogin = new JPanel();
		patientLogin.add(panelCenterPatientLogin, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenterPatientLogin = new GridBagLayout();
		gbl_panelCenterPatientLogin.columnWidths = new int[] { 0, 0 };
		gbl_panelCenterPatientLogin.rowHeights = new int[] { 0 };
		panelCenterPatientLogin.setLayout(gbl_panelCenterPatientLogin);

		JLabel lblFiscalCode = new JLabel("Fiscal Code:");
		lblFiscalCode.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFiscalCode.setAlignmentX(1.0f);
		GridBagConstraints gbc_lblFiscalCode = new GridBagConstraints();
		gbc_lblFiscalCode.anchor = GridBagConstraints.EAST;
		gbc_lblFiscalCode.fill = GridBagConstraints.VERTICAL;
		gbc_lblFiscalCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiscalCode.gridx = 0;
		gbc_lblFiscalCode.gridy = 0;
		panelCenterPatientLogin.add(lblFiscalCode, gbc_lblFiscalCode);

		textFieldFiscalCode = new JTextField(16);
		GridBagConstraints gbc_textFieldFiscalCode = new GridBagConstraints();
		gbc_textFieldFiscalCode.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFiscalCode.fill = GridBagConstraints.BOTH;
		gbc_textFieldFiscalCode.gridx = 1;
		gbc_textFieldFiscalCode.gridy = 0;
		panelCenterPatientLogin.add(textFieldFiscalCode, gbc_textFieldFiscalCode);

		JLabel lblPin = new JLabel("PIN:");
		lblPin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPin.setAlignmentX(1.0f);
		GridBagConstraints gbc_lblPin = new GridBagConstraints();
		gbc_lblPin.anchor = GridBagConstraints.EAST;
		gbc_lblPin.insets = new Insets(0, 0, 0, 5);
		gbc_lblPin.fill = GridBagConstraints.VERTICAL;
		gbc_lblPin.gridx = 0;
		gbc_lblPin.gridy = 1;
		panelCenterPatientLogin.add(lblPin, gbc_lblPin);

		passwordFieldPIN = new JPasswordField(16);
		GridBagConstraints gbc_passwordFieldPIN = new GridBagConstraints();
		gbc_passwordFieldPIN.fill = GridBagConstraints.BOTH;
		gbc_passwordFieldPIN.gridx = 1;
		gbc_passwordFieldPIN.gridy = 1;
		panelCenterPatientLogin.add(passwordFieldPIN, gbc_passwordFieldPIN);

		JPanel panelSouthPatientLogin = new JPanel();
		patientLogin.add(panelSouthPatientLogin, BorderLayout.SOUTH);
		panelSouthPatientLogin.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnLoginPatient = new JButton("Login");
		btnLoginPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelSouthPatientLogin.add(btnLoginPatient);

		JPanel employeeLogin = new JPanel();
		tabbedPaneLogin.addTab("Employee", null, employeeLogin, null);
		employeeLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterEmployeeLogin = new JPanel();
		employeeLogin.add(panelCenterEmployeeLogin, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenterEmployeeLogin = new GridBagLayout();
		gbl_panelCenterEmployeeLogin.columnWidths = new int[] { 0, 0 };
		gbl_panelCenterEmployeeLogin.rowHeights = new int[] { 0 };
		panelCenterEmployeeLogin.setLayout(gbl_panelCenterEmployeeLogin);

		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.BOTH;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		panelCenterEmployeeLogin.add(lblUsername, gbc_lblUsername);
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setAlignmentX(Component.RIGHT_ALIGNMENT);

		textField_usr = new JTextField(16);
		GridBagConstraints gbc_textField_usr = new GridBagConstraints();
		gbc_textField_usr.fill = GridBagConstraints.BOTH;
		gbc_textField_usr.insets = new Insets(0, 0, 5, 5);
		gbc_textField_usr.gridx = 1;
		gbc_textField_usr.gridy = 0;
		panelCenterEmployeeLogin.add(textField_usr, gbc_textField_usr);

		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		panelCenterEmployeeLogin.add(lblPassword, gbc_lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);

		textField_passwd = new JPasswordField(16);
		GridBagConstraints gbc_textField_passwd = new GridBagConstraints();
		gbc_textField_passwd.insets = new Insets(0, 0, 0, 5);
		gbc_textField_passwd.fill = GridBagConstraints.BOTH;
		gbc_textField_passwd.gridx = 1;
		gbc_textField_passwd.gridy = 1;
		panelCenterEmployeeLogin.add(textField_passwd, gbc_textField_passwd);

		JPanel panelSouthEmployeeLogin = new JPanel();
		employeeLogin.add(panelSouthEmployeeLogin, BorderLayout.SOUTH);
		JButton btnLoginEmployee = new JButton("Login");
		panelSouthEmployeeLogin.add(btnLoginEmployee);

		JPanel panelSouthLogin = new JPanel();
		loginPanel.add(panelSouthLogin, BorderLayout.SOUTH);

		JButton btnViewClinicAnd = new JButton("View clinics and services");
		panelSouthLogin.add(btnViewClinicAnd);

		JPanel patientPanel = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(patientPanel, "name_11222960382586");
		patientPanel.setLayout(new BorderLayout(0, 0));

		JPanel panelNorthPatient = new JPanel();
		patientPanel.add(panelNorthPatient, BorderLayout.NORTH);

		JLabel lblWelcomePatient = new JLabel("Benvenuto:<nome utente>");
		lblWelcomePatient.setFont(new Font("Tahoma", Font.BOLD, 22));
		panelNorthPatient.add(lblWelcomePatient);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		patientPanel.add(tabbedPane, BorderLayout.CENTER);

		JPanel visitPatientPanel = new JPanel();
		tabbedPane.addTab("Storico prenotazioni", null, visitPatientPanel, null);
		visitPatientPanel.setLayout(new CardLayout(0, 0));

		JPanel HistoryVisitPanel = new JPanel();
		visitPatientPanel.add(HistoryVisitPanel, "name_230301853210624");
		HistoryVisitPanel.setLayout(new BorderLayout(0, 0));

		JPanel historyNorthPanel = new JPanel();
		HistoryVisitPanel.add(historyNorthPanel, BorderLayout.NORTH);

		JLabel lblSelectYearPatientVisit = new JLabel("Seleziona anno:");
		historyNorthPanel.add(lblSelectYearPatientVisit);

		JComboBox<String> comboBoxVisitsYear = new JComboBox<String>();
		comboBoxVisitsYear.setModel(
				new DefaultComboBoxModel<String>(new String[] { "<anno_1>", "<anno_2>", "<anno_3>", "<anno_4>" }));
		historyNorthPanel.add(comboBoxVisitsYear);

		JScrollPane scrollPane = new JScrollPane();
		HistoryVisitPanel.add(scrollPane, BorderLayout.CENTER);

		// questa tabella si puo' implementare in maniera piu' pulita una volta completati i metodi con il db, vedi tests fatto su w10 (simone)
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{"13/07/2017", "Oculustica", "privata", "Media"},
					{"20/08/2017", "Cardiaca", "privata", "Alta"},
				},
				new String[] {
						"Data", "Tipo Visita", "Regime", "Urgenza"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);

		JPanel historyButtonsPanel = new JPanel();
		FlowLayout fl_historyButtonsPanel = (FlowLayout) historyButtonsPanel.getLayout();
		fl_historyButtonsPanel.setAlignment(FlowLayout.RIGHT);
		HistoryVisitPanel.add(historyButtonsPanel, BorderLayout.SOUTH);

		JButton btnViewSelectedVisit = new JButton("Visualizza");
		historyButtonsPanel.add(btnViewSelectedVisit);

		JPanel resultVisitpanel = new JPanel();
		visitPatientPanel.add(resultVisitpanel, "name_230217349041945");
		resultVisitpanel.setLayout(new BorderLayout(0, 0));

		JTextArea resultVisitTextArea = new JTextArea();
		resultVisitpanel.add(resultVisitTextArea, BorderLayout.CENTER);

		JPanel resultVisitSouthpanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) resultVisitSouthpanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		resultVisitpanel.add(resultVisitSouthpanel, BorderLayout.SOUTH);

		JButton btnBeckToHistory = new JButton("Indietro");
		resultVisitSouthpanel.add(btnBeckToHistory);

		JButton btnPrintVisitResult = new JButton("Stampa");
		btnPrintVisitResult.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPrintVisitResult.setAlignmentX(Component.CENTER_ALIGNMENT);
		resultVisitSouthpanel.add(btnPrintVisitResult);

		JPanel bookVisitPanel = new JPanel();
		tabbedPane.addTab("Prenotazione visite", null, bookVisitPanel, null);
		bookVisitPanel.setLayout(new BorderLayout(0, 0));

		JPanel employeePanel = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(employeePanel, "name_11222990618502");

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.3);
		frmHealthcareManagementSystem.getContentPane().add(splitPane, "name_18581128151280");

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
			String[] values = new String[] { "Clinic_1", "Clinic_2", "Clinic_3" };

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

		frmHealthcareManagementSystem.setSize(500, 400);
		// frame.pack();
		// frame.setResizable(false);
	}
}
