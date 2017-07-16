import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension; 
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class Employee extends User { 


	//private String fiscalcode;
	//private String employeeCode;
	//private String job;
	//private String clinic;

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
	protected ArrayList<Clinic> editableClinics = new ArrayList<Clinic>();
	private DefaultTableModel tableUpdateClinicsModel;

	public Employee(Map<String, Object> map) { 

		setParamenters(map);
		setTitle("HEALTHCARE MANAGEMENT SYSTEM");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Employee.class.getResource("/img/healthcare-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initializeView();

		setSize(800, 700);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void refreshEditableClinics() {
		// clear 
		while (tableUpdateClinicsModel.getRowCount() > 0)
			tableUpdateClinicsModel.removeRow(0);


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

			}
		}
	}

	protected void setParamenters(Map<String, Object> map) {
		name = (String) map.get("name");
		company = (String) map.get("company");
		surname = (String) map.get("surname");
		//job = (String) map.get("job");
		//fiscalcode = (String) map.get("fiscalcode"); 
		//clinic = (String) map.get("clinic"); 
	}

	protected void initializeView() {


		JPanel panelEmployee = new JPanel();
		getContentPane().add(panelEmployee, BorderLayout.CENTER);

		panelEmployee.setLayout(new BorderLayout(0, 0));
		getContentPane().add(panelEmployee, BorderLayout.CENTER);

		JTabbedPane tabbedPaneEmployee = new JTabbedPane(JTabbedPane.TOP);
		panelEmployee.add(tabbedPaneEmployee, BorderLayout.CENTER);

		JPanel panelInsertVisitInfo = new JPanel();
		tabbedPaneEmployee.addTab("Gestione visite", null, panelInsertVisitInfo, null);
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
		comboBoxClinicVisitInsertion.setPreferredSize(new Dimension(250, 27));
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
						JOptionPane.showMessageDialog(null, "Nessuna visita trovata in data: " + date, "Infomazione",
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
		gbl_panelInsertClinicMaster.rowHeights = new int[] { 200, 0, 288, 33, 0 };
		gbl_panelInsertClinicMaster.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelInsertClinicMaster.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panelInsertClinicMaster.setLayout(gbl_panelInsertClinicMaster);

		JScrollPane scrollPaneClinicsTable = new JScrollPane();
		GridBagConstraints gbc_scrollPaneClinicsTable = new GridBagConstraints();
		gbc_scrollPaneClinicsTable.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneClinicsTable.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneClinicsTable.gridx = 0;
		gbc_scrollPaneClinicsTable.gridy = 0;
		panelInsertClinicMaster.add(scrollPaneClinicsTable, gbc_scrollPaneClinicsTable);

		// table of editable clinics
		JTable tableUpdateClinics = new JTable();
		tableUpdateClinics.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUpdateClinics.getTableHeader().setReorderingAllowed(false);
		tableUpdateClinicsModel = new DefaultTableModel(new String[] {"N\u00B0", "Nome", "Via", "CAP", "Citta'", "Provincia" },0) {

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
					visitIndex = tableUpdateClinics.getSelectedRow();
					btnEditClinic.setEnabled(true);
				}
			}
		});
		scrollPaneClinicsTable.setViewportView(tableUpdateClinics);

		JPanel panelInsertEditClinicButtons = new JPanel();
		GridBagConstraints gbc_panelInsertEditClinicButtons = new GridBagConstraints();
		gbc_panelInsertEditClinicButtons.anchor = GridBagConstraints.NORTH;
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
				currentClinicName = clinic.getName();

			}
		});
		btnAddNewClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAddingNewClinic = true;
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
		gbc_panelInsertClinic.fill = GridBagConstraints.BOTH;
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

		// add new clinic
		GridBagConstraints gbc_panelInsertClinicButton = new GridBagConstraints();
		gbc_panelInsertClinicButton.anchor = GridBagConstraints.SOUTH;
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
				}
				// edit
				else {
					Database.getInstance().updateClinic(c);
				}

				refreshEditableClinics();
			}


			private void refreshEditableClinics() {
				// clear
				while (tableUpdateClinicsModel.getRowCount() > 0)
					tableUpdateClinicsModel.removeRow(0);

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
					}
				}
			}


		});
		panelInsertClinicButton.add(btnEditClinicOk);

		JButton btnEditClinicCancel = new JButton("Annulla");
		btnEditClinicCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

		textFieldInsertClinicName.getDocument().addDocumentListener(textFieldListener);  
		textFieldInsertClinicStreet.getDocument().addDocumentListener(textFieldListener);
		textFieldInsertClinicCity.getDocument().addDocumentListener(textFieldListener);
		textFieldInsertClinicCAP.getDocument().addDocumentListener(textFieldListener);
		textAreaInsertClinicDescription.getDocument().addDocumentListener(textFieldListener);


		JPanel panelVisitsResultsPerPatientMaster = new JPanel();
		tabbedPaneEmployee.addTab("Storico visite paziente", null, panelVisitsResultsPerPatientMaster, null);
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

		// show patient's visits
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
				Visit v = visits.get(visitIndex); 
				textPanePatientVisitResult.setText(v.toString()); 
				clpanelVisitsResultsPerPatientMaster.next(panelVisitsResultsPerPatientMaster); 
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
				clpanelVisitsResultsPerPatientMaster.previous(panelVisitsResultsPerPatientMaster);
			}
		});
		panelpanelViewVisitResultsPerPatientSouth.add(btnBackToViewVisitPerPatient);

		JButton btnPrintReaultVisitPatient = new JButton("Stampa");
		panelpanelViewVisitResultsPerPatientSouth.add(btnPrintReaultVisitPatient);

		JPanel panelEmployeeNorth = new JPanel();
		panelEmployee.add(panelEmployeeNorth, BorderLayout.NORTH);
		panelEmployeeNorth.setLayout(new BorderLayout(0, 0));

		JLabel lblWelcomeEmployee = new JLabel("Benvenuto " + capitalizeString(name) + " " + capitalizeString(surname));
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


		refreshEditableClinics();


		// add clinics
		for (Clinic c : editableClinics)
			comboBoxClinicVisitInsertion.addItem(c.getName());



	}

}
