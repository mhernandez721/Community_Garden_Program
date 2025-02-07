package gardenProgram;

public class Appointment extends GardenEntity implements Schedulable {
	
	private String date;
	private String time;
	

	//Constructor to make appointment details
	
	public Appointment(String name, String date, String time) {
		
		super(name);
		this.date = date;
		this.time = time;
	}
	
	//Prints appointment details to console and implements method.
	
	public void schedule() {
		
		System.out.println("Appointment schedule for " + name + " on " + date + " at " + time);
	}
	
	//Returns appointment details to String
	
	public String getDetails() {
		return "User: " + name + ", Date: " + date + ", Time: " + time;
	}

}
