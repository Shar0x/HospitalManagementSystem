package hospitalManagemenSystem;

public class Patient {
	private int id;
	private String name;
	private int severityLevel;
	LinkedList medicalHistory;

	public Patient(int id, String name, int severityLevel) {
		this.id = id;
		this.name = name;
		this.severityLevel = severityLevel;
		this.medicalHistory = new LinkedList();
	}

	public void addHistory(String record) {
		medicalHistory.add(record);
	}

	// getter and setter
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setSeverityLevel(int severitylevel) {
		this.severityLevel = severityLevel;
	}

	public int getSeverityLevel() {
		// TODO Auto-generated method stub
		return severityLevel;
	}
	public LinkedList getMedicalHistory() {
		return medicalHistory;
	}

	@Override
	public String toString() {
		return "ID: " + id + " | " + name + " | Seviye: " + severityLevel;
	}

}
