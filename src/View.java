import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class View {

	public JFrame frmHealthcareManagementSystem;
	private JTextField textField_usr;
	private JPasswordField textField_passwd;
	private JPasswordField passwordFieldPIN;
	private JTable tableHistoryVisits;
	private JTable tableVisitsFound;
	private JTextField textFieldInsertClinicStreet;
	private JTextField textFieldInsertClinicCity;
	private JTextField textFieldInsertClinicCAP;
	private JTextField textFieldInsertClinicName;
	private JTable tableClinics;
	private JTable tableVisitsPatientResults;
	private CardLayout clfrmHealhcareManagementSystem;
	private Database db;
	private JLabel lblWelcomePatient;
	private ArrayList<Visit> patientVisits;

	private final String company = "cp02";

	private JLabel[][] lbldays;
	private static final int DAYSR = 6;
	private static final int DAYSC = 7;
	private JComboBox<Integer> selectedMonth;
	private JComboBox<String> comboBoxSelectBookVisitMonth;
	private JComboBox<String> comboBoxClinicVisitInsertion;
	private JComboBox<String> comboBoxVisitsYear;
	private DefaultTableModel visitsHistoryModel;
	private JButton btnViewVisitsPatient;
	private CardLayout clpanelVisitPatient;
	private JPanel panelVisitPatient;

	private JList<String> listClinics;
	private ArrayList<String[]> companiesList;
	private JComboBox<String> comboBoxSelectCompany;

	private JTextPane txtpnVisitResultInfo;
	private JLabel lblWelcomeEmployee;

	private JComboBox<String> comboBoxSelectBookVisitType;
	private JTextArea textAreaVisitResult;
	private DefaultTableModel insertClinicModel;
	private JButton btnInsertNewVisitResult;

	private JComboBox<Integer> comboBoxSelectBookVisitDay; 

	private JButton btnLoginPatient;
	private JButton btnLoginEmployee; 
	private JComboBox<Integer> comboBoxSelectBookVisitYear; 
	private ArrayList<String[]> serviceInfos;
	private JComboBox<String> comboBoxSelectBookVisitClinic;
	private JComboBox<Integer> comboBoxSelectBookVisitHour;
	private ArrayList<Visit> visits = null;
	private JTextField textFieldRegime;
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private int visitIndex;
	private JButton btnViewVisitSelectedResult;
	private CardLayout clpanelVisitsResultsPerPatientMaster;
	private JPanel panelVisitsResultsPerPatientMaster;
	private JTextPane textPanePatientVisitResult;
	private JTable tableUpdateClinics;
	private DefaultTableModel tableUpdateClinicsModel;
	private JPanel panelInsertClinicButton;
	private JPanel panelInsertClinic;
	ArrayList<Clinic> editableClinics;
	private JFormattedTextField formattedTextFieldInsertClinicContractDate;
	private JComboBox<String> comboBoxInsertClinicProvince;
	private JTextArea textAreaInsertClinicDescription;
	private boolean isAddingNewClinic = false;
	private JButton btnEditClinic;
	private String currentClinicName;

	/**
	 * Launch the application.
	 * 
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {

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
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public View() throws ClassNotFoundException, SQLException {
		this.db = new Database();
		initialize();
	}

	private int getDaysInMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	} 
	
	private boolean duplicatedClinicName(String newName, String oldName) {
		
		newName = newName.toUpperCase();
		
		if(newName.equals(oldName))
			return true;
		
		for (Clinic c: editableClinics) {
			if (c.getName().equals(newName))
				return false;
		}
		return true;
	}
	
	private void refreshEditableClinics()
	{
		//clear 
		while(tableUpdateClinicsModel.getRowCount() > 0)
			tableUpdateClinicsModel.removeRow(0);
		
		//QUESTA DIVENTERA UNA FUNZIONE PER RIEMPIRE LA TABELLA DI CLINICHE IN INSERISCI AMBULATORIO___________________
		editableClinics = db.getClinics(Employee.getInstance().getCompany());

		if (editableClinics != null) {
			for(Clinic c: editableClinics) {

				Vector<Object> vector = new Vector<Object>();

				vector.add(c.getName());
				vector.add(c.getStreet());
				vector.add(c.getCAP());
				vector.add(c.getCity());
				vector.add(c.getProvince());
				tableUpdateClinicsModel.addRow(vector);

				System.out.println("clinica " + c.getName());

			}
		}
	}

	private void updateBookingDays()
	{
		int[] days = db.getVisitsInMonth(
				(int)comboBoxSelectBookVisitYear.getSelectedItem(),
				(int)comboBoxSelectBookVisitMonth.getSelectedIndex() + 1, 
				(String)comboBoxSelectBookVisitType.getSelectedItem(),
				(String)comboBoxSelectBookVisitClinic.getSelectedItem(),
				Patient.getInstance().getHealthcarecompany());

		comboBoxSelectBookVisitDay.removeAllItems();
		for(int i = 1; i < 32; i++)
		{
			if(days[i] < 8)
				comboBoxSelectBookVisitDay.addItem((int)i);
		} 
	} 
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmHealthcareManagementSystem = new JFrame("HEALTHCARE MANAGEMENT SYSTEM");
		frmHealthcareManagementSystem
		.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/img/healthcare-icon.png")));
		frmHealthcareManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHealthcareManagementSystem.getContentPane().setLayout(new CardLayout(0, 0));
		this.clfrmHealhcareManagementSystem = (CardLayout) frmHealthcareManagementSystem.getContentPane().getLayout();

		JPanel panelLogin = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(panelLogin, "panelLogin");
		panelLogin.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPaneLogin = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneLogin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				//if (tabbedPaneLogin.getSelectedIndex() == 0)
					//frmHealthcareManagementSystem.getRootPane().setDefaultButton(btnLoginPatient);
				//else
					//frmHealthcareManagementSystem.getRootPane().setDefaultButton(btnLoginEmployee);
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

		JFormattedTextField formattedTextFieldFiscalCode = new JFormattedTextField("MTBMHM93M51D251I"); // DA
		// FORMATTARE
		// CON
		// SOLO
		// CARATTERI
		// ALFA
		// NUMERICI
		// E
		// MAX
		// LENGHT
		// 16
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

		btnLoginPatient = new JButton("Login");
		GridBagConstraints gbc_btnLoginPatient = new GridBagConstraints();
		gbc_btnLoginPatient.gridx = 1;
		gbc_btnLoginPatient.gridy = 3;
		panelCenterPatientLogin.add(btnLoginPatient, gbc_btnLoginPatient);

		btnLoginPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fiscalCode = formattedTextFieldFiscalCode.getText();
				String passwd = new String(passwordFieldPIN.getPassword());
				if (db.checkPatient(fiscalCode, passwd)) {
					lblWelcomePatient.setText(lblWelcomePatient.getText() + Patient.getInstance().getName() + " "
							+ Patient.getInstance().getSurname());
					clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(), "panelPatient");

					// clean fiscal code and password
					formattedTextFieldFiscalCode.setText("");
					passwordFieldPIN.setText("");

					// get years
					ArrayList<Integer> years = db.getVisitsHistoryYears(fiscalCode);
					for (Integer y : years)
						comboBoxVisitsYear.addItem(y.toString());

					// fill table (visits history)
					int i = 1;
					patientVisits = db.getVisitsHistory(fiscalCode);
					if (patientVisits != null) { 
						for (Visit c : patientVisits) { 
							Vector<Object> row = new Vector<Object>();
							row.add(i++);
							row.add(c.getDate().toString());
							row.add(c.getHour());
							row.add(c.getServiceName());
							row.add(c.getRegime());
							row.add(c.getUrgency());
							visitsHistoryModel.addRow(row);
						}
					}

					// initialize book visit panel
					ArrayList<String> services = db.getServices(Patient.getInstance().getHealthcarecompany());

					for(String s : services) 
						comboBoxSelectBookVisitType.addItem(s);

				} else {
					System.out.println("credenziali errate: " + fiscalCode + " " + passwd);
					JOptionPane.showMessageDialog(null, "Codice fiscale o PIN errati o mancanti", "Errore accesso",
							JOptionPane.WARNING_MESSAGE);
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
		gbl_panelCenterEmployeeLogin.columnWidths = new int[] { 0, 0, 0 };
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
		textField_usr.setText("JOBIT0001");
		GridBagConstraints gbc_textField_usr = new GridBagConstraints();
		gbc_textField_usr.fill = GridBagConstraints.BOTH;
		gbc_textField_usr.insets = new Insets(0, 0, 5, 5);
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
		gbc_textField_passwd.insets = new Insets(0, 0, 5, 5);
		gbc_textField_passwd.fill = GridBagConstraints.BOTH;
		gbc_textField_passwd.gridx = 1;
		gbc_textField_passwd.gridy = 1;
		panelCenterEmployeeLogin.add(textField_passwd, gbc_textField_passwd);
		btnLoginEmployee = new JButton("Login");
		btnLoginEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("LOGIN EFFETTUATO");
				String user = textField_usr.getText();
				String pass = new String(textField_passwd.getPassword());

				if (db.checkEmployee(user, pass)) {
					lblWelcomeEmployee.setText(lblWelcomeEmployee.getText() + Employee.getInstance().getName() + " "
							+ Employee.getInstance().getSurname());

					
					refreshEditableClinics();

					clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(),
							"panelEmployee");

					// add clinics
					System.out.println(editableClinics.size() + "  " + Employee.getInstance().getCompany());
					for (Clinic c : editableClinics)
						comboBoxClinicVisitInsertion.addItem(c.getName());

				} else {
					System.out.println("wrong credentials: " + user + " " + pass);
					JOptionPane.showMessageDialog(null, "Nome utente o password errati o mancanti", "Errore accesso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnLoginEmployee = new GridBagConstraints();
		gbc_btnLoginEmployee.insets = new Insets(0, 0, 0, 5);
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

				companiesList = db.getCompanies();

				for (String[] o : companiesList) {
					comboBoxSelectCompany.addItem(o[1]);
				}

				clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(),
						"panelClinicsAndServices");
			}
		});
		panelSouthLogin.add(btnViewClinicAnd);

		JPanel panelPatient = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(panelPatient, "panelPatient");
		panelPatient.setLayout(new BorderLayout(0, 0));

		JPanel panelPatientNorth = new JPanel();
		panelPatient.add(panelPatientNorth, BorderLayout.NORTH);
		panelPatientNorth.setLayout(new BorderLayout(0, 0));

		lblWelcomePatient = new JLabel("Benvenuto ");
		lblWelcomePatient.setHorizontalAlignment(SwingConstants.CENTER);
		panelPatientNorth.add(lblWelcomePatient, BorderLayout.CENTER);
		lblWelcomePatient.setFont(new Font("Tahoma", Font.BOLD, 22));

		JPanel panelPatientNorthRightLabels = new JPanel();
		FlowLayout fl_panelPatientNorthRightLabels = (FlowLayout) panelPatientNorthRightLabels.getLayout();
		fl_panelPatientNorthRightLabels.setHgap(10);
		fl_panelPatientNorthRightLabels.setVgap(10);
		panelPatientNorth.add(panelPatientNorthRightLabels, BorderLayout.EAST);

		JLabel lblLogoutPatient = new JLabel("<HTML>\r\n\t<p style=\"color:blue;\"><u>Logout</u></p>\r\n</HTML>");
		lblLogoutPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Patient clicked on Logout");
				clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(), "panelLogin");

				// qui va chiamata una funzione per resettare tutti i parametri
				// quindi distruggere tutti i pannelli dell'utente loggato.
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblLogoutPatient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		panelPatientNorthRightLabels.add(lblLogoutPatient);
		lblLogoutPatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLogoutPatient.setHorizontalAlignment(SwingConstants.CENTER);

		JTabbedPane tabbedPanePatient = new JTabbedPane(JTabbedPane.TOP);
		panelPatient.add(tabbedPanePatient, BorderLayout.CENTER);

		panelVisitPatient = new JPanel();
		tabbedPanePatient.addTab("Storico prenotazioni", null, panelVisitPatient, null);
		panelVisitPatient.setLayout(new CardLayout(0, 0));
		clpanelVisitPatient = (CardLayout) panelVisitPatient.getLayout();

		JPanel panelHistoryVisitPatient = new JPanel();
		panelVisitPatient.add(panelHistoryVisitPatient, "panelHistoryVisitPatient");
		panelHistoryVisitPatient.setLayout(new BorderLayout(0, 0));

		JPanel panelHistoryNorth = new JPanel();
		FlowLayout fl_panelHistoryNorth = (FlowLayout) panelHistoryNorth.getLayout();
		fl_panelHistoryNorth.setAlignment(FlowLayout.LEFT);
		panelHistoryVisitPatient.add(panelHistoryNorth, BorderLayout.NORTH);

		JLabel lblSelectYearPatientVisit = new JLabel("Seleziona anno:");
		panelHistoryNorth.add(lblSelectYearPatientVisit);

		comboBoxVisitsYear = new JComboBox<String>();
		panelHistoryNorth.add(comboBoxVisitsYear);

		JScrollPane scrollPaneHistory = new JScrollPane();
		panelHistoryVisitPatient.add(scrollPaneHistory, BorderLayout.CENTER);

		tableHistoryVisits = new JTable();
		tableHistoryVisits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		visitsHistoryModel = new DefaultTableModel(
				new String[] { "N\u00B0", "Data", "Ora", "Tipo Visita", "Regime", "Urgenza" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableHistoryVisits.setModel(visitsHistoryModel);
		tableHistoryVisits.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableHistoryVisits.getColumnModel().getColumn(0).setMaxWidth(25);
		tableHistoryVisits.getTableHeader().setReorderingAllowed(false);
		tableHistoryVisits.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					System.out.println(tableHistoryVisits.getSelectedRow());
					visitIndex = tableHistoryVisits.getSelectedRow();
					btnViewVisitsPatient.setEnabled(true);
				}
			}
		});
		scrollPaneHistory.setViewportView(tableHistoryVisits);

		JPanel panelHistoryButtons = new JPanel();
		FlowLayout fl_panelHistoryButtons = (FlowLayout) panelHistoryButtons.getLayout();
		fl_panelHistoryButtons.setAlignment(FlowLayout.RIGHT);
		panelHistoryVisitPatient.add(panelHistoryButtons, BorderLayout.SOUTH);

		btnViewVisitsPatient = new JButton("Visualizza");
		btnViewVisitsPatient.setEnabled(false);
		btnViewVisitsPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visit v = patientVisits.get(visitIndex);
				txtpnVisitResultInfo.setText(v.toHTML(Patient.getInstance().getName(), Patient.getInstance().getSurname()));
				clpanelVisitPatient.next(panelVisitPatient);
				// patientVisits
			}
		});
		panelHistoryButtons.add(btnViewVisitsPatient);

		JPanel panelVisitResultPatient = new JPanel();
		panelVisitPatient.add(panelVisitResultPatient, "panelVisitResultPatient");
		panelVisitResultPatient.setLayout(new BorderLayout(0, 0));

		JPanel resultVisitSouthpanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) resultVisitSouthpanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelVisitResultPatient.add(resultVisitSouthpanel, BorderLayout.SOUTH);

		JButton btnBeckToHistory = new JButton("Indietro");
		btnBeckToHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clpanelVisitPatient.previous(panelVisitPatient);
			}
		});
		resultVisitSouthpanel.add(btnBeckToHistory);

		JButton btnPrintVisitResult = new JButton("Stampa");
		btnPrintVisitResult.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPrintVisitResult.setAlignmentX(Component.CENTER_ALIGNMENT);
		resultVisitSouthpanel.add(btnPrintVisitResult);

		txtpnVisitResultInfo = new JTextPane();
		txtpnVisitResultInfo.setEditable(false);
		txtpnVisitResultInfo.setContentType("text/html");
		panelVisitResultPatient.add(txtpnVisitResultInfo, BorderLayout.CENTER);

		JPanel panelBookVisit = new JPanel();
		tabbedPanePatient.addTab("Prenotazione visite", null, panelBookVisit, null);
		panelBookVisit.setLayout(new BorderLayout(0, 0));

		JPanel bookVisitCenterPanel = new JPanel();
		bookVisitCenterPanel.setBorder(new EmptyBorder(5, 5, 0, 0));
		panelBookVisit.add(bookVisitCenterPanel, BorderLayout.CENTER);

		JLabel lblSelectBookVisitType = new JLabel("Tipo visita:");
		lblSelectBookVisitType.setHorizontalAlignment(SwingConstants.RIGHT);

		comboBoxSelectBookVisitType = new JComboBox<String>();
		comboBoxSelectBookVisitType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxSelectBookVisitClinic.removeAllItems();
				serviceInfos = db.getServiceInfos(Patient.getInstance().getHealthcarecompany(), comboBoxSelectBookVisitType.getSelectedItem().toString());
				for(String[] info : serviceInfos)
					comboBoxSelectBookVisitClinic.addItem(info[0]);

				updateBookingDays();
			}
		});

		JLabel lblSelectBookVisitYear = new JLabel("Anno:");
		lblSelectBookVisitYear.setHorizontalAlignment(SwingConstants.RIGHT);

		comboBoxSelectBookVisitYear = new JComboBox<Integer>();
		int year = Calendar.getInstance().get(Calendar.YEAR); 
		comboBoxSelectBookVisitYear.addItem(year);
		comboBoxSelectBookVisitYear.addItem(year + 1);


		comboBoxSelectBookVisitYear.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				System.out.println("modified year");
				updateBookingDays();
			}
		}); 

		JLabel lblSelectBookVisitMonth = new JLabel("Mese:");

		comboBoxSelectBookVisitMonth = new JComboBox<String>();
		comboBoxSelectBookVisitMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				System.out.println("modified month");
				updateBookingDays();
			}
		});
		comboBoxSelectBookVisitMonth.setModel(new DefaultComboBoxModel(Month.values()));

		JLabel lblSelectBookVisitDay = new JLabel("Giorno:");
		lblSelectBookVisitDay.setHorizontalAlignment(SwingConstants.RIGHT);

		comboBoxSelectBookVisitDay = new JComboBox<Integer>();
		comboBoxSelectBookVisitDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				System.out.println("modified day");
				if(comboBoxSelectBookVisitDay.getSelectedItem() == null)
					return;

				boolean[] hours = db.getBookedVisitsInDay(
						(int)comboBoxSelectBookVisitYear.getSelectedItem(),
						(int)comboBoxSelectBookVisitMonth.getSelectedIndex() + 1,
						(int)comboBoxSelectBookVisitDay.getSelectedItem(), 
						(String)comboBoxSelectBookVisitType.getSelectedItem(),
						(String)comboBoxSelectBookVisitClinic.getSelectedItem(),
						Patient.getInstance().getHealthcarecompany());

				comboBoxSelectBookVisitHour.removeAllItems(); 

				for(int i = 8; i < 16; i++)
					if(!hours[i])
						comboBoxSelectBookVisitHour.addItem((int)i);


			}
		});

		JLabel lblSelectBookVisitHour = new JLabel("Ora:");

		comboBoxSelectBookVisitHour = new JComboBox<Integer>();
		JLabel lblSelectBookVisitUrgency = new JLabel("Urgenza:");
		lblSelectBookVisitUrgency.setHorizontalAlignment(SwingConstants.RIGHT);

		JComboBox comboBoxSelectBookVisitUrgency = new JComboBox();
		comboBoxSelectBookVisitUrgency.setModel(new DefaultComboBoxModel(new String[] { "Bassa", "Medio", "Alta" }));

		JLabel lblSelectBookVisitRegime = new JLabel("Regime:");
		lblSelectBookVisitRegime.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblAmbultorio = new JLabel("Ambulatorio:");
		lblAmbultorio.setHorizontalAlignment(SwingConstants.RIGHT);

		comboBoxSelectBookVisitClinic = new JComboBox<String>();
		comboBoxSelectBookVisitClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int idx = comboBoxSelectBookVisitClinic.getSelectedIndex();
				System.out.println(idx);
				if(idx >= 0)
				{
					String[] info = serviceInfos.get(idx);
					textFieldRegime.setText(info[1]);

				}
				updateBookingDays();
			}
		});

		textFieldRegime = new JTextField();
		textFieldRegime.setEditable(false);
		textFieldRegime.setColumns(10);

		GroupLayout gl_bookVisitCenterPanel = new GroupLayout(bookVisitCenterPanel);
		gl_bookVisitCenterPanel.setHorizontalGroup(
				gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
						.addGap(132)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSelectBookVisitUrgency, Alignment.TRAILING)
										.addComponent(lblSelectBookVisitType, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblSelectBookVisitYear, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblAmbultorio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
								.addComponent(lblSelectBookVisitDay, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
										.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(comboBoxSelectBookVisitDay, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(comboBoxSelectBookVisitYear, Alignment.LEADING, 0, 69, Short.MAX_VALUE)
												.addComponent(comboBoxSelectBookVisitUrgency, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(69)
										.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblSelectBookVisitMonth)
												.addComponent(lblSelectBookVisitHour)
												.addComponent(lblSelectBookVisitRegime))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(textFieldRegime, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
												.addComponent(comboBoxSelectBookVisitHour, 0, 104, Short.MAX_VALUE)
												.addComponent(comboBoxSelectBookVisitMonth, 0, 104, Short.MAX_VALUE)))
								.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(comboBoxSelectBookVisitClinic, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(comboBoxSelectBookVisitType, Alignment.LEADING, 0, 285, Short.MAX_VALUE)))
						.addGap(156))
				);
		gl_bookVisitCenterPanel.setVerticalGroup(
				gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
						.addGap(82)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSelectBookVisitType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectBookVisitType))
						.addGap(18)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSelectBookVisitClinic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAmbultorio))
						.addGap(18)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSelectBookVisitYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectBookVisitMonth)
								.addComponent(lblSelectBookVisitYear)
								.addComponent(comboBoxSelectBookVisitMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSelectBookVisitDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectBookVisitHour)
								.addComponent(lblSelectBookVisitDay)
								.addComponent(comboBoxSelectBookVisitHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSelectBookVisitUrgency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectBookVisitUrgency)
								.addComponent(lblSelectBookVisitRegime)
								.addComponent(textFieldRegime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(204, Short.MAX_VALUE))
				);
		bookVisitCenterPanel.setLayout(gl_bookVisitCenterPanel);

		JPanel bookVisitSouthButtonPanel = new JPanel();
		bookVisitSouthButtonPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		FlowLayout fl_bookVisitSouthButtonPanel = (FlowLayout) bookVisitSouthButtonPanel.getLayout();
		fl_bookVisitSouthButtonPanel.setAlignment(FlowLayout.RIGHT);
		panelBookVisit.add(bookVisitSouthButtonPanel, BorderLayout.SOUTH);

		JButton btnBookVisit = new JButton("Prenota");
		btnBookVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visit v = new Visit();
				v.setServiceName(comboBoxSelectBookVisitType.getSelectedItem().toString());
				v.setClinicName(comboBoxSelectBookVisitClinic.getSelectedItem().toString());
				v.setCompanyId(Patient.getInstance().getHealthcarecompany());
				v.setHour((int)comboBoxSelectBookVisitHour.getSelectedItem());
				int year = (int)comboBoxSelectBookVisitYear.getSelectedItem();
				int month = comboBoxSelectBookVisitMonth.getSelectedIndex() + 1;
				int day = (int)comboBoxSelectBookVisitDay.getSelectedItem();

				try {
					v.setDate(new Date(sdf.parse(day + "/" + month + "/" + year).getTime()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //SIMONE MODIFICA QUI
				v.setPatient(Patient.getInstance().getFiscalcode());
				v.setRegime(textFieldRegime.getText());
				v.setUrgency(comboBoxSelectBookVisitUrgency.getSelectedItem().toString());
				db.bookVisit(v);
				updateBookingDays();
				JOptionPane.showMessageDialog(null, "Prenotazione effettuta con successo","Informazione", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnBookVisit.setToolTipText("Clicca per prenotare");
		bookVisitSouthButtonPanel.add(btnBookVisit);

		JPanel panelEmployee = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(panelEmployee, "panelEmployee");
		panelEmployee.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPaneEmployee = new JTabbedPane(JTabbedPane.TOP);
		panelEmployee.add(tabbedPaneEmployee, BorderLayout.CENTER);

		JPanel panelInsertVisitInfo = new JPanel();
		tabbedPaneEmployee.addTab("Risultati visite", null, panelInsertVisitInfo, null);
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

		JFormattedTextField formattedTextFieldVisitInsertion = new JFormattedTextField(sdf);
		formattedTextFieldVisitInsertion.setToolTipText("Inserisci la data della visita nel formato gg/mm/aaaa");
		formattedTextFieldVisitInsertion.setColumns(10);
		panelInfovisitInsertionNorth.add(formattedTextFieldVisitInsertion);

		JLabel lblClinicVisitInsertion = new JLabel("Ambulatorio:");
		panelInfovisitInsertionNorth.add(lblClinicVisitInsertion);

		comboBoxClinicVisitInsertion = new JComboBox<String>();
		comboBoxClinicVisitInsertion.setPreferredSize(new Dimension(150, 27));
		comboBoxClinicVisitInsertion.setMaximumRowCount(10);
		panelInfovisitInsertionNorth.add(comboBoxClinicVisitInsertion);

		DefaultTableModel employeeInsertVisitModel = new DefaultTableModel(
				new String[] { "N\u00B0", "Codice Fiscale", "Tipo visita", "Urgenza", "Ora" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JButton btnFindVisits = new JButton("Trova");
		btnFindVisits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// free table

				while (employeeInsertVisitModel.getRowCount() > 0)
					employeeInsertVisitModel.removeRow(0);

				String date = formattedTextFieldVisitInsertion.getText();

				try {
					visits = db.getBookedVisits(Employee.getInstance().getCompany(),
							(String) comboBoxClinicVisitInsertion.getSelectedItem(),
							new Date(sdf.parse(date).getTime()));
				} catch (ParseException e1) {
					formattedTextFieldVisitInsertion.setText("");
					JOptionPane.showMessageDialog(null, "Inserire una data valida nel formato gg/MM/aaaa",
							"Errore data", JOptionPane.WARNING_MESSAGE);
				}
				if (visits != null) {
					System.out.println("visits is not null " + visits.size());
					int i = 1;
					for (Visit c : visits) {
						System.out.println("element: i" );
						Vector<Object> vector = new Vector<Object>();
						vector.add(i++);
						vector.add(c.getPatient());
						vector.add(c.getServiceName());
						vector.add(c.getUrgency());
						vector.add(c.getHour());
						employeeInsertVisitModel.addRow(vector);
					}
				}
			}
		});
		panelInfovisitInsertionNorth.add(btnFindVisits);

		JScrollPane scrollPaneInfoVisitInsertionCenter = new JScrollPane();
		panelInfoVisitInsertion.add(scrollPaneInfoVisitInsertionCenter, BorderLayout.CENTER);

		tableVisitsFound = new JTable();
		tableVisitsFound.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVisitsFound.setModel(employeeInsertVisitModel);
		tableVisitsFound.getTableHeader().setReorderingAllowed(false);
		tableVisitsFound.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if(! event.getValueIsAdjusting()){
					System.out.println("selected row:" + tableVisitsFound.getSelectedRow());
					textAreaVisitResult.setEnabled(true);
				}
			}
		});

		scrollPaneInfoVisitInsertionCenter.setViewportView(tableVisitsFound);

		JScrollPane scrollPaneInfoVisitInsertionSouth = new JScrollPane();
		scrollPaneInfoVisitInsertionSouth.setViewportBorder(
				new TitledBorder(null, "Risultato visita", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfoVisitInsertion.add(scrollPaneInfoVisitInsertionSouth, BorderLayout.SOUTH);

		textAreaVisitResult = new JTextArea();
		textAreaVisitResult.setEnabled(false);
		textAreaVisitResult.setRows(3);
		// Listen for changes in the text
		textAreaVisitResult.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("insert update");
				if(!textAreaVisitResult.getText().isEmpty()){
					btnInsertNewVisitResult.setEnabled(true);
				}
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				System.out.println("insert remove");
				if(textAreaVisitResult.getText().isEmpty()){
					btnInsertNewVisitResult.setEnabled(false);
				}
			}
		});
		scrollPaneInfoVisitInsertionSouth.setViewportView(textAreaVisitResult);

		JPanel panelInsertVisitButton = new JPanel();
		FlowLayout fl_panelInsertVisitButton = (FlowLayout) panelInsertVisitButton.getLayout();
		fl_panelInsertVisitButton.setAlignment(FlowLayout.RIGHT);
		panelInsertVisitInfo.add(panelInsertVisitButton, BorderLayout.SOUTH);

		btnInsertNewVisitResult = new JButton("Inserisci");
		btnInsertNewVisitResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = tableVisitsFound.getSelectedRow();
				visits.get(selectedIndex).setResult(textAreaVisitResult.getText());
				db.updateVisitResult(visits.get(selectedIndex));

				btnFindVisits.doClick();
				textAreaVisitResult.setText(null);
				JOptionPane.showMessageDialog(null, "Il risultato della vista è stato inserito correttamente", "Informazione",JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnInsertNewVisitResult.setEnabled(false);
		panelInsertVisitButton.add(btnInsertNewVisitResult);

		JPanel panelInsertClinicMaster = new JPanel();
		tabbedPaneEmployee.addTab("Gestione ambulatori", null, panelInsertClinicMaster, null);
		GridBagLayout gbl_panelInsertClinicMaster = new GridBagLayout();
		gbl_panelInsertClinicMaster.columnWidths = new int[] { 579, 0 };
		gbl_panelInsertClinicMaster.rowHeights = new int[] { 147, 0, 288, 33, 0 };
		gbl_panelInsertClinicMaster.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelInsertClinicMaster.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelInsertClinicMaster.setLayout(gbl_panelInsertClinicMaster);

		JScrollPane scrollPaneClinicsTable = new JScrollPane();
		scrollPaneClinicsTable.setViewportBorder(new TitledBorder(null, "Ambulatori", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_scrollPaneClinicsTable = new GridBagConstraints();
		gbc_scrollPaneClinicsTable.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneClinicsTable.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneClinicsTable.gridx = 0;
		gbc_scrollPaneClinicsTable.gridy = 0;
		panelInsertClinicMaster.add(scrollPaneClinicsTable, gbc_scrollPaneClinicsTable);

		//tabella cliniche già presenti (visualizza per comodità o modifica)
		tableUpdateClinics = new JTable();
		tableUpdateClinics.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUpdateClinics.getTableHeader().setReorderingAllowed(false);
		tableUpdateClinicsModel = new DefaultTableModel(
				new String[] { "Nome", "Via", "CAP", "Citta'", "Provincia" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableUpdateClinics.setModel(tableUpdateClinicsModel);
		tableUpdateClinics.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && !panelInsertClinic.isVisible()) {
					System.out.println(tableUpdateClinics.getSelectedRow());
					visitIndex = tableUpdateClinics.getSelectedRow();
					btnEditClinic.setEnabled(true);
				}
			}
		});
		scrollPaneClinicsTable.setViewportView(tableUpdateClinics);

		JPanel panelInsertEditClinicButtons = new JPanel();
		GridBagConstraints gbc_panelInsertEditClinicButtons = new GridBagConstraints();
		gbc_panelInsertEditClinicButtons.insets = new Insets(0, 0, 5, 0);
		gbc_panelInsertEditClinicButtons.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelInsertEditClinicButtons.gridx = 0;
		gbc_panelInsertEditClinicButtons.gridy = 1;
		panelInsertClinicMaster.add(panelInsertEditClinicButtons, gbc_panelInsertEditClinicButtons);

		JButton btnAddNewClinic = new JButton("Aggiungi ambulatorio");
		panelInsertEditClinicButtons.add(btnAddNewClinic);
		btnEditClinic = new JButton("Modifica ambulatorio");
		btnEditClinic.setEnabled(false);
		panelInsertEditClinicButtons.add(btnEditClinic); 
		
		btnEditClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAddingNewClinic = false;
				int row = tableUpdateClinics.getSelectedRow();
				if(row == -1)
					return;
				
				// da modificare 
				btnEditClinic.setEnabled(false);
				btnAddNewClinic.setEnabled(false);
				panelInsertClinic.setVisible(true);
				panelInsertClinicButton.setVisible(true);
				Clinic clinic = editableClinics.get(row); 
				textFieldInsertClinicName.setText(clinic.getName());
				textFieldInsertClinicCity.setText(clinic.getCity());
				textFieldInsertClinicStreet.setText(clinic.getStreet());
				textFieldInsertClinicCAP.setText(clinic.getCAP());
				formattedTextFieldInsertClinicContractDate.setValue(clinic.getContractDate());
				comboBoxInsertClinicProvince.setSelectedItem(clinic.getProvince());
				textAreaInsertClinicDescription.setText(clinic.getDescription());
				//formatted
				//fill fields
				//memorizzo il nome corrente della clinica
				currentClinicName = clinic.getName();
				
			}
		});
		btnAddNewClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAddingNewClinic = true;
				// da modificare 
				btnAddNewClinic.setEnabled(false);
				btnEditClinic.setEnabled(false);
				panelInsertClinic.setVisible(true);
				panelInsertClinicButton.setVisible(true);
				
				
				//clear parameters
				textFieldInsertClinicName.setText("");
				textFieldInsertClinicCity.setText("");
				textFieldInsertClinicStreet.setText("");
				textFieldInsertClinicCAP.setText(""); 
				comboBoxInsertClinicProvince.setSelectedIndex(0);;
				textAreaInsertClinicDescription.setText("");
			}
		});

		//Insert clinic data + description
		panelInsertClinic = new JPanel();
		GridBagConstraints gbc_panelInsertClinic = new GridBagConstraints();
		gbc_panelInsertClinic.anchor = GridBagConstraints.NORTH;
		gbc_panelInsertClinic.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelInsertClinic.insets = new Insets(0, 0, 5, 0);
		gbc_panelInsertClinic.gridx = 0;
		gbc_panelInsertClinic.gridy = 2;
		panelInsertClinicMaster.add(panelInsertClinic, gbc_panelInsertClinic);
		panelInsertClinic.setLayout(new BorderLayout(0, 0));

		//clinic data
		JPanel panelInsertClinicData = new JPanel();
		panelInsertClinic.add(panelInsertClinicData, BorderLayout.WEST);
		panelInsertClinicData.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dati",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_panelInsertClinicData = new GridBagLayout();
		gbl_panelInsertClinicData.columnWidths = new int[] { 150, 0 };
		gbl_panelInsertClinicData.rowHeights = new int[] { 20, 14, 20, 14, 20, 14, 20, 14, 20, 14, 20, 14, 0 };
		gbl_panelInsertClinicData.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelInsertClinicData.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
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

		textFieldInsertClinicStreet = new JTextField();
		GridBagConstraints gbc_textFieldInsertClinicRue = new GridBagConstraints();
		gbc_textFieldInsertClinicRue.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertClinicRue.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertClinicRue.gridx = 0;
		gbc_textFieldInsertClinicRue.gridy = 3;
		panelInsertClinicData.add(textFieldInsertClinicStreet, gbc_textFieldInsertClinicRue);
		textFieldInsertClinicStreet.setColumns(10);


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

		textFieldInsertClinicCAP = new JTextField();
		GridBagConstraints gbc_textFieldInsertClinicZipCode = new GridBagConstraints();
		gbc_textFieldInsertClinicZipCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertClinicZipCode.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertClinicZipCode.gridx = 0;
		gbc_textFieldInsertClinicZipCode.gridy = 7;
		panelInsertClinicData.add(textFieldInsertClinicCAP, gbc_textFieldInsertClinicZipCode);
		textFieldInsertClinicCAP.setColumns(10);


		JLabel lblInsertClinicProvince = new JLabel("Provincia:");
		GridBagConstraints gbc_lblInsertClinicProvince = new GridBagConstraints();
		gbc_lblInsertClinicProvince.anchor = GridBagConstraints.WEST;
		gbc_lblInsertClinicProvince.insets = new Insets(0, 0, 5, 0);
		gbc_lblInsertClinicProvince.gridx = 0;
		gbc_lblInsertClinicProvince.gridy = 8;
		panelInsertClinicData.add(lblInsertClinicProvince, gbc_lblInsertClinicProvince);

		comboBoxInsertClinicProvince = new JComboBox<String>();
		comboBoxInsertClinicProvince.setModel(new DefaultComboBoxModel<String>(new String[] {"BL","PD","RO","TV","VE","VR","VI"}));
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

		formattedTextFieldInsertClinicContractDate = new JFormattedTextField(sdf);
		formattedTextFieldInsertClinicContractDate.setText(sdf.format(new java.util.Date())); 
		 
		GridBagConstraints gbc_formattedTextFieldInsertClinicContractDate = new GridBagConstraints();
		gbc_formattedTextFieldInsertClinicContractDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldInsertClinicContractDate.gridx = 0;
		gbc_formattedTextFieldInsertClinicContractDate.gridy = 11;
		panelInsertClinicData.add(formattedTextFieldInsertClinicContractDate,
				gbc_formattedTextFieldInsertClinicContractDate);
		//clinic description
		JScrollPane scrollPaneInsertClinicDescription = new JScrollPane();
		panelInsertClinic.add(scrollPaneInsertClinicDescription, BorderLayout.CENTER);
		scrollPaneInsertClinicDescription.setBorder(new TitledBorder(null, "Descrizione", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textAreaInsertClinicDescription = new JTextArea();
		scrollPaneInsertClinicDescription.setViewportView(textAreaInsertClinicDescription);

		//bottone inserisci clinica
		panelInsertClinicButton = new JPanel();
		GridBagConstraints gbc_panelInsertClinicButton = new GridBagConstraints();
		gbc_panelInsertClinicButton.anchor = GridBagConstraints.NORTH;
		gbc_panelInsertClinicButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelInsertClinicButton.gridx = 0;
		gbc_panelInsertClinicButton.gridy = 3;
		panelInsertClinicMaster.add(panelInsertClinicButton, gbc_panelInsertClinicButton);
		FlowLayout fl_panelInsertClinicButton = (FlowLayout) panelInsertClinicButton.getLayout();
		fl_panelInsertClinicButton.setAlignment(FlowLayout.RIGHT);


		//hide panels 
		panelInsertClinic.setVisible(false);
		panelInsertClinicButton.setVisible(false);

		JButton btnEditClinicOk = new JButton("OK");
		btnEditClinicOk.setEnabled(false);
		btnEditClinicOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//da modificare
				panelInsertClinic.setVisible(false);
				panelInsertClinicButton.setVisible(false);
				btnAddNewClinic.setEnabled(true);
				int row = tableUpdateClinics.getSelectedRow();
				if(row != -1) 
					btnEditClinic.setEnabled(true);

				Clinic c = new Clinic();
				c.setName(textFieldInsertClinicName.getText());
				c.setCAP(textFieldInsertClinicCAP.getText());
				c.setCity(textFieldInsertClinicCity.getText());
				c.setCompany(Employee.getInstance().getCompany());
				try { c.setContractDate(new Date(sdf.parse(formattedTextFieldInsertClinicContractDate.getText()).getTime()));}
				catch (ParseException e1) {}
				
				c.setDescription(textAreaInsertClinicDescription.getText());
				c.setProvince(comboBoxInsertClinicProvince.getSelectedItem().toString());
				c.setStreet(textFieldInsertClinicStreet.getText()); 
				 
				//add new
				if(isAddingNewClinic)
				{
					
					db.insertClinic(c);
					System.out.println("clinic added");
				}
				//edit 
				else
				{
					db.updateClinic(c);
					System.out.println("clinic updated");
				}
				
				refreshEditableClinics();
			}
			
		});
		panelInsertClinicButton.add(btnEditClinicOk);

		JButton btnEditClinicCancel = new JButton("Annulla"); 
		btnEditClinicCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//da modificare
				panelInsertClinic.setVisible(false);
				panelInsertClinicButton.setVisible(false);
				btnAddNewClinic.setEnabled(true);
				int row = tableUpdateClinics.getSelectedRow();
				if(row != -1) 
					btnEditClinic.setEnabled(true);
			}
		});
		panelInsertClinicButton.add(btnEditClinicCancel);
 
		DocumentListener textFieldListener = new DocumentListener() {

			private void updateEditClinicTextFields() {
				
				boolean okStatus = true;
				
				// TODO Auto-generated method stub
				if(textFieldInsertClinicName.getText().isEmpty() ||
						textFieldInsertClinicStreet.getText().isEmpty() ||
						textFieldInsertClinicCity.getText().isEmpty() ||
						textFieldInsertClinicCAP.getText().isEmpty() ||
						!formattedTextFieldInsertClinicContractDate.isEditValid() || 
						textAreaInsertClinicDescription.getText().isEmpty()
						) 
				 okStatus = false;
				
				if (textFieldInsertClinicCAP.getText().length() != 5 || !textFieldInsertClinicCAP.getText().matches("[0-9]+")) {
					okStatus=false;
					textFieldInsertClinicCAP.setBackground(Color.red);
				}
				else
					textFieldInsertClinicCAP.setBackground(Color.white);
				
				if (!duplicatedClinicName(textFieldInsertClinicName.getText(), currentClinicName)) {
					okStatus=false;
					textFieldInsertClinicName.setBackground(Color.red);
				}
				else
					textFieldInsertClinicName.setBackground(Color.white);
				
				
				btnEditClinicOk.setEnabled(okStatus);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e)
			{
				updateEditClinicTextFields(); 
			}

			

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateEditClinicTextFields();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("changed");
				
			}

		};

		//AGGIUNGO IL LISTENER A TUTTI I MIEI TEXTFIELD
		textFieldInsertClinicName.getDocument().addDocumentListener(textFieldListener); //serve il getDocument
		textFieldInsertClinicStreet.getDocument().addDocumentListener(textFieldListener);
		textFieldInsertClinicCity.getDocument().addDocumentListener(textFieldListener);
		textFieldInsertClinicCAP.getDocument().addDocumentListener(textFieldListener);
		textAreaInsertClinicDescription.getDocument().addDocumentListener(textFieldListener);
		

		insertClinicModel = new DefaultTableModel(
				new String[] { "Nome", "Via", "Paese", "CAP", "Provincia", "Data contratto", "" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};


		//fine panel inserisci clinica

		//INIZIO panel inserisci visit result
		panelVisitsResultsPerPatientMaster = new JPanel();
		tabbedPaneEmployee.addTab("Storico visite", null, panelVisitsResultsPerPatientMaster, null);
		panelVisitsResultsPerPatientMaster.setLayout(new CardLayout(0, 0));
		clpanelVisitsResultsPerPatientMaster  = (CardLayout) panelVisitsResultsPerPatientMaster.getLayout();

		JPanel panelViewVisitPerPatient = new JPanel();
		panelVisitsResultsPerPatientMaster.add(panelViewVisitPerPatient, "panelViewVisitPerPatient");
		panelViewVisitPerPatient.setLayout(new BorderLayout(0, 0));

		JPanel panelViewVisitPerPatientNorth = new JPanel();
		FlowLayout fl_panelViewVisitPerPatientNorth = (FlowLayout) panelViewVisitPerPatientNorth.getLayout();
		fl_panelViewVisitPerPatientNorth.setAlignment(FlowLayout.LEFT);
		panelViewVisitPerPatient.add(panelViewVisitPerPatientNorth, BorderLayout.NORTH);

		JLabel lblPaziente = new JLabel("Codice fiscale paziente:");
		panelViewVisitPerPatientNorth.add(lblPaziente);

		JFormattedTextField searchPatientTextField = new JFormattedTextField();
		panelViewVisitPerPatientNorth.add(searchPatientTextField);
		searchPatientTextField.setColumns(16);

		DefaultTableModel employeeHistoryModel = new DefaultTableModel(
				new String[] { "Data", "Ora", "Tipo visita", "Regime", "Urgenza" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// visualizza le visite di un paziente
		JButton btnVisualizza = new JButton("Cerca");
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// free table
				while (employeeHistoryModel.getRowCount() > 0)
					employeeHistoryModel.removeRow(0);

				visits = db.getVisitsHistory(searchPatientTextField.getText());
				if (visits != null) { 
					for (Visit c : visits) { 
						Vector<Object> vector = new Vector<Object>();

						vector.add(c.getDate().toString());
						vector.add(c.getHour());
						vector.add(c.getServiceName());
						vector.add(c.getRegime());
						vector.add(c.getUrgency());
						employeeHistoryModel.addRow(vector);
					}
				}
			}
		});
		panelViewVisitPerPatientNorth.add(btnVisualizza);

		JScrollPane scrollPaneViewVisitPerPatient = new JScrollPane();
		panelViewVisitPerPatient.add(scrollPaneViewVisitPerPatient, BorderLayout.CENTER);

		tableVisitsPatientResults = new JTable();
		tableVisitsPatientResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVisitsPatientResults.setModel(employeeHistoryModel);
		tableVisitsPatientResults.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					System.out.println(tableVisitsPatientResults.getSelectedRow());
					visitIndex = tableVisitsPatientResults.getSelectedRow();
					btnViewVisitSelectedResult.setEnabled(true);
				}
			}
		});
		tableVisitsPatientResults.getTableHeader().setReorderingAllowed(false);
		scrollPaneViewVisitPerPatient.setViewportView(tableVisitsPatientResults);
		JPanel panelViewVisitPerPatientSouth = new JPanel();
		FlowLayout fl_panelViewVisitPerPatientSouth = (FlowLayout) panelViewVisitPerPatientSouth.getLayout();
		fl_panelViewVisitPerPatientSouth.setAlignment(FlowLayout.RIGHT);
		panelViewVisitPerPatient.add(panelViewVisitPerPatientSouth, BorderLayout.SOUTH);

		btnViewVisitSelectedResult = new JButton("Visualizza");
		btnViewVisitSelectedResult.setEnabled(false);
		btnViewVisitSelectedResult.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Visit index : " + visitIndex);
				Visit v = visits.get(visitIndex);
				textPanePatientVisitResult.setText(v.toString());
				//clpanelVisitsResultsPerPatientMaster  = (CardLayout) panelVisitsResultsPerPatientMaster.getLayout();
				clpanelVisitsResultsPerPatientMaster.next(panelVisitsResultsPerPatientMaster);
				//clpanelVisitPatient.next(panelVisitPatient);
			}
		});
		panelViewVisitPerPatientSouth.add(btnViewVisitSelectedResult);

		JPanel panelViewVisitResultsPerPatient = new JPanel();
		panelVisitsResultsPerPatientMaster.add(panelViewVisitResultsPerPatient, "panelViewVisitResultsPerPatient");
		panelViewVisitResultsPerPatient.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPanepanelViewVisitResultsPerPatient = new JScrollPane();
		panelViewVisitResultsPerPatient.add(scrollPanepanelViewVisitResultsPerPatient, BorderLayout.CENTER);

		textPanePatientVisitResult = new JTextPane();
		textPanePatientVisitResult.setContentType("text/html");
		scrollPanepanelViewVisitResultsPerPatient.setViewportView(textPanePatientVisitResult);

		JPanel panelpanelViewVisitResultsPerPatientSouth = new JPanel();
		FlowLayout fl_panelpanelViewVisitResultsPerPatientSouth = (FlowLayout) panelpanelViewVisitResultsPerPatientSouth
				.getLayout();
		fl_panelpanelViewVisitResultsPerPatientSouth.setAlignment(FlowLayout.RIGHT);
		panelViewVisitResultsPerPatient.add(panelpanelViewVisitResultsPerPatientSouth, BorderLayout.SOUTH);

		JButton btnBackToViewVisitPerPatient = new JButton("Indietro");
		btnBackToViewVisitPerPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clcked back buttoon");
				clpanelVisitsResultsPerPatientMaster.previous(panelVisitsResultsPerPatientMaster);
			}
		});
		panelpanelViewVisitResultsPerPatientSouth.add(btnBackToViewVisitPerPatient);

		JButton btnPrintReaultVisitPatient = new JButton("Stampa");
		panelpanelViewVisitResultsPerPatientSouth.add(btnPrintReaultVisitPatient);

		JPanel panelEmployeeNorth = new JPanel();
		panelEmployee.add(panelEmployeeNorth, BorderLayout.NORTH);
		panelEmployeeNorth.setLayout(new BorderLayout(0, 0));

		lblWelcomeEmployee = new JLabel("Benvenuto ");
		lblWelcomeEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeEmployee.setFont(new Font("Tahoma", Font.BOLD, 22));
		panelEmployeeNorth.add(lblWelcomeEmployee, BorderLayout.CENTER);

		JPanel panelEmployeeNorthRightLabels = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelEmployeeNorthRightLabels.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(10);
		panelEmployeeNorth.add(panelEmployeeNorthRightLabels, BorderLayout.EAST);

		JLabel lblLogoutEmployee = new JLabel("<HTML>\r\n\t<p style=\"color:blue;\"><u>Logout</u></p>\r\n</HTML>");
		lblLogoutEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Employee clicked on Logout");
				clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(), "panelLogin");

				// qui va chiamata una funzione per resettare tutti i parametri
				// quindi distruggere tutti i pannelli dell'utente loggato.

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblLogoutEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});

		lblLogoutEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLogoutEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		panelEmployeeNorthRightLabels.add(lblLogoutEmployee);

		JPanel panelClinicsAndServices = new JPanel();
		frmHealthcareManagementSystem.getContentPane().add(panelClinicsAndServices, "panelClinicsAndServices");
		panelClinicsAndServices.setLayout(new BorderLayout(0, 0));

		JPanel panelClinicsAndServicesNorth = new JPanel();
		// sezione nord, combo box companies
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
				String companyID = companiesList.get(comboBoxIndex)[0]; // [0] e' il primo parametro, ovvero l'id della clinica

				ArrayList<Clinic> clinics = db.getClinics(companyID);
				int selectedIndex = listClinics.getSelectedIndex();

				if (selectedIndex >= 0) { // quando cambio contenuto in
					// combobox mi ritorna -1 e mi fa
					// segfault in clinics.get
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
				clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(), "panelLogin");
			}
		});
		panelClinicAndServicesButton.add(btnBackToLogin);

		frmHealthcareManagementSystem.setSize(700, 600);
		frmHealthcareManagementSystem.setLocationRelativeTo(null);
		initializeCalendar();
		// frame.pack();
		// frame.setResizable(false);
	}

	private void initializeCalendar() {
		/*
		 * // DA CAMBIARE IN GRID BAG LAYOUT lbldays = new JLabel[DAYSR][DAYSC];
		 * System.out.println("qui valentina: "+comboBoxSelectBookVisitMonth.
		 * getSelectedItem());
		 * 
		 * // Get the number of days in that month YearMonth yearMonthObject =
		 * YearMonth.of(2017, 1); int daysInMonth =
		 * yearMonthObject.lengthOfMonth(); //28
		 * 
		 * 
		 * // header for(int i = 0; i < 1; i++){ for(int j = 0; j < DAYSC; j++){
		 * lbldays[i][j] = new JLabel(""+Days.values()[j],
		 * SwingConstants.CENTER); lbldays[i][j].setBorder(new
		 * LineBorder(Color.LIGHT_GRAY)); lbldays[i][j].setOpaque(true);
		 * lbldays[i][j].setBackground(Color.decode("0x87CEEB"));
		 * 
		 * GridBagConstraints gbc_lblDays = new GridBagConstraints();
		 * gbc_lblDays.insets = new Insets(0, 0, 0, 0); gbc_lblDays.gridx = j;
		 * gbc_lblDays.gridy = i;
		 * bookVisitCenterCalendarPanel.add(lbldays[i][j], gbc_lblDays); } }
		 */

		/*
		 * // cells int day = 1; for(int i = 1; i < DAYSR; i++){ for(int j = 0;
		 * j < DAYSC ; j++){ if(day > daysInMonth){ lbldays[i][j] = new
		 * JLabel("", SwingConstants.CENTER); lbldays[i][j].setBorder(new
		 * LineBorder(Color.LIGHT_GRAY)); GridBagConstraints gbc_lblDays = new
		 * GridBagConstraints(); gbc_lblDays.insets = new Insets(0, 0, 0, 0);
		 * gbc_lblDays.gridx = j; gbc_lblDays.gridy = i;
		 * bookVisitCenterCalendarPanel.add(lbldays[i][j], gbc_lblDays); } else{
		 * lbldays[i][j] = new JLabel(""+day++, SwingConstants.CENTER);
		 * lbldays[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY));
		 * GridBagConstraints gbc_lblDays = new GridBagConstraints();
		 * gbc_lblDays.insets = new Insets(0, 0, 0, 0); gbc_lblDays.gridx = j;
		 * gbc_lblDays.gridy = i;
		 * bookVisitCenterCalendarPanel.add(lbldays[i][j], gbc_lblDays); } } }
		 */

	}
}
