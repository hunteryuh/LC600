package com.alg;

import java.util.ArrayList;

/**
 * Created by HAU on 1/5/2018.
 */
/*题目内容
就是给一棵多叉树，表示公司内部的上下级关系。每个节点表示一个员工，节点包含的成员是他工作了几个月(int)，以及一个下属数组(ArrayList)。
目标就是找到一棵子树，满足：这棵子树所有节点的工作月数的平均数是所有子树中最大的。最后返回这棵子树的根节点。这题补充一点，返回的不能是叶子节点(因为叶子节点没有下属)，一定要是一个有子节点的节点。*/
public class Sol0_amz_companyTree {
    static class Node { //这个是题目给好的
        int val;
        ArrayList<Node> children;
        public Node(int val){
            this.val = val;
            children = new ArrayList<Node>();
        }
    }
    private static double subAve = Double.MIN_VALUE;
    private static Node resNode;

    public static Node getMaxAveSubTree(Node root){
        if ( root == null) return null;
        dfs(root);
        return resNode;
    }

    private static SumCount dfs(Node root) {
        if( root.children == null || root.children.size() == 0){
            return new SumCount(root.val, 1);
        }
        int curSum = root.val;
        int curCount = 1;
        for(Node child: root.children){
            SumCount sc = dfs(child);
            curSum += sc.sum;
            curCount += sc.count;
        }
        double curAve = (double)(curSum/curCount);
        if(subAve < curAve){
            subAve = curAve;
            resNode = root;
        }
        return new SumCount(curSum,curCount);
    }
    static class SumCount{
        int sum;
        int count;
        public SumCount(int sum, int count){
            this.sum = sum;
            this.count = count;
        }
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        Node l21 = new Node(2);
        Node l22 = new Node(3);
        Node l23 = new Node(4);
        Node l31 = new Node(5);
        Node l32 = new Node(5);
        Node l33 = new Node(5);
        Node l34 = new Node(5);
        Node l35 = new Node(5);
        Node l36 = new Node(5);

        l21.children.add(l31);
        l21.children.add(l32);

        l22.children.add(l33);
        l22.children.add(l34);

        l23.children.add(l35);
        l23.children.add(l36);

        root.children.add(l21);
        root.children.add(l22);
        root.children.add(l23);

        Node res = getMaxAveSubTree(root);
        System.out.println(res.val + " " + subAve);
    }

}
