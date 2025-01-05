package com.alg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 11/21/2017.
 */
/*Given preorder and inorder traversal of a tree, construct the binary tree.
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
Note:
You may assume that duplicates do not exist in the tree.

Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
*/
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

    // O(n^2)
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
    // with hashmap, time O(n), get the node value to inorder index beforehand
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

    public TreeNode buildTreeWithPreAndInOrderArray(int[] preorder, int[] inorder) {
        int length = preorder.length;
        if (length == 0 || inorder.length != length) return null;
        return helper(preorder, 0, length, inorder, 0, length);
    }

    private TreeNode helper(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if (inLeft >= inRight) {
            return null;
        }
        if (inLeft + 1 == inRight) {
            return new TreeNode(inorder[inLeft]);
        }
        int rootVal = preorder[preLeft];
        int rootIndexInInOrder = 0;
        for (int i = 0; i < inRight; i++) {
            if (inorder[i] == rootVal) {
                rootIndexInInOrder = i;
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        int newLength = rootIndexInInOrder - inLeft;
        root.left = helper(preorder, preLeft + 1, preLeft + 1 + newLength, inorder, inLeft, rootIndexInInOrder);
//        root.right = helper(preorder, preRight - (inRight - rootIndexInInOrder -1), preRight, inorder, rootIndexInInOrder + 1, inRight);
        root.right = helper(preorder, preLeft + 1 + newLength , preRight, inorder, rootIndexInInOrder + 1, inRight);
        return root;
    }

    // preOrder性质优化解法
    int[] preorder; int[] inorder;
    int preOrderIndex;
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        this.preOrderIndex = 0;
        int n = preorder.length;
        for (int i = 0; i < n; i++) map.put(inorder[i], i);
        return helperBuilder(0, n- 1);
    }
    private TreeNode helperBuilder(int instart, int inend) { // only pass the bound for th inorder traversal
        if (instart > inend) return null;
        TreeNode root = new TreeNode(preorder[preOrderIndex++]);  // only increasing
        int index = map.get(root.val);
        root.left = helperBuilder(instart, index  - 1);
        root.right = helperBuilder(index + 1, inend);
        return root;
    }

}
