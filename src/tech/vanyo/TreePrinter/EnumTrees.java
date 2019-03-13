package tech.vanyo.TreePrinter;

import java.util.ArrayList;
import java.util.List;

public class EnumTrees {

    // This tests TreePrinter by enumerating trees of a given size.
    // These trees are labelled with either ints or words for ints.

    public static void main(String[] args) {

        List<TreeNode> trees = enumTrees(5, true);

        // this prints trees by separate calls to printTree
//        for (int i = 0; i < trees.size(); i++) {
//            TreeNode treeNode = trees.get(i);
//            TreePrinter.printTree(treeNode, 1);
//            System.out.println("\n\n");
//        }

        // this prints trees in rows across the page
        TreePrinter.printTrees(trees, 1, 2, 125);
    }

    public static List<TreeNode> enumTrees(int treeSize, boolean useWordsForNumbers) {
        return enumTrees(1, treeSize, useWordsForNumbers);
    }


    private static List<TreeNode> enumTrees(int firstLabel, int lastLabel, boolean useWordsForNumbers) {
        List<TreeNode> allTrees = new ArrayList<>();

        if (firstLabel > lastLabel) {
            allTrees.add(null);
        } else {
            int totalNodes = lastLabel - firstLabel + 1;
            for (int rightCount = 0; rightCount < totalNodes; rightCount++) {

                List<TreeNode> leftTrees = enumTrees(firstLabel, firstLabel+rightCount-1, useWordsForNumbers);
                List<TreeNode> rightTrees = enumTrees(firstLabel+rightCount+1, lastLabel, useWordsForNumbers);

                for (int i = 0; i < leftTrees.size(); i++) {
                    TreeNode leftTree = leftTrees.get(i);

                    for (int j = 0; j < rightTrees.size(); j++) {
                        TreeNode rightTree = rightTrees.get(j);
                        TreeNode root = new TreeNode(labelForNode(firstLabel+rightCount, useWordsForNumbers), leftTree, rightTree);
                        allTrees.add(root);
                    }
                }
            }
        }

        return allTrees;
    }

    private static String labelForNode(int n, boolean useWordsForNumbers) {
        final String numberNames[] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
        "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four"};

        if (useWordsForNumbers) return numberNames[n];
        else return "" + n;
    }

}
