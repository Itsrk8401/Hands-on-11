class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

class BST {
    TreeNode root;

    public BST() {
        root = null;
    }

    // Insertion in BST
    public void insert(int val) {
        root = insertRec(root, val);
    }

    private TreeNode insertRec(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        if (val < root.val)
            root.left = insertRec(root.left, val);
        else if (val > root.val)
            root.right = insertRec(root.right, val);

        return root;
    }

    // Searching in BST
    public boolean search(int val) {
        return searchRec(root, val);
    }

    private boolean searchRec(TreeNode root, int val) {
        if (root == null)
            return false;

        if (root.val == val)
            return true;

        if (val < root.val)
            return searchRec(root.left, val);
        else
            return searchRec(root.right, val);
    }
}

// Test class for BST
public class BSTTest {
    public static void main(String[] args) {
        BST bst = new BST();
        
        // Insertion test
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        
        // Searching test
        System.out.println("BST Search 7: " + bst.search(7)); Output: true
        System.out.println("BST Search 20: " + bst.search(20));  Output: false
    }
}
