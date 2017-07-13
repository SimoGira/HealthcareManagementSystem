import java.sql.Date;
import java.util.ArrayList;

public class Clinic {
	private String name;
	private String company;
	private String street;
	private String cap;
	private String city;
	private String province;
	private Date contractDate;
	private String description;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCAP() {
		return cap;
	}
	public void setCAP(String cap) {
		this.cap = cap;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Date getContractDate() {
		return contractDate;
	}
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getCompleteDescription(ArrayList<String> services)
	{
		String head =  "<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\">"
				+ "<tr>" + "<td align=\"right\"><b>" + "Nome Ambulatorio:" + "</b></td>" + "<td>" + name + "</td>" + "</tr>" 
				+ "<tr>" + "<td align=\"right\"><b>" + "Indirizzo:" + "</b></td>" + "<td>" + street + ", " + cap + " " + city + " (" + province + ")" + "</td>" + "</tr>"
				+ "<tr>" + "<td align=\"right\"><b>" + "Data contratto:" + "</b></td>" + "<td>" + contractDate + "</td>" + "</tr>"  
				+ "</table>"
				+ "<p><hr/><b>Descrizione</b><hr/></p>"
				+ description ;

		String svc = "<p><hr/><b>Services</b><hr/></p><ul>";
		for(String s :services)
			svc += "<li>" + s + "</li>";
		svc += "</ul>";


		return head + svc;



	}

}
