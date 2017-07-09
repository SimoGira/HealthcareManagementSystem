import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class View {

	public JFrame frmHealthcareManagementSystem;
	private JTextField textField_usr;
	private JPasswordField textField_passwd;
	private JPasswordField passwordFieldPIN;
	private JTable tableHistoryVisits;
	private JTable tableVisitsFounded;
	private JTextField textFieldInsertClinicRue;
	private JTextField textFieldInsertClinicCity;
	private JTextField textFieldInsertClinicZipCode;
	private JTextField textFieldInsertClinicName;
	private JTable tableClinics;
	private JTable tableVisitsPatientResults;
	private CardLayout clfrmHealhcareManagementSystem;
	private Database db;
	private JLabel lblWeolcomePatient;


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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public View() throws ClassNotFoundException, SQLException {
		this.db = new Database();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHealthcareManagementSystem = new JFrame("HEALTHCARE MANAGEMENT SYSTEM");
		frmHealthcareManagementSystem.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/img/caduceus.png")));
		frmHealthcareManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHealthcareManagementSystem.getContentPane().setLayout(new CardLayout(0, 0));
		this.clfrmHealhcareManagementSystem = (CardLayout) frmHealthcareManagementSystem.getContentPane().getLayout();

		JPanel panelLogin = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(panelLogin, "panelLogin");
		panelLogin.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPaneLogin = new JTabbedPane(JTabbedPane.TOP);
		panelLogin.add(tabbedPaneLogin, BorderLayout.CENTER);

		JPanel panelPatientLogin = new JPanel();
		tabbedPaneLogin.addTab("Area paziente", null, panelPatientLogin, null);
		panelPatientLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterPatientLogin = new JPanel();
		panelCenterPatientLogin.setBorder(new EmptyBorder(0, 20, 0, 0));
		panelPatientLogin.add(panelCenterPatientLogin, BorderLayout.WEST);
		GridBagLayout gbl_panelCenterPatientLogin = new GridBagLayout();
		gbl_panelCenterPatientLogin.columnWeights = new double[]{0.0, 1.0};
		gbl_panelCenterPatientLogin.columnWidths = new int[] { 0, 0 };
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

		JFormattedTextField formattedTextFieldFiscalCode = new JFormattedTextField("MTBMHM93M51D251I");    // DA FORMATTARE CON SOLO CARATTERI ALFA NUMERICI E MAX LENGHT 16
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

		passwordFieldPIN = new JPasswordField(16);
		GridBagConstraints gbc_passwordFieldPIN = new GridBagConstraints();
		gbc_passwordFieldPIN.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldPIN.fill = GridBagConstraints.BOTH;
		gbc_passwordFieldPIN.gridx = 1;
		gbc_passwordFieldPIN.gridy = 1;
		panelCenterPatientLogin.add(passwordFieldPIN, gbc_passwordFieldPIN);

		JButton btnLoginPatient = new JButton("Login");
		GridBagConstraints gbc_btnLoginPatient = new GridBagConstraints();
		gbc_btnLoginPatient.gridx = 1;
		gbc_btnLoginPatient.gridy = 3;
		panelCenterPatientLogin.add(btnLoginPatient, gbc_btnLoginPatient);

		btnLoginPatient.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				String username = formattedTextFieldFiscalCode.getText();
				String passwd = new String(passwordFieldPIN.getPassword());
				if(db.checkPatient(username, passwd)){
					lblWeolcomePatient.setText(lblWeolcomePatient.getText() + " " + Patient.getInstance().getName() + " " + Patient.getInstance().getSurname()); 
					clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(), "panelPatient");
				}
				else{
					System.out.println("credenziali errate: " + username + " " + passwd );
				}
			}
		});

		JLabel lblBackGroundImageMainPage = new JLabel();
		lblBackGroundImageMainPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackGroundImageMainPage.setIcon(new ImageIcon(View.class.getResource("/img/healthcare-icon.png")));
		panelPatientLogin.add(lblBackGroundImageMainPage, BorderLayout.CENTER);

		JPanel panelEmployeeLogin = new JPanel();
		tabbedPaneLogin.addTab("Area dipendente", null, panelEmployeeLogin, null);
		panelEmployeeLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterEmployeeLogin = new JPanel();
		panelCenterEmployeeLogin.setBorder(new EmptyBorder(0, 20, 0, 0));
		panelEmployeeLogin.add(panelCenterEmployeeLogin, BorderLayout.WEST);
		GridBagLayout gbl_panelCenterEmployeeLogin = new GridBagLayout();
		gbl_panelCenterEmployeeLogin.columnWidths = new int[] { 0, 0 };
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

		textField_usr = new JTextField(16);
		GridBagConstraints gbc_textField_usr = new GridBagConstraints();
		gbc_textField_usr.fill = GridBagConstraints.BOTH;
		gbc_textField_usr.insets = new Insets(0, 0, 5, 0);
		gbc_textField_usr.gridx = 1;
		gbc_textField_usr.gridy = 0;
		panelCenterEmployeeLogin.add(textField_usr, gbc_textField_usr);

		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.BOTH;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		panelCenterEmployeeLogin.add(lblPassword, gbc_lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);

		textField_passwd = new JPasswordField(16);
		GridBagConstraints gbc_textField_passwd = new GridBagConstraints();
		gbc_textField_passwd.insets = new Insets(0, 0, 5, 0);
		gbc_textField_passwd.fill = GridBagConstraints.BOTH;
		gbc_textField_passwd.gridx = 1;
		gbc_textField_passwd.gridy = 1;
		panelCenterEmployeeLogin.add(textField_passwd, gbc_textField_passwd);
		JButton btnLoginEmployee = new JButton("Login");
		btnLoginEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String user = textField_usr.getText();
				String pass = new String(textField_passwd.getPassword());
				if(db.checkEmployee(user, pass)){ 
					System.out.println("ok");
					clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(), "panelEmployee");
				}
				else{
					System.out.println("wrong credentials: " + user + " " + pass );
				}
			}
		});
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

		JButton btnViewClinicAnd = new JButton("Ambulatori & Servizi");
		btnViewClinicAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(), "panelClinicsAndServices");
			}
		});
		panelSouthLogin.add(btnViewClinicAnd);

		JPanel panelPatient = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(panelPatient, "panelPatient");
		panelPatient.setLayout(new BorderLayout(0, 0));

		JPanel panelPatientNorth = new JPanel();
		panelPatient.add(panelPatientNorth, BorderLayout.NORTH);
		panelPatientNorth.setLayout(new BorderLayout(0, 0));

		lblWeolcomePatient = new JLabel("Benvenuto ");
		lblWeolcomePatient.setHorizontalAlignment(SwingConstants.CENTER);
		panelPatientNorth.add(lblWeolcomePatient, BorderLayout.CENTER);
		lblWeolcomePatient.setFont(new Font("Tahoma", Font.BOLD, 22));

		JPanel panelPatientNorthRightLabels = new JPanel();
		FlowLayout fl_panelPatientNorthRightLabels = (FlowLayout) panelPatientNorthRightLabels.getLayout();
		fl_panelPatientNorthRightLabels.setHgap(10);
		fl_panelPatientNorthRightLabels.setVgap(10);
		panelPatientNorth.add(panelPatientNorthRightLabels, BorderLayout.EAST);

		JLabel lblLogoutPatient = new JLabel("<HTML>\r\n\t<p style=\"color:blue;\"><u>Logout</u></p>\r\n</HTML>");
		panelPatientNorthRightLabels.add(lblLogoutPatient);
		lblLogoutPatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLogoutPatient.setHorizontalAlignment(SwingConstants.CENTER);


		JTabbedPane tabbedPanePatient = new JTabbedPane(JTabbedPane.TOP);
		panelPatient.add(tabbedPanePatient, BorderLayout.CENTER);

		JPanel panelVisitPatient = new JPanel();
		tabbedPanePatient.addTab("Storico prenotazioni", null, panelVisitPatient, null);
		panelVisitPatient.setLayout(new CardLayout(0, 0));

		JPanel panelHistoryVisitPatient = new JPanel();
		panelVisitPatient.add(panelHistoryVisitPatient, "panelHistoryVisitPatient");
		panelHistoryVisitPatient.setLayout(new BorderLayout(0, 0));

		JPanel panelHistoryNorth = new JPanel();
		FlowLayout fl_panelHistoryNorth = (FlowLayout) panelHistoryNorth.getLayout();
		fl_panelHistoryNorth.setAlignment(FlowLayout.LEFT);
		panelHistoryVisitPatient.add(panelHistoryNorth, BorderLayout.NORTH);

		JLabel lblSelectYearPatientVisit = new JLabel("Seleziona anno:");
		panelHistoryNorth.add(lblSelectYearPatientVisit);

		JComboBox<String> comboBoxVisitsYear = new JComboBox<String>();
		comboBoxVisitsYear.setModel(
				new DefaultComboBoxModel<String>(new String[] { "<anno_1>", "<anno_2>", "<anno_3>", "<anno_4>" }));
		panelHistoryNorth.add(comboBoxVisitsYear);

		JScrollPane scrollPaneHistory = new JScrollPane();
		panelHistoryVisitPatient.add(scrollPaneHistory, BorderLayout.CENTER);

		// questa tabella si puo' implementare in maniera piu' pulita una volta
		// completati i metodi con il db, vedi tests fatto su w10 (simone)
		tableHistoryVisits = new JTable();
		tableHistoryVisits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableHistoryVisits.setModel(new DefaultTableModel(
				new Object[][] { { "13/07/2017", "10:00", "Oculustica", "privata", "Media" },
					{ "20/08/2017", "11:00", "Cardiaca", "privata", "Alta" }, },
				new String[] { "Data", "Ora", "Tipo Visita", "Regime", "Urgenza" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

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
		panelHistoryVisitPatient.add(panelHistoryButtons, BorderLayout.SOUTH);

		JButton btnViewSelectedVisit = new JButton("Visualizza");
		panelHistoryButtons.add(btnViewSelectedVisit);

		JPanel panelVisitResultPatient = new JPanel();
		panelVisitPatient.add(panelVisitResultPatient, "panelVisitResultPatient");
		panelVisitResultPatient.setLayout(new BorderLayout(0, 0));

		JTextArea resultVisitTextArea = new JTextArea();
		panelVisitResultPatient.add(resultVisitTextArea, BorderLayout.CENTER);

		JPanel resultVisitSouthpanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) resultVisitSouthpanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelVisitResultPatient.add(resultVisitSouthpanel, BorderLayout.SOUTH);

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
		panelBookVisit.add(bookVisitNorthPanel, BorderLayout.WEST);
		GridBagLayout gbl_bookVisitNorthPanel = new GridBagLayout();
		gbl_bookVisitNorthPanel.columnWidths = new int[]{29, 49, 29, 37, 35, 28, 22, 28, 0};
		gbl_bookVisitNorthPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_bookVisitNorthPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bookVisitNorthPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		bookVisitNorthPanel.setLayout(gbl_bookVisitNorthPanel);

		JLabel lblBookVisitAnno = new JLabel("Anno:");
		GridBagConstraints gbc_lblBookVisitAnno = new GridBagConstraints();
		gbc_lblBookVisitAnno.anchor = GridBagConstraints.WEST;
		gbc_lblBookVisitAnno.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookVisitAnno.gridx = 0;
		gbc_lblBookVisitAnno.gridy = 0;
		bookVisitNorthPanel.add(lblBookVisitAnno, gbc_lblBookVisitAnno);

		JComboBox comboBoxBookVisitYear = new JComboBox();
		comboBoxBookVisitYear.setModel(new DefaultComboBoxModel(new String[] {"2017", "2018"}));
		GridBagConstraints gbc_comboBoxBookVisitYear = new GridBagConstraints();
		gbc_comboBoxBookVisitYear.anchor = GridBagConstraints.NORTHWEST;
		gbc_comboBoxBookVisitYear.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxBookVisitYear.gridx = 0;
		gbc_comboBoxBookVisitYear.gridy = 1;
		bookVisitNorthPanel.add(comboBoxBookVisitYear, gbc_comboBoxBookVisitYear);

		JLabel lblBookVisitMonth = new JLabel("Mese:");
		GridBagConstraints gbc_lblBookVisitMonth = new GridBagConstraints();
		gbc_lblBookVisitMonth.anchor = GridBagConstraints.WEST;
		gbc_lblBookVisitMonth.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookVisitMonth.gridx = 0;
		gbc_lblBookVisitMonth.gridy = 2;
		bookVisitNorthPanel.add(lblBookVisitMonth, gbc_lblBookVisitMonth);

		JComboBox<Month> comboBoxBookVisitMonth = new JComboBox<Month>();
		comboBoxBookVisitMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		GridBagConstraints gbc_comboBoxBookVisitMonth = new GridBagConstraints();
		gbc_comboBoxBookVisitMonth.anchor = GridBagConstraints.NORTHWEST;
		gbc_comboBoxBookVisitMonth.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxBookVisitMonth.gridx = 0;
		gbc_comboBoxBookVisitMonth.gridy = 3;
		bookVisitNorthPanel.add(comboBoxBookVisitMonth, gbc_comboBoxBookVisitMonth);
		JLabel lblBookVisitDay = new JLabel("Urgenza:");
		GridBagConstraints gbc_lblBookVisitDay = new GridBagConstraints();
		gbc_lblBookVisitDay.anchor = GridBagConstraints.WEST;
		gbc_lblBookVisitDay.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookVisitDay.gridx = 0;
		gbc_lblBookVisitDay.gridy = 4;
		bookVisitNorthPanel.add(lblBookVisitDay, gbc_lblBookVisitDay);

		JComboBox comboBoxBookVisitDay = new JComboBox();
		comboBoxBookVisitDay.setModel(new DefaultComboBoxModel(new String[] {"Bassa", "Medio", "Alta"}));
		GridBagConstraints gbc_comboBoxBookVisitDay = new GridBagConstraints();
		gbc_comboBoxBookVisitDay.anchor = GridBagConstraints.NORTHWEST;
		gbc_comboBoxBookVisitDay.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxBookVisitDay.gridx = 0;
		gbc_comboBoxBookVisitDay.gridy = 5;
		bookVisitNorthPanel.add(comboBoxBookVisitDay, gbc_comboBoxBookVisitDay);

		JLabel lblBookVisitHour = new JLabel("Regime:");
		GridBagConstraints gbc_lblBookVisitHour = new GridBagConstraints();
		gbc_lblBookVisitHour.anchor = GridBagConstraints.WEST;
		gbc_lblBookVisitHour.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookVisitHour.gridx = 0;
		gbc_lblBookVisitHour.gridy = 6;
		bookVisitNorthPanel.add(lblBookVisitHour, gbc_lblBookVisitHour);

		JComboBox comboBoxBookVisitHour = new JComboBox();
		GridBagConstraints gbc_comboBoxBookVisitHour = new GridBagConstraints();
		gbc_comboBoxBookVisitHour.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxBookVisitHour.anchor = GridBagConstraints.NORTHWEST;
		gbc_comboBoxBookVisitHour.gridx = 0;
		gbc_comboBoxBookVisitHour.gridy = 7;
		bookVisitNorthPanel.add(comboBoxBookVisitHour, gbc_comboBoxBookVisitHour);

		JPanel panel = new JPanel();
		panelBookVisit.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(6, 6, 0, 0));

		JPanel panelEmployee = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(panelEmployee, "panelEmployee");
		panelEmployee.setLayout(new BorderLayout(0, 0));

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

		JComboBox<String> comboBoxClinicVisitInsertion = new JComboBox<String>();
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

		JPanel panelInsertClinicMaster = new JPanel();
		tabbedPaneEmployee.addTab("Inserisci ambulatorio", null, panelInsertClinicMaster, null);
		GridBagLayout gbl_panelInsertClinicMaster = new GridBagLayout();
		gbl_panelInsertClinicMaster.columnWidths = new int[]{579, 0};
		gbl_panelInsertClinicMaster.rowHeights = new int[]{288, 75, 33, 0};
		gbl_panelInsertClinicMaster.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelInsertClinicMaster.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelInsertClinicMaster.setLayout(gbl_panelInsertClinicMaster);

		JPanel panelInsertClinic = new JPanel();
		GridBagConstraints gbc_panelInsertClinic = new GridBagConstraints();
		gbc_panelInsertClinic.anchor = GridBagConstraints.NORTH;
		gbc_panelInsertClinic.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelInsertClinic.insets = new Insets(0, 0, 5, 0);
		gbc_panelInsertClinic.gridx = 0;
		gbc_panelInsertClinic.gridy = 0;
		panelInsertClinicMaster.add(panelInsertClinic, gbc_panelInsertClinic);
		panelInsertClinic.setLayout(new BorderLayout(0, 0));

		JPanel panelInsertClinicData = new JPanel();
		panelInsertClinic.add(panelInsertClinicData, BorderLayout.WEST);
		panelInsertClinicData.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dati",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_panelInsertClinicData = new GridBagLayout();
		gbl_panelInsertClinicData.columnWidths = new int[]{150, 0};
		gbl_panelInsertClinicData.rowHeights = new int[]{20, 14, 20, 14, 20, 14, 20, 14, 20, 14, 20, 14, 0};
		gbl_panelInsertClinicData.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelInsertClinicData.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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

		JLabel lblInsertClinicZipCode = new JLabel("CAP:");
		GridBagConstraints gbc_lblInsertClinicZipCode = new GridBagConstraints();
		gbc_lblInsertClinicZipCode.anchor = GridBagConstraints.WEST;
		gbc_lblInsertClinicZipCode.insets = new Insets(0, 0, 5, 0);
		gbc_lblInsertClinicZipCode.gridx = 0;
		gbc_lblInsertClinicZipCode.gridy = 6;
		panelInsertClinicData.add(lblInsertClinicZipCode, gbc_lblInsertClinicZipCode);

		textFieldInsertClinicZipCode = new JTextField();
		GridBagConstraints gbc_textFieldInsertClinicZipCode = new GridBagConstraints();
		gbc_textFieldInsertClinicZipCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertClinicZipCode.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertClinicZipCode.gridx = 0;
		gbc_textFieldInsertClinicZipCode.gridy = 7;
		panelInsertClinicData.add(textFieldInsertClinicZipCode, gbc_textFieldInsertClinicZipCode);
		textFieldInsertClinicZipCode.setColumns(10);

		JLabel lblInsertClinicProvince = new JLabel("Provincia:");
		GridBagConstraints gbc_lblInsertClinicProvince = new GridBagConstraints();
		gbc_lblInsertClinicProvince.anchor = GridBagConstraints.WEST;
		gbc_lblInsertClinicProvince.insets = new Insets(0, 0, 5, 0);
		gbc_lblInsertClinicProvince.gridx = 0;
		gbc_lblInsertClinicProvince.gridy = 8;
		panelInsertClinicData.add(lblInsertClinicProvince, gbc_lblInsertClinicProvince);

		JComboBox<String> comboBoxInsertClinicProvince = new JComboBox<String>();
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
		gbc_formattedTextFieldInsertClinicContractDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldInsertClinicContractDate.gridx = 0;
		gbc_formattedTextFieldInsertClinicContractDate.gridy = 11;
		panelInsertClinicData.add(formattedTextFieldInsertClinicContractDate, gbc_formattedTextFieldInsertClinicContractDate);

		JScrollPane scrollPaneInsertClinicDescription = new JScrollPane();
		panelInsertClinic.add(scrollPaneInsertClinicDescription, BorderLayout.CENTER);
		scrollPaneInsertClinicDescription.setBorder(new TitledBorder(null, "Descrizione", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JTextArea textAreaInsertClinicDescription = new JTextArea();
		scrollPaneInsertClinicDescription.setViewportView(textAreaInsertClinicDescription);

		JScrollPane scrollPaneViewClinics = new JScrollPane();
		GridBagConstraints gbc_scrollPaneViewClinics = new GridBagConstraints();
		gbc_scrollPaneViewClinics.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneViewClinics.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneViewClinics.gridx = 0;
		gbc_scrollPaneViewClinics.gridy = 1;
		panelInsertClinicMaster.add(scrollPaneViewClinics, gbc_scrollPaneViewClinics);

		tableClinics = new JTable();
		tableClinics.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
				},
				new String[] {
						"Nome", "Via", "Paese", "CAP", "Provincia", "Data contratto", ""
				}
				) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableClinics.getColumnModel().getColumn(0).setResizable(false);
		tableClinics.getColumnModel().getColumn(1).setResizable(false);
		tableClinics.getColumnModel().getColumn(2).setResizable(false);
		tableClinics.getColumnModel().getColumn(3).setResizable(false);
		tableClinics.getColumnModel().getColumn(4).setResizable(false);
		tableClinics.getColumnModel().getColumn(5).setResizable(false);
		tableClinics.getColumnModel().getColumn(6).setResizable(false);
		scrollPaneViewClinics.setViewportView(tableClinics);

		JPanel panelInsertClinicButton = new JPanel();
		GridBagConstraints gbc_panelInsertClinicButton = new GridBagConstraints();
		gbc_panelInsertClinicButton.anchor = GridBagConstraints.NORTH;
		gbc_panelInsertClinicButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelInsertClinicButton.gridx = 0;
		gbc_panelInsertClinicButton.gridy = 2;
		panelInsertClinicMaster.add(panelInsertClinicButton, gbc_panelInsertClinicButton);
		FlowLayout fl_panelInsertClinicButton = (FlowLayout) panelInsertClinicButton.getLayout();
		fl_panelInsertClinicButton.setAlignment(FlowLayout.RIGHT);

		JButton btnInsertClinic = new JButton("Inserisci");
		btnInsertClinic.setEnabled(false);
		panelInsertClinicButton.add(btnInsertClinic);

		JPanel panelVisitsResultsPerPatientMaster = new JPanel();
		tabbedPaneEmployee.addTab("Visualizza visite", null, panelVisitsResultsPerPatientMaster, null);
		panelVisitsResultsPerPatientMaster.setLayout(new CardLayout(0, 0));

		JPanel panelViewVisitPerPatient = new JPanel();
		panelVisitsResultsPerPatientMaster.add(panelViewVisitPerPatient, "panelViewVisitPerPatient");
		panelViewVisitPerPatient.setLayout(new BorderLayout(0, 0));

		JPanel panelViewVisitPerPatientNorth = new JPanel();
		FlowLayout fl_panelViewVisitPerPatientNorth = (FlowLayout) panelViewVisitPerPatientNorth.getLayout();
		fl_panelViewVisitPerPatientNorth.setAlignment(FlowLayout.LEFT);
		panelViewVisitPerPatient.add(panelViewVisitPerPatientNorth, BorderLayout.NORTH);

		JLabel lblPaziente = new JLabel("Inserisci codice fiscale paziente:");
		panelViewVisitPerPatientNorth.add(lblPaziente);


		JFormattedTextField searchPatientTextField = new JFormattedTextField();
		panelViewVisitPerPatientNorth.add(searchPatientTextField);
		searchPatientTextField.setColumns(16);

		//visualizza le visite di un paziente
		JButton btnVisualizza = new JButton("Cerca");
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("DA IMPLEMENTARE");
				ArrayList<Clinic> clincs = db.getClinics(Employee.getInstance().getClinic());
			}
		});
		panelViewVisitPerPatientNorth.add(btnVisualizza);

		JScrollPane scrollPaneViewVisitPerPatient = new JScrollPane();
		panelViewVisitPerPatient.add(scrollPaneViewVisitPerPatient, BorderLayout.CENTER);

		tableVisitsPatientResults = new JTable();
		tableVisitsPatientResults.setModel(new DefaultTableModel(
				new Object[][] {
					{"17/5/17", "13:30", "oncologica", "privata", "alta"},
				},
				new String[] {
						"Data", "Ora", "Tipo visita", "Regime", "Urgenza"
				}
				) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableVisitsPatientResults.getColumnModel().getColumn(0).setResizable(false);
		tableVisitsPatientResults.getColumnModel().getColumn(1).setResizable(false);
		tableVisitsPatientResults.getColumnModel().getColumn(2).setResizable(false);
		tableVisitsPatientResults.getColumnModel().getColumn(3).setResizable(false);
		tableVisitsPatientResults.getColumnModel().getColumn(4).setResizable(false);
		scrollPaneViewVisitPerPatient.setViewportView(tableVisitsPatientResults);

		JPanel panelViewVisitPerPatientSouth = new JPanel();
		FlowLayout fl_panelViewVisitPerPatientSouth = (FlowLayout) panelViewVisitPerPatientSouth.getLayout();
		fl_panelViewVisitPerPatientSouth.setAlignment(FlowLayout.RIGHT);
		panelViewVisitPerPatient.add(panelViewVisitPerPatientSouth, BorderLayout.SOUTH);

		JButton btnDettagli = new JButton("Visualizza");
		panelViewVisitPerPatientSouth.add(btnDettagli);

		JPanel panelViewVisitResultsPerPatient = new JPanel();
		panelVisitsResultsPerPatientMaster.add(panelViewVisitResultsPerPatient, "panelViewVisitResultsPerPatient");
		panelViewVisitResultsPerPatient.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPanepanelViewVisitResultsPerPatient = new JScrollPane();
		panelViewVisitResultsPerPatient.add(scrollPanepanelViewVisitResultsPerPatient, BorderLayout.CENTER);

		JTextArea textArea = new JTextArea();
		scrollPanepanelViewVisitResultsPerPatient.setViewportView(textArea);

		JPanel panelpanelViewVisitResultsPerPatientSouth = new JPanel();
		FlowLayout fl_panelpanelViewVisitResultsPerPatientSouth = (FlowLayout) panelpanelViewVisitResultsPerPatientSouth.getLayout();
		fl_panelpanelViewVisitResultsPerPatientSouth.setAlignment(FlowLayout.RIGHT);
		panelViewVisitResultsPerPatient.add(panelpanelViewVisitResultsPerPatientSouth, BorderLayout.SOUTH);

		JLabel lblBackToViewVisitPerPatient = new JLabel("Indietro");
		panelpanelViewVisitResultsPerPatientSouth.add(lblBackToViewVisitPerPatient);

		JButton btnBackToViewVisitPerPatient = new JButton("Stampa");
		panelpanelViewVisitResultsPerPatientSouth.add(btnBackToViewVisitPerPatient);

		JPanel panelEmployeeNorth = new JPanel();
		panelEmployee.add(panelEmployeeNorth, BorderLayout.NORTH);
		panelEmployeeNorth.setLayout(new BorderLayout(0, 0));

		JLabel lblWeolcomeEmployee = new JLabel("Benvenuto");
		lblWeolcomeEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeolcomeEmployee.setFont(new Font("Tahoma", Font.BOLD, 22));
		panelEmployeeNorth.add(lblWeolcomeEmployee, BorderLayout.CENTER);

		JPanel panelEmployeeNorthRightLabels = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelEmployeeNorthRightLabels.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(10);
		panelEmployeeNorth.add(panelEmployeeNorthRightLabels, BorderLayout.EAST);

		JLabel lblLogoutEmployee = new JLabel("<HTML>\r\n\t<p style=\"color:blue;\"><u>Logout</u></p>\r\n</HTML>");
		lblLogoutEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLogoutEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		panelEmployeeNorthRightLabels.add(lblLogoutEmployee);

		JPanel panelClinicsAndServices = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(panelClinicsAndServices, "panelClinicsAndServices");
		panelClinicsAndServices.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPaneClinics = new JSplitPane();
		panelClinicsAndServices.add(splitPaneClinics, BorderLayout.CENTER);
		splitPaneClinics.setResizeWeight(0.3);

		JPanel ClinicsPanel = new JPanel();
		splitPaneClinics.setLeftComponent(ClinicsPanel);
		ClinicsPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblClinics = new JLabel("Clinics");
		lblClinics.setHorizontalAlignment(SwingConstants.CENTER);
		ClinicsPanel.add(lblClinics, BorderLayout.NORTH);

		JList<String> listCLinics = new JList<String>();
		listCLinics.setModel(new AbstractListModel<String>() {
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
		ClinicsPanel.add(listCLinics, BorderLayout.CENTER);

		JPanel ServicesPanel = new JPanel();
		splitPaneClinics.setRightComponent(ServicesPanel);
		ServicesPanel.setLayout(new BorderLayout(0, 0));

		JTextPane txtpnclinicsOfJesus = new JTextPane();
		txtpnclinicsOfJesus.setContentType("text/html");
		txtpnclinicsOfJesus.setText(
				"<html>\r\n\t<head></head>\r\n\t<body>\r\n\t\t<h1>Clinics of Jesus</h1>\r\n\t\t<h3>Services</h3>\r\n\t\t.....Prova html.....\r\n\t</body>\r\n</html>\r\n");
		ServicesPanel.add(txtpnclinicsOfJesus, BorderLayout.CENTER);

		JPanel panelClinicAndServicesButton = new JPanel();
		FlowLayout fl_panelClinicAndServicesButton = (FlowLayout) panelClinicAndServicesButton.getLayout();
		fl_panelClinicAndServicesButton.setAlignment(FlowLayout.RIGHT);
		panelClinicsAndServices.add(panelClinicAndServicesButton, BorderLayout.SOUTH);

		JButton btnBackToLogin = new JButton("Indietro");
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(), "panelLogin");
			}
		});
		panelClinicAndServicesButton.add(btnBackToLogin);

		frmHealthcareManagementSystem.setSize(700, 600);
		frmHealthcareManagementSystem.setLocationRelativeTo(null);
		// frame.pack();
		// frame.setResizable(false);
	}
}
