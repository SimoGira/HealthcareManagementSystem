import java.sql.*;

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
		user = "simonegirardi";
		password = "";

		Class.forName("org.postgresql.Driver");
	}

	public void insertAmbulatory(Ambulatory amb)
	{
		try(Connection con = DriverManager.getConnection(url, user, password)){

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
	
	private void updateDb() {
		try(Connection con = DriverManager.getConnection(url, user, password)){

			// create tables
			try(Statement st = con.createStatement()){
				st.execute( "DROP TABLE IF EXISTS PAZIENTE" );
				st.execute( "CREATE TABLE PAZIENTE (id SERIAL PRIMARY KEY, dataNascita DATE, name VARCHAR, age SMALLINT)" );
				System.out.println( "Esito creazione tabella: " + st.getUpdateCount() );
			} catch (SQLException e) {
				System.out.println( "Problema durante creazione tabella: " + e.getMessage() );
			}

			//update tables
			SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
			try(PreparedStatement pst = con.prepareStatement("INSERT INTO Paziente(dataNascita, name, age) VALUES (?,?,?),(?,?,?)")){
				pst.clearParameters();

				pst.setDate(1, new Date( sdf.parse("24/02/1993").getTime()));
				pst.setString(2, "KARIM");
				pst.setInt(3, 24);
				
				pst.setDate(4, new Date( sdf.parse("16/12/1991").getTime()));
				pst.setString(5, "ADRIANO");
				pst.setInt(6, 25);
				
				int n = pst.executeUpdate();
				System.out.println( "Inserted " + n + " rows" );

			}
			catch (SQLException e) {
				System.out.println( "Errore durante inserimento dati: " + e.getMessage() );
				return;
			}
			
			
			// TODO: query database 
			// ...
			
		} catch (SQLException e1) {
			System.out.println( "Errore durante connessione al database: " + e1.getMessage() );
		}
	}

}
