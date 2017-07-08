import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class View {

	public JFrame frmHealthcareManagementSystem;
	private JTextField textField_usr;
	private JTextField textField_passwd;
	private JTextField textFieldFiscalCode;
	private JPasswordField passwordFieldPIN;
	private JTable tableHistoryVisits;
	private JTable tableVisitsFounded;
	private JTextField textFieldInsertClinicRue;
	private JTextField textFieldInsertClinicCity;
	private JTextField textFieldInsertClinicCAP;
	private JTextField textFieldInsertClinicName;

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

		JPanel panelLogin = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(panelLogin, "name_11222847950945");
		panelLogin.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPaneLogin = new JTabbedPane(JTabbedPane.TOP);
		panelLogin.add(tabbedPaneLogin, BorderLayout.CENTER);

		JPanel panelPatientLogin = new JPanel();
		tabbedPaneLogin.addTab("Patient", null, panelPatientLogin, null);
		panelPatientLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterPatientLogin = new JPanel();
		panelPatientLogin.add(panelCenterPatientLogin, BorderLayout.CENTER);
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
		panelPatientLogin.add(panelSouthPatientLogin, BorderLayout.SOUTH);
		panelSouthPatientLogin.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnLoginPatient = new JButton("Login");
		btnLoginPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelSouthPatientLogin.add(btnLoginPatient);

		JPanel panelEmployeeLogin = new JPanel();
		tabbedPaneLogin.addTab("Employee", null, panelEmployeeLogin, null);
		panelEmployeeLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterEmployeeLogin = new JPanel();
		panelEmployeeLogin.add(panelCenterEmployeeLogin, BorderLayout.CENTER);
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
		panelEmployeeLogin.add(panelSouthEmployeeLogin, BorderLayout.SOUTH);
		JButton btnLoginEmployee = new JButton("Login");
		panelSouthEmployeeLogin.add(btnLoginEmployee);

		JPanel panelSouthLogin = new JPanel();
		panelLogin.add(panelSouthLogin, BorderLayout.SOUTH);

		JButton btnViewClinicAnd = new JButton("View clinics and services");
		panelSouthLogin.add(btnViewClinicAnd);

		JPanel panelPatient = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(panelPatient, "name_11222960382586");
		panelPatient.setLayout(new BorderLayout(0, 0));

		JPanel panelNorthPatient = new JPanel();
		panelPatient.add(panelNorthPatient, BorderLayout.NORTH);

		JLabel lblWelcomePatient = new JLabel("Benvenuto:<nome paziente>");
		lblWelcomePatient.setFont(new Font("Tahoma", Font.BOLD, 22));
		panelNorthPatient.add(lblWelcomePatient);

		JTabbedPane tabbedPanePatient = new JTabbedPane(JTabbedPane.TOP);
		panelPatient.add(tabbedPanePatient, BorderLayout.CENTER);

		JPanel panelVisitPatient = new JPanel();
		tabbedPanePatient.addTab("Storico prenotazioni", null, panelVisitPatient, null);
		panelVisitPatient.setLayout(new CardLayout(0, 0));

		JPanel panelHistoryVisit = new JPanel();
		panelVisitPatient.add(panelHistoryVisit, "name_230301853210624");
		panelHistoryVisit.setLayout(new BorderLayout(0, 0));

		JPanel panelHistoryNorth = new JPanel();
		FlowLayout fl_panelHistoryNorth = (FlowLayout) panelHistoryNorth.getLayout();
		fl_panelHistoryNorth.setAlignment(FlowLayout.LEFT);
		panelHistoryVisit.add(panelHistoryNorth, BorderLayout.NORTH);

		JLabel lblSelectYearPatientVisit = new JLabel("Seleziona anno:");
		panelHistoryNorth.add(lblSelectYearPatientVisit);

		JComboBox<String> comboBoxVisitsYear = new JComboBox<String>();
		comboBoxVisitsYear.setModel(
				new DefaultComboBoxModel<String>(new String[] { "<anno_1>", "<anno_2>", "<anno_3>", "<anno_4>" }));
		panelHistoryNorth.add(comboBoxVisitsYear);

		JScrollPane scrollPaneHistory = new JScrollPane();
		panelHistoryVisit.add(scrollPaneHistory, BorderLayout.CENTER);

		// questa tabella si puo' implementare in maniera piu' pulita una volta
		// completati i metodi con il db, vedi tests fatto su w10 (simone)
		tableHistoryVisits = new JTable();
		tableHistoryVisits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableHistoryVisits.setModel(new DefaultTableModel(
				new Object[][] { { "13/07/2017", null, "Oculustica", "privata", "Media" },
					{ "20/08/2017", null, "Cardiaca", "privata", "Alta" }, },
				new String[] { "Data", "Ora", "Tipo Visita", "Regime", "Urgenza" }) {
			boolean[] columnEditables = new boolean[] { false, true, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableHistoryVisits.getColumnModel().getColumn(0).setResizable(false);
		tableHistoryVisits.getColumnModel().getColumn(2).setResizable(false);
		tableHistoryVisits.getColumnModel().getColumn(3).setResizable(false);
		tableHistoryVisits.getColumnModel().getColumn(4).setResizable(false);
		scrollPaneHistory.setViewportView(tableHistoryVisits);

		JPanel panelHistoryButtons = new JPanel();
		FlowLayout fl_panelHistoryButtons = (FlowLayout) panelHistoryButtons.getLayout();
		fl_panelHistoryButtons.setAlignment(FlowLayout.RIGHT);
		panelHistoryVisit.add(panelHistoryButtons, BorderLayout.SOUTH);

		JButton btnViewSelectedVisit = new JButton("Visualizza");
		panelHistoryButtons.add(btnViewSelectedVisit);

		JPanel panelVisitResult = new JPanel();
		panelVisitPatient.add(panelVisitResult, "name_230217349041945");
		panelVisitResult.setLayout(new BorderLayout(0, 0));

		JTextArea resultVisitTextArea = new JTextArea();
		panelVisitResult.add(resultVisitTextArea, BorderLayout.CENTER);

		JPanel resultVisitSouthpanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) resultVisitSouthpanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelVisitResult.add(resultVisitSouthpanel, BorderLayout.SOUTH);

		JButton btnBeckToHistory = new JButton("Indietro");
		resultVisitSouthpanel.add(btnBeckToHistory);

		JButton btnPrintVisitResult = new JButton("Stampa");
		btnPrintVisitResult.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPrintVisitResult.setAlignmentX(Component.CENTER_ALIGNMENT);
		resultVisitSouthpanel.add(btnPrintVisitResult);

		JPanel panelBookVisit = new JPanel();
		tabbedPanePatient.addTab("Prenotazione visite", null, panelBookVisit, null);
		panelBookVisit.setLayout(new BorderLayout(0, 0));

		JPanel bookVisitNorthPanel = new JPanel();
		panelBookVisit.add(bookVisitNorthPanel, BorderLayout.NORTH);

		JLabel lblBookVisitAnno = new JLabel("Anno:");
		bookVisitNorthPanel.add(lblBookVisitAnno);

		JComboBox comboBoxBookVisitYear = new JComboBox();
		bookVisitNorthPanel.add(comboBoxBookVisitYear);

		JLabel lblBookVisitMonth = new JLabel("Mese:");
		bookVisitNorthPanel.add(lblBookVisitMonth);

		JComboBox comboBoxBookVisitMonth = new JComboBox();
		bookVisitNorthPanel.add(comboBoxBookVisitMonth);

		JLabel lblBookVisitDay = new JLabel("Giorno:");
		bookVisitNorthPanel.add(lblBookVisitDay);

		JComboBox comboBoxBookVisitDay = new JComboBox();
		bookVisitNorthPanel.add(comboBoxBookVisitDay);

		JLabel lblBookVisitHour = new JLabel("Ora:");
		bookVisitNorthPanel.add(lblBookVisitHour);

		JComboBox comboBoxBookVisitHour = new JComboBox();
		bookVisitNorthPanel.add(comboBoxBookVisitHour);

		JPanel panelEmployee = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(panelEmployee, "name_11222990618502");
		panelEmployee.setLayout(new BorderLayout(0, 0));

		JPanel panelNorthEmployee = new JPanel();
		panelEmployee.add(panelNorthEmployee, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Benvenuto: <nome dipendente>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		panelNorthEmployee.add(lblNewLabel);

		JTabbedPane tabbedPaneEmployee = new JTabbedPane(JTabbedPane.TOP);
		panelEmployee.add(tabbedPaneEmployee, BorderLayout.CENTER);

		JPanel panelInsertVisitInfo = new JPanel();
		tabbedPaneEmployee.addTab("Inserisci risultato", null, panelInsertVisitInfo, null);
		panelInsertVisitInfo.setLayout(new BorderLayout(0, 0));

		JPanel panelInfoVisitInsertion = new JPanel();
		panelInsertVisitInfo.add(panelInfoVisitInsertion, BorderLayout.CENTER);
		panelInfoVisitInsertion.setLayout(new BorderLayout(0, 0));

		JPanel panelInfovisitInsertionNorth = new JPanel();
		FlowLayout fl_panelInfovisitInsertionNorth = (FlowLayout) panelInfovisitInsertionNorth.getLayout();
		fl_panelInfovisitInsertionNorth.setAlignment(FlowLayout.LEFT);
		panelInfoVisitInsertion.add(panelInfovisitInsertionNorth, BorderLayout.NORTH);

		JLabel lblDateVisitInsertion = new JLabel("Data:");
		panelInfovisitInsertionNorth.add(lblDateVisitInsertion);

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		JFormattedTextField formattedTextFieldVisitInsertion = new JFormattedTextField(sdf);
		formattedTextFieldVisitInsertion.setToolTipText("Inserisci la data della visita nel formato gg/mm/aaaa");
		formattedTextFieldVisitInsertion.setColumns(10);
		panelInfovisitInsertionNorth.add(formattedTextFieldVisitInsertion);

		JLabel lblClinicVisitInsertion = new JLabel("Ambulatorio:");
		panelInfovisitInsertionNorth.add(lblClinicVisitInsertion);

		JComboBox comboBoxClinicVisitInsertion = new JComboBox();
		panelInfovisitInsertionNorth.add(comboBoxClinicVisitInsertion);

		JButton btnFindVisits = new JButton("Trova");
		panelInfovisitInsertionNorth.add(btnFindVisits);

		JScrollPane scrollPaneInfoVisitInsertionCenter = new JScrollPane();
		panelInfoVisitInsertion.add(scrollPaneInfoVisitInsertionCenter, BorderLayout.CENTER);

		tableVisitsFounded = new JTable();
		tableVisitsFounded.setModel(
				new DefaultTableModel(new Object[][] { { "Adriano", "Tumminelli", "oculistica", "bassa", "10:00" }, },
						new String[] { "Nome", "Cognome", "Tipo visita", "Urgenza", "Ora" }) {
					Class[] columnTypes = new Class[] { Object.class, Object.class, String.class, Object.class,
							Object.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		scrollPaneInfoVisitInsertionCenter.setViewportView(tableVisitsFounded);

		JScrollPane scrollPaneInfoVisitInsertionSouth = new JScrollPane();
		scrollPaneInfoVisitInsertionSouth.setViewportBorder(
				new TitledBorder(null, "Risultato visita", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfoVisitInsertion.add(scrollPaneInfoVisitInsertionSouth, BorderLayout.SOUTH);

		JTextArea textAreaVisitResult = new JTextArea();
		textAreaVisitResult.setRows(3);
		scrollPaneInfoVisitInsertionSouth.setViewportView(textAreaVisitResult);

		JPanel panelInsertVisitButton = new JPanel();
		FlowLayout fl_panelInsertVisitButton = (FlowLayout) panelInsertVisitButton.getLayout();
		fl_panelInsertVisitButton.setAlignment(FlowLayout.RIGHT);
		panelInsertVisitInfo.add(panelInsertVisitButton, BorderLayout.SOUTH);

		JButton btnInsertNewVisit = new JButton("Inserisci");
		btnInsertNewVisit.setEnabled(false);
		panelInsertVisitButton.add(btnInsertNewVisit);

		JPanel panelInsertClinic = new JPanel();
		tabbedPaneEmployee.addTab("Inserisci ambulatorio", null, panelInsertClinic, null);
		panelInsertClinic.setLayout(new BorderLayout(0, 0));

		JPanel panelInsertClinicData = new JPanel();
		panelInsertClinicData.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dati",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelInsertClinic.add(panelInsertClinicData, BorderLayout.WEST);
		GridBagLayout gbl_panelInsertClinicData = new GridBagLayout();
		gbl_panelInsertClinicData.columnWidths = new int[]{150, 0};
		gbl_panelInsertClinicData.rowHeights = new int[]{20, 14, 20, 14, 20, 14, 20, 14, 20, 14, 20, 14, 20, 0};
		gbl_panelInsertClinicData.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelInsertClinicData.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInsertClinicData.setLayout(gbl_panelInsertClinicData);

		JLabel lblInsertClinicName = new JLabel("Nome:");
		GridBagConstraints gbc_lblInsertClinicName = new GridBagConstraints();
		gbc_lblInsertClinicName.anchor = GridBagConstraints.WEST;
		gbc_lblInsertClinicName.insets = new Insets(0, 0, 5, 0);
		gbc_lblInsertClinicName.gridx = 0;
		gbc_lblInsertClinicName.gridy = 0;
		panelInsertClinicData.add(lblInsertClinicName, gbc_lblInsertClinicName);

		textFieldInsertClinicName = new JTextField();
		GridBagConstraints gbc_textFieldInsertClinicName = new GridBagConstraints();
		gbc_textFieldInsertClinicName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertClinicName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertClinicName.gridx = 0;
		gbc_textFieldInsertClinicName.gridy = 1;
		panelInsertClinicData.add(textFieldInsertClinicName, gbc_textFieldInsertClinicName);
		textFieldInsertClinicName.setColumns(10);

		JLabel lblInsertClinicRue = new JLabel("Via:");
		GridBagConstraints gbc_lblInsertClinicRue = new GridBagConstraints();
		gbc_lblInsertClinicRue.anchor = GridBagConstraints.WEST;
		gbc_lblInsertClinicRue.insets = new Insets(0, 0, 5, 0);
		gbc_lblInsertClinicRue.gridx = 0;
		gbc_lblInsertClinicRue.gridy = 2;
		panelInsertClinicData.add(lblInsertClinicRue, gbc_lblInsertClinicRue);

		textFieldInsertClinicRue = new JTextField();
		GridBagConstraints gbc_textFieldInsertClinicRue = new GridBagConstraints();
		gbc_textFieldInsertClinicRue.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertClinicRue.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertClinicRue.gridx = 0;
		gbc_textFieldInsertClinicRue.gridy = 3;
		panelInsertClinicData.add(textFieldInsertClinicRue, gbc_textFieldInsertClinicRue);
		textFieldInsertClinicRue.setColumns(10);

		JLabel lblInsertClinicCity = new JLabel("Paese:");
		GridBagConstraints gbc_lblInsertClinicCity = new GridBagConstraints();
		gbc_lblInsertClinicCity.anchor = GridBagConstraints.WEST;
		gbc_lblInsertClinicCity.insets = new Insets(0, 0, 5, 0);
		gbc_lblInsertClinicCity.gridx = 0;
		gbc_lblInsertClinicCity.gridy = 4;
		panelInsertClinicData.add(lblInsertClinicCity, gbc_lblInsertClinicCity);

		textFieldInsertClinicCity = new JTextField();
		GridBagConstraints gbc_textFieldInsertClinicCity = new GridBagConstraints();
		gbc_textFieldInsertClinicCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertClinicCity.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertClinicCity.gridx = 0;
		gbc_textFieldInsertClinicCity.gridy = 5;
		panelInsertClinicData.add(textFieldInsertClinicCity, gbc_textFieldInsertClinicCity);
		textFieldInsertClinicCity.setColumns(10);

		JLabel lblInsertClinicCAP = new JLabel("CAP:");
		GridBagConstraints gbc_lblInsertClinicCAP = new GridBagConstraints();
		gbc_lblInsertClinicCAP.anchor = GridBagConstraints.WEST;
		gbc_lblInsertClinicCAP.insets = new Insets(0, 0, 5, 0);
		gbc_lblInsertClinicCAP.gridx = 0;
		gbc_lblInsertClinicCAP.gridy = 6;
		panelInsertClinicData.add(lblInsertClinicCAP, gbc_lblInsertClinicCAP);

		textFieldInsertClinicCAP = new JTextField();
		GridBagConstraints gbc_textFieldInsertClinicCAP = new GridBagConstraints();
		gbc_textFieldInsertClinicCAP.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertClinicCAP.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertClinicCAP.gridx = 0;
		gbc_textFieldInsertClinicCAP.gridy = 7;
		panelInsertClinicData.add(textFieldInsertClinicCAP, gbc_textFieldInsertClinicCAP);
		textFieldInsertClinicCAP.setColumns(10);

		JLabel lblInsertClinicProvince = new JLabel("Provincia:");
		GridBagConstraints gbc_lblInsertClinicProvince = new GridBagConstraints();
		gbc_lblInsertClinicProvince.anchor = GridBagConstraints.WEST;
		gbc_lblInsertClinicProvince.insets = new Insets(0, 0, 5, 0);
		gbc_lblInsertClinicProvince.gridx = 0;
		gbc_lblInsertClinicProvince.gridy = 8;
		panelInsertClinicData.add(lblInsertClinicProvince, gbc_lblInsertClinicProvince);

		JComboBox comboBoxInsertClinicProvince = new JComboBox();
		GridBagConstraints gbc_comboBoxInsertClinicProvince = new GridBagConstraints();
		gbc_comboBoxInsertClinicProvince.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInsertClinicProvince.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxInsertClinicProvince.gridx = 0;
		gbc_comboBoxInsertClinicProvince.gridy = 9;
		panelInsertClinicData.add(comboBoxInsertClinicProvince, gbc_comboBoxInsertClinicProvince);

		JLabel lblInsertClinicContractDate = new JLabel("Data Contratto:");
		GridBagConstraints gbc_lblInsertClinicContractDate = new GridBagConstraints();
		gbc_lblInsertClinicContractDate.anchor = GridBagConstraints.WEST;
		gbc_lblInsertClinicContractDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblInsertClinicContractDate.gridx = 0;
		gbc_lblInsertClinicContractDate.gridy = 10;
		panelInsertClinicData.add(lblInsertClinicContractDate, gbc_lblInsertClinicContractDate);

		JFormattedTextField formattedTextFieldInsertClinicContractDate = new JFormattedTextField(sdf);
		GridBagConstraints gbc_formattedTextFieldInsertClinicContractDate = new GridBagConstraints();
		gbc_formattedTextFieldInsertClinicContractDate.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextFieldInsertClinicContractDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldInsertClinicContractDate.gridx = 0;
		gbc_formattedTextFieldInsertClinicContractDate.gridy = 11;
		panelInsertClinicData.add(formattedTextFieldInsertClinicContractDate, gbc_formattedTextFieldInsertClinicContractDate);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Descrizione", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInsertClinic.add(scrollPane, BorderLayout.CENTER);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JPanel panelInsertClinicButton = new JPanel();
		FlowLayout fl_panelInsertClinicButton = (FlowLayout) panelInsertClinicButton.getLayout();
		fl_panelInsertClinicButton.setAlignment(FlowLayout.RIGHT);
		panelInsertClinic.add(panelInsertClinicButton, BorderLayout.SOUTH);

		JButton btnInsertClinic = new JButton("Inserisci");
		btnInsertClinic.setEnabled(false);
		panelInsertClinicButton.add(btnInsertClinic);

		JPanel panelVisitsResultsPerPatient = new JPanel();
		tabbedPaneEmployee.addTab("Visualizza visite", null, panelVisitsResultsPerPatient, null);

		JSplitPane splitPaneClinics = new JSplitPane();
		splitPaneClinics.setResizeWeight(0.3);
		frmHealthcareManagementSystem.getContentPane().add(splitPaneClinics, "name_18581128151280");

		JPanel ClinicsPanel = new JPanel();
		splitPaneClinics.setLeftComponent(ClinicsPanel);
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
		splitPaneClinics.setRightComponent(ServicesPanel);
		ServicesPanel.setLayout(new BorderLayout(0, 0));

		JTextPane txtpnclinicsOfJesus = new JTextPane();
		txtpnclinicsOfJesus.setContentType("text/html");
		txtpnclinicsOfJesus.setText(
				"<html>\r\n\t<head></head>\r\n\t<body>\r\n\t\t<h1>Clinics of Jesus</h1>\r\n\t\t<h3>Services</h3>\r\n\t\t.....Prova html.....\r\n\t</body>\r\n</html>\r\n");
		ServicesPanel.add(txtpnclinicsOfJesus, BorderLayout.CENTER);

		frmHealthcareManagementSystem.setSize(600, 500);
		// frame.pack();
		// frame.setResizable(false);
	}
}
