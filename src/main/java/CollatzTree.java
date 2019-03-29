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
            // Forward Collatz sequence has that an even number N is followed by N/2, which is either an even or odd number.
            // So in reverse, either an even or odd number (any number) can be preceded by 2N.
            root.setLeft(collatzTree(start*2, curLength+1, maxLength));
            // Forward Collatz sequence has that an odd number N (i.e. a number of form 2X+1) is followed 
            // by 3N+1 (i.e. 3(2X+1)+1, or 6X+4).
            // So in reverse, a number N of the form 6X+4 can be preceded by (N-1)/3 (N-1 is divisible by 3)
            // But if N is 4, we don't want it preceded (in reverse) by 1, since in the forward direction, 
            // the sequence stops at 1 (or, in the reverse direction, 1 is where we started).
            if (start%6==4 && start>4) root.setRight(collatzTree((start-1)/3, curLength+1, maxLength));
        }
        return root;
    }
}
