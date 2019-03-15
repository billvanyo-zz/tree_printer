import tech.vanyo.treePrinter.TreePrinter;

import java.util.Random;

public class RandomTree {

    private static Random r = new Random();

    public static void main(String[] args) {
        TreeNode tree = randomTree(60, false);

        /*
            We declare a TreePrinter object, parameterized with the type of tree object it will be printing (in this
            case TreeNode), and call the TreePrinter constructor, providing lambda functions to get the TreeNode's
            label as a String, and to get the left and right and right subtrees.
         */
        TreePrinter<TreeNode> printer = new TreePrinter<>(n -> n.getLabel(), n -> n.getLeft(), n -> n.getRight());

        printer.printTree(tree, 1, true);
        System.out.println();
        printer.printTree(tree, 1, false);
        System.out.println();
        printer.printTree(tree, 3, true);
        System.out.println();
        printer.printTree(tree, 3, false);
    }

    public static TreeNode randomTree(int n, boolean userWordsForNumbers) {
        return randomTree(1, n, userWordsForNumbers);
    }

    private static TreeNode randomTree(int firstLabel, int lastLabel, boolean userWordsForNumbers) {
        if (firstLabel > lastLabel) return null;
        else {
            int treeSize = lastLabel - firstLabel + 1;
            int leftCount = r.nextInt(treeSize);
            int rightCount = treeSize - leftCount - 1;
            TreeNode root = new TreeNode(labelForNode(firstLabel + leftCount, userWordsForNumbers));
            root.setLeft(randomTree(firstLabel, firstLabel + leftCount - 1, userWordsForNumbers));
            root.setRight(randomTree(firstLabel + leftCount + 1, lastLabel, userWordsForNumbers));
            return root;
        }
    }

    private static String labelForNode(int n, boolean useWordsForNumbers) {
        if (useWordsForNumbers) return nameForNumber(n);
        else return "" + n;
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
