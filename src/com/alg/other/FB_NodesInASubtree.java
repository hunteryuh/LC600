package com.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given a tree that contains N nodes, each containing an integer u which corresponds to a lowercase character c in the string s using 1-based indexing.
You are required to answer Q queries of type [u, c], where u is an integer and c is a lowercase letter. The query result is the number of nodes in the subtree of node u containing c.
Signature
int[] countOfNodes(Node root, ArrayList<Query> queries, String s)
Input
A pointer to the root node, an array list containing Q queries of type [u, c], and a string s
Constraints
N and Q are the integers between 1 and 1,000,000
u is a unique integer between 1 and N
s is of the length of N, containing only lowercase letters
c is a lowercase letter contained in string s
Node 1 is the root of the tree
Output
An integer array containing the response to each query
Example
        1(a)
        /   \
      2(b)  3(a)
s = "aba"
RootNode = 1
query = [[1, 'a']]
Note: Node 1 corresponds to first letter 'a', Node 2 corresponds to second letter of the string 'b', Node 3 corresponds to third letter of the string 'a'.
output = [2]
Both Node 1 and Node 3 contain 'a', so the number of nodes within the subtree of Node 1 containing 'a' is 2.
 */
public class FB_NodesInASubtree {
        // Tree Node
        class Node {
            public int val;
            public List<Node> children;

            public Node() {
                val = 0;
                children = new ArrayList<Node>();
            }

            public Node(int _val) {
                val = _val;
                children = new ArrayList<Node>();
            }

            public Node(int _val, ArrayList<Node> _children) {
                val = _val;
                children = _children;
            }
        }

        class Query {
            int u;
            char c;

            Query(int u, char c) {
                this.u = u;
                this.c = c;
            }
        }



        // Add any helper functions you may need here


        public int[] countOfNodes(Node root, ArrayList<Query> queries, String s) {
            // Write your code here
            int size = queries.size();
            int[] res = new int[size];
            Map<Integer, Map<Character, Integer>> charCountMap = new HashMap<>();
            buildMap(charCountMap, root, s);
            System.out.println(charCountMap);

            for (int i = 0; i < size; i++) {
                Query q = queries.get(i);
                char c = q.c;
                int u = q.u;
                res[i] = charCountMap.get(u).getOrDefault(c, 0);
            }
            return res;

        }

        private Map<Character, Integer> buildMap(Map<Integer, Map<Character, Integer>> charCountMap, Node node, String s) {
            Map<Character, Integer> countMap = new HashMap<>();
//            if (node == null) return countMap;
            countMap.put(s.charAt(node.val - 1), 1);
            for (Node n : node.children) {
                Map<Character, Integer> childMap = buildMap(charCountMap, n, s);
                for (Character c : childMap.keySet()) {
                    int count = 0;
                    if (countMap.containsKey(c)) {
                        count = countMap.get(c);
                    }
                    countMap.put(c, count + childMap.get(c));
                }
            }
            charCountMap.put(node.val, countMap);
            return countMap;
        }

        public void run() {

            // Testcase 2
            //            1a
            //       2b    3a   7b
            //     4a 5c   6a

            // charCountMap: {1={a=4, b=2, c=1}, 2={a=1, b=1, c=1}, 3={a=2}, 4={a=1}, 5={c=1}, 6={a=1}, 7={b=1}}
            int n_2 = 7, q_2 = 3;
            String s_2 = "abaacab";
            Node root_2 = new Node(1);
            root_2.children.add(new Node(2));
            root_2.children.add(new Node(3));
            root_2.children.add(new Node(7));
            root_2.children.get(0).children.add(new Node(4));
            root_2.children.get(0).children.add(new Node(5));
            root_2.children.get(1).children.add(new Node(6));
            ArrayList<Query> queries_2 = new ArrayList<>();
            queries_2.add(new Query(1, 'a'));
            queries_2.add(new Query(2, 'b'));
            queries_2.add(new Query(3, 'a'));
            int[] output_2 = countOfNodes(root_2, queries_2, s_2);
            System.out.println(Arrays.toString(output_2));
        }

    public static void main(String[] args) {
        new FB_NodesInASubtree().run();
    }
}


