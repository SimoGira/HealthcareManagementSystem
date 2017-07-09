import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;

public class Database {
	private String url;
	private String user;
	private String password;

	public Database() throws ClassNotFoundException, SQLException{
		setParameters();
	}

	private void setParameters() throws ClassNotFoundException {
		// TODO: replace the below code opening a configuration file and getting the credentials
		url = "jdbc:postgresql://localhost/dbhms";
		user = "postgres";
		password = "";

		Class.forName("org.postgresql.Driver");
	}

	public void insertClinic(Clinic clinc)
	{
		try(Connection con = DriverManager.getConnection(url, user, password)){ // forse la password non � da mettere necessariamente (io accedo senza pswd)

			String query = "INSERT INTO Clinic (name, company, street, cap, city, province, contractDate, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, clinc.name);
				pst.setString(2, clinc.company);
				pst.setString(3, clinc.street);
				pst.setString(4, clinc.cap);
				pst.setString(5, clinc.city);
				pst.setString(6, clinc.province);
				pst.setDate(7, clinc.contractDate);
				pst.setString(8, clinc.description);
			}

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}
	}

	public void updateClinic(Clinic clinic)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{ 
			String query = "UPDATE Clinic SET street = ?, cap = ?, city = ?, province = ?, contractDate = ?, description = ? WHERE name = ? AND company = ?";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, clinic.street);
				pst.setString(2, clinic.cap);
				pst.setString(3, clinic.city);
				pst.setString(4, clinic.province);
				pst.setDate(5, clinic.contractDate);
				pst.setString(6, clinic.description);
				pst.setString(7, clinic.name);
				pst.setString(8, clinic.company);
			}

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}
	}
	
	public boolean checkEmployee(String employeeCode, String password)
	{
		try(Connection con = DriverManager.getConnection(this.url, this.user, this.password))
		{
			String query = "SELECT COUNT(*) FROM employee WHERE employeeCode = ? AND password = ?";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, employeeCode); 
				pst.setString(2, password); 
				ResultSet rs = pst.executeQuery(); 
				while(rs.next())
				{
					if(rs.getInt(1) == 1)
						return true;
				} 
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return false;
	}
	 
	
	public boolean checkPatient(String fiscalCode, String pin)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT * FROM patient WHERE fiscalCode = ? AND pin = ?";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, fiscalCode); 
				pst.setString(2, pin); 
				ResultSet rs = pst.executeQuery(); 

				Patient p = Patient.getInstance();
				while(rs.next())
				{
					p.setFiscalcode(rs.getString("fiscalcode"));
					p.setHealthcarecompany(rs.getString("healthcarecompany"));
					p.setPin(rs.getString("pin"));
					p.setName(rs.getString("name"));  
					p.setSurname(rs.getString("surname"));
					p.setBirthdate(rs.getDate("birthdate"));
					p.setBirthplace(rs.getString("birthplace")); 
					p.setProvince(rs.getString("province"));
					p.setEmail(rs.getString("email"));
					return true;
					
				}
			}

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return false;
	}
	
	public ArrayList<Visit> getVisitsHistory(String fiscalCode)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT * FROM visit WHERE patient = ? AND result NOT IS NULL";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, fiscalCode); 
				ResultSet rs = pst.executeQuery();
				ArrayList<Visit> visits = new ArrayList<Visit>();
				while(rs.next())
				{
					Visit v = new Visit();
					v.clinicName = rs.getString("Clinic");
					v.patient = rs.getString("patient");
					v.clinicName = rs.getString("Clinic");
					v.companyId = rs.getString("company");
					v.serviceName = rs.getString("serviceName");
					v.doctor = rs.getString("doctor");
					v.date = rs.getDate("date");
					v.hour = rs.getInt("hour");
					v.urgency = rs.getString("urgency");
					v.regime = rs.getString("regime");
					v.result = rs.getString("result");
					visits.add(v);
				}
				return visits;
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}


	public ArrayList<Clinic> getclinics(String company)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT * FROM Clinic WHERE company = ?";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, company); 
				ResultSet rs = pst.executeQuery();
				ArrayList<Clinic> clinics = new ArrayList<Clinic>();
				while(rs.next())
				{
					Clinic a = new Clinic();
					a.name = rs.getString("name");
					a.company = rs.getString("company");
					a.street = rs.getString("street");
					a.cap = rs.getString("cap");
					a.city = rs.getString("city");  
					a.province = rs.getString("province");
					a.contractDate = rs.getDate("contractDate");
					a.description = rs.getString("description");
					clinics.add(a);
				}
				return clinics;
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}

	// for each day of this month, get the number of visits in this day (min 0 - max 8)
	public int[] getVisitsInMonth(int year, int month, String clinic, String company)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT EXTRACT(DAY FROM(date)) as day , COUNT(*) as cnt FROM visit WHERE clinic = ? AND company = ? AND result NOT IS NULL GROUP BY date";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, clinic);
				pst.setString(2, company); 
				pst.setInt(2, year); 
				pst.setInt(2, month); 
				ResultSet rs = pst.executeQuery();
				int[] result = new int[31];
				while(rs.next())
				{ 
					int day = rs.getInt("day");
					result[day] =  rs.getInt("cnt");
				} 
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}

}
