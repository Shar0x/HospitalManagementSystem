package hospitalManagemenSystem;

public class BinarySeacrhTree {

	private TreeNode root;

	public void insert(Patient p) {
		root = insertRec(root, p);
	}

	private TreeNode insertRec(TreeNode root, Patient p) {
		if (root == null) {
			root = new TreeNode(p);
			return root;
		}

		String pName = p.getName();
		String rootName = root.getPatient().getName();

		if (pName.compareTo(rootName) < 0)
			root.setLeft(insertRec(root.getLeft(), p));
		else if (pName.compareTo(rootName) > 0)
			root.setRight(insertRec(root.getRight(), p));

		return root;
	}

	public Patient search(String name) {
		return searchRec(root, name);
	}

	private Patient searchRec(TreeNode root, String name) {
		if (root == null)
			return null;

		String rootName = root.getPatient().getName();

		if (rootName.equals(name)) {
			return root.getPatient();
		}

		if (name.compareTo(rootName) < 0)
			return searchRec(root.getLeft(), name);

		return searchRec(root.getRight(), name);
	}
}
