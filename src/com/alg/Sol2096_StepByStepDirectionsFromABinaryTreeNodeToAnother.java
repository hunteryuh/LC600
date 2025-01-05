package com.alg;
/*
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

    'L' means to go from a node to its left child node.
    'R' means to go from a node to its right child node.
    'U' means to go from a node to its parent node.

Return the step-by-step directions of the shortest path from node s to node t.



Example 1:

Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Example 2:

Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.



Constraints:

    The number of nodes in the tree is n.
    2 <= n <= 105
    1 <= Node.val <= n
    All the values in the tree are unique.
    1 <= startValue, destValue <= n
    startValue != destValue


 */
public class Sol2096_StepByStepDirectionsFromABinaryTreeNodeToAnother {
    //https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/solutions/1781093/java-dfs-solution-o-n-solution-easy-to-understand/
    public String getShortestDirectionPath(TreeNode root, int startValue, int destValue) {
        StringBuilder startToRoot = new StringBuilder();
        StringBuilder destToRoot = new StringBuilder();
        dfs(root, startToRoot, startValue);
        dfs(root, destToRoot, destValue);

        System.out.println(startToRoot); // start to root with direction of parent - child on each part
        System.out.println(destToRoot);
        int i = startToRoot.length();
        int j = destToRoot.length();
        int common = 0;
        while (i > 0 && j > 0 && startToRoot.charAt(i - 1) == destToRoot.charAt(j - 1)) {
            i--;j--;
            common++;
        }
        StringBuilder path1 = new StringBuilder();
        for (int k = 0; k < startToRoot.length() - common; k++) {
            path1.append("U");
        }
//              String sPath = "U".repeat(startToRoot.length() - common); // since java 11
        String path2 = destToRoot.reverse().substring(common);
        return path1 + path2;
    }

    private boolean dfs(TreeNode cur, StringBuilder sb, int value) {
        if (cur == null) return false;
        if (cur.val == value) return true;
        if (dfs(cur.left, sb, value)) {
            sb.append("L");
        } else if (dfs(cur.right, sb, value)) {
            sb.append("R");
        }
        return sb.length() > 0;
    }

}
