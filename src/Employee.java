import java.sql.Date;

public class Employee {
	private static Employee instance = null;

	private String fiscalcode;
	private String employeeCode;
	private String password;
	private String name;
	private String surname;
	private String job;
	private String clinic;
	private String company;
	
	private Employee() {}
	public static Employee getInstance()
	{
		if ( instance == null )
			instance = new Employee();
		return instance;
	} 
 
	public String getFiscalcode() {
		return fiscalcode;
	}
	public void setFiscalcode(String fiscalcode) {
		this.fiscalcode = fiscalcode;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}

	public String getClinic() {
		return clinic;
	}
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

}
