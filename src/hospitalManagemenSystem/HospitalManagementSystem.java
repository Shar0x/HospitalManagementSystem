package hospitalManagemenSystem;

import java.util.Random;


//This class acts as the main controller for the application.
// It coordinates different data structures to manage patients, doctors,
// and system operations effectively.
 public class HospitalManagementSystem {
	HashMap patientMap; // Lookup by unique ID
	BinarySeacrhTree patientTree; // We used seperate BST class to store patients sorted by name
	PriorityQueue emergencyRoom; // Used for the ER to triage patients by their severity
	Doctor[] doctors; //For seeing the availabke doctors
	Stack undoStack; // Using LIFO For Undo function

	long studentID; // To meet the Unique ID requirement

	public HospitalManagementSystem(long studentID) {
		this.studentID = studentID;

		// Integrating data structers types for the project
		patientMap = new HashMap(100);
		patientTree = new BinarySeacrhTree();
		emergencyRoom = new PriorityQueue(50);
		undoStack = new Stack();

		// Initialize Doctors
		doctors = new Doctor[3];
		doctors[0] = new Doctor(1, "Cemre", "Cardiology");
		doctors[1] = new Doctor(2, "Ceyda", "Internal Med.");
		doctors[2] = new Doctor(3, "Yaren", "Deramtalogy");

		// Generate initial data using Student ID as seed
		generateInitialData();
	}

	// Requirement: Use Student ID in logic
	private void generateInitialData() {
		System.out.println("System initializing with Student ID: " + studentID);
		Random rand = new Random(studentID); // Seeding random with ID

		// Automatically register 3 patients for demo
		for (int i = 0; i < 3; i++) {
			int id = 1000 + i;
			int severity = rand.nextInt(10) + 1; // Giving eandom severity 1-10
			registerPatient(id, "TestPatient" + i, severity);
		}
		System.out.println("--- Initial Data Loaded ---\n");
	}

	// 1. Patient Registration
	// Adding patients to HashMap and BST
	public void registerPatient(int id, String name, int severity) {
		Patient newPatient = new Patient(id, name, severity);
		patientMap.put(id, newPatient);
		patientTree.insert(newPatient);

		// Push action to Stack to enable Undo function
		undoStack.push("REGISTER:" + id);
		System.out.println("Patient Registered: " + newPatient.toString());
	}

	// 2. Adding Medical History
	public void addMedicalHistory(int id, String record) {
		Patient p = patientMap.get(id);
		if (p != null) {
			p.addHistory(record);
			System.out.println("History Added to: " + p.getName());
		} else {
			System.out.println("Error: Patient not found with ID: " + id);
		}
	}

	// 3. Emergency Room (Priority Queue)
	// Used to treat patients according to their severity level
	public void sendToEmergency(int id) {
		Patient p = patientMap.get(id);
		if (p != null) {
			emergencyRoom.insert(p);
			System.out.println("Admitted to ER: " + p.getName() + " (Severity: " + p.getSeverityLevel() + ")");
		} else {
			System.out.println("Patient not found.");
		}
	}
	// Finds the patient with the highest highest severity
	public void treatEmergencyPatient() {
		if (!emergencyRoom.isEmpty()) {
			Patient p = emergencyRoom.extractMax();
			System.out.println("TREATING ER PATIENT: " + p.toString());
		} else {
			System.out.println("ER is empty.");
		}
	}

	// 4. Doctor Appointment (Queue)
	// Adding a patient to a selected doctor's line
	public void sendToDoctor(int patientId, int doctorIndex) {
		if (doctorIndex < 0 || doctorIndex >= doctors.length) {
			System.out.println("Invalid doctor selection.");
			return;
		}
		Patient p = patientMap.get(patientId);
		if (p != null) {
			doctors[doctorIndex].waitingLine.enqueue(p);
			System.out.println(p.getName() + " added to Dr. " + doctors[doctorIndex].getName() + "'s queue.");
		}
	}
	// Continueing with the next patient on the line
	public void processDoctorQueue(int doctorIndex) {
		if (!doctors[doctorIndex].waitingLine.isEmpty()) {
			Patient p = doctors[doctorIndex].waitingLine.dequeue();
			System.out.println("Dr. " + doctors[doctorIndex].getName() + " is examining: " + p.getName());
		} else {
			System.out.println("No patients in queue for Dr. " + doctors[doctorIndex].getName());
		}
	}

	// 5. Search Function
	public void searchPatientById(int id) {
		Patient p = patientMap.get(id);
		if (p != null)
			System.out.println("SEARCH FOUND (ID): " + p);
		else
			System.out.println("SEARCH NOT FOUND ID: " + id);
	}

	public void searchPatientByName(String name) {
		Patient p = patientTree.search(name);
		if (p != null)
			System.out.println("SEARCH FOUND (Name): " + p);
		else
			System.out.println("SEARCH NOT FOUND Name: " + name);
	}

	// 6. Undo Function (Stack)
	// Uses the Stack to reverse the last registering action
	public void undoLastAction() {
		if (undoStack.isEmpty()) {
			System.out.println("Nothing to undo.");
			return;
		}

		String lastAction = undoStack.pop();
		String[] parts = lastAction.split(":");
		String actionType = parts[0];

		if (actionType.equals("REGISTER")) {
			int id = Integer.parseInt(parts[1]);
			// Remove from HashMap
			patientMap.remove(id);
			System.out.println("UNDO SUCCESSFUL: Removed patient with ID " + id);
		}
	}
}
