package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Created by HAU on 2/4/2018.
 */
/*Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/

 https://www.lintcode.com/problem/137/
 https://www.jiuzhang.com/problem/clone-graph/

 */
public class Sol133_CloneGraph {
    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x; neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    // 2nd trial with bfs
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public Node cloneGraph(Node node) {
        // copy nodes
        if (node == null) return null;
        // copy edges
        List<Node> allNodes = getAllNodes(node);
        Map<Node, Node> map = new HashMap<>();
        for (Node nd: allNodes) {
            map.put(nd, new Node(nd.val, new ArrayList<>()));
        }
        for (Node nd: map.keySet()) {
            Node cl = map.get(nd);
            for (Node nb: nd.neighbors) {
                cl.neighbors.add(map.get(nb));
            }
        }
        return map.get(node);
    }

    private List<Node> getAllNodes(Node node) {
//        List<Node> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        Set<Node> set = new HashSet<>();
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
//            list.add(cur);
            for (Node nb: cur.neighbors) {
                if (set.contains(nb)) continue;
                set.add(nb);
                queue.offer(nb);
            }
        }
        return new ArrayList<>(set);
    }

    private static Map<Integer,UndirectedGraphNode> map = new HashMap<>();
    public static UndirectedGraphNode cloneGraph0(UndirectedGraphNode node) {

        if (node == null) return null;
        if (map.containsKey(node.label)){
            return map.get(node.label);
        }
        UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
        map.put(cloned.label, cloned);
        for (UndirectedGraphNode neighbor: node.neighbors) {
            cloned.neighbors.add(cloneGraph0(neighbor));
        }
        return cloned;
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        /*
        1. 从原图给定的点找到所有点
        2. 复制所有的点
        3. 复制所有的边
         */
        if (node == null) {
            return null;
        }
        //BFS
        ArrayList<UndirectedGraphNode> nodes = getAllNodes(node);

        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        for (UndirectedGraphNode oldNode : nodes) {
            map.put(oldNode, new UndirectedGraphNode(oldNode.label));
        }

        // copy neighbors
        for (UndirectedGraphNode oldNode : nodes) {
            UndirectedGraphNode newNode = map.get(oldNode);
            for (UndirectedGraphNode neighbor: oldNode.neighbors) {
                UndirectedGraphNode newNeighbor = map.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }
        return map.get(node);
    }

    private ArrayList<UndirectedGraphNode> getAllNodes(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        Set<UndirectedGraphNode> set = new HashSet<>();
        set.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
            for (UndirectedGraphNode neighbor: head.neighbors) {
                if (!set.contains(neighbor)) {
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return new ArrayList<>(set);
    }
}
