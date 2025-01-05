package com.alg;

/**
 * Created by HAU on 1/10/2018.
 */
/*Given a binary tree, find the subtree with maximum average. Return the root of the subtree.*/
/*Example
Given a binary tree:

     1
   /   \
 -5     11
 / \   /  \
1   2 4    -2
return the node 11.*/
public class Sol0_amz_MaximumSubtreeofAverage {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }
    private static class ResultType {
        public int sum, size;
        public ResultType(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }
    private static TreeNode subtreeRoot = null;
    private static ResultType subtreeRes = null;
    // private static average = Integer.MIN_VALUE;

    public static TreeNode findMaxSubAve(TreeNode root){
        helper(root);
        return subtreeRoot;
    }

    private static ResultType helper(TreeNode root) {
        if(root == null){
            return new ResultType(0,0);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        ResultType result = new ResultType(
                left.sum + right.sum + root.val,
                left.size + right.size + 1
        );
        if (subtreeRoot == null ||
                subtreeRes.sum * result.size < subtreeRes.size * result.sum) {
            subtreeRes = result;
            subtreeRoot = root;
        }

        // write 2
        /*
        int sum = left.sum + right.sum + root.val;
        int size = left.size + right.size + 1;
        double ave = (double) sum / (double) size;
        if ( ave > average){
            average = ave;
            subtreeNode = root;
        }
        */


        return result;
    }

    // writing 2, using 2 global variables
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(-5);
        TreeNode n3 = new TreeNode(11);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(2);
        TreeNode n6 = new TreeNode(4);
        TreeNode n7 = new TreeNode(-2);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        TreeNode res = findMaxSubAve(n1);
        System.out.println(res.val);
    }
}
