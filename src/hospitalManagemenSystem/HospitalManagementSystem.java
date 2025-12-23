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

	public void searchByID(int id) {
		Patient p = patientTable.get(id);
		if (p != null) {
			System.out.println("FOUND (ID Search): " + p.toString());
		} else {
			System.out.println("Patient not found with ID: " + id);
		}
	}

	public void printDoctors() {
		System.out.println("--- Doctor List ---");
		for (Doctor d : doctors) {
			if (d != null) {
				System.out.println("Dr. " + d.getName() + " - " + d.getDepartment());
			}
		}
	}
	// This method handles the logic for scheduling a visit with a specific doctor.
    public void bookAppointment(int docId, int patientId) {
        // Step 1: Retrieve the patient from the Hash Table using the ID.
        Patient p = patientTable.get(patientId);
        
        // Validation: Check if the patient exists in the system.
        if (p == null) {
            System.out.println("Error: Patient not found! Please register the patient first.");
            return;
        }

        // Step 2: Search for the doctor in the doctors array.
        Doctor selectedDoc = null;
        for (Doctor d : doctors) {
            // Check if the doctor slot is not null and IDs match
            if (d != null && d.getId() == docId) {
                selectedDoc = d;
                break; // Doctor found, exit loop
            }
        }

        // Step 3: Add the patient to the doctor's waiting queue.
        if (selectedDoc != null) {
            // Enqueue operation (O(1)) adds patient to the end of the line.
            selectedDoc.waitingLine.enqueue(p);
            System.out.println("Success: " + p.getName() + " has been added to Dr. " + selectedDoc.getName() + "'s waiting line.");
        } else {
            System.out.println("Error: Doctor with ID " + docId + " could not be found.");
        }
    }

    
    // This method simulates a doctor treating the next patient in line (FIFO).
    public void processDoctorAppointment(int docId) {
        // Step 1: Find the doctor by ID.
        Doctor selectedDoc = null;
        for (Doctor d : doctors) {
            if (d != null && d.getId() == docId) {
                selectedDoc = d;
                break;
            }
        }

        // Step 2: Process the queue if the doctor exists.
        if (selectedDoc != null) {
            // Check if there are any patients waiting.
            if (!selectedDoc.waitingLine.isEmpty()) {
                // Dequeue the next patient (First-In-First-Out).
                Patient p = selectedDoc.waitingLine.dequeue();
                
                System.out.println(">>> TREATING OUTPATIENT: " + p.getName());
                
                // Step 3: Automatically update the patient's medical history.
                // This fulfills the "Dynamic History Update" requirement.
                p.addHistory("General Checkup completed with Dr. " + selectedDoc.getName());
            } else {
                System.out.println("Message: Dr. " + selectedDoc.getName() + "'s waiting line is currently empty.");
            }
        } else {
            System.out.println("Error: Doctor not found.");
        }
    }
}
