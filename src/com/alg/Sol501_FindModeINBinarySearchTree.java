package com.alg;

import java.util.*;

/**
 * Created by HAU on 1/18/2018.
 */
/*Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).*/
public class Sol501_FindModeINBinarySearchTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x){
            val = x;
        }
    }
    // O(n) time O(n) space
    Map<Integer,Integer> map;
    int max = 0;
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        map = new HashMap<>();
        inorder(root);
        List<Integer> list = new LinkedList<>();
        for(int key: map.keySet()){
            if (map.get(key) == max){
                list.add(key);
            }
        }
        int[] res = new int[list.size()];
        for(int i = 0; i < res.length;i++){
            res[i] = list.get(i);
        }
//        return list.stream().mapToInt(Integer::intValue).toArray();
        return list.stream().mapToInt(i -> i).toArray();
//        return res;
    }

    private void inorder(TreeNode node){
        if(node.left != null) inorder(node.left);
        map.put(node.val, map.getOrDefault(node.val,0)+1);
        max = Math.max(max, map.get(node.val));
        if (node.right!= null) inorder(node.right);
    }
    // no map

    public static List<Integer> ans = new ArrayList<>();
    private static TreeNode pre = null;
    public static int maxCount = 0, curCount = 0;
    public static int[] findM2(TreeNode root){
        traverse(root);
        int[] res = new int[ans.size()];
        for(int i = 0; i < res.length; i++){
            res[i] = ans.get(i);
        }
        return res;

    }
    private static void traverse(TreeNode root){
        if (root == null) return;
        traverse(root.left);
        if (pre != null && root.val == pre.val) {
            curCount++;
        } else curCount = 1;

        if (curCount == maxCount){
            ans.add(root.val);
        } else if (curCount > maxCount){
            maxCount = curCount;
//            ans = new ArrayList<>();
            ans.clear();
            ans.add(root.val);
        }
        pre = root;
        traverse(root.right);
    }

}
