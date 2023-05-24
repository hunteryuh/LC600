package com.alg;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by HAU on 6/10/2017.
 */
/*Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states.
Your serialize and deserialize algorithms should be stateless.
https://www.lintcode.com/help/binary-tree-representation/
https://www.jiuzhang.com/solution/binary-tree-serialization/
*/


public class Sol297_SerializeAndDeserializeBinaryTree {

  public static class Codec {
      public static String serialize_with_nulls(TreeNode root) {
          if (root == null)
              return "{}";
          StringBuilder sb = new StringBuilder();
          sb.append("{");
          LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
          queue.add(root);
          while(!queue.isEmpty()){
              TreeNode node = queue.poll();
              if (node != null){
                  sb.append(node.val + ",");
                  queue.add(node.left);
                  queue.add(node.right);
              }else{
                  sb.append("#,"); // many null "#" for the last few leaves in this method
              }
          }
          sb.deleteCharAt(sb.length()-1);
          sb.append("}");
          System.out.println(sb.toString());
          return sb.toString();
      }
      public static String serialize(TreeNode root){
          if (root == null)
              return "{}";
          StringBuilder sb = new StringBuilder();

          LinkedList<TreeNode> queue = new LinkedList<TreeNode>(); // can use ArrayList as well
          queue.add(root);
          for(int i = 0; i < queue.size(); i++){
              TreeNode node = queue.get(i);
                  //sb.append(node.val + ",");
              if (node == null) continue;
              queue.add(node.left);
              queue.add(node.right);
          }

          while (queue.get(queue.size()-1) == null){
              queue.remove(queue.size()-1);
          }
          sb.append("{");
          for (int i = 0; i < queue.size(); i++){
              if (queue.get(i) == null){
                  sb.append("#,");
              } else {
                  sb.append(queue.get(i).val).append(",");
              }
          }
          sb.deleteCharAt(sb.length()-1);
          sb.append("}");
          System.out.println(sb.toString());
          return sb.toString();
      }

      public static TreeNode deserialize_with_null(String data){
          if (data.equals("{}"))
              return null;
          String[] vals = data.substring(1,data.length()-1).split(",");// put all values in the string to a string array
          // first and last characters are { and }
          LinkedList<TreeNode> queue = new LinkedList<>();
          TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
          queue.add(root);
          int i = 1;
          while (!queue.isEmpty()){
              TreeNode node = queue.poll();
              if (node == null) continue;
              if (!vals[i].equals("#")){
                  node.left = new TreeNode(Integer.parseInt(vals[i]));
                  queue.add(node.left);
              }else {
                  node.left = null;
                  queue.add(null);
              }
              i++;
              if (!vals[i].equals("#")){
                  node.right = new TreeNode(Integer.parseInt(vals[i]));
                  queue.add(node.right);
              }else {
                  node.right = null;
                  queue.add(null);
              }
              i++;
          }
          return root;


      }
      public static TreeNode deserialize(String data){
          if (data.equals("{}"))
              return null;
          String[] vals = data.substring(1,data.length()-1).split(",");// put all values in the string to a string array
          // first and last characters are { and }
          LinkedList<TreeNode> queue = new LinkedList<>();
          TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
          queue.add(root);
          int index = 0;
          boolean left = true;
          for (int i = 1; i < vals.length; i++){
              if (!vals[i].equals("#")){
                  TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
                  if (left){
                      queue.get(index).left = node;
                  } else{
                      queue.get(index).right = node;
                  }
                  queue.add(node);
              }
              if (!left) {  // get the current parent node to add child node
                  index++;
              }
              left = !left;
          }
          for (TreeNode t: queue) {
              System.out.println(t.val);
          }
          return root;
      }

      public static void main(String args[]) {
        /* creating a binary tree and entering
         the nodes */
/*          TreeNode node = new TreeNode(10);
          node.left = new TreeNode(5);
          node.right = new TreeNode(30);
          node.left.left = new TreeNode(4);
          node.right.right = new TreeNode(38);
          node.right.left = new TreeNode(20);*/


          /*System.out.println("Level order serialization of binary tree is - ");
          serialize(node);*/
          String data = "{10,5,30,4,#,20,38}";
          deserialize(data);
      }

      // https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/1659686/Java-oror-BFS
      // Encodes a tree to a single string.
      public String serialize2(TreeNode root) {

          List<String> l = new ArrayList<>();
          Queue<TreeNode> q = new LinkedList<>();
          q.add(root);

          while(!q.isEmpty()) {
              TreeNode n = q.poll();
              if (n != null) {
                  l.add("" + n.val);
                  q.add(n.left);
                  q.add(n.right);
              } else {
                  l.add("#");
              }
          }

          return  String.join(",", l);  // no need to deal with first or last char
      }

      // Decodes your encoded data to tree.
      public TreeNode deserialize2(String data) {

          String[] nodes = data.split(",");
          if (nodes[0].equals("#")) return null;  //root is null
          TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
          Queue<TreeNode> queue = new LinkedList<>();
          queue.offer(root);
          for (int i = 1; i < nodes.length; i += 2) {
              TreeNode node = queue.poll();
              if (!nodes[i].equals("#")) {
                  node.left = new TreeNode(Integer.parseInt(nodes[i]));
                  queue.offer(node.left);
              }
              if (!nodes[i+1].equals("#")) {
                  node.right = new TreeNode(Integer.parseInt(nodes[i+1]));
                  queue.offer(node.right);
              }

          }
          return root;
      }


      // recursion, preorder
      public String serialize3(TreeNode root) {
          if (root == null) return "#";
          return root.val + "," + serialize3(root.left) + "," + serialize3(root.right);
      }

      public String serialize3_1(TreeNode root) {
          if (root == null) return "#";
          StringBuilder sb = new StringBuilder();
          sb.append(root.val);
          sb.append(",").append(serialize3_1(root.left));
          sb.append(",").append(serialize3_1(root.right));
          return sb.toString();
      }
      public TreeNode deserialize3(String data) {
          Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
          return helper(queue);
      }
      private TreeNode helper(Queue<String> queue) {
          String s = queue.poll();
          if (s.equals("#")) return null;
          TreeNode root = new TreeNode(Integer.valueOf(s));
          root.left = helper(queue);
          root.right = helper(queue);
          return root;
      }
  }

}
