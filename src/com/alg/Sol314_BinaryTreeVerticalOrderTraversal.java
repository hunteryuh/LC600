package com.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/*
Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.



Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]

Example 2:

Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]

Example 3:

Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]



Constraints:

    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100


 */
public class Sol314_BinaryTreeVerticalOrderTraversal {
    // bfs
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Map<TreeNode, Integer> map = new HashMap<>(); // node to column
        Map<Integer, List<Integer>> columnNodes = new HashMap<>();
        map.put(root, 0);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int minCol = 0;
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            int col = map.get(cur);
            columnNodes.computeIfAbsent(col, x -> new ArrayList<>()).add(cur.val);

            if (cur.left != null) {
                q.offer(cur.left);
                map.put(cur.left, col - 1);
            }
            if (cur.right != null) {
                q.offer(cur.right);
                map.put(cur.right, col + 1);
            }
            minCol = Math.min(minCol, col);
        }
        // trick: this avoids a sort
        while (columnNodes.containsKey(minCol)) {
            res.add(columnNodes.get(minCol));
            minCol++;
        }
        return res;
    }

    // dfs
    public List<List<Integer>> verticalOrder2(TreeNode root) {
        Map<Integer, List<int[]>> colToNode = new TreeMap<>();
        dfs(root, 0, 0, colToNode);
        List<List<Integer>> res = new ArrayList<>();
        for (List<int[]> nodes: colToNode.values()) {
            Collections.sort(nodes, (n1 , n2) -> (n1[0] - n2[0])); // sort by depth
            List<Integer> sub = new ArrayList<>();
            for (int[] node: nodes) {
                sub.add(node[1]);
            }
            res.add(sub);
        }
        return res;
    }

    private void dfs(TreeNode root, int depth, int offset, Map<Integer, List<int[]>> colToNode) {
        if (root == null) return;
        if (!colToNode.containsKey(offset)) {
            colToNode.put(offset, new ArrayList<>());
        }
        colToNode.get(offset).add(new int[]{depth, root.val});
        dfs(root.left, depth + 1, offset - 1, colToNode);
        dfs(root.right, depth + 1, offset + 1, colToNode);
    }
}
