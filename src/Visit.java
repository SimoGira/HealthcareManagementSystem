import java.sql.Date;

public class Visit {
	private String patient;
	private String clinicName;
	private String companyId;
	private String serviceName;
	private String doctor;
	private Date date;
	private int hour;
	private String urgency;
	private String regime;
	private String result;

	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getRegime() {
		return regime;
	}
	public void setRegime(String regime) {
		this.regime = regime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public String toString()
	{
		return  "<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\">"
				+ "<tr>" + "<td align=\"right\"><b>" + "Appuntamento per:" + "</b></td>" + "<td>" + patient + "</td>" + "</tr>" 
				+ "<tr>" + "<td align=\"right\"><b>" + "Prestazione:" + "</b></td>" + "<td>" + serviceName + "</td>" + "</tr>" 
				+ "<tr>" + "<td align=\"right\"><b>" + "Unita' erogatrice:" + "</b></td>" + "<td>" + clinicName + "</td>" + "</tr>"  
				+ "</table>"
				+ "<p><hr/><b>Risultato</b><hr/></p>"
				+ result;
	}

	public String toHTML(String name, String surname)
	{
		return  "<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\">"
				+ "<tr>" + "<td align=\"right\"><b>" + "Appuntamento per:" + "</b></td>" + "<td>" + name + " "  + surname + "</td>" + "</tr>" 
				+ "<tr>" + "<td align=\"right\"><b>" + "Prestazione:" + "</b></td>" + "<td>" + serviceName + "</td>" + "</tr>" 
				+ "<tr>" + "<td align=\"right\"><b>" + "Unita' erogatrice:" + "</b></td>" + "<td>" + clinicName + "</td>" + "</tr>"  
				+ "</table>"
				+ "<p><hr/><b>Risultato</b><hr/></p>"
				+ result;
	}

}
