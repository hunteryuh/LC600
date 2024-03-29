package com.alg;

/**
 * Created by HAU on 8/26/2017.
 */
/*Given a binary tree and a sum, determine if the tree has a root-to-leaf path such
        that adding up all the values along the path equals the given sum.*/
public class Sol112_PathSum {
    public static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }

   public static boolean hasPathSum(TreeNode root, int sum){
       if (root == null) return false;
       if (root.left == null && root.right == null) {
           return root.val == sum;
       }
       boolean left = hasPathSum(root.left, sum - root.val);
       boolean right = hasPathSum(root.right, sum - root.val);
       return left || right;
   }
    public static void main(String args[]) {
        int sum = 21;

        /* Constructed binary tree is
              10
             /  \
           8     2
          / \   /
         3   5 2
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
//        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(2);
        System.out.println(hasPathSum(root, 10));

        Sol112_PathSum ss = new Sol112_PathSum();
        System.out.println(ss.hasPathSum2(root, 10));
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        int newTarget = targetSum - root.val;
        if (root.left == null && root.right == null) {
            return newTarget == 0;
        }
        if (root.left != null) {
            boolean left = hasPathSum2(root.left, newTarget);
            if (left) return true;
        }
        if (root.right != null) {
            boolean right = hasPathSum2(root.right, newTarget);
            if (right) return true;
        }
        return false;
    }
}
