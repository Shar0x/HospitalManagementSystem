package hospitalManagemenSystem;

public class LinkedList {
        Node head;

        public void add(String data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        public void printHistory() {
            Node current = head;
            System.out.print("   Medical History: ");
            if (current == null) {
                System.out.println("No records.");
                return;
            }
            while (current != null) {
                System.out.print("(" + current.data + ") --> ");
                current = current.next;
            }
            System.out.println("END");
        }
    }

