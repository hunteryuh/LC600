package com.alg;

/**
 * Created by HAU on 1/16/2018.
 */
/*Given a binary tree, you need to compute the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.

*/
public class Sol543_DiameterOfABinaryTree {

    static int diameter;
    public static int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        depth(root);
        return diameter;
    }

    private static int depth(TreeNode node) {
        if (node == null) return 0;
        int L = depth(node.left);
        int R = depth(node.right);
        diameter = Math.max(diameter, L + R);
        // ans = Math.max(ans, L + R + 1 );
        // If we knew the maximum length arrows L, R for each child, then the best path touches L + R + 1 nodes.
        return Math.max(L,R) + 1;
    }
    public static void main(String[] args) {
        /*Given a binary tree:

     1
   /   \
 -5     11
 / \   /  \
1   2 4
       \
        -2
*/

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
        n6.right = n7;

        System.out.println(diameterOfBinaryTree(n1)); //5
    }
}
