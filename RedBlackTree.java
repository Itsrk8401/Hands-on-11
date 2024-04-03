class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        int val;
        Node left, right;
        boolean color;

        Node(int val) {
            this.val = val;
            this.color = RED;
        }
    }

    private Node root;

    // Insertion in RBT
    public void insert(int val) {
        root = insert(root, val);
        root.color = BLACK;
    }

    private Node insert(Node h, int val) {
        if (h == null)
            return new Node(val);

        if (val < h.val)
            h.left = insert(h.left, val);
        else if (val > h.val)
            h.right = insert(h.right, val);

        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        return h;
    }

    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    // Searching in RBT
    public boolean search(int val) {
        return search(root, val);
    }

    private boolean search(Node x, int val) {
        while (x != null) {
            if (val < x.val)
                x = x.left;
            else if (val > x.val)
                x = x.right;
            else
                return true;
        }
        return false;
    }
}

// Test class for Red-Black Tree
public class RedBlackTreeTest {
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        
        // Insertion test
        rbt.insert(10);
        rbt.insert(5);
        rbt.insert(15);
        rbt.insert(3);
        rbt.insert(7);
        
        // Searching test
        System.out.println("RBT Search 7: " + rbt.search(7)); Output: true
        System.out.println("RBT Search 20: " + rbt.search(20));  Output: false
    }
}
