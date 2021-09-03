package com.alg.lint;
/*
Description
Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
Example
Example 1

Input：
{1,-5,11,1,2,4,-2}
Output：11
Explanation:
The tree is look like this:
     1
   /   \
 -5     11
 / \   /  \
1   2 4    -2
The average of subtree of 11 is 4.3333, is the maximum.


Input：
{1,-5,11}
Output：11
Explanation:
     1
   /   \
 -5     11
The average of subtree of 1,-5,11 is 2.333,-5,11. So the subtree of 11 is the maximun.
 */
public class P597_SubtreeWithMaximumAverage {
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }


    private TreeNode subTree;
    private ResultType subTreeResult;
    // with a custom class as return type to hold multiple values
    class ResultType {
        public int sum, size;
        public ResultType(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }

    /**
     * @param root: the root of binary tree
     * @return: the root of the subtree with maximum average
     */
    public TreeNode findSubtreePureDC(TreeNode root) {
        divideHelper(root);
        return subTree;
    }
    public ResultType divideHelper(TreeNode node) {
        if (node == null) {
            return new ResultType(0, 0);
        }

        ResultType leftResult = divideHelper(node.left);
        ResultType rightResult = divideHelper(node.right);

        ResultType result = new ResultType(
        leftResult.sum + rightResult.sum + node.val,
        leftResult.size + rightResult.size + 1
        );

        if (subTree == null ||
            //result.sum / result.size > subTreeResult.sum / subTreeResult.size
            // avoid double result by division, so use multiplication
            result.sum * subTreeResult.size > subTreeResult.sum * result.size
        ) {
            subTree = node;
            subTreeResult = result;
        }

        return result;
    }

}
