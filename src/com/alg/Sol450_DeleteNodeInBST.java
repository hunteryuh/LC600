package com.alg;
/*
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.


Example 1:


Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.

Example 2:

Input: root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]
Explanation: The tree does not contain a node with value = 0.
Example 3:

Input: root = [], key = 0
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 104].
-105 <= Node.val <= 105
Each node has a unique value.
root is a valid binary search tree.
-105 <= key <= 105
 */
public class Sol450_DeleteNodeInBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val > key) {
            root.left = deleteNode(root.left, key);  // assign root.left to the recursion, not return
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            // root.val == key
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // root.left != null && root.right != null
                // find the min of root.right and give it to root
                root.val = min(root.right);
                root.right = deleteNode(root.right, root.val); // since "min" was moved upward, need to delete the node in the tree of root.right
            }

        }
        return root;
    }

    // recursion to find min in bst
    private int min(TreeNode head) {
        if (head.left == null) {
            return head.val;
        }
        return min(head.left);
    }

    // iterative to find min in bst
    private int min2(TreeNode head) {
        while (head.left != null) {
            head = head.left;
        }
        return head.val;
    }
}
