package hospitalManagemenSystem;

public class PriorityQueue {
    public class PriorityQueue {
        Patient[] heap;
        int size;
        int capacity;

        public PriorityQueue(int capacity) {
            this.capacity = capacity;
            this.heap = new Patient[capacity];
            this.size = 0;
        }

        private int parent(int i) { return (i - 1) / 2; }
        private int leftChild(int i) { return (2 * i) + 1; }
        private int rightChild(int i) { return (2 * i) + 2; }

        public void insert(Patient p) {
            if (size == capacity) {
                System.out.println("ER is full");
                return;
            }
            heap[size] = p;
            int current = size;
            size++;

            // Heapify Up
            while (current > 0 && heap[current].getSeverityLevel() > heap[parent(current)].getSeverityLevel()) {
                swap(current, parent(current));
                current = parent(current);
            }
        }

        public Patient extractMax() {
            if (size <= 0) return null;
            if (size == 1) {
                size--;
                return heap[0];
            }

            Patient root = heap[0];
            heap[0] = heap[size - 1];
            size--;
            maxHeapify(0);

            return root;
        }

        private void maxHeapify(int i) {
            int largest = i;
            int left = leftChild(i);
            int right = rightChild(i);

            if (left < size && heap[left].getSeverityLevel() > heap[largest].getSeverityLevel())
                largest = left;
            if (right < size && heap[right].getSeverityLevel() > heap[largest].getSeverityLevel())
                largest = right;

            if (largest != i) {
                swap(i, largest);
                maxHeapify(largest);
            }
        }

        private void swap(int i, int j) {
            Patient temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }
}
