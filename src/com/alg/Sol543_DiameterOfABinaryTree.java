package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 1/16/2018.
 */
/*Given a binary tree, you need to compute the length of the diameter of the tree.
The diameter of a binary tree is the length of the
longest path between any two nodes in a tree.
This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.

*/
public class Sol543_DiameterOfABinaryTree {

    static int diameter;
    public static int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        height(root);
        return diameter;
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        // Find height of left and right subTrees
        int L = height(node.left);
        int R = height(node.right);
        // New global max is either already reached,
        // or is achieved using this node as the root
        diameter = Math.max(diameter, L + R);
        // ans = Math.max(ans, L + R);
        // If we knew the maximum length arrows L, R for each child, then the best path touches L + R + 1 nodes.
        // Return height of tree rooted at this node, leaf node has height 1
        return Math.max(L,R) + 1;
    }

    int d = 0;
    List<Integer> path;
    List<Integer> maxPath;
    private int DiameterOfTree(TreeNode root) {
        path = new ArrayList<>();
        maxPath = new ArrayList<>();
        longestPath1(root);
        System.out.println("maxPath is " + maxPath);
        return d;
    }

    private int longestPath1(TreeNode node) {
        if (node == null) return 0;
        path.add(node.val);
        int left = longestPath1(node.left);
        int right = longestPath1(node.right);
        if (left + right > d) {
            d = left + right;
            maxPath = new ArrayList<>(path);
        }
        System.out.println(path);
        if (left != 0 && right != 0) {
//            System.out.println("start removing " + path.get(path.size() - 1));
            path.remove(path.size() - 1);
        }
        System.out.println(path);

        return Math.max(left, right) + 1;
    }

    int dd = 0;
    List nodeList = new ArrayList<>();

    class Result {
        int diameter;
        List<TreeNode> list;
        Result(int diameter, List<TreeNode> list) {
            this.diameter = diameter;
            this.list = list;
        }
    }

    public int diameterOfBinaryTree2(TreeNode root) {
        longestPath(root);
        printList(nodeList);
        return diameter;
    }

    private void printList(List<TreeNode> list){
        for(TreeNode node : list){
            System.out.print(node.val+"->");
        }
        System.out.println();
    }

    private List<TreeNode> longestPath(TreeNode node){
        if (node == null) {
            return new ArrayList<>();
        }

        List<TreeNode> left = longestPath(node.left);
        List<TreeNode> right = longestPath(node.right);

        if (left.size() + right.size() > diameter){
            diameter = left.size() + right.size();
            nodeList = new ArrayList<>();
            nodeList.addAll(left);
            nodeList.add(node);
            nodeList.addAll(right);
        }

        if (left.size() >= right.size()){
//            left.list.add(node);
//            left.diameter = left.diameter + 1;
            List<TreeNode> list = new ArrayList<>(left);
            list.add(node);
            return list;

//            return new Result(left.size() + 1, list);
//            return left;
        } else {
            List<TreeNode> list = new ArrayList<>(right);
            list.add(node);
            return list;
        }
    }


    public static void main(String[] args) {
        /*Given a binary tree:

                 0
               /   \
             -5     11
             / \   /  \
            1   2 4
                /  \
               3    -2
*/

        TreeNode n1 = new TreeNode(0);
        TreeNode n2 = new TreeNode(-5);
        TreeNode n3 = new TreeNode(11);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(2);
        TreeNode n6 = new TreeNode(4);
        TreeNode n61 = new TreeNode(3);

        TreeNode n7 = new TreeNode(-2);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n6.right = n7;
        n6.left = n61;

//        System.out.println(diameterOfBinaryTree(n1)); //5

        Sol543_DiameterOfABinaryTree ss = new Sol543_DiameterOfABinaryTree();
        int t = ss.diameterOfBinaryTree2(n1);
        System.out.println(t);
    }
}
