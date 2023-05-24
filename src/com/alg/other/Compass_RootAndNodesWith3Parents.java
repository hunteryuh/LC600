package com.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/discuss/interview-question/1006763/compass-technical-assessment-karat
/*
Technical Question 1
Prompt: Given an array of arrays, where each array contains two numbers -
the first number being a parent and the second number being a child of a tree -
return an array of arrays where the 0ith element is an array of all nodes with no parents (roots),
and the 1st element is an array of all nodes with 3 parents.
signature getRootAndNodesWithThreeParents(arrayOfRelationships)

Technical Question 2
Prompt: Given an array of arrays, where each array contains two numbers -
the first number being a parent and the second number being a child of a tree -, and two nodes - node1 and node2 -
return an array of all common ancestors.
 */
public class Compass_RootAndNodesWith3Parents {
    public int[][] getRootAndNodesWith3Parents(int[][] nodes) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pairs: nodes) {
            int node = pairs[1];
            int parent = pairs[0];
            set.add(node);
            set.add(parent);
            if (!map.containsKey(node)) {
                map.put(node, new ArrayList<>());
            }
            map.get(node).add(parent);
        }
        List<Integer> r1 = new ArrayList<>();
        List<Integer> r2 = new ArrayList<>();
        for (Integer i: set) {
            if(!map.containsKey(i)) {
                r1.add(i);
            } else if (map.get(i).size() == 3) {
                r2.add(i);
            }
        }
        int[] r11 = r1.stream().mapToInt(i -> i).toArray();  // List<Integer> to int[]
        int[] r12 = r2.stream().mapToInt(i -> i).toArray();
        return new int[][]{r11, r12};
    }

    public int[] getAllCommonAncestors(int[][] nodes, int node1, int node2) {
        Map<Integer, List<Integer>> parentMap =new HashMap<>();
        for (int[] pairs : nodes) {
            int parent = pairs[0];
            int node = pairs[1];
            if(!parentMap.containsKey(node)) {
                parentMap.put(node, new ArrayList<>());
            }
            parentMap.get(node).add(parent);
        }
        Set<Integer> ancestor1 = getAncestors(node1, parentMap);
        Set<Integer> ancestor2 = getAncestors(node2, parentMap);
        ancestor1.retainAll(ancestor2);
        return ancestor1.stream().mapToInt(Integer::intValue).toArray();

    }
    private Set<Integer> getAncestors(int node, Map<Integer, List<Integer>> relations) {
        Set<Integer> res = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (relations.containsKey(i)) {
                List<Integer> parents = relations.get(i);
                for (Integer j : parents) {
                    res.add(j);
                    queue.offer(j);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] nodes = { {1,4}, {2,4},{3,4}, {5,1}, {6,5}};
        // 4 -> 1,2 ,3
        // 1 -> 5
        // 5 -> 6

        Compass_RootAndNodesWith3Parents ss = new Compass_RootAndNodesWith3Parents();
        int[][] res = ss.getRootAndNodesWith3Parents(nodes);
        System.out.println(Arrays.deepToString(res));
        int[] as =ss.getAllCommonAncestors(nodes, 1, 4);
        System.out.println(Arrays.toString(as));
    }
}
