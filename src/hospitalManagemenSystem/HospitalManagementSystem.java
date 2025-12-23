package hospitalManagemenSystem;

import java.util.Random;

public class HospitalManagementSystem {
	private HashMap patientTable;
	private PriorityQueue emergencyQueue;
	private BinarySeacrhTree nameSearchTree;
	private Stack undoStack;
	private Doctor[] doctors;

	public HospitalManagementSystem(long studentID) {
		Random rand = new Random(studentID);
		System.out.println("System Initialized. ID Seed: " + studentID);
		System.out.println("Random Verification Number: " + rand.nextInt(100));

		this.patientTable = new HashMap(100);
		this.emergencyQueue = new PriorityQueue(50);
		this.nameSearchTree = new BinarySeacrhTree();
		this.undoStack = new Stack();
		this.doctors = new Doctor[5];

		doctors[0] = new Doctor(1, "Dr. Kayra", "Internal diseases");
		doctors[1] = new Doctor(2, "Dr. Yaren", "Dermatalogy ");
		doctors[2] = new Doctor(3, "Dr. Cemre", "Cardiology");
	}

	public void registerPatient(int id, String name, int severity) {
		Patient p = new Patient(id, name, severity);
		patientTable.put(id, p);
		nameSearchTree.insert(p);

		undoStack.push("REGISTER:" + id);
		System.out.println("Patient Registered: " + name + " (ID: " + id + ")");
	}

	public void admitToER(int id) {
		Patient p = patientTable.get(id);
		if (p != null) {
			emergencyQueue.insert(p);
			System.out.println("ER ADMISSION: " + p.getName() + " (Severity: " + p.getSeverityLevel() + ")");
			undoStack.push("ER_ADMIT:" + id); // Log for undo
		} else {
			System.out.println("Error: Patient with ID " + id + " not found!");
		}
	}

	public void processEmergency() {
		if (!emergencyQueue.isEmpty()) {
			Patient p = emergencyQueue.extractMax();
			System.out.println("TREATING PATIENT: " + p.getName() + " (Severity: " + p.getSeverityLevel() + ")");
			p.addHistory("Treated in ER. Date: " + System.currentTimeMillis());
		} else {
			System.out.println("ER is empty. No patients to treat.");
		}
	}

	public void undo() {
		if (undoStack.isEmpty()) {
			System.out.println("Undo Stack is empty. Nothing to undo.");
			return;
		}
		String action = undoStack.pop();
		String[] parts = action.split(":");
		String command = parts[0];
		int id = Integer.parseInt(parts[1]);

		System.out.println(">>> UNDOING Operation: " + command);
		if (command.equals("REGISTER")) {
			patientTable.remove(id);
			System.out.println("Success: Patient registration removed.");
		}
	}

	public void searchByName(String name) {
		Patient p = nameSearchTree.search(name);
		if (p != null) {
			System.out.println("FOUND: " + p.toString());
			System.out.println("Medical History:");
			p.getMedicalHistory().printHistory();
		}

		else
			System.out.println("Patient not found by name: " + name);
	}
}
