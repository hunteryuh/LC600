package com.alg;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HAU on 2/11/2018.
 */
/*Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.

Example :

Input: root = [4,2,6,1,3,null,null]
Output: 1
Explanation:
Note that root is a TreeNode object, not an array.

The given tree [4,2,6,1,3,null,null] is represented by the following diagram:

          4
        /   \
      2      6
     / \
    1   3

while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.*/
public class Sol738_MinimumDistanceBetweenBSTNodes {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    static Integer res = Integer.MAX_VALUE;
    static Integer pre = null;
    // in-order traversal
    public static int minDiffInBST(TreeNode root) {
        if(root.left != null) minDiffInBST(root.left);
        if( pre != null){
            res = Math.min( res, root.val - pre);
        }
        pre = root.val;
        if(root.right != null) minDiffInBST(root.right);
        return res;
    }
    // in order 2
    static int min = Integer.MAX_VALUE;
    public static int minDiffBST(TreeNode root){
        List<TreeNode> list = new LinkedList<>();
        inorder(root,list);
        for(int i = 1; i < list.size(); i++){
            min = Math.min(min, list.get(i).val - list.get(i-1).val);
        }
        return min;
    }

    private static void inorder(TreeNode root, List<TreeNode> list) {
        if(root == null) return;
        inorder(root.left,list);
        list.add(root);
        inorder(root.right,list);
    }
}
