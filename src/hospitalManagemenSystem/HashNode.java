package hospitalManagemenSystem;

public class HashNode {
	
	int key;
	Patient value;
	HashNode next;

	public HashNode(int key, Patient value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
}
