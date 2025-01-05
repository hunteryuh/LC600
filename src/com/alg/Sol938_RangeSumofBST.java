package com.alg;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
Given the root node of a binary search tree and two integers low and high,
return the sum of values of all nodes with a value in the inclusive range [low, high].



Example 1:


Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
Example 2:


Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.


Constraints:

The number of nodes in the tree is in the range [1, 2 * 104].
1 <= Node.val <= 105
1 <= low <= high <= 105
All Node.val are unique.
 */
public class Sol938_RangeSumofBST {
    int res = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if (low <= root.val && root.val <= high) {
            res += root.val;
        }
        if (low < root.val) {
            rangeSumBST(root.left, low, high);
        }
        if (root.val < high) {
            rangeSumBST(root.right, low, high);
        }
        return res;
    }

    public int rangeSumBST2(TreeNode root, int L, int R) {
        if (root == null) return 0; // base case.
        if (root.val < L) return rangeSumBST2(root.right, L, R); // left branch excluded.
        if (root.val > R) return rangeSumBST2(root.left, L, R); // right branch excluded.
        return root.val + rangeSumBST2(root.right, L, R) + rangeSumBST2(root.left, L, R); // count in both children.
    }

    // iterative
    // time O(N). space O(N)
    public int rangeSumBST3(TreeNode root, int low, int high) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (low <= node.val && node.val <= high)
                    ans += node.val;
                if (low < node.val)
                    stack.push(node.left);
                if (node.val < high)
                    stack.push(node.right);
            }
        }
        return ans;
    }

    // stack method 2
    public int rangeSumBST4(TreeNode root, int L, int R) {
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        int sum = 0;
        while (!stk.isEmpty()) {
            TreeNode n = stk.pop();
            if (n == null) { continue; }
            if (n.val > L) { stk.push(n.left); } // left child is a possible candidate.
            if (n.val < R) { stk.push(n.right); } // right child is a possible candidate.
            if (L <= n.val && n.val <= R) { sum += n.val; }
        }
        return sum;
    }

    // bfs
    public int rangeSumBST5(TreeNode root, int low, int high) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.val >= low && cur.val <= high) {
                res += cur.val;
            }
            if (cur.left != null && cur.val >= low) {
                queue.offer(cur.left);
            }
            if (cur.right != null && cur.val <= high) {
                queue.offer(cur.right);
            }
        }
        return res;
    }
}
