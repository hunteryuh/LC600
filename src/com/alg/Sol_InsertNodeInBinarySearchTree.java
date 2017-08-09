package com.alg;

/**
 * Created by HAU on 6/22/2017.
 */
public class Sol_InsertNodeInBinarySearchTree {
    public static class BinarySearchTree{
        TreeNode root;

        public void printInOrder() {
            printInOrderhelper(root);
        }

        private void printInOrderhelper(TreeNode node) {
            if ( node != null){
                printInOrderhelper(node.left);
                System.out.println(node.val);
                printInOrderhelper(node.right);
            }
        }


        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) {
                val = x;
                //this.left = this.right = null;
            }
        }

        BinarySearchTree(){
            root = null;
        }
        public void insert_Iter(TreeNode node){
            root = insert_IterHelper(root,node);
        }
        public TreeNode insert_IterHelper(TreeNode root, TreeNode node){
            if ( root == null){
                root = node;
                return root;
            }

            TreeNode cur = root;
            TreeNode last = null;  // initialize as null
            while ( cur != null){
                last = cur;
                if ( node.val < cur.val){
                    cur = cur.left;
                }
                else if ( node.val > cur.val){
                    cur = cur.right;
                }else break;
            }
            if ( last != null){
                if ( node.val < last.val){
                    last.left = node;
                }else if ( node.val > last.val){
                    last.right = node;
                }
            }
            return root;

        }

        public void insert(int i) {
            root = inserthelper(root,i);

        }

        private TreeNode inserthelper(TreeNode root, int i) {
            if (root == null){
                root = new TreeNode(i);
                return root;
            }
            if (i < root.val) {
                root.left = inserthelper(root.left,i);
            }else if ( i > root.val){
                root.right = inserthelper(root.right,i);
            }
            return root;
        }
    }

    public static void main(String[] args) {
//        BinarySearchTree tree = new BinarySearchTree();
//
//        tree.insert(50);
//        tree.insert(30);
//        tree.insert(20);
//        tree.insert(40);
//        tree.insert(70);
//        tree.insert(60);
//        tree.insert(80);
//        tree.printInOrder();

        BinarySearchTree t2 = new BinarySearchTree();
        BinarySearchTree.TreeNode node = new BinarySearchTree.TreeNode(21);
        BinarySearchTree.TreeNode node2 = new BinarySearchTree.TreeNode(5);
        BinarySearchTree.TreeNode node3 = new BinarySearchTree.TreeNode(0);

        //t2.root = node;
        t2.insert_Iter(node);
        t2.insert_Iter(node2);
        t2.insert_Iter(node3);

        //System.out.println(t2.root.val);
        t2.printInOrder();


    }


}
