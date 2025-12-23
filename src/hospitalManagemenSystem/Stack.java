package hospitalManagemenSystem;

public class Stack {
        Node top;

        public void push(String action) {
            Node newNode = new Node(action);
            newNode.next = top;
            top = newNode;
        }

        public String pop() {
            if (top == null) return null;
            String action = (String) top.data;
            top = top.next;
            return action;
        }

        public boolean isEmpty() {
            return top == null;
        }
    }

