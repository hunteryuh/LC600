package com.alg;

import java.util.*;
import java.util.stream.Collectors;

/*
Given the root of a binary tree, collect a tree's nodes as if you were doing this:

    Collect all the leaf nodes.
    Remove all the leaf nodes.
    Repeat until the tree is empty.



Example 1:

Input: root = [1,2,3,4,5]
Output: [[4,5,3],[2],[1]]
Explanation:
[[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.

Example 2:

Input: root = [1]
Output: [[1]]



Constraints:

    The number of nodes in the tree is in the range [1, 100].
    -100 <= Node.val <= 100


 */
public class Sol366_FindLeavesOfBinaryTree {
    // https://leetcode.com/problems/find-leaves-of-binary-tree/solutions/153057/logical-thinking-with-verbose-but-clear-code/
    Map<Integer, List<Integer>> htmap;

    public List<List<Integer>> findLeaves2(TreeNode root) {
        htmap = new HashMap<>();
        getHeight2(root);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 1; i <=  htmap.size(); i++){
            result.add(htmap.get(i) );
        }
        return result;
    }
    private int getHeight2(TreeNode curr){
        if (curr == null)
            return 0;
        int lft = getHeight2(curr.left);
        int rgt = getHeight2(curr.right);
        int height = Math.max(lft, rgt) + 1;
        if (!htmap.containsKey(height)) {
            htmap.put(height, new ArrayList<>());
        }
        htmap.get(height).add(curr.val);
        return height;
    }

    // modify the data structure to get result directly after dfs to calculate height
    private List<List<Integer>> solution;

    private int getHeight(TreeNode root) {
        // return -1 for null nodes
        if (root == null) {
            return -1;  // so that it + 1 == 0 at lowest level/leaf nodes for each subtree
        }

        // first calculate the height of the left and right children
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        int currHeight = Math.max(leftHeight, rightHeight) + 1;

        if (this.solution.size() == currHeight) {
            this.solution.add(new ArrayList<>());
        }

        this.solution.get(currHeight).add(root.val);

        return currHeight;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        this.solution = new ArrayList<>();

        getHeight(root);

        return this.solution;
    }

    // https://www.geeksforgeeks.org/print-leaf-nodes-left-right-binary-tree/
    // get all leaf node count
    // leaf node: nodes that have no child nodes
    private int getLeafNodeCount(TreeNode root) {
        // initializing queue for level order traversal
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        // initializing count variable
        int count = 0;
        while(!q.isEmpty()) {
            TreeNode temp = q.poll();
            if (temp.left == null && temp.right == null) count++;
            if (temp.left != null) q.offer(temp.left);
            if (temp.right != null) q.offer(temp.right);
        }
        return count;
    }

    // self pick "top K" after leve order traversal to get all leaves node
    // topK leave nodes, equal
    // what if there are less than 3 leaves in the tree;
    // what if only output unique 3 (equals are excluded)

    public List<Integer> topKSmallestLeaves(TreeNode root, int k) {
        Queue<TreeNode> queue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> b - a
        ); // largest on top (reversed the default order where the smallest is on top)
        queue.offer(root);
        while (queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null) {
                pq.offer(node.val);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
//        List<Integer> list = new ArrayList<>();
//        list.addAll(pq);
        return new ArrayList<>(pq);
    }
}
