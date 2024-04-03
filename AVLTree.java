class AVLTree {
    private class TreeNode {
        int val, height;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            height = 1;
        }
    }

    private TreeNode root;

    // Insertion in AVL Tree
    public void insert(int val) {
        root = insertRec(root, val);
    }

    private TreeNode insertRec(TreeNode node, int val) {
        if (node == null)
            return new TreeNode(val);

        if (val < node.val)
            node.left = insertRec(node.left, val);
        else if (val > node.val)
            node.right = insertRec(node.right, val);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && val < node.left.val)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && val > node.right.val)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && val > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && val < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int height(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalance(TreeNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Searching in AVL Tree
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

// Test class for AVL Tree
public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree avl = new AVLTree();
        
        // Insertion test
        avl.insert(10);
        avl.insert(5);
        avl.insert(15);
        avl.insert(3);
        avl.insert(7);
        
        // Searching test
        System.out.println("AVL Search 7: " + avl.search(7)); Output: true
        System.out.println("AVL Search 20: " + avl.search(20));  Output: false
    }
}
