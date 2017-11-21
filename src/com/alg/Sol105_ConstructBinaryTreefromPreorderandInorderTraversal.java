package com.alg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 11/21/2017.
 */
/*Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.*/
public class Sol105_ConstructBinaryTreefromPreorderandInorderTraversal {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(inorder.length != preorder.length) return null;
        return helper6(inorder,0,inorder.length - 1, preorder, 0,preorder.length - 1);
    }

    private TreeNode helper6(int[] inorder, int instart, int inend, int[] preorder, int prestart, int preend){
        if (instart > inend) return null;
        TreeNode root = new TreeNode(preorder[prestart]);
        int pos = 0;
        for (int i = instart; i <= inend; i++){
            if (inorder[i] == root.val) pos = i; // Index of current root in inorder
        }
        root.left = helper6(inorder,instart,pos - 1, preorder,prestart + 1, prestart + pos - instart);
        // pos - instart: the length of sub-left tree
        root.right = helper6(inorder, pos + 1, inend, preorder, preend - (inend - pos) + 1 , preend);
        // inend - pos: the length of sub-right tree , or prestart + pos - instart + 1, right side(+1) of the rightmost chilc in left tree
        return root;
    }

//
    // with hashmap
    public static TreeNode buildTreewithMap(int[] preorder, int[] inorder){
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            inMap.put(inorder[i],i);
        }
        TreeNode root = maphelper(preorder, 0, preorder.length - 1, inorder,0,inorder.length - 1, inMap);
        return root;
    }

    private static TreeNode maphelper(int[] preorder, int prestart, int preend, int[] inorder, int instart, int inend, Map<Integer, Integer> inMap) {
        if (prestart > preend || instart > inend) return null;
        TreeNode root = new TreeNode(preorder[prestart]);
        int inRoot = inMap.get(root.val);
        int numsleft = inRoot - instart;
        root.left = maphelper(preorder, prestart + 1, prestart + numsleft,inorder,instart,inRoot-1,inMap);
        root.right = maphelper(preorder,prestart+numsleft + 1, preend, inorder,inRoot+1, inend, inMap);
        return root;
    }
}
