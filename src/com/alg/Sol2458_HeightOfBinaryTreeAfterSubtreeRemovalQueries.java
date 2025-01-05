package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.

You have to perform m independent queries on the tree where in the ith query you do the following:

    Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.

Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.

Note:

    The queries are independent, so the tree returns to its initial state after each query.
    The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.



Example 1:

Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
Output: [2]
Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
The height of the tree is 2 (The path 1 -> 3 -> 2).

Example 2:

Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
Output: [3,2,3,2]
Explanation: We have the following queries:
- Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
- Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
- Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
- Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).



Constraints:

    The number of nodes in the tree is n.
    2 <= n <= 105
    1 <= Node.val <= n
    important: All the values in the tree are unique.
    m == queries.length
    1 <= m <= min(n, 104)
    1 <= queries[i] <= n
    queries[i] != root.val


 */
public class Sol2458_HeightOfBinaryTreeAfterSubtreeRemovalQueries {
    // https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/solutions/2759353/c-python-preoder-and-postorder-dfs/
    /*
    Pre-order dfs the tree (node, left, right),
update res[i] to the max height before node i in preorder,
can cover all nodes on the left of node i in the tree.

Pre-order-right dfs the tree (node, right, left),
update res[i] to the max height before node i in Pre-order-right,
can cover all nodes on the right of node i in the tree.
     */
    int[] preleft = new int[100001];
    int[] preright = new int[100001];
    int max = 0;
    public int[] treeQueries(TreeNode root, int[] queries) {
        max = 0;
        preOrderLeft(root, 0);
        max = 0;
        preOrderRight(root, 0);
        int[] res = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            res[i] = Math.max(preleft[queries[i]], preright[queries[i]]);
        }

        return res;
    }

    private void preOrderLeft(TreeNode root, int height) {
        if (root == null) return;
        preleft[root.val] = max;
        // When they reach to the same node (for example),
        //update the max level they saw so far. (Does not include current node.)
        max = Math.max(max, height);
        preOrderLeft(root.left, height + 1);
        preOrderLeft(root.right, height + 1);
    }

    private void preOrderRight(TreeNode root, int height) {
        if (root == null) return;
        preright[root.val] = max;
        max = Math.max(max, height);
        preOrderRight(root.right, height + 1);
        preOrderRight(root.left, height + 1);
    }

    // https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/solutions/2758211/java-2-pass-dfs-with-hashmaps/
    private Map<Integer, Integer> leftMap = new HashMap<>();
    private Map<Integer, Integer> rightMap = new HashMap<>();
    private Map<Integer, Integer> removed = new HashMap<>();

    public int[] treeQueries2(TreeNode root, int[] queries) {
        populateHeights(root, 0);
        calculateRemovedHeights(root, 0);

        int[] output = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            output[i] = removed.get(queries[i]);
        }
        return output;
    }

    // height is the max tree height with this node removed
    private void calculateRemovedHeights(TreeNode node, int height) {
        if (node == null) {
            return;
        }
        removed.put(node.val, height);

        // for each child, the height when removed is the max of the height following
        // the opposite child, or the passed-in height with this node removed
        calculateRemovedHeights(node.left, Math.max(height, rightMap.get(node.val)));
        calculateRemovedHeights(node.right, Math.max(height, leftMap.get(node.val)));
    }

    // populate the maps with the total height of the left and right subtree of
    // each node, and return the larger of the two values
    private int populateHeights(TreeNode node, int height) {
        if (node == null) {
            return height - 1;
        }

        leftMap.put(node.val, populateHeights(node.left, height + 1));
        rightMap.put(node.val, populateHeights(node.right, height + 1));

        return Math.max(leftMap.get(node.val), rightMap.get(node.val));
    }
}
