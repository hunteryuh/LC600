package com.alg.lint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*

Given a undirected graph, a node and a target, return the nearest node to given node which value of it is target, return NULL if you can't find.

There is a mapping store the nodes' values in the given parameters.

It's guaranteed there is only one available solution

样例
Example 1:

Input:
{1,2,3,4#2,1,3#3,1,2#4,1,5#5,4}
[3,4,5,50,50]
1
50
Output:
4
Explanation:
2------3  5
 \     |  |
  \    |  |
   \   |  |
    \  |  |
      1 --4
Give a node 1, target is 50

there a hash named values which is [3,4,10,50,50], represent:
Value of node 1 is 3
Value of node 2 is 4
Value of node 3 is 10
Value of node 4 is 50
Value of node 5 is 50

Return node 4
Example 2:

Input:
{1,2#2,1}
[0,1]
1
1
Output:
2


https://www.jiuzhang.com/problem/search-graph-nodes/

 */
public class P618_SearchGraphNode {
    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x; neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    /**
     * @param graph a list of Undirected graph node
     * @param values a hash mapping, <UndirectedGraphNode, (int)value>
     * @param node an Undirected graph node
     * @param target an integer
     * @return the a node
     */
    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
                                          Map<UndirectedGraphNode, Integer> values,
                                          UndirectedGraphNode node,
                                          int target) {

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        Set<UndirectedGraphNode> set = new HashSet<>();
        set.add(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
//            if (head.label == target) {
            if (values.get(head) == target) {
                return head;
            }

            for (UndirectedGraphNode neighbor: head.neighbors) {
                if (!set.contains(neighbor)) {
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return null;
    }
}
