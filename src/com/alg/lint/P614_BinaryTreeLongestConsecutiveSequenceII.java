package com.alg.lint;


/*



Description
Given a binary tree, find the length(number of nodes) of the longest consecutive sequence(Monotonic and adjacent node values differ by 1) path.
The path could be start and end at any node in the tree


Example
Example 1:

Input:
{1,2,0,3}
Output:
4
Explanation:
    1
   / \
  2   0
 /
3
0-1-2-3


https://www.jiuzhang.com/problem/binary-tree-longest-consecutive-sequence-ii/

 */
public class P614_BinaryTreeLongestConsecutiveSequenceII {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x){
            val = x;
            this.left = this.right = null;
        }
    }

    private static class ResultType {
        public int totalLength;
        public int increaseLength;
        public int decreaseLength;
        public ResultType(int totalLength, int increaseLength, int decreaseLength) {
            this.totalLength = totalLength;
            this.increaseLength = increaseLength;
            this.decreaseLength = decreaseLength;
        }
    }

    public static int longestConsecutiveII(TreeNode root) {
        // write your code here
        ResultType result = helper(root);
        return result.totalLength;
    }

    private static ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0);
        }

        ResultType leftResult = helper(root.left);
        ResultType rightResult = helper(root.right);

        int curIncr = 0;
        int curDecr = 0;
        if (root.left != null) {
            if (root.left.val == root.val + 1) {
                curIncr = Math.max(curIncr, leftResult.increaseLength + 1);
            }

            if (root.left.val == root.val - 1) {
                curDecr = Math.max(curDecr, leftResult.decreaseLength + 1);
            }
        }

        if (root.right != null) {
            if (root.right.val == root.val + 1) {
                curIncr = Math.max(curIncr, rightResult.increaseLength + 1);
            }

            if (root.right.val == root.val - 1) {
                curDecr = Math.max(curDecr, rightResult.decreaseLength + 1);
            }
        }

        int length = curDecr + curIncr + 1;
        length = Math.max(length, Math.max(leftResult.totalLength, rightResult.totalLength));
        return new ResultType(length, curIncr, curDecr);
    }

    public static void main(String[] args) {
        //      8
        //    /   \
        //   2     9
        //  / \   /
        // 1   3 7
        //    /
        //   4
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(9);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node5.left = node7;
        System.out.println(longestConsecutiveII(node1)); // 4: 1-2-3-4
    }

}
