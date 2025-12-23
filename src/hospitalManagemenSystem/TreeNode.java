package hospitalManagemenSystem;

public class TreeNode {
	private Patient patient;
	private TreeNode left;
	private TreeNode right;

	public TreeNode(Patient patient) {
		this.patient = patient;
		this.left = null;
		this.right = null;
	}

	// Getter and setter
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
}
