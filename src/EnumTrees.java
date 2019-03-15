import tech.vanyo.treePrinter.TreePrinter;

import java.util.ArrayList;
import java.util.List;

public class EnumTrees {

    // This tests treePrinter by enumerating trees of a given size.
    // These trees are labelled with either ints or words for ints.

    public static void main(String[] args) {
        List<TreeNode> trees = enumTrees(6, true);

        /*
            We declare a TreePrinter object, parameterized with the type of tree object it will be printing (in this
            case TreeNode), and call the TreePrinter constructor, providing lambda functions to get the TreeNode's
            label as a String, and to get the left and right and right subtrees.
         */
        TreePrinter<TreeNode> printer = new TreePrinter<>(n -> n.getLabel(), n -> n.getLeft(), n -> n.getRight());

        printer.setSquareBranches(true);
        printer.printTrees(trees, 120);
    }

    public static List<TreeNode> enumTrees(int treeSize, boolean useWordsForNumbers) {
        return enumTrees(1, treeSize, useWordsForNumbers);
    }

    private static List<TreeNode> enumTrees(int firstLabel, int lastLabel, boolean useWordsForNumbers) {
        List<TreeNode> allTrees = new ArrayList<>();
        if (firstLabel > lastLabel) {
            allTrees.add(null);
        } else {
            for (int rootLabel = firstLabel; rootLabel <= lastLabel; rootLabel++) {
                List<TreeNode> leftTrees = enumTrees(firstLabel, rootLabel - 1, useWordsForNumbers);
                List<TreeNode> rightTrees = enumTrees(rootLabel + 1, lastLabel, useWordsForNumbers);
                for (TreeNode leftTree : leftTrees) {
                    for (TreeNode rightTree : rightTrees) {
                        TreeNode root = new TreeNode(labelForNode(rootLabel, useWordsForNumbers), leftTree, rightTree);
                        allTrees.add(root);
                    }
                }
            }
        }
        return allTrees;
    }

    private static String labelForNode(int n, boolean useWordsForNumbers) {
        final String[] numberNames = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

        if (useWordsForNumbers) return numberNames[n];
        else return "" + n;
    }

}
