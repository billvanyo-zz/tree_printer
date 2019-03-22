import tech.vanyo.treePrinter.TreePrinter;

import java.util.Random;

public class RandomTree {

    private static Random r = new Random();

    public static void main(String[] args) {
        TreeNode tree = randomTree(30);

        /*
            We declare a TreePrinter object, parameterized with the type of tree object it will be printing (in this
            case TreeNode), and call the TreePrinter constructor, providing lambda functions to get the TreeNode's
            label as a String, and to get the left and right and right subtrees.
         */
        TreePrinter<TreeNode> printer = new TreePrinter<>(n -> nameForNumber(n.getValue()), n -> n.getLeft(), n -> n.getRight());
        // set minimum horizontal spacing between node labels with setHspace
        printer.setHspace(1);
        // use square branches
        printer.setSquareBranches(true);
        printer.printTree(tree);
        System.out.println();

        printer = new TreePrinter<>(n -> ""+n.getValue(), n -> n.getLeft(), n -> n.getRight());
        printer.setHspace(1);
        // use square branches
        printer.setSquareBranches(true);
        printer.printTree(tree);
        System.out.println();

        // option to render single left or right subtree as straight down branch (i.e. no indication of left or right)
        printer.setLrAgnostic(true);
        printer.printTree(tree);

        // use diagonal branches
        printer.setSquareBranches(false);
        printer.printTree(tree);
        System.out.println();

        printer.setHspace(3);
        printer.setSquareBranches(true);
        printer.printTree(tree);
        System.out.println();

        printer.setSquareBranches(false);
        printer.printTree(tree);
    }

    public static TreeNode randomTree(int n) {
        return randomTree(1, n);
    }

    private static TreeNode randomTree(int firstValue, int lastValue) {
        if (firstValue > lastValue) return null;
        else {
            int treeSize = lastValue - firstValue + 1;
            int leftCount = r.nextInt(treeSize);
            int rightCount = treeSize - leftCount - 1;
            TreeNode root = new TreeNode(firstValue + leftCount);
            root.setLeft(randomTree(firstValue, firstValue + leftCount - 1));
            root.setRight(randomTree(firstValue + leftCount + 1, lastValue));
            return root;
        }
    }

    private static String nameForNumber(int n) {
        final String[] underTwenty = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
                "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        final String[] decades = {"twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        if (n < 20) return underTwenty[n];
        else if (n > 99) return "" + n;  // not implemented
        else return decades[n / 10 - 2] + (n % 10 == 0 ? "" : (" " + underTwenty[n % 10]));
    }
}
