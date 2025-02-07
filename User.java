package gardenProgram;

public class User extends GardenEntity {
	
	private String contact;
	
	//Constructor for User information initialization
	
	public User(String name, String contact) {
		
		super(name);
		this.contact = contact;
		
	}
	
	//Returns contact info of the user
	
	public String getContact() {
		return contact;
	}

}
