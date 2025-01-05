package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
If the depth of a tree is smaller than 5, then this tree can be represented by an array of three-digit integers. For each integer in this array:

    The hundreds digit represents the depth d of this node where 1 <= d <= 4.
    The tens digit represents the position p of this node in the level it belongs to where 1 <= p <= 8. The position is the same as that in a full binary tree.
    The units digit represents the value v of this node where 0 <= v <= 9.

Given an array of ascending three-digit integers nums representing a binary tree with a depth smaller than 5, return the sum of all paths from the root towards the leaves.

It is guaranteed that the given array represents a valid connected binary tree.



Example 1:

Input: nums = [113,215,221]
Output: 12
Explanation: The tree that the list represents is shown.
The path sum is (3 + 5) + (3 + 1) = 12.

Example 2:

Input: nums = [113,221]
Output: 4
Explanation: The tree that the list represents is shown.
The path sum is (3 + 1) = 4.



Constraints:

    1 <= nums.length <= 15
    110 <= nums[i] <= 489
    nums represents a valid binary tree with depth less than 5.


 */
public class Sol666_PathSumIV {
    class Node {
        Node left, right;
        int val;
        Node(int v) {
            this.val = v;
        }
    }
    int ans = 0;
    public int pathSum(int[] nums) {
        Node root = new Node(nums[0] % 10); // value
        for (int num: nums) {
            if (num == nums[0]) continue;
            int depth = num/100;
            int pos = num/10 % 10;
            int val = num % 10;
            pos--;
            Node cur = root;
            for (int d = depth - 2; d >= 0; --d) {
                if (pos < 1<<d) {
                    if (cur.left == null) cur.left = new Node(val);
                    cur = cur.left;
                } else {
                    if (cur.right == null) cur.right = new Node(val);
                    cur = cur.right;
                }
                pos %= 1<<d;
            }
        }

        dfs(root, 0);
        return ans;
    }
    public void dfs(Node node, int sum) {
        if (node == null) return;
        sum += node.val;
        if (node.left == null && node.right == null) {
            ans += sum;
        } else {
            dfs(node.left, sum);
            dfs(node.right, sum);
        }
    }


    // https://leetcode.com/problems/path-sum-iv/solutions/106892/java-solution-represent-tree-using-hashmap/
    // method 2 with map
    int res = 0;
    Map<Integer, Integer> values;
    public int pathSum2(int[] nums) {
        values = new HashMap<>();
        for (int num: nums) {
            values.put(num/10, num % 10); // depth + position, value
        }
        dfs(nums[0] / 10, 0);
        return res;
    }

    public void dfs(int node, int sum) {
        if (!values.containsKey(node)) return;
        sum += values.get(node);
        int depth = node / 10;
        int pos = node % 10;
        int left = (depth + 1) * 10 + 2 * pos - 1; // left child of node at (depth, pos)
        int right = left + 1;
        if (!values.containsKey(left) && !values.containsKey(right)) {
            res += sum;
            return;
        }
        if (values.containsKey(left)) { // can remove if we have line 101 (check if contains)
            dfs(left, sum);
        }
        if (values.containsKey(right)) {
            dfs(right, sum);
        }

    }


}
