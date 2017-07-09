import java.sql.Date;

public class Patient {
	private static Patient instance = null;

	private Patient() {}
	public static Patient getInstance()
	{
		if ( instance == null )
			instance = new Patient();
		return instance;
	} 

	public String fiscalcode;
	public String healthcarecompany;
	public char[] pin;
	public String name;  
	public String surname;
	public Date birthdate;
	public String birthplace; 
	public String province;
	public String email;
	
}
