package com.alg;
/*
Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.



Example 1:

Input: root = [2,1,3]
Output: [2,1,3]
Example 2:

Input: root = []
Output: []

 */
public class Sol449_SerializeAndDeserializeBST {
    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            preorder(root, sb);
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;
            String[] nodeValues = data.split("-");
            return buildTree(nodeValues, 0, nodeValues.length - 1);
        }

        private void preorder(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            sb.append(root.val).append("-");
            preorder(root.left, sb);
            preorder(root.right, sb);
        }

        private TreeNode buildTree(String[] values, int left, int right) {
            // left, right  [2, 1]
            if (left > right) return null;
            if (left == right) {
                return new TreeNode(Integer.parseInt(values[left]));
            }
            TreeNode root = new TreeNode(Integer.parseInt(values[left]));
            int rightChildIndex = left + 1;
//            for (int i = left + 1; i < values.length; i++) {
//                if (Integer.parseInt(values[i]) > Integer.parseInt(values[left])) {
//                    rightChildIndex = i;
//                    break;
//                }
//            } // wrong
            while(rightChildIndex < values.length && Integer.parseInt(values[left]) > Integer.parseInt(values[rightChildIndex])){
                rightChildIndex++;
            }
            root.left = buildTree(values, left + 1, rightChildIndex - 1);
            root.right = buildTree(values, rightChildIndex, right);
            return root;
        }

        public TreeNode deserialize2(String data) {
            if (data.isEmpty()) return null;
            String[] nodeValues = data.split("-");
            return buildTree2(nodeValues, 0, nodeValues.length); // [left, right)
        }
        private TreeNode buildTree2(String[] values, int left, int right) {
            // left, right  [2, 1]
            if (left >= right) return null;
            if (left + 1== right) {
                return new TreeNode(Integer.parseInt(values[left]));
            }
            TreeNode root = new TreeNode(Integer.parseInt(values[left]));
            int rightChildIndex = left + 1;
//            for (int i = left + 1; i < values.length; i++) {
//                if (Integer.parseInt(values[i]) > Integer.parseInt(values[left])) {
//                    rightChildIndex = i;
//                    break;
//                }
//            } // wrong
            while(rightChildIndex < values.length && Integer.parseInt(values[left]) > Integer.parseInt(values[rightChildIndex])){
                rightChildIndex++;
            }
            root.left = buildTree2(values, left + 1, rightChildIndex);
            root.right = buildTree2(values, rightChildIndex, right);
            return root;
        }
    }

    public TreeNode deserialize2(String data) {
        if (data.isEmpty()) return null;
        String[] nodeValues = data.split("-");
        return buildTree2(nodeValues, 0, nodeValues.length); // [left, right)
    }
    private TreeNode buildTree2(String[] values, int left, int right) {
        // left, right  [2, 1]
        if (left >= right) return null;
        if (left + 1== right) {
            return new TreeNode(Integer.parseInt(values[left]));
        }
        TreeNode root = new TreeNode(Integer.parseInt(values[left]));
        int rightChildIndex = left + 1;
//            for (int i = left + 1; i < values.length; i++) {
//                if (Integer.parseInt(values[i]) > Integer.parseInt(values[left])) {
//                    rightChildIndex = i;
//                    break;
//                }
//            } // wrong
        while(rightChildIndex < values.length && Integer.parseInt(values[left]) > Integer.parseInt(values[rightChildIndex])) {
            rightChildIndex++;
        }
        root.left = buildTree2(values, left + 1, rightChildIndex);
        root.right = buildTree2(values, rightChildIndex, right);
        return root;
    }

    public static void main(String[] args) {
        Sol449_SerializeAndDeserializeBST ss = new Sol449_SerializeAndDeserializeBST();
        TreeNode root = new TreeNode(2);
        TreeNode l1 = new TreeNode(1);
        root.left  = l1;
        String tree = new Codec().serialize(root);
        System.out.println(tree);
        TreeNode droot = new Codec().deserialize2(tree);
        System.out.println(droot.val);
        System.out.println(droot.left.val);
    }

}
