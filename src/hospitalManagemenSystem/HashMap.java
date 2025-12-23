package hospitalManagemenSystem;

public class HashMap {
	private HashNode[] table;
	private int capacity;

	public HashMap(int capacity) {
		this.capacity = capacity;
		table = new HashNode[capacity];
	}

	private int hash(int key) {
		return key % capacity;
	}

	public void put(int key, Patient value) {
		int index = hash(key);
		HashNode newNode = new HashNode(key, value);

		if (table[index] == null) {
			table[index] = newNode;
		} else {

			HashNode current = table[index];
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
		}
	}

	public Patient get(int key) {
		int index = hash(key);
		HashNode current = table[index];
		while (current != null) {
			if (current.key == key)
				return current.value;
			current = current.next;
		}
		return null;
	}
	// remove
	public void remove(int key) {
		int index = hash(key);
		HashNode current = table[index];
		HashNode prev = null;

		while (current != null) {
			if (current.key == key) {
				if (prev == null)
					table[index] = current.next;
				else
					prev.next = current.next;
				return;
			}
			prev = current;
			current = current.next;
		}
	}
}
