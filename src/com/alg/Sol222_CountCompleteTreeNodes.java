package com.alg;

/**
 * Created by HAU on 1/19/2018.
 */
/*Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

*/
public class Sol222_CountCompleteTreeNodes {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x){
            val = x;
        }
    }
    // Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)).
    // So overall O(log(n)^2).
    public static int countNodes(TreeNode root){
        int leftDepth = getLeftHeight(root);
        int rightDepth = getRightHeight(root);
        if ( leftDepth == rightDepth){
            return (1 << leftDepth) - 1;  // 2 ^ n - 1
        }else{
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
    private static int getLeftHeight(TreeNode root){
        int depth = 0;
        while ( root!= null){
            root = root.left;
            depth++;
        }
        return depth;
    }
    private static int getRightHeight(TreeNode root){
        int dep = 0;
        while ( root != null){
            root = root.right;
            dep++;
        }
        return dep;
    }
}
