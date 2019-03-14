package tech.vanyo.TreePrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreePrinter {

    /*
        Prints ascii representation of binary tree.
        Parameter hspace is minimum number of spaces between adjacent node labels.
     */
    public static void printTree(TreeNode root, int hspace) {
        printTree(root, hspace, false);
    }

    /*
        Prints ascii representation of binary tree.
        Parameter hspace is minimum number of spaces between adjacent node labels.
        Parameter squareBranches, when set to true, results in branches being printed with ASCII box
        drawing characters.
     */
    public static void printTree(TreeNode root, int hspace, boolean squareBranches) {
        List<TreeLine> treeLines = buildTreeLines(hspace, root, squareBranches);
        printTreeLines(treeLines);
    }

    /*
        Prints ascii representations of multiple trees across page.
        Parameter hspace is minimum number of spaces between adjacent node labels in a tree.
        Parameter tspace is horizontal distance between trees, as well as number of blank lines
        between rows of trees.
        Parameter lineWidth is maximum width of output
     */
    public static void printTrees(List<TreeNode> trees, int hspace, int tspace, int lineWidth) {
        printTrees(trees, hspace, tspace, lineWidth, false);
    }


    /*
        Prints ascii representations of multiple trees across page.
        Parameter hspace is minimum number of spaces between adjacent node labels in a tree.
        Parameter tspace is horizontal distance between trees, as well as number of blank lines
        between rows of trees.
        Parameter lineWidth is maximum width of output
        Parameter squareBranches, when set to true, results in branches being printed with ASCII box
        drawing characters.
     */
    public static void printTrees(List<TreeNode> trees, int hspace, int tspace, int lineWidth, boolean squareBranches) {
        List<List<TreeLine>> allTreeLines = new ArrayList<>();
        int[] treeWidths = new int[trees.size()];
        int[] minLeftOffsets = new int[trees.size()];
        int[] maxRightOffsets = new int[trees.size()];
        for (int i = 0; i < trees.size(); i++) {
            TreeNode treeNode = (TreeNode) trees.get(i);
            List<TreeLine> treeLines = buildTreeLines(hspace, treeNode, squareBranches);
            allTreeLines.add(treeLines);
            minLeftOffsets[i] = minLeftOffset(treeLines);
            maxRightOffsets[i] = maxRightOffset(treeLines);
            treeWidths[i] = maxRightOffsets[i] - minLeftOffsets[i] + 1;
        }

        int nextTreeIndex = 0;
        while (nextTreeIndex < trees.size()) {
            // print a row of trees starting at nextTreeIndex

            // first figure range of trees we can print for next row
            int sumOfWidths = treeWidths[nextTreeIndex];
            int endTreeIndex = nextTreeIndex+1;
            while (endTreeIndex<trees.size() && sumOfWidths + tspace + treeWidths[endTreeIndex] < lineWidth) {
                if (endTreeIndex<trees.size()) {
                    sumOfWidths += (tspace + treeWidths[endTreeIndex]);
                    endTreeIndex++;
                }
            }
            endTreeIndex--;

            // find max number of lines for tallest tree
            int maxLines = 0;
            for (int i = nextTreeIndex; i <= endTreeIndex; i++) {
                int lineCount = allTreeLines.get(i).size();
                if (lineCount > maxLines) maxLines = lineCount;
            }

            // print trees line by line
            for (int i = 0; i < maxLines; i++) {
                for (int j = nextTreeIndex; j <= endTreeIndex; j++) {
                    List<TreeLine> treeLines = allTreeLines.get(j);
                    if (i >= treeLines.size()) {
                        System.out.print(spaces(treeWidths[j]));
                    }
                    else {
                        int leftSpaces = -(minLeftOffsets[j]-treeLines.get(i).leftOffset);
                        int rightSpaces = maxRightOffsets[j]-treeLines.get(i).rightOffset;
                        System.out.print(spaces(leftSpaces) + treeLines.get(i).line + spaces(rightSpaces));
                    }
                    if (j < endTreeIndex) System.out.print(spaces(tspace));
                }
                System.out.println();
            }
            System.out.println();

            for (int i = 0; i < tspace; i++) {
                System.out.println();
            }

            nextTreeIndex = endTreeIndex + 1;

        }


    }

    private static int minLeftOffset(List<TreeLine> treeLines) {
        int minLeftOffset = treeLines.get(0).leftOffset;
        for (int i = 1; i < treeLines.size(); i++) {
            TreeLine treeLine =  treeLines.get(i);
            if (treeLine.leftOffset < minLeftOffset) minLeftOffset = treeLine.leftOffset;
        }
        return minLeftOffset;
    }

    private static int maxRightOffset(List<TreeLine> treeLines) {
        int maxRightOffset = treeLines.get(0).rightOffset;
        for (int i = 1; i < treeLines.size(); i++) {
            TreeLine treeLine =  treeLines.get(i);
            if (treeLine.rightOffset > maxRightOffset) maxRightOffset = treeLine.rightOffset;
        }
        return maxRightOffset;
    }

    private static void printTreeLines(List<TreeLine> treeLines) {
        if (treeLines.size()==0) return;
        int minLeftOffset = minLeftOffset(treeLines);
        int maxRightOffset = maxRightOffset(treeLines);
        for (int i = 0; i < treeLines.size(); i++) {
            TreeLine treeLine =  treeLines.get(i);
            int leftSpaces = -(minLeftOffset-treeLine.leftOffset);
            int rightSpaces = maxRightOffset-treeLine.rightOffset;
            System.out.println(spaces(leftSpaces) + treeLine.line + spaces(rightSpaces)); // + " |  " + treeLine.leftOffset + "   " + treeLine.rightOffset);
        }
    }


    static List<TreeLine> buildTreeLines(int hspace, TreeNode root, boolean squareBranches) {
        if (root == null) return Collections.emptyList();
        else {
            List<TreeLine> leftTreeLines = buildTreeLines(hspace, root.getLeft(), squareBranches);
            List<TreeLine> rightTreeLines = buildTreeLines(hspace, root.getRight(), squareBranches);

            int leftCount = leftTreeLines.size();
            int rightCount = rightTreeLines.size();
            int minCount = Math.min(leftCount, rightCount);
            int maxCount = Math.max(leftCount, rightCount);

            int maxRootSpacing = 0;
            for (int i = 0; i < minCount; i++) {
                int spacing = leftTreeLines.get(i).rightOffset - rightTreeLines.get(i).leftOffset;
                if (spacing > maxRootSpacing) maxRootSpacing = spacing;
            }
            int rootSpacing = maxRootSpacing + hspace;
            if (rootSpacing %2 == 0) rootSpacing++;
            // rootSpacing is now the number of spaces between the roots of the two subtrees

            List<TreeLine> allTreeLines = new ArrayList<>();

            // add the root and the two branches leading to the subtrees

            allTreeLines.add(new TreeLine(root.getLabel(), -(root.getLabel().length()-1)/2, root.getLabel().length()/2));

            // also calculate offset adjustments for left and right subtrees
            int leftTreeAdjust = 0;
            int rightTreeAdjust = 0;

            if (leftTreeLines.isEmpty()) {
                if (!rightTreeLines.isEmpty()) {
                    // there's a right subtree only
                    if (squareBranches) {
                        allTreeLines.add(new TreeLine("\u2514\u2500\u2510", 0, 2));
                        rightTreeAdjust = 2;

                    } else {
                        allTreeLines.add(new TreeLine("\\", 1, 1));
                        rightTreeAdjust = 2;
                    }
                }
            } else if (rightTreeLines.isEmpty()) {
                // there's a left subtree only
                if (squareBranches) {
                    allTreeLines.add(new TreeLine("\u250C\u2500\u2518", -2, 0));
                    leftTreeAdjust = -2;
                } else {
                    allTreeLines.add(new TreeLine("/", -1, -1));
                    leftTreeAdjust = -2;
                }
            } else {
                // there's a left and right subtree
                if (squareBranches) {
                    int adjust = (rootSpacing == 1 ? 1 : ((rootSpacing/2)+1));
                    String horizontal = String.join("", Collections.nCopies(rootSpacing/2, "\u2500"));
                    String branch = "\u250C" + horizontal + "\u2534" + horizontal + "\u2510";
                    allTreeLines.add(new TreeLine(branch, -adjust, adjust));
                    rightTreeAdjust = adjust;
                    leftTreeAdjust = -adjust;
                } else {
                    if (rootSpacing==1) {
                        allTreeLines.add(new TreeLine("/ \\", -1, 1));
                        rightTreeAdjust = 2;
                        leftTreeAdjust = -2;
                    } else {
                        for (int i = 1; i < rootSpacing ; i+=2) {
                            String branches = "/" + spaces(i) + "\\";
                            allTreeLines.add(new TreeLine(branches,  -((i+1)/2), (i+1)/2));
                        }
                        rightTreeAdjust = (rootSpacing/2)+1;
                        leftTreeAdjust = -((rootSpacing/2)+1);
                    }
                }
            }

            // now add joined lines of subtrees, with appropriate number of separating spaces, and adjusting offsets

            for (int i = 0; i < maxCount; i++) {
                TreeLine leftLine, rightLine;
                if (i >= leftTreeLines.size()) {
                    // nothing remaining on left subtree
                    rightLine = rightTreeLines.get(i);
                    rightLine.leftOffset+=rightTreeAdjust;
                    rightLine.rightOffset+=rightTreeAdjust;
                    allTreeLines.add(rightLine);
                } else if (i >= rightTreeLines.size()) {
                    // nothing remaining on right subtree
                    leftLine = leftTreeLines.get(i);
                    leftLine.leftOffset+=leftTreeAdjust;
                    leftLine.rightOffset+=leftTreeAdjust;
                    allTreeLines.add(leftLine);
                } else {
                    leftLine = leftTreeLines.get(i);
                    rightLine = rightTreeLines.get(i);
                    int adjustedRootSpacing = (rootSpacing==1 ? (squareBranches ? 1 : 3) : rootSpacing);
                    TreeLine combined  = new TreeLine(leftLine.line + spaces(adjustedRootSpacing - leftLine.rightOffset + rightLine.leftOffset) + rightLine.line,
                            leftLine.leftOffset+leftTreeAdjust, rightLine.rightOffset+rightTreeAdjust);
                    allTreeLines.add(combined);
                }
            }
            return allTreeLines;
        }
    }


    private static String spaces(int n) {
        return String.join("", Collections.nCopies(n, " "));
    }

    private static class TreeLine {
        String line;
        int leftOffset;
        int rightOffset;

        TreeLine(String line, int leftOffset, int rightOffset) {
            this.line = line;
            this.leftOffset = leftOffset;
            this.rightOffset = rightOffset;
        }
    }
}
