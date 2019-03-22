import tech.vanyo.treePrinter.TreePrinter;

public class CollatzTree {

    // prints tree diagram for tree representation of "reverse" Collatz sequences
    public static void main(String[] args) {
        TreeNode root;

        root = collatzTree(18);

        TreePrinter<TreeNode> printer = new TreePrinter<>(n -> ""+n.getValue(), n -> n.getLeft(), n -> n.getRight());

        printer.setHspace(1);
        printer.setSquareBranches(true);
        printer.setLrAgnostic(true);
        printer.printTree(root);
    }


    private static TreeNode collatzTree(int depth) {
        return collatzTree(1, 1, depth);
    }

    private static TreeNode collatzTree(int start, int curLength, int maxLength) {
        TreeNode root = new TreeNode(start);
        if (curLength < maxLength) {
            root.setLeft(collatzTree(start*2, curLength+1, maxLength));
            if (start%6==4 && start>4) root.setRight(collatzTree((start-1)/3, curLength+1, maxLength));
        }
        return root;
    }
}
