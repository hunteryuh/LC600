package com.alg;

import java.util.*;

/*
Given the root of a binary tree, calculate the vertical order traversal of the binary tree.

For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

Return the vertical order traversal of the binary tree.



Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Column -1: Only node 9 is in this column.
Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
Column 1: Only node 20 is in this column.
Column 2: Only node 7 is in this column.

Example 2:

Input: root = [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
Column -2: Only node 4 is in this column.
Column -1: Only node 2 is in this column.
Column 0: Nodes 1, 5, and 6 are in this column.
          1 is at the top, so it comes first.
          5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
Column 1: Only node 3 is in this column.
Column 2: Only node 7 is in this column.

Example 3:

Input: root = [1,2,3,4,6,5,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
This case is the exact same as example 2, but with nodes 5 and 6 swapped.
Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.



Constraints:

    The number of nodes in the tree is in the range [1, 1000].
    0 <= Node.val <= 1000


 */
public class Sol987_VerticalOrderTraversalOfABinaryTree {
    // https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/solutions/2746705/explained-and-detailed-solution-java-accepted/
    static class Tuple {
        TreeNode node;
        int row, col;
        public Tuple (TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Tuple> queue = new LinkedList<>(); // level order traversal
        queue.offer(new Tuple(root, 0, 0));

        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();
            TreeNode cur = tuple.node;
            int x = tuple.row; // value of x coordinate is the index of column
            int y = tuple.col; // value of y coordinate is the index of row
            if (!map.containsKey(x)) {
                map.put(x, new TreeMap<>());
            }
            if (!map.get(x).containsKey(y)) {
                map.get(x).put(y, new PriorityQueue<>());
            }
            // add the value;
            map.get(x).get(y).offer(cur.val);
            if (cur.left != null) {
                queue.offer(new Tuple(cur.left, x - 1, y  + 1));
            }
            if (cur.right != null) {
                queue.offer(new Tuple(cur.right, x + 1, y + 1));
            }
        }
        // map is built
        List<List<Integer>> res = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> mapqueue: map.values()) {
            res.add(new ArrayList<>());  // min column will be added first as ordered in treemap
            int index = res.size() - 1;
            for (PriorityQueue<Integer> pq : mapqueue.values()) {
                while (!pq.isEmpty()) {
                    res.get(index).add(pq.poll());
                }
            }
        }
        return res;

    }
    // rewrite 11/16/2024
    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        Map<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<NodeItem> queue = new LinkedList<>();
        queue.offer(new NodeItem(root, 0, 0));
        while (!queue.isEmpty()) {
            NodeItem cur = queue.poll();
            if (!map.containsKey(cur.col)) {
                map.put(cur.col, new TreeMap<>());
            }
            // only sort when at both the same row and the same column
            if (!map.get(cur.col).containsKey(cur.row)) {
                map.get(cur.col).put(cur.row, new PriorityQueue<>());
            }
            map.get(cur.col).get(cur.row).offer(cur.node.val);
            if (cur.node.left != null) {
                queue.offer(new NodeItem(cur.node.left, cur.col - 1, cur.row + 1));
            }
            if (cur.node.right != null) {
                queue.offer(new NodeItem(cur.node.right, cur.col + 1, cur.row + 1));
            }
        }
        // build the result with the map
        List<List<Integer>> res = new ArrayList<>();
        for (Integer key: map.keySet()) {
            res.add(new ArrayList<>());
            int pos = res.size() - 1;
            for (PriorityQueue<Integer> pq: map.get(key).values()) {
                while (!pq.isEmpty()) {
                    res.get(pos).add(pq.poll());
                }

            }
        }
        return res;
    }

    // this will sort nodes at the same column even for different rows
    public List<List<Integer>> verticalTraversal3(TreeNode root) {
        Map<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
        Queue<NodeWithColumn> queue = new LinkedList<>();
        queue.offer(new NodeWithColumn(root, 0));
        while (!queue.isEmpty()) {
            NodeWithColumn cur = queue.poll();
            if (!map.containsKey(cur.col)) {
                map.put(cur.col, new PriorityQueue<>());
            }

            map.get(cur.col).add(cur.node.val);
            if (cur.node.left != null) {
                queue.offer(new NodeWithColumn(cur.node.left, cur.col - 1));
            }
            if (cur.node.right != null) {
                queue.offer(new NodeWithColumn(cur.node.right, cur.col + 1));
            }
        }
        // build the result with the map
        List<List<Integer>> res = new ArrayList<>();
        for (Integer key: map.keySet()) {
            res.add(new ArrayList<>());
            int pos = res.size() - 1;
            res.get(pos).addAll(new ArrayList<>(map.get(key)));
        }
        return res;
    }

    class NodeWithColumn{
        TreeNode node;
        int col;
        NodeWithColumn(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }


    class NodeItem{
        TreeNode node;
        int col;
        int row;
        NodeItem(TreeNode node, int col, int row) {
            this.node = node;
            this.col = col;
            this.row = row;
        }
    }

    // official https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/solutions/627153/vertical-order-traversal-of-a-binary-tree/
}
