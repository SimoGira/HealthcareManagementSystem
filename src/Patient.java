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

	private String fiscalcode;
	private String healthcarecompany;
	private String pin;
	private String name;  
	private String surname;
	private Date birthdate;
	private String birthplace; 
	private String province;
	private String email;

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
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
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
	

	
}
