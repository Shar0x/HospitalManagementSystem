package hospitalManagemenSystem;

import java.util.Scanner;

public class Main {

    // Student ID required for project uniqueness
    final long STUDENT_ID = 230315023L;
    
    // Core components
    HospitalManagementSystem hms;
    Scanner scanner;

    // Constructor to initialize the system
    public Main() {
        this.hms = new HospitalManagementSystem(STUDENT_ID);
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        // Start the application
        Main app = new Main();
        app.startSystem();
    }

    public void startSystem() {
        System.out.println("\n=== HOSPITAL MANAGEMENT SYSTEM ===");
        System.out.println("Initializing with Student ID: " + STUDENT_ID);

        boolean running = true;
        while (running) {
            printMenu();
            int choice = -1;
            
            try {
                System.out.print("Select an option: ");
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    registerPatient(); 
                    break;
                case 2:
                    searchPatient();   
                    break;
                case 3:
                    admitToER();       
                    break;
                case 4:
                    processER();       
                    break;
                case 5:
                    undoLastAction();  
                    break;
                case 6:
                    bookAppointment(); 
                    break;
                case 7:
                    processDoctorQueue(); 
                    break;
                case 8:
                    hms.printDoctors();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Register New Patient");
        System.out.println("2. Search Patient (by Name)");
        System.out.println("3. Admit to Emergency Room (ER)");
        System.out.println("4. Treat Critical Patient (ER Triage)");
        System.out.println("5. Undo Last Action");
        System.out.println("6. Book Doctor Appointment");
        System.out.println("7. Process Doctor's Waiting Line");
        System.out.println("8. List Doctors");
        System.out.println("0. Exit");
    }

    private void registerPatient() {
        System.out.print("Enter Patient ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Severity Level (1-10): ");
        int sev = Integer.parseInt(scanner.nextLine());

        hms.registerPatient(id, name, sev); 
    }

    private void searchPatient() {
        System.out.print("Enter Patient Name to Search: ");
        String name = scanner.nextLine();
        hms.searchByName(name);
    }

    private void admitToER() {
        System.out.print("Enter Patient ID for ER Admission: ");
        int id = Integer.parseInt(scanner.nextLine());
        hms.admitToER(id);
    }

    private void processER() {
        System.out.println("--- Processing Emergency Room ---");
        hms.processEmergency();
    }

    private void undoLastAction() {
        System.out.println("--- Undoing Last Operation ---");
        hms.undo();
    }

    private void bookAppointment() {
        hms.printDoctors();
        System.out.print("Select Doctor ID: ");
        int docId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Patient ID: ");
        int pId = Integer.parseInt(scanner.nextLine());
        
        hms.bookAppointment(docId, pId);
    }

    private void processDoctorQueue() {
        hms.printDoctors();
        System.out.print("Enter Doctor ID to process: ");
        int docId = Integer.parseInt(scanner.nextLine());
        
        hms.processDoctorAppointment(docId);
    }
}
