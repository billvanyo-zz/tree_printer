package tech.vanyo.TreePrinter;

import java.util.Random;

public class RandomTree {

    public static void main(String[] args) {
        TreeNode tree = randomTree(30, true);
        TreePrinter.printTree(tree, 3);
    }


    private static Random r = new Random();

    public static TreeNode randomTree(int n, boolean userWordsForNumbers) {
        return randomTree(1, n, userWordsForNumbers);
    }

    private static TreeNode randomTree(int firstLabel, int lastLabel, boolean userWordsForNumbers) {
        if (firstLabel > lastLabel) return null;
        else {
            int treeSize = lastLabel - firstLabel + 1;
            int leftCount = r.nextInt(treeSize);
            int rightCount = treeSize-leftCount-1;
            TreeNode root = new TreeNode(labelForNode(firstLabel + leftCount, userWordsForNumbers));
            root.setLeft(randomTree(firstLabel, firstLabel + leftCount -1, userWordsForNumbers));
            root.setRight(randomTree(firstLabel + leftCount + 1, lastLabel, userWordsForNumbers));
            return root;
        }
    }



    private static String labelForNode(int n, boolean useWordsForNumbers) {
        if (useWordsForNumbers) return nameForNumber(n);
        else return "" + n;
    }


    private static String nameForNumber(int n) {
        final String underTwenty[] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
                "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        final String decades[] = {"twenty", "thrity", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        if (n<20) return underTwenty[n];
        else return decades[n/10-2] + (n%10 == 0 ? "" : (" " + underTwenty[n%10]));
    }



}
