package hospitalManagemenSystem;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		long myStudentId = 230315073;

		System.out.println("==========================================");
		System.out.println("   HOSPITAL MANAGEMENT SYSTEM STARTED     ");
		System.out.println("==========================================\n");

		HospitalManagementSystem hms = new HospitalManagementSystem(myStudentId);

		// 1. Patient Registration Test
		System.out.println("--- 1. REGISTRATION PATIENTS ---");
		hms.registerPatient(101, "Hilmi Aydın", 2); // Low severity
		hms.registerPatient(102, "Kayra Emre Karaosmanoğlu", 9); // Critical severity
		hms.registerPatient(103, "Kayra Öz", 5); // Medium severity

		// 2. Medical History Test
		System.out.println("\n--- 2. MEDICAL HISTORY RECORDS ---");
		hms.addMedicalHistory(101, "Complained of mild headache.");
		hms.addMedicalHistory(102, "Severe chest pain reported.");

		// 3. Search Test (HashMap & BST)
		System.out.println("\n--- 3. SEARCHING PATIENTS ---");
		hms.searchPatientById(102); // Lookup by ID
		hms.searchPatientByName("Hilmi Aydın"); // Lookup by Name

		// 4. ER Triage Test (Priority Queue)
		// High severity patients must be treated first, no matter the arrival time
		System.out.println("\n--- 4. ER TRIAGE  ---");
		hms.sendToEmergency(101); // Severity 2
		hms.sendToEmergency(103); // Severity 5
		hms.sendToEmergency(102); // Severity 9 (Last arrival but highest priority)

		System.out.println("\n[Doctors treating ER patients...]");
		hms.treatEmergencyPatient(); // Should be Kayra (9)
		hms.treatEmergencyPatient(); // Should be Kayra (5)
		hms.treatEmergencyPatient(); // Should be Hilmi (2)

		// 5. Doctor Queue Test (FIFO)
		System.out.println("\n--- 5. DOCTOR APPOINTMENT  ---");
		hms.sendToDoctor(101, 0); // Hilmi to Dr. Cemre
		hms.sendToDoctor(103, 0); // Kayra to Dr. Cemre

		hms.processDoctorQueue(0); // Hilmi first (FIFO)
		hms.processDoctorQueue(0); // Kayra second

		// 6. Undo Test (Stack)
		System.out.println("\n--- 6. UNDO ACTIONS ---");
		// Create an erroneous entry
		hms.registerPatient(999, "Wrong Entry", 1);
		hms.searchPatientById(999); // Verify existence

		// Undo the action
		System.out.println("-> Performing Undo...");
		hms.undoLastAction();

		// Verify deletion
		hms.searchPatientById(999); // Should not be found

		System.out.println("\n==========================================");
		System.out.println("       SYSTEM TEST COMPLETED              ");
		System.out.println("==========================================");
	}
}
