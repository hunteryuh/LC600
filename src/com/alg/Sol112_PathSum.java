package com.alg;

/*
Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.



Example 1:

Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.

Example 2:

Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.

Example 3:

Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths.



Constraints:

    The number of nodes in the tree is in the range [0, 5000].
    -1000 <= Node.val <= 1000
    -1000 <= targetSum <= 1000


 */
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
