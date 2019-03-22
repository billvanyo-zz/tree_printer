import tech.vanyo.treePrinter.TreePrinter;

import java.util.ArrayList;
import java.util.List;

public class EnumTrees {

    // This tests treePrinter by enumerating trees of a given size.
    // These trees are labelled with either ints or words for ints.

    public static void main(String[] args) {
        List<TreeNode> trees = enumTrees(6);

        /*
            We declare a TreePrinter object, parameterized with the type of tree object it will be printing (in this
            case TreeNode), and call the TreePrinter constructor, providing lambda functions to get the TreeNode's
            label as a String, and to get the left and right and right subtrees.
         */
        TreePrinter<TreeNode> printer = new TreePrinter<>(n -> labelForNode(n.getValue()), n -> n.getLeft(), n -> n.getRight());
        printer.setSquareBranches(true);
        printer.printTrees(trees, 120);
    }

    public static List<TreeNode> enumTrees(int treeSize) {
        return enumTrees(1, treeSize);
    }

    private static List<TreeNode> enumTrees(int firstValue, int lastValue) {
        List<TreeNode> allTrees = new ArrayList<>();
        if (firstValue > lastValue) {
            allTrees.add(null);
        } else {
            for (int rootValue = firstValue; rootValue <= lastValue; rootValue++) {
                List<TreeNode> leftTrees = enumTrees(firstValue, rootValue - 1);
                List<TreeNode> rightTrees = enumTrees(rootValue + 1, lastValue);
                for (TreeNode leftTree : leftTrees) {
                    for (TreeNode rightTree : rightTrees) {
                        TreeNode root = new TreeNode(rootValue, leftTree, rightTree);
                        allTrees.add(root);
                    }
                }
            }
        }
        return allTrees;
    }

    private static String labelForNode(int n) {
        final String[] numberNames = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        return numberNames[n];
    }

}
