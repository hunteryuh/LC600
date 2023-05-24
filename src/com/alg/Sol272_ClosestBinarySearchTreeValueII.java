package com.alg;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HAU on 12/1/2017.
 */
/* Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

    Given target value is a floating point.
    You may assume k is always valid, that is: k ≤ total nodes.
    You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)? */
public class Sol272_ClosestBinarySearchTreeValueII {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
    public static List<Integer> closestValue(TreeNode root, double target,int k) {
        LinkedList<Integer> res = new LinkedList<>();
        inOrderTra(root,target,k,res);
        return res;
    }

    private static void inOrderTra(TreeNode root, double target, int k, LinkedList<Integer> res) {
        if (root == null){
            return;
        }
        inOrderTra(root.left,target,k,res);
        if(res.size() < k){
            res.add(root.val);
        }else if(res.size() == k){
            if(Math.abs(res.getFirst() - target) > Math.abs(root.val - target)){
                res.removeFirst(); // earlist entry, left most leaf
                res.addLast(root.val); //add current node
            }else{
                return;  // k smallest found because the rest is bigger
            }
        }
        inOrderTra(root.right,target,k,res);
    }

    public static void main(String[] args) {
        //      8
        //    /   \
        //   2     10
        //  / \   /
        // 1   5 9
        //    /
        //   3
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(10);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node5.left = node7;
        System.out.println(closestValue(node1, 3.2, 3));
    }
}
