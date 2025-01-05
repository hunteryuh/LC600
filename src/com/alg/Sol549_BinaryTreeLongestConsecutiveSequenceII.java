package com.alg;
/*
Given the root of a binary tree, return the length of the longest consecutive path in the tree.

A consecutive path is a path where the values of the consecutive nodes in the path differ by one. This path can be either increasing or decreasing.

    For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.

On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.



Example 1:

Input: root = [1,2,3]
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].

Example 2:

Input: root = [2,1,3]
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].



Constraints:

    The number of nodes in the tree is in the range [1, 3 * 104].
    -3 * 104 <= Node.val <= 3 * 104


 */
public class Sol549_BinaryTreeLongestConsecutiveSequenceII {
    int maxval = 0;
    public int longestConsecutive(TreeNode root) {
        longestPath(root);
        return maxval;
    }

    //int[]{inr, dec}  inr:表示包括当前节点 + child里面最长的上升子序列的长度（包括当前node) dcr:表示child里面最上的下降子序列的长度
    // 从root 到 leaf 方向 （从上到下）
    private int[] longestPath(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int inc = 1;
        int dec = 1;
        if (root.left != null) {
            int[] left = longestPath(root.left);
            if (root.val == root.left.val + 1) {
                dec = left[1] + 1;
            } else if (root.val == root.left.val - 1) {
                inc = left[0] + 1;
            }
        }
        if (root.right != null) {
            int[] right = longestPath(root.right);
            if (root.val == root.right.val + 1) {
                dec = Math.max(dec, right[1] + 1);
            } else if (root.val == root.right.val - 1) {
                inc = Math.max(inc, right[0] + 1);
            }
        }
        maxval = Math.max(maxval, dec + inc - 1); // there is common node
        return new int[]{inc, dec};
    }

    // with resultType, pure divide + conquer
    public int longestConsecutive2(TreeNode root) {
        ResultType result = helper(root);
        return result.longestLength;
    }

    ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0);
        }

        ResultType leftInfo = helper(root.left);
        ResultType rightInfo = helper(root.right);

        // 获得左右节点为端点的的最长递增和递减长度
        int leftIncr = leftInfo.incr;
        int leftDecr = leftInfo.decr;
        int rightIncr = rightInfo.incr;
        int rightDecr = rightInfo.decr;
        int curIncr = 0;
        int curDecr = 0;

        // 更新当前节点为端点时的最长递增和递减的长度
        if (root.left != null) {
            if (root.val == root.left.val - 1) {
                curIncr = Math.max(curIncr, leftIncr + 1);
            }
            if (root.val == root.left.val + 1) {
                curDecr = Math.max(curDecr, leftDecr + 1);
            }
        }

        if (root.right != null) {
            if (root.val == root.right.val - 1) {
                curIncr = Math.max(curIncr, rightIncr + 1);
            }
            if (root.val == root.right.val + 1) {
                curDecr = Math.max(curDecr, rightDecr + 1);
            }
        }

        // 更新答案
        int longestLength = Math.max(Math.max(leftInfo.longestLength, rightInfo.longestLength),
                curDecr + curIncr + 1);

        // 返回当前节点为端点时的最长递增和递减长度
        return new ResultType(curIncr, curDecr, longestLength);
    }

    class ResultType {
        int incr, decr, longestLength;
        ResultType(int incr, int decr, int longestLength) {
            this.incr = incr;
            this.decr = decr;
            this.longestLength = longestLength;
        }
    }

}

