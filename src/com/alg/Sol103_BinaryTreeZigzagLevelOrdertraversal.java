package com.alg;


import java.util.*;

/**
 * Created by HAU on 11/30/2017.
 */
/*Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]*/
public class Sol103_BinaryTreeZigzagLevelOrdertraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // double stack method
        List<List<Integer>> res = new ArrayList<>();
        if ( root == null) return res;
        Stack<TreeNode> curLevel = new Stack<>();
        Stack<TreeNode> nextLevel = new Stack<>();
        Stack<TreeNode> tmp;

        curLevel.push(root);
        boolean normalOrder = true;
        while(!curLevel.isEmpty()){
            ArrayList<Integer> curLevelResult = new ArrayList<>();
            while(!curLevel.isEmpty()){
                TreeNode node = curLevel.pop();
                curLevelResult.add(node.val);

                if(normalOrder){
                    if(node.left != null){
                        nextLevel.push(node.left);
                    }
                    if(node.right != null){
                        nextLevel.push(node.right);
                    }
                }else{
                    if (node.right != null){
                        nextLevel.push(node.right);
                    }
                    if(node.left != null){
                        nextLevel.push(node.left);
                    }
                }
            }
            res.add(curLevelResult);
            tmp = curLevel;
            curLevel = nextLevel;
            nextLevel = tmp;
            normalOrder = !normalOrder;
        }
        return res;
    }
    public static void main(String[] args) {
        //      1
        //    /   \
        //   2     3
        //  / \   /
        // 4   5 6
        //    /
        //   7
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node5.left = node7;
        System.out.println(zigzagLevelOrder(node1));
        System.out.println(zzLevelOrder(node1));
    }

    // writing 2
    public static List<List<Integer>> zzLevelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if ( root == null) return res;
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int curLevel = 1, nextLevel = 0;
        boolean order = true;
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(order) list.add(node.val);
            else list.add(0,node.val); //add the node at index 0
            curLevel--;

            if(node.left != null){
                q.add(node.left);
                nextLevel++;
            }
            if(node.right != null){
                q.add(node.right);
                nextLevel++;
            }
            if(curLevel == 0){
                curLevel = nextLevel;
                nextLevel = 0;
                res.add(new ArrayList<>(list));
                list.clear();//for next level
                order = !order;
            }
        }
        return res;
    }
}