package hospitalManagemenSystem;

public class Queue {
	// Pointers to the front and rear of the queue.
    Node front;
    Node rear;
    int size;

    // Constructor to initialize an empty queue.
    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    
     // Enqueue: Adds a patient to the end of the line.
     // Uses the Node constructor that accepts a 'Patient'.
     
    public void enqueue(Patient patient) {
        // Create a new node specifically for a Patient
        Node newNode = new Node(patient);

        // If the queue is empty, the new node is both front and rear.
        if (rear == null) {
            front = rear = newNode;
        } else {
            // Link the new node to the end and update rear
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    
     // Dequeue: Removes the patient from the front of the line.
     // Returns the 'Patient' object directly (no casting needed).
     
    public Patient dequeue() {
        if (front == null) {
            return null; // Queue is empty
        }

        // Retrieve the patient from the 'patient' field of your Node class
        Patient leavingPatient = front.patient;
        
        // Move the front pointer to the next node
        front = front.next;

        // If the queue becomes empty, set rear to null as well
        if (front == null) {
            rear = null;
        }
        size--;
        
        return leavingPatient;
    }

    
     // Peek: Checks who is at the front without removing them.
     
    public Patient peek() {
        if (front == null) {
            return null;
        }
        // Directly access the patient field
        return front.patient;
    }

    
     // Checks if the waiting line is empty.
     
    public boolean isEmpty() {
        return front == null;
    }
    
    
     // Returns the number of patients in line.
     
    public int getSize() {
        return size;
    }

    
     // Helper method to print the current waiting line.
     
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Waiting line is empty.");
            return;
        }
        
        Node current = front;
        System.out.print("Waiting Line: ");
        while (current != null) {
            // We can safely access current.patient.name because we know it holds a Patient
            if (current.patient != null) {
                System.out.print("[" + current.patient.getName() + "] -> ");
            }
            current = current.next;
        }
        System.out.println("END");
    }

}
