package hospitalManagemenSystem;

public class Doctor {
	private int id;
	private String name;
	private String department;
	Queue waitingLine;

	// Default constructor
	public Doctor(int id, String name, String department) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.waitingLine = new Queue();
	}
	// getter and setter
	public int getId() {
		return id;
	}

	public String getName() {
		return getName();
	}

	@Override
	public String toString() {
		return "Dr." + name + "(" + department + ") - ID" + id;
	}

}
