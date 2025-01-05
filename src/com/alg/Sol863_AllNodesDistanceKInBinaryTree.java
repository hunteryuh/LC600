package com.alg;

import java.util.*;

/*
Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.



Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Example 2:

Input: root = [1], target = 1, k = 3
Output: []



Constraints:

    The number of nodes in the tree is in the range [1, 500].
    0 <= Node.val <= 500
    All the values Node.val are unique.
    target is the value of one of the nodes in the tree.
    0 <= k <= 1000


 */
public class Sol863_AllNodesDistanceKInBinaryTree {
    Map<Integer, List<Integer>> graph;
    List<Integer> answer;
    Set<Integer> visited;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        graph = new HashMap<>();
        buildGraph(root, null);

        answer = new ArrayList<>();
        visited = new HashSet<>();
        visited.add(target.val);

        dfs(target.val, 0, k);

        return answer;
    }

    // Recursively build the undirected graph from the given binary tree.
    private void buildGraph(TreeNode cur, TreeNode parent) {
        if (cur != null && parent != null) {
            graph.computeIfAbsent(cur.val, k -> new ArrayList<>()).add(parent.val);
            graph.computeIfAbsent(parent.val, k -> new ArrayList<>()).add(cur.val);
        }
        if (cur.left != null) {
            buildGraph(cur.left, cur);
        }
        if (cur.right != null) {
            buildGraph(cur.right, cur);
        }
    }

    private void dfs(int cur, int distance, int k) {
        if (distance == k) {
            answer.add(cur);
            return;
        }
        for (int neighbor : graph.getOrDefault(cur, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                dfs(neighbor, distance + 1, k);
            }
        }
    }
}
