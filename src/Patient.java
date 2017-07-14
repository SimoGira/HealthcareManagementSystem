import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Patient extends JFrame{

	private static final long serialVersionUID = 123L;
	private String fiscalcode;
	private String healthcarecompany;
	private String name;  
	private String surname;
	private Date birthdate;
	private String birthplace; 
	private String province;
	private String email;
	private ArrayList<Visit> patientVisits;
	private JComboBox<Integer> comboBoxSelectBookVisitYear;
	private JComboBox<String> comboBoxSelectBookVisitMonth;
	private JComboBox<String> comboBoxSelectBookVisitType;
	private JComboBox<String> comboBoxSelectBookVisitClinic;
	private JComboBox<Integer> comboBoxSelectBookVisitDay;
	private ArrayList<String[]> serviceInfos;
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private int visitIndex;
	private JButton btnViewVisitsPatient;

	public Patient(Map<String, Object> map) {
		setParamenters(map);
		setTitle("HEALTHCARE MANAGEMENT SYSTEM");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Patient.class.getResource("/img/healthcare-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initializeView();

		setSize(700, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void setParamenters(Map<String, Object> map) {
		
		fiscalcode = (String) map.get("fiscalcode");
		healthcarecompany = (String) map.get("healthcarecompany");
		name = (String) map.get("name");
		surname=(String) map.get("surname");
		birthdate=(Date) map.get("birthdate");
		birthplace=(String) map.get("birthplace"); 
		province=(String) map.get("province");
		email=(String) map.get("email");
	}

	public String getFiscalcode() {
		return fiscalcode;
	}
	public void setFiscalcode(String fiscalcode) {
		this.fiscalcode = fiscalcode;
	}
	public String getHealthcarecompany() {
		return healthcarecompany;
	}
	public void setHealthcarecompany(String healthcarecompany) {
		this.healthcarecompany = healthcarecompany;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private void initializeView() {
		JPanel panelPatient = new JPanel();
		getContentPane().add(panelPatient, BorderLayout.CENTER);
		panelPatient.setLayout(new BorderLayout(0, 0));

		JPanel panelPatientNorth = new JPanel();
		panelPatient.add(panelPatientNorth, BorderLayout.NORTH);
		panelPatientNorth.setLayout(new BorderLayout(0, 0));

		JLabel lblWelcomePatient = new JLabel("Benvenuto " + this.name + " " + this.surname);
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
 
				OpenLoginWindow();
				dispose();
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

		JPanel panelVisitPatient = new JPanel();
		tabbedPanePatient.addTab("Storico prenotazioni", null, panelVisitPatient, null);
		panelVisitPatient.setLayout(new CardLayout(0, 0));
		CardLayout clpanelVisitPatient = (CardLayout) panelVisitPatient.getLayout();

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
		panelHistoryNorth.add(comboBoxVisitsYear);
		
		JScrollPane scrollPaneHistory = new JScrollPane();
		panelHistoryVisitPatient.add(scrollPaneHistory, BorderLayout.CENTER);
		
		JTable tableHistoryVisits = new JTable();
		tableHistoryVisits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel visitsHistoryModel = new DefaultTableModel(
			new String[] { "N\u00B0", "Data", "Ora", "Tipo Visita", "Urgenza", "Stato"}, 0) {																// da togliere il campo stato
			private static final long serialVersionUID = 5L;

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
					System.out.println("selezionata la riga: " + tableHistoryVisits.getSelectedRow());
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

		JTextPane txtpnVisitResultInfo;
		txtpnVisitResultInfo = new JTextPane();
		txtpnVisitResultInfo.setEditable(false);
		txtpnVisitResultInfo.setContentType("text/html");
		
		JPanel panelVisitResultPatient = new JPanel();
		panelVisitPatient.add(panelVisitResultPatient, "panelVisitResultPatient");
		panelVisitResultPatient.setLayout(new BorderLayout(0, 0));
		panelVisitResultPatient.add(txtpnVisitResultInfo, BorderLayout.CENTER);

		
		btnViewVisitsPatient = new JButton("Visualizza");
		btnViewVisitsPatient.setEnabled(false);
		btnViewVisitsPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visit v = patientVisits.get(visitIndex);
				txtpnVisitResultInfo.setText(v.toHTML(name, surname));
				clpanelVisitPatient.next(panelVisitPatient);
				// patientVisits
			}
		});
		panelHistoryButtons.add(btnViewVisitsPatient);

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

		JPanel panelBookVisit = new JPanel();
		tabbedPanePatient.addTab("Prenotazione visite", null, panelBookVisit, null);
		panelBookVisit.setLayout(new BorderLayout(0, 0));

		JPanel bookVisitCenterPanel = new JPanel();
		bookVisitCenterPanel.setBorder(new EmptyBorder(5, 5, 0, 0));
		panelBookVisit.add(bookVisitCenterPanel, BorderLayout.CENTER);

		JLabel lblSelectBookVisitType = new JLabel("Tipo visita:");
		lblSelectBookVisitType.setHorizontalAlignment(SwingConstants.RIGHT);

		JComboBox<String>comboBoxSelectBookVisitType = new JComboBox<String>();
		comboBoxSelectBookVisitType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxSelectBookVisitClinic.removeAllItems();
				serviceInfos = Database.getInstance().getServiceInfos(healthcarecompany,
						comboBoxSelectBookVisitType.getSelectedItem().toString());
				for (String[] info : serviceInfos)
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
		comboBoxSelectBookVisitMonth.setModel(new DefaultComboBoxModel<String>(new String[] {"GENNAIO", "FEBBRAIO", "MARZO", "APRILE", "MAGGIO", "GIUGNO", "LUGLIO", "AGOSTO", "SETTEMBRE", "OTTOBRE", "NOVEMBRE", "DICEMBRE"}));

		JLabel lblSelectBookVisitDay = new JLabel("Giorno:");
		lblSelectBookVisitDay.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSelectBookVisitHour = new JLabel("Ora:");
		JComboBox<Integer> comboBoxSelectBookVisitHour = new JComboBox<Integer>();

		comboBoxSelectBookVisitDay = new JComboBox<Integer>();
		comboBoxSelectBookVisitDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("modified day");
				if (comboBoxSelectBookVisitDay.getSelectedItem() == null)
					return;

				boolean[] hours = Database.getInstance().getBookedVisitsInDay((int) comboBoxSelectBookVisitYear.getSelectedItem(),
						(int) comboBoxSelectBookVisitMonth.getSelectedIndex() + 1,
						(int) comboBoxSelectBookVisitDay.getSelectedItem(),
						(String) comboBoxSelectBookVisitType.getSelectedItem(),
						(String) comboBoxSelectBookVisitClinic.getSelectedItem(),
						healthcarecompany);

				comboBoxSelectBookVisitHour.removeAllItems();

				for (int i = 8; i < 16; i++)
					if (!hours[i])
						comboBoxSelectBookVisitHour.addItem((int) i);

			}
		});
		
		JLabel lblSelectBookVisitUrgency = new JLabel("Urgenza:");
		lblSelectBookVisitUrgency.setHorizontalAlignment(SwingConstants.RIGHT);

		JComboBox<String> comboBoxSelectBookVisitUrgency = new JComboBox<String>();
		comboBoxSelectBookVisitUrgency.setModel(new DefaultComboBoxModel<String>(new String[] { "Bassa", "Medio", "Alta" }));

		JLabel lblSelectBookVisitRegime = new JLabel("Regime:");
		lblSelectBookVisitRegime.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblAmbultorio = new JLabel("Ambulatorio:");
		lblAmbultorio.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JTextField textFieldRegime = new JTextField();
		textFieldRegime.setEditable(false);
		textFieldRegime.setColumns(10);

		comboBoxSelectBookVisitClinic = new JComboBox<String>();
		comboBoxSelectBookVisitClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int idx = comboBoxSelectBookVisitClinic.getSelectedIndex();
				System.out.println(idx);
				if (idx >= 0) {
					String[] info = serviceInfos.get(idx);
					textFieldRegime.setText(info[1]);

				}
				updateBookingDays();
			}
		});

		GroupLayout gl_bookVisitCenterPanel = new GroupLayout(bookVisitCenterPanel);
		gl_bookVisitCenterPanel.setHorizontalGroup(
				gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_bookVisitCenterPanel
						.createSequentialGroup().addGap(132).addGroup(gl_bookVisitCenterPanel
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSelectBookVisitUrgency, Alignment.TRAILING)
										.addComponent(lblSelectBookVisitType, GroupLayout.PREFERRED_SIZE, 91,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblSelectBookVisitYear, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(lblAmbultorio, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
								.addComponent(lblSelectBookVisitDay, GroupLayout.PREFERRED_SIZE, 56,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_bookVisitCenterPanel.createSequentialGroup()
										.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(comboBoxSelectBookVisitDay, Alignment.LEADING, 0,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(comboBoxSelectBookVisitYear, Alignment.LEADING, 0, 69,
														Short.MAX_VALUE)
												.addComponent(comboBoxSelectBookVisitUrgency, Alignment.LEADING,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGap(69)
										.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblSelectBookVisitMonth)
												.addComponent(lblSelectBookVisitHour)
												.addComponent(lblSelectBookVisitRegime))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(textFieldRegime, GroupLayout.DEFAULT_SIZE, 104,
														Short.MAX_VALUE)
												.addComponent(comboBoxSelectBookVisitHour, 0, 104, Short.MAX_VALUE)
												.addComponent(comboBoxSelectBookVisitMonth, 0, 104, Short.MAX_VALUE)))
								.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(comboBoxSelectBookVisitClinic, Alignment.LEADING, 0,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(comboBoxSelectBookVisitType, Alignment.LEADING, 0, 285,
												Short.MAX_VALUE)))
						.addGap(156)));
		gl_bookVisitCenterPanel.setVerticalGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bookVisitCenterPanel.createSequentialGroup().addGap(82)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSelectBookVisitType, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectBookVisitType))
						.addGap(18)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSelectBookVisitClinic, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAmbultorio))
						.addGap(18)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSelectBookVisitYear, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectBookVisitMonth).addComponent(lblSelectBookVisitYear)
								.addComponent(comboBoxSelectBookVisitMonth, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSelectBookVisitDay, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectBookVisitHour).addComponent(lblSelectBookVisitDay)
								.addComponent(comboBoxSelectBookVisitHour, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_bookVisitCenterPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSelectBookVisitUrgency, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectBookVisitUrgency).addComponent(lblSelectBookVisitRegime)
								.addComponent(textFieldRegime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(204, Short.MAX_VALUE)));
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
				v.setCompanyId(healthcarecompany);
				v.setHour((int) comboBoxSelectBookVisitHour.getSelectedItem());
				int year = (int) comboBoxSelectBookVisitYear.getSelectedItem();
				int month = comboBoxSelectBookVisitMonth.getSelectedIndex() + 1;
				int day = (int) comboBoxSelectBookVisitDay.getSelectedItem();

				try {
					v.setDate(new Date(sdf.parse(day + "/" + month + "/" + year).getTime()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				} 
				v.setPatient(fiscalcode);
				v.setUrgency(comboBoxSelectBookVisitUrgency.getSelectedItem().toString());
				Database.getInstance().bookVisit(v);
				updateBookingDays();
				JOptionPane.showMessageDialog(null, "Prenotazione effettuta con successo", "Informazione",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnBookVisit.setToolTipText("Clicca per prenotare");
		bookVisitSouthButtonPanel.add(btnBookVisit);

		// get years
		ArrayList<Integer> years = Database.getInstance().getVisitsHistoryYears(fiscalcode);
		for (Integer y : years)
			comboBoxVisitsYear.addItem(y.toString());

		// fill table (visits history)
		int i = 1;
		patientVisits = Database.getInstance().getVisitsHistory(fiscalcode);
		if (patientVisits != null) {
			for (Visit c : patientVisits) {
				Vector<Object> row = new Vector<Object>();
				row.add(i++);
				row.add(c.getDate().toString());
				row.add(c.getHour());
				row.add(c.getServiceName());
				row.add(c.getUrgency());
				
				if(c.getResult() != null)
					row.add("Risultato disponibile");
				else
					row.add("In attesa del risultato");
				
				visitsHistoryModel.addRow(row);
			}
		}

		// initialize book visit panel
		ArrayList<String> services = Database.getInstance().getServices(healthcarecompany);

		for (String s : services)
			comboBoxSelectBookVisitType.addItem(s);

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
	
	private void updateBookingDays() {
		int[] days = Database.getInstance().getVisitsInMonth((int) comboBoxSelectBookVisitYear.getSelectedItem(),
				(int) comboBoxSelectBookVisitMonth.getSelectedIndex() + 1,
				(String) comboBoxSelectBookVisitType.getSelectedItem(),
				(String) comboBoxSelectBookVisitClinic.getSelectedItem(), healthcarecompany);

		comboBoxSelectBookVisitDay.removeAllItems();
		for (int i = 1; i < 32; i++) {
			if (days[i] < 8)
				comboBoxSelectBookVisitDay.addItem((int) i);
		}
	}

	
}
