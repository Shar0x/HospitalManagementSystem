package hospitalManagemenSystem;

public class Doctor {
	int id;
	String name;
	String department;
	Queue waitingLine;

	// Default constructor
	public Doctor(int id, String name, String department) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.waitingLine = new Queue();
	}

}
