package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.


Example 1:


Input: root = [3,9,20,null,15,7]
Output: [3.00000,14.50000,11.00000]
Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
Hence return [3, 14.5, 11].
Example 2:


Input: root = [3,9,20,15,7]
Output: [3.00000,14.50000,11.00000]
 */
public class Sol637_AverageOfLevelsInBinaryTree {
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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add((double) sum/size);
        }
        return res;
    }

    public List < Double > averageOfLevels2(TreeNode root) {
        List < Double > res = new ArrayList < > ();
        Queue< TreeNode > queue = new LinkedList < > ();
        queue.add(root);
        while (!queue.isEmpty()) {
            long sum = 0, count = 0;
            Queue < TreeNode > temp = new LinkedList < > ();
            while (!queue.isEmpty()) {
                TreeNode n = queue.remove();
                sum += n.val;
                count++;
                if (n.left != null)
                    temp.add(n.left);
                if (n.right != null)
                    temp.add(n.right);
            }
            queue = temp;
            res.add(sum * 1.0 / count);
        }
        return res;
    }

    // depth first search
    public List<Double> averageOfLevels3(TreeNode root) {
        // list answer for sum all value in same level
        List<Double> answer = new ArrayList<Double>();

        // list counter for count number of node in same level
        List<Integer> counter = new ArrayList<Integer>();

        // using dfs to sum all value in same level and count number of node in same level
        dfs(0, root, answer, counter);

        // answer will be answer[level] / counter[level]
        for (int level = 0; level < answer.size(); level++) {
            answer.set(level, answer.get(level) / counter.get(level));
        }
        return answer;
    }

    public void dfs(int level, TreeNode node, List<Double> answer, List<Integer> counter) {
        if (node == null) {
            return;
        }

        if (answer.size() <= level) {
            answer.add(0.0);
            counter.add(0);
        }

        answer.set(level, answer.get(level) + node.val);
        counter.set(level, counter.get(level) + 1);

        // go left node and right node
        dfs(level + 1, node.left, answer, counter);
        dfs(level + 1, node.right, answer, counter);
    }
}
