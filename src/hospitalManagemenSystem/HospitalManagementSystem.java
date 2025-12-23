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

		this.patientTable = new HashMap(100);
		this.emergencyQueue = new PriorityQueue(50);
		this.nameSearchTree = new BinarySeacrhTree();
		this.undoStack = new Stack();
		this.doctors = new Doctor[5];

		doctors[0] = new Doctor(1, "Dr. Ali", "Dahiliye");
		doctors[1] = new Doctor(2, "Dr. Ayse", "KBB");
	}

	public void registerPatient(int id, String name, int severity) {
		Patient p = new Patient(id, name, severity);
		patientTable.put(id, p);
		nameSearchTree.insert(p);

		undoStack.push("REGISTER:" + id);
		System.out.println("Hasta Kaydedildi: " + name);
	}

	public void admitToER(int id) {
		Patient p = patientTable.get(id);
		if (p != null) {
			emergencyQueue.insert(p);
			System.out.println(p.getName() + " Acil Servise Alındı (Seviye: " + p.getSeverityLevel() + ")");
			undoStack.push("ER_ADMIT:" + id);
		} else {
			System.out.println("Hasta bulunamadı!");
		}
	}

	public void processEmergency() {
		if (!emergencyQueue.isEmpty()) {
			Patient p = emergencyQueue.extractMax();
			System.out.println("ACİL TEDAVİ: " + p.getName() + " (Durum: " + p.getSeverityLevel() + ")");
			p.addHistory("Acil müdahale yapıldı.");
		} else {
			System.out.println("Acil serviste bekleyen yok.");
		}
	}

	public void undo() {
		if (undoStack.isEmpty()) {
			System.out.println("Geri alınacak işlem yok.");
			return;
		}
		String action = undoStack.pop();
		String[] parts = action.split(":");
		String command = parts[0];
		int id = Integer.parseInt(parts[1]);

		System.out.println(">>> Geri Alınıyor: " + command);
		if (command.equals("REGISTER")) {
			patientTable.remove(id);
			System.out.println("Hasta kaydı silindi (Hash Table'dan).");
		}
	}

	public void searchByName(String name) {
		Patient p = nameSearchTree.search(name);
		if (p != null)
			System.out.println("Bulundu: " + p); //
		else
			System.out.println("İsimle bulunamadı: " + name);
	}
}
