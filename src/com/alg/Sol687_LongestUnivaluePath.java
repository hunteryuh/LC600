package com.alg;

/**
 * Created by HAU on 1/16/2018.
 */
/*Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2
Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output:

2
Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.*/
public class Sol687_LongestUnivaluePath {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    static int res;
    public static int longestUnivaluePath(TreeNode root) {
        res = 0;
        pathLength(root);
        return res;
    }
    private static int pathLength(TreeNode node){
        if ( node == null) return 0;
        int left = pathLength(node.left);
        int right = pathLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if(node.left != null && node.val == node.left.val){
            arrowLeft += left + 1;
        }
        if(node.right!= null && node.val == node.right.val){
            arrowRight += right + 1;
        }
        res = Math.max(res, arrowLeft + arrowRight);
        return Math.max(arrowLeft,arrowRight);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(1);
        TreeNode n6 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;
        //System.out.println(longestUnivaluePath(n1));
        //System.out.println(longestUniPath(n1));

        TreeNode n11 = new TreeNode(1);
        TreeNode n12 = new TreeNode(4);
        TreeNode n13 = new TreeNode(5);
        TreeNode n14 = new TreeNode(4);
        TreeNode n15 = new TreeNode(4);
        TreeNode n16 = new TreeNode(5);
        TreeNode n17 = new TreeNode(4);
        n11.left = n12;
        n11.right = n13;
        n12.left = n14;
        n12.right = n15;
        n13.right = n16;
        n15.left = n17;
        //System.out.println(longestUnivaluePath(n11)); //3
        //System.out.println(longestUniPath(n11)); //3


        TreeNode t1 = new TreeNode(1);
        System.out.println(longestUnivaluePath(t1));
        System.out.println(longestUniPath(t1));
    }
    static  int len = 0;
    public static int longestUniPath(TreeNode root){
        if(root == null) return 0;
        //len = 0 ;
        getLen(root,root.val);
        return len;
    }

    private static int getLen(TreeNode node, int val) {
        if(node == null) return 0;
        int left = getLen(node.left, node.val);
        int right = getLen(node.right, node.val);
        len = Math.max(len, left + right);
        if ( val == node.val){
            return Math.max(left, right) + 1;
        }
        return 0;
    }


}
