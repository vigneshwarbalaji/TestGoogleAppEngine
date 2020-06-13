package datastoretest;

public class Employee 
{
	long id;
	String name;
	String mail;
	
	
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String MAIL = "email";
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
}
