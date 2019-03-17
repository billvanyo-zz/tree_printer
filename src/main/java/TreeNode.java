public class TreeNode {
    private String label;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(String label) {
        this.label = label;
    }

    public TreeNode(String label, TreeNode left, TreeNode right) {
        this.label = label;
        this.left = left;
        this.right = right;
    }

    public static TreeNode tree(String label, TreeNode left, TreeNode right) {
        return new TreeNode(label, left, right);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public static TreeNode treeLeft(String label, TreeNode left) {
        return new TreeNode(label, left, null);
    }

    public static TreeNode treeRight(String label, TreeNode right) {
        return new TreeNode(label, null, right);
    }

    public static TreeNode leaf(String label) {
        return new TreeNode(label, null, null);
    }
}

