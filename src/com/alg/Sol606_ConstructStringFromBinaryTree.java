package com.alg;

/**
 * Created by HAU on 8/26/2017.
 */
/*You need to construct a string consists of parenthesis and integers from a binary tree
        with the preorder traversing way.

        The null node needs to be represented by empty
        parenthesis pair "()". And you need to omit all the empty parenthesis pairs
        that don't affect the one-to-one mapping relationship
        between the string and the original binary tree.*/
public class Sol606_ConstructStringFromBinaryTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x){
            val = x;
        }
    }
    public static String tree2str(TreeNode t){
        if ( t == null) return "";
        StringBuilder sb = new StringBuilder(t.val + "");
        if (t.left != null){
            sb.append("(" + tree2str(t.left) + ")");
        }else if (t.right != null){
            sb.append("()");
        }
        if (t.right != null){
            sb.append("(" + tree2str(t.right) + ")");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(5);
        n0.left = new TreeNode(2);
        n0.right = new TreeNode(15);
        n0.right.right = new TreeNode(25);
        System.out.println(tree2str(n0));
    }
}
