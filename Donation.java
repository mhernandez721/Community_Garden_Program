package gardenProgram;

//Class that handles donations

public class Donation extends GardenEntity implements Donatable {
	
	private String type;
	private double amount;
	
	//Constructor to make donation details
	
	public Donation(String name, String type, double amount) {
		
		super(name);
		this.type = type;
		this.amount = amount;
		
	}
	
	//Prints donation details to console and implements method
	
	public void donate() {
		
		System.out.println("Donation received: " + type + " from " + name + " (Amount: " + amount + ")");
	}
	
	//Returns details as a formated string
	
	public String getDetails() {
		return "Type: " + type + ", Amount " + amount;
	}
}
