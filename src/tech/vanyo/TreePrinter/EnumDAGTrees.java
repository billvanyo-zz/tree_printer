package tech.vanyo.TreePrinter;

import java.util.ArrayList;
import java.util.List;

public class EnumDAGTrees {

    // This tests TreePrinter by enumerating trees of a given size.
    // Note that these trees are actually Directed Acyclic Graphs (DAGs), in that two identical subtrees are
    // represented by a single representation.
    // TreePrinter doesn't know the difference; it traverses the structure as a tree.
    // Also note that TreePrinter doesn't detect cycles (don't give it graphs with cycles)

    public static void main(String[] args) {

        List<TreeNode> trees = enumTrees(5);

        // this prints trees by separate calls to printTree
//        for (int i = 0; i < trees.size(); i++) {
//            TreeNode treeNode =  trees.get(i);
//            TreePrinter.printTree(treeNode, 1);
//            System.out.println("\n\n");
//        }

        // this prints trees in rows across the page
        TreePrinter.printTrees(trees, 1, 3, 100);

    }

    public static List<TreeNode> enumTrees(int n) {
        List<TreeNode>[] subProblems = new ArrayList[n+1];

        subProblems[0] = new ArrayList<>();
        subProblems[0].add(null);
        subProblems[1] = new ArrayList<>();
        subProblems[1].add(new TreeNode("O"));

        for (int totalNodes = 2; totalNodes <= n; totalNodes++) {
            subProblems[totalNodes] = new ArrayList<>();

            for (int rightCount = 0; rightCount < totalNodes; rightCount++) {
                int leftCount = totalNodes - rightCount - 1;
                List<TreeNode> leftTrees = subProblems[leftCount];
                List<TreeNode> rightTrees = subProblems[rightCount];

                for (int i = 0; i < leftTrees.size(); i++) {
                    TreeNode leftNode =  leftTrees.get(i);

                    for (int j = 0; j < rightTrees.size(); j++) {
                        TreeNode rightNode =  rightTrees.get(j);
                        TreeNode newRoot = new TreeNode("O");
                        newRoot.setLeft(leftNode);
                        newRoot.setRight(rightNode);
                        subProblems[totalNodes].add(newRoot);
                    }
                }
            }
        }
        return subProblems[n];
    }
}
