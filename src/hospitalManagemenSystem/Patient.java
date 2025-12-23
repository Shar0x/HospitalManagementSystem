package hospitalManagemenSystem;

public class Patient {
	int id;
	String name;
	int severityLevel; 
	LinkedList medicalHistory; 

	public Patient(int id, String name, int severityLevel) {
		this.id = id;
		this.name = name;
		this.severityLevel = severityLevel;
		this.medicalHistory = new LinkedList();
	}

	public void addHistory(String record) {
		// medicalHistory.add(record);
	}

	@Override
	public String toString() {
		return "ID: " + id + " | " + name + " | Seviye: " + severityLevel;
	}

}
