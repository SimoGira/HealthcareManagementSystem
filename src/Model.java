import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Model {
	private String url;
	private String user;
	private String password;

	public Model() throws ClassNotFoundException, SQLException{
		setParameters();
	}

	private void setParameters() throws ClassNotFoundException {
		// TODO: replace the below code opening a configuration file and getting the credentials
		url = "jdbc:postgresql://localhost/dbhms";
		user = "postgres";
		password = "";

		Class.forName("org.postgresql.Driver");
	}

	public void insertAmbulatory(Ambulatory amb)
	{
		try(Connection con = DriverManager.getConnection(url, user, password)){ // forse la password non è da mettere necessariamente (io accedo senza pswd)

			String query = "INSERT INTO ambulatory (name, company, street, cap, city, province, contractDate, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(0, amb.name);
				pst.setString(1, amb.company);
				pst.setString(2, amb.street);
				pst.setString(3, amb.cap);
				pst.setString(4, amb.city);
				pst.setString(5, amb.province);
				pst.setDate(6, amb.contractDate);
				pst.setString(7, amb.description);
			}

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}
	}

	public void updateAmbulatory(Ambulatory amb)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{ 
			String query = "UPDATE ambulatory SET street = ?, cap = ?, city = ?, province = ?, contractDate = ?, description = ? WHERE name = ? AND company = ?";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(0, amb.street);
				pst.setString(1, amb.cap);
				pst.setString(2, amb.city);
				pst.setString(3, amb.province);
				pst.setDate(4, amb.contractDate);
				pst.setString(5, amb.description);
				pst.setString(6, amb.name);
				pst.setString(7, amb.company);
			}

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}
	}

	public ArrayList<Visit> getVisitsHistory(String patient)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT * FROM visit WHERE patient = ? AND result NOT IS NULL";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(0, patient); 
				ResultSet rs = pst.executeQuery();
				ArrayList<Visit> visits = new ArrayList<Visit>();
				while(rs.next())
				{
					Visit v = new Visit();
					v.ambulatoryName = rs.getString("ambulatory");
					v.patient = rs.getString("patient");
					v.ambulatoryName = rs.getString("ambulatory");
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


	public ArrayList<Ambulatory> getAmbulatories(String company)
	{
		try(Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT * FROM ambulatory WHERE company = ?";
			try(PreparedStatement pst = con.prepareStatement(query)){
				pst.clearParameters();
				pst.setString(0, company); 
				ResultSet rs = pst.executeQuery();
				ArrayList<Ambulatory> ambulatories = new ArrayList<Ambulatory>();
				while(rs.next())
				{
					Ambulatory a = new Ambulatory();
					a.name = rs.getString("name");
					a.company = rs.getString("company");
					a.street = rs.getString("street");
					a.cap = rs.getString("cap");
					a.city = rs.getString("city");  
					a.province = rs.getString("province");
					a.contractDate = rs.getDate("contractDate");
					a.description = rs.getString("description");
					ambulatories.add(a);
				}
				return ambulatories;
			} 

		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}

		return null;
	}


}
