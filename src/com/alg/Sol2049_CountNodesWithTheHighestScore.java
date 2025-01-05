package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
There is a binary tree rooted at 0 consisting of n nodes. The nodes are labeled from 0 to n - 1.
You are given a 0-indexed integer array parents representing the tree,
where parents[i] is the parent of node i. Since node 0 is the root, parents[0] == -1.

Each node has a score. To find the score of a node, consider if the node and the edges connected to it were removed. The tree would become one or more non-empty subtrees. The size of a subtree is the number of the nodes in it. The score of the node is the product of the sizes of all those subtrees.

Return the number of nodes that have the highest score.



Example 1:
example-1

          0
        /  \
       2    4
      / \
     3  1
Input: parents = [-1,2,0,2,0]
Output: 3
Explanation:
- The score of node 0 is: 3 * 1 = 3
- The score of node 1 is: 4 = 4
- The score of node 2 is: 1 * 1 * 2 = 2
- The score of node 3 is: 4 = 4
- The score of node 4 is: 4 = 4
The highest score is 4, and three nodes (node 1, node 3, and node 4) have the highest score.

Example 2:
example-2

Input: parents = [-1,2,0]
Output: 2
Explanation:
- The score of node 0 is: 2 = 2
- The score of node 1 is: 2 = 2
- The score of node 2 is: 1 * 1 = 1
The highest score is 2, and two nodes (node 0 and node 1) have the highest score.



Constraints:

    n == parents.length
    2 <= n <= 105
    parents[0] == -1
    0 <= parents[i] <= n - 1 for i != 0
    parents represents a valid binary tree.


 */
public class Sol2049_CountNodesWithTheHighestScore {
    //https://leetcode.com/problems/count-nodes-with-the-highest-score/solutions/1537603/python-3-graph-dfs-post-order-traversal-o-n-explanation/
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        // build graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.putIfAbsent(i, new ArrayList<>());
            graph.computeIfAbsent(parents[i], x -> new ArrayList<>()).add(i);
        }

        Map<Long, Integer> product = new HashMap<>();
        int x = dfs(0, graph, product, n);
        System.out.println(x);
        long maxP = 0;
        for (long p: product.keySet()) {
            if ( p > maxP) {
                maxP = p;
            }
        }
        return product.get(maxP);
    }

    // dfs to return number of children node + 1 (self)
    private int dfs(int cur, Map<Integer, List<Integer>> graph, Map<Long, Integer> product, int n) {
        long p = 1;
        int sum = 0;
        for (int child: graph.get(cur)) {
            int numFromChild = dfs(child, graph, product, n);
            sum += numFromChild;
            p *= (long) numFromChild;
        }
        // times up-branch (number of nodes other than left, right children ans itself)

        p *= (long) Math.max(1, n - 1 - sum);
//        product.merge(p, 1, (a, b) -> a + b);
        product.put(p, product.getOrDefault(p, 0) + 1);
        // return number of children node + 1 (self) (current "root" in the parameter)
        return sum + 1;
    }

    public static void main(String[] args) {
        int[] parents = {-1,2,0,2,0};
        Sol2049_CountNodesWithTheHighestScore ss = new Sol2049_CountNodesWithTheHighestScore();
        System.out.println(ss.countHighestScoreNodes(parents));
    }
}
