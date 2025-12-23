package hospitalManagemenSystem;

public class Node {
	
	// "Object" is used so this Node can hold Strings (History) or Patients (Queue)
	Patient patient;
    String data; 
    Node next;   // Reference to the next node in the list
    
    public Node(Patient patient) {
    	this.patient = patient;
        this.data = null;
        this.next = null;
    }

    public Node(String data) {
        this.data = data;
        this.patient = null;
        this.next = null;
    }
}
