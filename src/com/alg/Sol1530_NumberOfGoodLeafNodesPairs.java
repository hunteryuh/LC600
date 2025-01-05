package com.alg;

import java.util.ArrayList;
import java.util.List;

/*
You are given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.

Return the number of good leaf node pairs in the tree.



Example 1:

Input: root = [1,2,3,null,4], distance = 3
Output: 1
Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.

Example 2:

Input: root = [1,2,3,4,5,6,7], distance = 3
Output: 2
Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.

Example 3:

Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
Output: 1
Explanation: The only good pair is [2,5].



Constraints:

    The number of nodes in the tree is in the range [1, 210].
    1 <= Node.val <= 100
    1 <= distance <= 10


 */
public class Sol1530_NumberOfGoodLeafNodesPairs {
    // https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/solutions/1372128/java-dfs-solution-with-explanation/
    /*
    Algorithm:

    To calculate the number of good leaf nodes. You need to traverse the left subtree and right subtree of a particular node.
    While traversing the left or right subtree of a particular node you can return an Arraylist of the distance of the leaf nodes from that particular parent node.
    When you have the ArrayList of distance from both left and right subtree you can brute force to check pairs that fulfil the given condition and increment the global count.
    After you are done with the given brute force you also need to increment every distance by one for the next parent node and then return the new list.

     */
    int count = 0;
    public int countPairs(TreeNode root, int distance) {
        DfsDistance(root, distance);
        return count;
    }
    public List<Integer> DfsDistance(TreeNode root,int distance) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        if (root.left == null && root.right == null){
            List<Integer> sublist = new ArrayList<Integer>();
            sublist.add(1);
            return sublist;
        }
        List<Integer> l1 = DfsDistance(root.left, distance);
        List<Integer> l2 = DfsDistance(root.right, distance);
        //System.out.println("left "+l1);
        //System.out.println("right "+l2);
        for (int d1:l1) {
            for (int d2:l2) {
                if (d1 + d2 <= distance)
                    count++;
            }
        }
        List<Integer> list=new ArrayList<Integer>();
        for (int val:l1)
            list.add(val+1);
        for (int val:l2)
            list.add(val+1);
        return list; // an Arraylist of the distance of the leaf nodes from that particular parent node.

    }


    public int countPairs2(TreeNode root, int distance) {
        return dfs(root, new ArrayList<>(), distance);
    }

    private int dfs(TreeNode root, List<Integer> list, int distance) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            list.add(0);
            return 0;
        }
        int count = 0;
        List<Integer> leftList = new ArrayList<>(); // the distance between the current node and all the leaf nodes below it.
        List<Integer> rightList = new ArrayList<>();
        int left = dfs(root.left, leftList, distance);
        int right = dfs(root.right, rightList, distance);

        for (int i = 0; i < leftList.size(); i++) {
            for (int j = 0; j < rightList.size(); j++) {
                if (leftList.get(i) + rightList.get(j) + 2 <= distance) {
                    count++;
                }
            }
        }
        for (int l : leftList) {
            list.add(l + 1);
        }
        for (int r: rightList) {
            list.add(r + 1);
        }
        return count + left + right;

    }
}
