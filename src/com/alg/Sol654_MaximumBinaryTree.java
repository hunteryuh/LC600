package com.alg;

/*
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    /
     2  0
       \
        1
        */
public class Sol654_MaximumBinaryTree {
    /**
     * Definition for a binary tree node.
     * */
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

     public TreeNode constructMaximumBinaryTree(int[] nums) {
         return constructTree(nums, 0, nums.length);
     }

    private TreeNode constructTree(int[] nums, int left, int right) {
         if (left >= right) return null;
         int maxPosition = findMax(nums, left, right);
         TreeNode root = new TreeNode(nums[maxPosition]);
         root.left = constructTree(nums, left, maxPosition);
         root.right = constructTree(nums, maxPosition + 1, right);

         return root;
    }

    private int findMax(int[] nums, int left, int right) {
         int maxIndex = left;
         for (int i = left; i < right; i++) {
             if (nums[i] > nums[maxIndex]) {
                 maxIndex = i;
             }
         }
         return maxIndex;
    }

    public static void main(String[] args) {
       int[] nums = {3,2,1,6,0,5};
    }
}
