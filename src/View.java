import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
	private ArrayList<Visit> patientVisits;

	private final String company = "cp02";

	private JLabel[][] lbldays;
	private static final int DAYSR = 6;
	private static final int DAYSC = 7;
	private JComboBox<Integer> selectedMonth;
	private JComboBox<String> comboBoxSelectBookVisitMonth;
	JComboBox<String> comboBoxClinicVisitInsertion;
	private JComboBox<String> comboBoxVisitsYear;
	private DefaultTableModel visitsHistoryModel;
	private JButton btnViewVisitsPatient;
	private CardLayout clpanelVisitPatient;
	private JPanel panelVisitPatient;

	private JList<String> listClinics;
	private ArrayList<String[]> companiesList;
	private JComboBox<String> comboBoxSelectCompany;

	private JTextPane txtpnVisitResultInfo;


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

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmHealthcareManagementSystem = new JFrame("HEALTHCARE MANAGEMENT SYSTEM");
		frmHealthcareManagementSystem
		.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/img/caduceus.png")));
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

		JButton btnLoginPatient = new JButton("Login");
		GridBagConstraints gbc_btnLoginPatient = new GridBagConstraints();
		gbc_btnLoginPatient.gridx = 1;
		gbc_btnLoginPatient.gridy = 3;
		panelCenterPatientLogin.add(btnLoginPatient, gbc_btnLoginPatient);

		btnLoginPatient.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String fiscalCode = formattedTextFieldFiscalCode.getText();
				String passwd = new String(passwordFieldPIN.getPassword());

				if (db.checkPatient(fiscalCode, passwd)) {
					lblWeolcomePatient.setText(lblWeolcomePatient.getText() + " " + Patient.getInstance().getName()
							+ " " + Patient.getInstance().getSurname());
					clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(), "panelPatient");

					// clean fiscal code and password
					formattedTextFieldFiscalCode.setText("");
					passwordFieldPIN.setText("");

					// get years
					ArrayList<Integer> years = db.getVisitsHistoryYears(fiscalCode);
					for(Integer y : years)
						comboBoxVisitsYear.addItem(y.toString());

					//fill table (visits history) 
					int i = 1;
					patientVisits = db.getVisitsHistory(fiscalCode);
					if (patientVisits != null) {
						System.out.println("visits is not null " + patientVisits.size());
						for (Visit c : patientVisits) {
							System.out.println("element i");
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


				} else {
					System.out.println("credenziali errate: " + fiscalCode + " " + passwd);

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
		textField_usr.setText("RFCVCV85A42E952E");
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
		JButton btnLoginEmployee = new JButton("Login");
		btnLoginEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textField_usr.getText();
				String pass = new String(textField_passwd.getPassword());
				if (db.checkEmployee(user, pass)) {
					System.out.println("ok");
					clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(), "panelEmployee");
					//add clinics
					ArrayList<Clinic> clinics = db.getClinics(Employee.getInstance().getCompany());
					System.out.println(clinics.size() + "  " + Employee.getInstance().getCompany());
					for(Clinic c : clinics)
						comboBoxClinicVisitInsertion.addItem(c.getName());
				}
				else{
					System.out.println("wrong credentials: " + user + " " + pass );

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
				
				for (String[] o: companiesList) {
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
		lblLogoutPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Patient clicked on Logout");
				clfrmHealhcareManagementSystem.show(frmHealthcareManagementSystem.getContentPane(), "panelLogin");
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

		// questa tabella si puo' implementare in maniera piu' pulita una volta
		// completati i metodi con il db, vedi tests fatto su w10 (simone)
		tableHistoryVisits = new JTable();
		tableHistoryVisits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		visitsHistoryModel = new DefaultTableModel(new String[] { "N\u00B0", "Data", "Ora", "Tipo Visita", "Regime", "Urgenza" }, 0);
		tableHistoryVisits.setModel(visitsHistoryModel);
		tableHistoryVisits.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableHistoryVisits.getColumnModel().getColumn(0).setMaxWidth(25);
		tableHistoryVisits.getTableHeader().setReorderingAllowed(false);
		tableHistoryVisits.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// do some actions here, for example
				// print first column value from selected row
				if (!event.getValueIsAdjusting()) {
					System.out.println(tableHistoryVisits.getValueAt(tableHistoryVisits.getSelectedRow(), 0).toString());
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
				clpanelVisitPatient.next(panelVisitPatient);
				int visitIndex = (int)tableHistoryVisits.getValueAt(tableHistoryVisits.getSelectedRow(), 0) - 1;
				Visit v = patientVisits.get(visitIndex);
				txtpnVisitResultInfo.setText(v.toHTML(Patient.getInstance().getName(), Patient.getInstance().getSurname()));
				//patientVisits
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

		JComboBox comboBoxSelectBookVisitType = new JComboBox();

		JLabel lblSelectBookVisitYear = new JLabel("Anno:");

		JComboBox<Integer> comboBoxSelectBookVisitYear = new JComboBox<Integer>();
		comboBoxSelectBookVisitYear.addItem(Calendar.getInstance().get(Calendar.YEAR));
		comboBoxSelectBookVisitYear.addItem(Calendar.getInstance().get(Calendar.YEAR) + 1);

		JLabel lblSelectBookVisitMonth = new JLabel("Mese:");

		comboBoxSelectBookVisitMonth = new JComboBox<String>();
		comboBoxSelectBookVisitMonth.setModel(new DefaultComboBoxModel(Month.values()));

		JLabel lblSelectBookVisitDay = new JLabel("Giorno:");

		JComboBox comboBoxSelectBookVisitDAy = new JComboBox();

		JLabel lblSelectBookVisitHour = new JLabel("Ora:");

		JComboBox comboBoxSelectBookVisitHour = new JComboBox();
		JLabel lblSelectBookVisitUrgency = new JLabel("Urgenza:");

		JComboBox comboBoxSelectBookVisitUrgency = new JComboBox();
		comboBoxSelectBookVisitUrgency.setModel(new DefaultComboBoxModel(new String[] { "Bassa", "Medio", "Alta" }));

		JLabel lblSelectBookVisitRegime = new JLabel("Regime:");

		JComboBox comboBoxSelectBookVisitRegime = new JComboBox();

		JLabel lblAmbultorio = new JLabel("Ambulatorio:");
		lblAmbultorio.setHorizontalAlignment(SwingConstants.RIGHT);

		JComboBox comboBox = new JComboBox();
		GroupLayout gl_bookVisitCenterPanel = new GroupLayout(bookVisitCenterPanel);
		gl_bookVisitCenterPanel.setHorizontalGroup(
				gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_bookVisitCenterPanel.createSequentialGroup()
										.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
														.addContainerGap()
														.addComponent(lblAmbultorio, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
												.addGroup(Alignment.LEADING, gl_bookVisitCenterPanel.createSequentialGroup()
														.addGap(197)
														.addComponent(lblSelectBookVisitType)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(comboBoxSelectBookVisitType, 0, 248, Short.MAX_VALUE)
												.addComponent(comboBox, 0, 248, Short.MAX_VALUE)))
								.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
										.addContainerGap(205, Short.MAX_VALUE)
										.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
														.addComponent(lblSelectBookVisitUrgency)
														.addGap(5)
														.addComponent(comboBoxSelectBookVisitUrgency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
														.addGap(5)
														.addComponent(lblSelectBookVisitRegime)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(comboBoxSelectBookVisitRegime, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
														.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING, false)
																.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
																		.addGap(10)
																		.addComponent(lblSelectBookVisitDay)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(comboBoxSelectBookVisitDAy, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
																		.addGap(15)
																		.addComponent(lblSelectBookVisitYear)
																		.addGap(5)
																		.addComponent(comboBoxSelectBookVisitYear, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))
														.addGap(35)
														.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING)
																.addComponent(lblSelectBookVisitMonth)
																.addComponent(lblSelectBookVisitHour))
														.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
																		.addGap(5)
																		.addComponent(comboBoxSelectBookVisitMonth, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(comboBoxSelectBookVisitHour, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))))))
						.addGap(173))
				);
		gl_bookVisitCenterPanel.setVerticalGroup(
				gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
						.addGap(95)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSelectBookVisitType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectBookVisitType))
						.addGap(35)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAmbultorio)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
										.addGap(3)
										.addComponent(lblSelectBookVisitYear))
								.addComponent(comboBoxSelectBookVisitYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
										.addGap(3)
										.addComponent(lblSelectBookVisitMonth))
								.addComponent(comboBoxSelectBookVisitMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(32)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSelectBookVisitDay)
								.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSelectBookVisitHour)
										.addComponent(comboBoxSelectBookVisitHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBoxSelectBookVisitDAy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
										.addGap(3)
										.addComponent(lblSelectBookVisitUrgency))
								.addComponent(comboBoxSelectBookVisitUrgency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(38)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSelectBookVisitRegime)
								.addComponent(comboBoxSelectBookVisitRegime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(72))
				);
		bookVisitCenterPanel.setLayout(gl_bookVisitCenterPanel);

		JLabel lblLuned = new JLabel("Luned\u00EC");
		JLabel lblMarted = new JLabel("Marted\u00EC");

		JPanel bookVisitSouthButtonPanel = new JPanel();
		bookVisitSouthButtonPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		FlowLayout fl_bookVisitSouthButtonPanel = (FlowLayout) bookVisitSouthButtonPanel.getLayout();
		fl_bookVisitSouthButtonPanel.setAlignment(FlowLayout.RIGHT);
		panelBookVisit.add(bookVisitSouthButtonPanel, BorderLayout.SOUTH);

		JButton btnBookVisit = new JButton("Prenota");
		btnBookVisit.setEnabled(false);
		btnBookVisit.setToolTipText("Clicca per prenotare");
		bookVisitSouthButtonPanel.add(btnBookVisit);

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

		comboBoxClinicVisitInsertion = new JComboBox<String>();
		panelInfovisitInsertionNorth.add(comboBoxClinicVisitInsertion);

		DefaultTableModel employeeInsertModel = new DefaultTableModel(new String[] {  "Codice Fiscale", "Tipo visita", "Urgenza", "Ora" }, 0);

		JButton btnFindVisits = new JButton("Trova");
		btnFindVisits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// free table
				while (employeeInsertModel.getRowCount() > 0)
					employeeInsertModel.removeRow(0);

				String date = formattedTextFieldVisitInsertion.getText();

				ArrayList<Visit> visits = null;
				try {
					visits = db.getBookedVisits(Employee.getInstance().getCompany(),
							(String) comboBoxClinicVisitInsertion.getSelectedItem(),
							new Date(sdf.parse(date).getTime()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				if (visits != null) {
					System.out.println("visits is not null " + visits.size());
					for (Visit c : visits) {
						System.out.println("element i");
						Vector<Object> vector = new Vector<Object>();

						vector.add(c.getPatient());
						vector.add(c.getServiceName());
						vector.add(c.getUrgency());
						vector.add(c.getHour()); 
						employeeInsertModel.addRow(vector);
					}
				}
			}
		});
		panelInfovisitInsertionNorth.add(btnFindVisits);

		JScrollPane scrollPaneInfoVisitInsertionCenter = new JScrollPane();
		panelInfoVisitInsertion.add(scrollPaneInfoVisitInsertionCenter, BorderLayout.CENTER);

		tableVisitsFounded = new JTable();
		tableVisitsFounded.setModel(employeeInsertModel);

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
		btnInsertNewVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked inserisci");
			}
		});
		btnInsertNewVisit.setEnabled(false);
		panelInsertVisitButton.add(btnInsertNewVisit);

		JPanel panelInsertClinicMaster = new JPanel();
		tabbedPaneEmployee.addTab("Inserisci ambulatorio", null, panelInsertClinicMaster, null);
		GridBagLayout gbl_panelInsertClinicMaster = new GridBagLayout();
		gbl_panelInsertClinicMaster.columnWidths = new int[] { 579, 0 };
		gbl_panelInsertClinicMaster.rowHeights = new int[] { 288, 75, 33, 0 };
		gbl_panelInsertClinicMaster.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelInsertClinicMaster.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
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
		panelInsertClinicData.add(formattedTextFieldInsertClinicContractDate,
				gbc_formattedTextFieldInsertClinicContractDate);

		JScrollPane scrollPaneInsertClinicDescription = new JScrollPane();
		panelInsertClinic.add(scrollPaneInsertClinicDescription, BorderLayout.CENTER);
		scrollPaneInsertClinicDescription
		.setBorder(new TitledBorder(null, "Descrizione", TitledBorder.LEADING, TitledBorder.TOP, null, null));

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
		tableClinics.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
				new String[] { "Nome", "Via", "Paese", "CAP", "Provincia", "Data contratto", "" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, Object.class };

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

		DefaultTableModel employeeHistoryModel = new DefaultTableModel(
				new String[] { "Data", "Ora", "Tipo visita", "Regime", "Urgenza" }, 0);

		// visualizza le visite di un paziente
		JButton btnVisualizza = new JButton("Cerca");
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// free table
				while (employeeHistoryModel.getRowCount() > 0)
					employeeHistoryModel.removeRow(0);

				ArrayList<Visit> visits = db.getVisitsHistory(searchPatientTextField.getText());
				if (visits != null) {
					System.out.println("visits is not null " + visits.size());
					for (Visit c : visits) {
						System.out.println("element i");
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
		tableVisitsPatientResults.setModel(employeeHistoryModel);
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
		FlowLayout fl_panelpanelViewVisitResultsPerPatientSouth = (FlowLayout) panelpanelViewVisitResultsPerPatientSouth
				.getLayout();
		fl_panelpanelViewVisitResultsPerPatientSouth.setAlignment(FlowLayout.RIGHT);
		panelViewVisitResultsPerPatient.add(panelpanelViewVisitResultsPerPatientSouth, BorderLayout.SOUTH);

		JButton btnBackToViewVisitPerPatient = new JButton("Indietro");
		panelpanelViewVisitResultsPerPatientSouth.add(btnBackToViewVisitPerPatient);

		JButton btnPrintReaultVisitPatient = new JButton("Stampa");
		panelpanelViewVisitResultsPerPatientSouth.add(btnPrintReaultVisitPatient);

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
		
		JPanel panelClinicsAndServicesNorth = new JPanel();
		//sezione nord, combo box companies
		FlowLayout fl_panelClinicsAndServicesNorth = (FlowLayout) panelClinicsAndServicesNorth.getLayout();
		fl_panelClinicsAndServicesNorth.setAlignment(FlowLayout.LEFT);
		panelClinicsAndServices.add(panelClinicsAndServicesNorth, BorderLayout.NORTH);

		JLabel lblSelectCompany = new JLabel("Seleziona azienda:");
		panelClinicsAndServicesNorth.add(lblSelectCompany);

		comboBoxSelectCompany = new JComboBox<String>();
		listClinics = new JList<String>();
		listClinics.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(listClinics.getSelectedIndex());
			}
		});
		
		
		
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
				
				
				
			}
		});
		panelClinicsAndServicesNorth.add(comboBoxSelectCompany);
		
		JSplitPane splitPaneClinics = new JSplitPane();
		panelClinicsAndServices.add(splitPaneClinics, BorderLayout.CENTER);
		splitPaneClinics.setResizeWeight(0.3);

		JPanel ClinicsPanel = new JPanel();
		ClinicsPanel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		splitPaneClinics.setLeftComponent(ClinicsPanel);
		ClinicsPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblClinics = new JLabel("Ambulatori");
		lblClinics.setHorizontalAlignment(SwingConstants.CENTER);
		ClinicsPanel.add(lblClinics, BorderLayout.NORTH);

		/*ListSelectionModel listSelectionModel = listClinics.getSelectionModel();
		
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(clinics.get(e.getFirstIndex()).getName());
			}
			
		});*/
		
		
		
		ClinicsPanel.add(listClinics, BorderLayout.CENTER);

		JPanel ServicesPanel = new JPanel();
		splitPaneClinics.setRightComponent(ServicesPanel);
		ServicesPanel.setLayout(new BorderLayout(0, 0));

		JTextPane txtClinic = new JTextPane();
		txtClinic.setContentType("text/html");
		
		txtClinic.setText(
				"<html>\r\n\t<head></head>\r\n\t<body>\r\n\t\t<h1>Clinics of Jesus</h1>\r\n\t\t<h3>Services</h3>\r\n\t\t.....Prova html.....\r\n\t</body>\r\n</html>\r\n");
		ServicesPanel.add(txtClinic, BorderLayout.CENTER);

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
