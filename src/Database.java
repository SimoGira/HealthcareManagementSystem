import java.sql.Connection;
import java.sql.Date;
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
		try(Connection con = DriverManager.getConnection(url, user, password)){ // forse la password non è da mettere necessariamente (io accedo senza pswd)

			String query = "INSERT INTO Clinic (name, company, street, cap, city, province, contractDate, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, clinc.getName());
				pst.setString(2, clinc.getCompany());
				pst.setString(3, clinc.getStreet());
				pst.setString(4, clinc.getCap());
				pst.setString(5, clinc.getCity());
				pst.setString(6, clinc.getProvince());
				pst.setDate(7, clinc.getContractDate());
				pst.setString(8, clinc.getDescription());
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
				pst.setString(1, clinic.getStreet());
				pst.setString(2, clinic.getCap());
				pst.setString(3, clinic.getCity());
				pst.setString(4, clinic.getProvince());
				pst.setDate(5, clinic.getContractDate());
				pst.setString(6, clinic.getDescription());
				pst.setString(7, clinic.getName());
				pst.setString(8, clinic.getCompany());
			}

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}
	}

	public boolean checkEmployee(String employeeCode, String password)
	{
		try(Connection con = DriverManager.getConnection(this.url, this.user, this.password))
		{
			String query = "SELECT * FROM employee WHERE employeeCode = ? AND password = ?";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, employeeCode); 
				pst.setString(2, password); 
				ResultSet rs = pst.executeQuery(); 
				Employee e = Employee.getInstance();
				while(rs.next())
				{ 
					e.setFiscalcode(rs.getString("fiscalcode")); 
					e.setName(rs.getString("name"));  
					e.setClinic(rs.getString("clinic"));
					e.setCompany(rs.getString("company"));
					e.setJob(rs.getString("job"));
					e.setSurname(rs.getString("surname")); 
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

	public ArrayList<Visit> getBookedVisits(String company, String clinic, Date date)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT * FROM visit WHERE company = ? AND clinic = ? AND result IS NULL AND date = ?";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, company); 
				pst.setString(2, clinic); 
				pst.setDate(3, date); 
				ResultSet rs = pst.executeQuery();
				ArrayList<Visit> visits = new ArrayList<Visit>();
				while(rs.next())
				{
					Visit v = new Visit(); 
					v.setPatient(rs.getString("patient"));
					v.setClinicName(rs.getString("clinic"));
					v.setCompanyId(rs.getString("company"));
					v.setServiceName(rs.getString("servicename"));
					v.setDoctor(rs.getString("doctor"));
					v.setDate(rs.getDate("date"));
					v.setHour(rs.getInt("hour"));
					v.setUrgency(rs.getString("urgency"));
					v.setRegime(rs.getString("regime"));
					v.setResult(rs.getString("result"));
					visits.add(v);
				}
				return visits;
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}

	public ArrayList<Integer> getVisitsHistoryYears(String fiscalCode)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT DISTINCT EXTRACT(YEAR FROM(date)) as year FROM visit WHERE patient = ? AND NOT result IS NULL";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, fiscalCode); 
				ResultSet rs = pst.executeQuery();
				ArrayList<Integer> years = new ArrayList<Integer>();
				while(rs.next()) 
					years.add(rs.getInt("year")); 
				return years;
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}
	
	public ArrayList<Visit> getVisitsHistory(String fiscalCode)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT * FROM visit WHERE patient = ? AND NOT result IS NULL";
			try(PreparedStatement pst = con.prepareStatement(query))
			{
				pst.clearParameters();
				pst.setString(1, fiscalCode); 
				ResultSet rs = pst.executeQuery();
				ArrayList<Visit> visits = new ArrayList<Visit>();
				while(rs.next())
				{
					System.out.println("adding");
					Visit v = new Visit();
					v.setClinicName(rs.getString("clinic"));
					v.setPatient(rs.getString("patient")); 
					v.setCompanyId(rs.getString("company"));
					v.setServiceName(rs.getString("servicename"));
					v.setDoctor(rs.getString("doctor"));
					v.setDate(rs.getDate("date"));
					v.setHour(rs.getInt("hour"));
					v.setUrgency(rs.getString("urgency"));
					v.setRegime(rs.getString("regime"));
					v.setResult(rs.getString("result"));
					visits.add(v);
				}
				return visits;
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}


	public ArrayList<Clinic> getClinics(String company)
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
					a.setName(rs.getString("name"));
					a.setCompany(rs.getString("company"));
					a.setStreet(rs.getString("street"));
					a.setCap(rs.getString("cap"));
					a.setCity(rs.getString("city"));  
					a.setProvince(rs.getString("province"));
					a.setContractDate(rs.getDate("contractdate"));
					a.setDescription(rs.getString("description"));
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
	public int[] getVisitsInMonth(int year, int month, String visitType, String company)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT EXTRACT(DAY FROM(date)) as day , COUNT(*) as cnt FROM visit WHERE serviceName = ? AND company = ? AND result IS NULL AND EXTRACT(MONTH FROM(date)) = ?  AND EXTRACT(YEAR FROM(date)) = ?  GROUP BY date";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, visitType);
				pst.setString(2, company); 
				pst.setInt(3, year); 
				pst.setInt(4, month); 
				ResultSet rs = pst.executeQuery();
				int[] result = new int[32];
				while(rs.next())
				{ 
					int day = rs.getInt("day") - 1; //start from 0
					result[day] =  rs.getInt("cnt");
				} 
				return result;
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}

	
	public boolean[] getBookedVisitsInDay(int year, int month, int day, String visitType, String company)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT hour as cnt FROM visit WHERE serviceName = ? AND company = ? AND result IS NULL AND EXTRACT(MONTH FROM(date)) = ?  AND EXTRACT(YEAR FROM(date)) = ? AND EXTRACT(DAY FROM(date)) = ?  GROUP BY date";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, visitType);
				pst.setString(2, company); 
				pst.setInt(3, year); 
				pst.setInt(4, month);
				pst.setInt(5, day); 
				ResultSet rs = pst.executeQuery();
				boolean[] result = new boolean[24];
				while(rs.next())
				{ 
					int hour = rs.getInt("hour");
					result[hour] =  true;
				} 
				return result;
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}
	
	public ArrayList<String> getServices(String company)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT DISTINCT name FROM service WHERE company = ?";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, company); 
				ResultSet rs = pst.executeQuery();
				ArrayList<String> result = new ArrayList<String>();
				while(rs.next()) 
					result.add(rs.getString("name")); 
				return result;
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}


	public ArrayList<String> getClinicServices(String company, String clinic)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT DISTINCT name FROM service WHERE company = ? AND clinic = ?";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(1, company); 
				pst.setString(2, clinic); 
				ResultSet rs = pst.executeQuery();
				ArrayList<String> result = new ArrayList<String>();
				while(rs.next()) 
					result.add(rs.getString("name")); 
				return result;
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}
	
	
	/**
	 * @return array of elements like  new String[] {String id, String name}
	 */
	public ArrayList<String[]> getCompanies()
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT id, name FROM company";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				ResultSet rs = pst.executeQuery();
				ArrayList<String[]> result = new ArrayList<String[]>();
				while(rs.next()) 
					result.add(new String[]{rs.getString("id"), rs.getString("name")}); 
				return result;
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}
}
