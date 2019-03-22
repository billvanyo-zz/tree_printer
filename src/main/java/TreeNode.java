public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    // convenience methods to build trees

    public static TreeNode tree(int value, TreeNode left, TreeNode right) {
        return new TreeNode(value, left, right);
    }

    public static TreeNode treeLeft(int value, TreeNode left) {
        return new TreeNode(value, left, null);
    }

    public static TreeNode treeRight(int value, TreeNode right) {
        return new TreeNode(value, null, right);
    }

    public static TreeNode leaf(int value) {
        return new TreeNode(value, null, null);
    }
}

