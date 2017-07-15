import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
 
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Employee extends JFrame { 
	
	//private String fiscalcode;
	//private String employeeCode;
	private String name;
	private String surname;
	//private String job;
	//private String clinic;
	private String company;



	// interface variables
	private ArrayList<Visit> visits;
	private JTextField textFieldInsertClinicStreet;
	private JTextField textFieldInsertClinicCity;
	private JTextField textFieldInsertClinicCAP;
	private JComboBox<String> comboBoxInsertClinicProvince;
	private JFormattedTextField formattedTextFieldInsertClinicContractDate;
	private JTextArea textAreaInsertClinicDescription;
	protected int visitIndex;
	protected boolean isAddingNewClinic;
	protected String currentClinicName;
	protected ArrayList<Clinic> editableClinics;
	
	public Employee(Map<String, Object> map) { 

		setParamenters(map);
		setTitle("HEALTHCARE MANAGEMENT SYSTEM");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Employee.class.getResource("/img/healthcare-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//getContentPane().setLayout(new CardLayout(0, 0));                                                                                                // card layout inutile
		//this.clfrmHealhcareManagementSystem = (CardLayout) frmHealthcareManagementSystem.getContentPane().getLayout();

		initializeView();

		setSize(700, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	private void OpenLoginWindow() {
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

	private void refreshEditableClinics(DefaultTableModel model, ArrayList<Clinic> clinics) {
		// clear
		while (model.getRowCount() > 0)
			model.removeRow(0);

		// QUESTA DIVENTERA UNA FUNZIONE PER RIEMPIRE LA TABELLA DI CLINICHE IN
		// INSERISCI AMBULATORIO___________________
		 
		clinics = Database.getInstance().getClinics(company);
		 

		int i = 1;
		if (clinics != null) {
			for (Clinic c : clinics) {

				Vector<Object> vector = new Vector<Object>();

				vector.add(i++);
				vector.add(c.getName());
				vector.add(c.getStreet());
				vector.add(c.getCAP());
				vector.add(c.getCity());
				vector.add(c.getProvince());
				model.addRow(vector);

				System.out.println("clinica " + c.getName());

			}
		}
	}

	private void setParamenters(Map<String, Object> map) {
		//fiscalcode = rs.getString("fiscalcode"); 
		name = (String) map.get("name");
		//clinic = rs.getString("clinic"); 
		company = (String) map.get("company");
		//job = rs.getString("job");
		surname = (String) map.get("surname");
	}

	// ----------------------------------------------- EMPLOYEE SECTION -------------------------------------------------------------------
	private void initializeView() {
		/*
		JTable tableVisitsFound;
		JTextArea textAreaVisitResult;
		JButton btnInsertNewVisitResult;
		JTable tableUpdateClinics;
		DefaultTableModel tableUpdateClinicsModel;
		JPanel panelInsertClinic;
	 
		JTable tableHistoryVisits; 
		JTextField textFieldInsertClinicStreet;
		JTextField textFieldInsertClinicCity;
		JTextField textFieldInsertClinicCAP;
		JTextField textFieldInsertClinicName;
		JTable tableVisitsPatientResults;
		CardLayout clfrmHealhcareManagementSystem;

		JLabel lblWelcomePatient;
		ArrayList<Visit> patientVisits;

		JComboBox<String> comboBoxSelectBookVisitMonth; 
		JComboBox<String> comboBoxVisitsYear;
		DefaultTableModel visitsHistoryModel;
		JButton btnViewVisitsPatient;
		CardLayout clpanelVisitPatient;
		JPanel panelVisitPatient;

		JList<String> listClinics;
		ArrayList<String[]> companiesList;
		JComboBox<String> comboBoxSelectCompany;

		JTextPane txtpnVisitResultInfo;
		JLabel lblWelcomeEmployee;

		JComboBox<String> comboBoxSelectBookVisitType; 

		JComboBox<Integer> comboBoxSelectBookVisitDay;

		JComboBox<Integer> comboBoxSelectBookVisitYear;
		ArrayList<String[]> serviceInfos;
		JComboBox<String> comboBoxSelectBookVisitClinic;
		JComboBox<Integer> comboBoxSelectBookVisitHour; 
		JTextField textFieldRegime; 
		int visitIndex;
		JButton btnViewVisitSelectedResult;
		CardLayout clpanelVisitsResultsPerPatientMaster;
		JPanel panelVisitsResultsPerPatientMaster;
		JTextPane textPanePatientVisitResult; 
		JPanel panelInsertClinicButton; 
		ArrayList<Clinic> editableClinics;
		JFormattedTextField formattedTextFieldInsertClinicContractDate;
		JComboBox<String> comboBoxInsertClinicProvince;
		JTextArea textAreaInsertClinicDescription;
		boolean isAddingNewClinic = false;
		JButton btnEditClinic;
		String currentClinicName;
		*/
		
		
		JPanel panelEmployee = new JPanel();
		getContentPane().add(panelEmployee, BorderLayout.CENTER);
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		JFormattedTextField formattedTextFieldVisitInsertion = new JFormattedTextField(sdf);
		formattedTextFieldVisitInsertion.setToolTipText("Inserisci la data della visita nel formato gg/mm/aaaa");
		formattedTextFieldVisitInsertion.setColumns(10);
		panelInfovisitInsertionNorth.add(formattedTextFieldVisitInsertion);

		JLabel lblClinicVisitInsertion = new JLabel("Ambulatorio:");
		panelInfovisitInsertionNorth.add(lblClinicVisitInsertion);

		//components



		JComboBox<String> comboBoxClinicVisitInsertion = new JComboBox<String>();
		comboBoxClinicVisitInsertion.setPreferredSize(new Dimension(150, 27));
		comboBoxClinicVisitInsertion.setMaximumRowCount(10);
		panelInfovisitInsertionNorth.add(comboBoxClinicVisitInsertion);

		DefaultTableModel employeeInsertVisitModel = new DefaultTableModel(
				new String[] { "N\u00B0", "Codice Fiscale", "Tipo visita", "Urgenza", "Ora" }, 0) {
			private static final long serialVersionUID = 6L;

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
					visits = Database.getInstance().getBookedVisits(company,
							(String) comboBoxClinicVisitInsertion.getSelectedItem(),
							new Date(sdf.parse(date).getTime()));
				} catch (ParseException e1) {
					formattedTextFieldVisitInsertion.setText("");
					JOptionPane.showMessageDialog(null, "Inserire una data valida nel formato gg/MM/aaaa",
							"Errore data", JOptionPane.WARNING_MESSAGE);
				}
				if (visits != null) {
					if (visits.size() == 0) {
						JOptionPane.showMessageDialog(null, "Nessuna visita trovata in data:" + date, "Infomazione",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						int i = 1;
						for (Visit c : visits) {
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

			}
		});
		panelInfovisitInsertionNorth.add(btnFindVisits);

		JScrollPane scrollPaneInfoVisitInsertionCenter = new JScrollPane();
		panelInfoVisitInsertion.add(scrollPaneInfoVisitInsertionCenter, BorderLayout.CENTER);

		JTable tableVisitsFound = new JTable();
		tableVisitsFound.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVisitsFound.setModel(employeeInsertVisitModel);
		tableVisitsFound.getTableHeader().setReorderingAllowed(false);
		tableVisitsFound.getColumnModel().getColumn(0).setPreferredWidth(25);
		JTextArea textAreaVisitResult = new JTextArea();
		tableVisitsFound.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
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
 
		textAreaVisitResult.setEnabled(false);
		textAreaVisitResult.setRows(3);
		JButton btnInsertNewVisitResult = new JButton("Inserisci");
		// Listen for changes in the text
		textAreaVisitResult.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (!textAreaVisitResult.getText().isEmpty()) { 
					btnInsertNewVisitResult.setEnabled(true);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (textAreaVisitResult.getText().isEmpty()) {
					btnInsertNewVisitResult.setEnabled(false);
				}
			}
		});
		scrollPaneInfoVisitInsertionSouth.setViewportView(textAreaVisitResult);

		JPanel panelInsertVisitButton = new JPanel();
		FlowLayout fl_panelInsertVisitButton = (FlowLayout) panelInsertVisitButton.getLayout();
		fl_panelInsertVisitButton.setAlignment(FlowLayout.RIGHT);
		panelInsertVisitInfo.add(panelInsertVisitButton, BorderLayout.SOUTH);

		btnInsertNewVisitResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = tableVisitsFound.getSelectedRow();
				visits.get(selectedIndex).setResult(textAreaVisitResult.getText());
				Database.getInstance().updateVisitResult(visits.get(selectedIndex));
				String email = Database.getInstance().getPatientEmail((String) tableVisitsFound.getValueAt(selectedIndex, 1));

				textAreaVisitResult.setText(null);

				if(email != null)
					JOptionPane.showMessageDialog(null, "Il risultato della vista è stato inserito correttamente.\nUna mail di notifica e' stata inviata all'indirizzo: " + email,"Informazione", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Il risultato della vista è stato inserito correttamente.","Informazione", JOptionPane.INFORMATION_MESSAGE);

				btnFindVisits.doClick();
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
		GridBagConstraints gbc_scrollPaneClinicsTable = new GridBagConstraints();
		gbc_scrollPaneClinicsTable.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneClinicsTable.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneClinicsTable.gridx = 0;
		gbc_scrollPaneClinicsTable.gridy = 0;
		panelInsertClinicMaster.add(scrollPaneClinicsTable, gbc_scrollPaneClinicsTable);

		// tabella cliniche già presenti (visualizza per comodità o modifica)
		JTable tableUpdateClinics = new JTable();
		tableUpdateClinics.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUpdateClinics.getTableHeader().setReorderingAllowed(false);
		DefaultTableModel tableUpdateClinicsModel = new DefaultTableModel(new String[] {"N\u00B0", "Nome", "Via", "CAP", "Citta'", "Provincia" },0) {

			private static final long serialVersionUID = 3L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableUpdateClinics.setModel(tableUpdateClinicsModel);
		tableUpdateClinics.getColumnModel().getColumn(0).setPreferredWidth(25);
		JButton btnEditClinic = new JButton("Modifica ambulatorio");
		final JPanel panelInsertClinic = new JPanel();
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
		btnEditClinic.setEnabled(false);
		panelInsertEditClinicButtons.add(btnEditClinic);

		JTextField textFieldInsertClinicName = new JTextField();;
		final JPanel panelInsertClinicButton = new JPanel();;
		btnEditClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAddingNewClinic = false;
				int row = tableUpdateClinics.getSelectedRow();
				if (row == -1)
					return;

				// da modificare
				tableUpdateClinics.setEnabled(false);
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
				// formatted
				// fill fields
				// memorizzo il nome corrente della clinica
				currentClinicName = clinic.getName();

			}
		});
		btnAddNewClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAddingNewClinic = true;
				// da modificare
				tableUpdateClinics.clearSelection();
				tableUpdateClinics.setEnabled(false);
				btnAddNewClinic.setEnabled(false);
				btnEditClinic.setEnabled(false);
				panelInsertClinic.setVisible(true);
				panelInsertClinicButton.setVisible(true);

				// clear parameters
				textFieldInsertClinicName.setText("");
				textFieldInsertClinicCity.setText("");
				textFieldInsertClinicStreet.setText("");
				textFieldInsertClinicCAP.setText("");
				comboBoxInsertClinicProvince.setSelectedIndex(0);
				;
				textAreaInsertClinicDescription.setText("");
			}
		});

		// Insert clinic data + description 
		GridBagConstraints gbc_panelInsertClinic = new GridBagConstraints();
		gbc_panelInsertClinic.anchor = GridBagConstraints.NORTH;
		gbc_panelInsertClinic.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelInsertClinic.insets = new Insets(0, 0, 5, 0);
		gbc_panelInsertClinic.gridx = 0;
		gbc_panelInsertClinic.gridy = 2;
		panelInsertClinicMaster.add(panelInsertClinic, gbc_panelInsertClinic);
		panelInsertClinic.setLayout(new BorderLayout(0, 0));

		// clinic data
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
		comboBoxInsertClinicProvince
		.setModel(new DefaultComboBoxModel<String>(new String[] { "BL", "PD", "RO", "TV", "VE", "VR", "VI" }));
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
		// clinic description
		JScrollPane scrollPaneInsertClinicDescription = new JScrollPane();
		panelInsertClinic.add(scrollPaneInsertClinicDescription, BorderLayout.CENTER);
		scrollPaneInsertClinicDescription
		.setBorder(new TitledBorder(null, "Descrizione", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textAreaInsertClinicDescription = new JTextArea();
		scrollPaneInsertClinicDescription.setViewportView(textAreaInsertClinicDescription);

		// bottone inserisci clinica
		GridBagConstraints gbc_panelInsertClinicButton = new GridBagConstraints();
		gbc_panelInsertClinicButton.anchor = GridBagConstraints.NORTH;
		gbc_panelInsertClinicButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelInsertClinicButton.gridx = 0;
		gbc_panelInsertClinicButton.gridy = 3;
		panelInsertClinicMaster.add(panelInsertClinicButton, gbc_panelInsertClinicButton);
		FlowLayout fl_panelInsertClinicButton = (FlowLayout) panelInsertClinicButton.getLayout();
		fl_panelInsertClinicButton.setAlignment(FlowLayout.RIGHT);

		// hide panels
		panelInsertClinic.setVisible(false);
		panelInsertClinicButton.setVisible(false);

		JButton btnEditClinicOk = new JButton("OK");
		btnEditClinicOk.setEnabled(false);
		btnEditClinicOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// da modificare
				tableUpdateClinics.setEnabled(true);
				panelInsertClinic.setVisible(false);
				panelInsertClinicButton.setVisible(false);
				btnAddNewClinic.setEnabled(true);
				int row = tableUpdateClinics.getSelectedRow();
				if (row != -1)
					btnEditClinic.setEnabled(true);

				Clinic c = new Clinic();
				c.setName(textFieldInsertClinicName.getText());
				c.setCAP(textFieldInsertClinicCAP.getText());
				c.setCity(textFieldInsertClinicCity.getText());
				c.setCompany(company);
				try {
					c.setContractDate(
							new Date(sdf.parse(formattedTextFieldInsertClinicContractDate.getText()).getTime()));
				} catch (ParseException e1) {
				}

				c.setDescription(textAreaInsertClinicDescription.getText());
				c.setProvince(comboBoxInsertClinicProvince.getSelectedItem().toString());
				c.setStreet(textFieldInsertClinicStreet.getText());

				// add new
				if (isAddingNewClinic) {

					Database.getInstance().insertClinic(c);
					System.out.println("clinic added");
				}
				// edit
				else {
					Database.getInstance().updateClinic(c);
					System.out.println("clinic updated");
				}

				refreshEditableClinics();
			}


			private void refreshEditableClinics() {
				// clear
				while (tableUpdateClinicsModel.getRowCount() > 0)
					tableUpdateClinicsModel.removeRow(0);

				// QUESTA DIVENTERA UNA FUNZIONE PER RIEMPIRE LA TABELLA DI CLINICHE IN
				// INSERISCI AMBULATORIO___________________
				editableClinics = Database.getInstance().getClinics(company);

				int i = 1;
				if (editableClinics != null) {
					for (Clinic c : editableClinics) {

						Vector<Object> vector = new Vector<Object>();

						vector.add(i++);
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


		});
		panelInsertClinicButton.add(btnEditClinicOk);

		JButton btnEditClinicCancel = new JButton("Annulla");
		btnEditClinicCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// da modificare
				tableUpdateClinics.setEnabled(true);
				panelInsertClinic.setVisible(false);
				panelInsertClinicButton.setVisible(false);
				btnAddNewClinic.setEnabled(true);
				int row = tableUpdateClinics.getSelectedRow();
				if (row != -1)
					btnEditClinic.setEnabled(true);
			}
		});
		panelInsertClinicButton.add(btnEditClinicCancel);

		DocumentListener textFieldListener = new DocumentListener() {

			private void updateEditClinicTextFields() {

				boolean okStatus = true;

				if (textFieldInsertClinicName.getText().isEmpty() || textFieldInsertClinicStreet.getText().isEmpty()
						|| textFieldInsertClinicCity.getText().isEmpty() || textFieldInsertClinicCAP.getText().isEmpty()
						|| !formattedTextFieldInsertClinicContractDate.isEditValid()
						|| textAreaInsertClinicDescription.getText().isEmpty())
					okStatus = false;

				if (textFieldInsertClinicCAP.getText().length() > 0 && !textFieldInsertClinicCAP.getText().matches("[0-9]{5}")) {
					okStatus = false;
					textFieldInsertClinicCAP.setBackground(Color.red);
				} else
					textFieldInsertClinicCAP.setBackground(Color.white);

				if (!duplicatedClinicName(textFieldInsertClinicName.getText(), currentClinicName)) {
					okStatus = false;
					textFieldInsertClinicName.setBackground(Color.red);
				} else
					textFieldInsertClinicName.setBackground(Color.white);

				btnEditClinicOk.setEnabled(okStatus);
			}

			private boolean duplicatedClinicName(String newName, String oldName) {

				newName = newName.toUpperCase();

				if (newName.equals(oldName))
					return true;

				for (Clinic c : editableClinics) {
					if (c.getName().equals(newName))
						return false;
				}
				return true;
			}


			@Override
			public void insertUpdate(DocumentEvent e) {
				updateEditClinicTextFields();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateEditClinicTextFields();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {}

		};

		// AGGIUNGO IL LISTENER A TUTTI I MIEI TEXTFIELD
		textFieldInsertClinicName.getDocument().addDocumentListener(textFieldListener); // serve
		// il
		// getDocument
		textFieldInsertClinicStreet.getDocument().addDocumentListener(textFieldListener);
		textFieldInsertClinicCity.getDocument().addDocumentListener(textFieldListener);
		textFieldInsertClinicCAP.getDocument().addDocumentListener(textFieldListener);
		textAreaInsertClinicDescription.getDocument().addDocumentListener(textFieldListener);

		// fine panel inserisci clinica

		// INIZIO panel inserisci visit result
		JPanel panelVisitsResultsPerPatientMaster = new JPanel();
		tabbedPaneEmployee.addTab("Storico visite", null, panelVisitsResultsPerPatientMaster, null);
		panelVisitsResultsPerPatientMaster.setLayout(new CardLayout(0, 0));
		CardLayout clpanelVisitsResultsPerPatientMaster = (CardLayout) panelVisitsResultsPerPatientMaster.getLayout();

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
				new String[] {"N\u00B0", "Data", "Ora", "Tipo visita", "Urgenza" }, 0) {

			private static final long serialVersionUID = 2L;

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

				int i = 1;
				visits = Database.getInstance().getVisitsHistory(searchPatientTextField.getText());
				if (visits != null) {
					for (Visit c : visits) {
						Vector<Object> vector = new Vector<Object>();

						vector.add(i++);
						vector.add(c.getDate().toString());
						vector.add(c.getHour());
						vector.add(c.getServiceName());
						vector.add(c.getUrgency());
						employeeHistoryModel.addRow(vector);
					}
				}
			}
		});
		panelViewVisitPerPatientNorth.add(btnVisualizza);

		JScrollPane scrollPaneViewVisitPerPatient = new JScrollPane();
		panelViewVisitPerPatient.add(scrollPaneViewVisitPerPatient, BorderLayout.CENTER);


		JButton btnViewVisitSelectedResult = new JButton("Visualizza");
		btnViewVisitSelectedResult.setEnabled(false);
		
		JTable tableVisitsPatientResults = new JTable();
		tableVisitsPatientResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVisitsPatientResults.setModel(employeeHistoryModel);
		tableVisitsPatientResults.getColumnModel().getColumn(0).setPreferredWidth(25);
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

		JTextPane textPanePatientVisitResult = new JTextPane();
		
		btnViewVisitSelectedResult.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Visit index : " + visitIndex);
				Visit v = visits.get(visitIndex); 
				textPanePatientVisitResult.setText(v.toString());
				// clpanelVisitsResultsPerPatientMaster = (CardLayout)
				// panelVisitsResultsPerPatientMaster.getLayout();
				clpanelVisitsResultsPerPatientMaster.next(panelVisitsResultsPerPatientMaster);
				// clpanelVisitPatient.next(panelVisitPatient);
			}
		});
		panelViewVisitPerPatientSouth.add(btnViewVisitSelectedResult);

		JPanel panelViewVisitResultsPerPatient = new JPanel();
		panelVisitsResultsPerPatientMaster.add(panelViewVisitResultsPerPatient, "panelViewVisitResultsPerPatient");
		panelViewVisitResultsPerPatient.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPanepanelViewVisitResultsPerPatient = new JScrollPane();
		panelViewVisitResultsPerPatient.add(scrollPanepanelViewVisitResultsPerPatient, BorderLayout.CENTER);

		
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

		JLabel lblWelcomeEmployee = new JLabel("Benvenuto " + name + " " + surname);
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

				OpenLoginWindow();
				dispose();
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
		getContentPane().add(panelClinicsAndServices, BorderLayout.CENTER);
		panelClinicsAndServices.setLayout(new BorderLayout(0, 0));

		JPanel panelClinicsAndServicesNorth = new JPanel();
		// sezione nord, combo box companies
		FlowLayout fl_panelClinicsAndServicesNorth = (FlowLayout) panelClinicsAndServicesNorth.getLayout();
		fl_panelClinicsAndServicesNorth.setAlignment(FlowLayout.LEFT);
		panelClinicsAndServices.add(panelClinicsAndServicesNorth, BorderLayout.NORTH);

		JLabel lblSelectCompany = new JLabel("Seleziona azienda:");
		panelClinicsAndServicesNorth.add(lblSelectCompany);

		JComboBox<String> comboBoxSelectCompany = new JComboBox<String>();
		comboBoxSelectCompany.setPreferredSize(new Dimension(150, 27));
		JList<String> listClinics = new JList<String>();

		comboBoxSelectCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String companyID = (String) comboBoxSelectCompany.getSelectedItem(); 
				ArrayList<Clinic> clinics = Database.getInstance().getClinics(companyID);
				listClinics.setModel(new AbstractListModel<String>() {
					private static final long serialVersionUID = 4L;

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

		refreshEditableClinics(tableUpdateClinicsModel, editableClinics);

		// add clinics
		for (Clinic c : editableClinics)
			comboBoxClinicVisitInsertion.addItem(c.getName());

		//clfrmHealhcareManagementSystem.show(getContentPane(),"panelEmployee");


	}

}
