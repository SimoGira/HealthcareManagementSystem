import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

	 
}
