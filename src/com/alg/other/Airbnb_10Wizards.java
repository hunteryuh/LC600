package com.alg.other;

import java.util.*;
import java.util.ArrayList;

public class Airbnb_10Wizards {
/*
There are 10 wizards, 0-9, you are given a list that each entry is a list of wizards known by wizard.
Define the cost between wizards and wizard as square of different of i and j. To find the min cost between 0 and 9.

For example:

wizard[0] list: 1, 4, 5 

wizard[4] list: 9

 wizard 0 to 9 min distance is (0-4)^2+(4-9)^2=41 (wizard[0]->wizard[4]->wizard[9])
 */

    static class Wizard {
        int id;
        int cost = Integer.MAX_VALUE;

        Wizard(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        Wizard(int id) {
            this.id = id;
        }
    }

    public static int minCost(List<List<Integer>> wizards, int source, int target) {
        int n = wizards.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        Queue<Wizard> queue = new LinkedList<>();
        queue.offer(new Wizard(source, 0));

        while (!queue.isEmpty()) {
            Wizard current = queue.poll();
            int curId = current.id;
            int curCost = current.cost;

            for (int nextId : wizards.get(curId)) {
                int nextCost = curCost + (curId - nextId) * (curId - nextId);
                if (nextCost < dist[nextId]) {
                    dist[nextId] = nextCost;
                    queue.offer(new Wizard(nextId, nextCost));
                }
            }
        }

        return dist[target];
    }

    public static void main(String[] args) {
        List<List<Integer>> wizards = new ArrayList<>();
        wizards.add(Arrays.asList(1, 9, 5)); // wizard 0
        wizards.add(Arrays.asList(2, 3, 9));        // wizard 1
        wizards.add(Arrays.asList(4));        // wizard 2
        wizards.add(Arrays.asList());        // wizard 3
        wizards.add(Arrays.asList(9));       // wizard 4
        wizards.add(Arrays.asList());        // wizard 5
        wizards.add(Arrays.asList());        // wizard 6
        wizards.add(Arrays.asList());        // wizard 7
        wizards.add(Arrays.asList());        // wizard 8
        wizards.add(Arrays.asList());        // wizard 9

        int source = 0;
        int target = 9;

        System.out.println(minCost(wizards, source, target)); // Output: 31
    }
    // bfs 2
    // https://github.com/allaboutjst/airbnb/blob/master/src/main/java/ten_wizards/TenWizards.java
    public static List<Integer> getShortestPathForWizards(List<List<Integer>> wizards, int source, int target) {
        int n = wizards.size();
        int[] parents = new int[n];
        Map<Integer, Wizard> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            map.put(i, new Wizard(i));
        }
        map.get(source).cost = 0;
        Queue<Wizard> queue = new LinkedList<>();
        queue.offer(map.get(source));
        while (!queue.isEmpty()) {
            Wizard curr = queue.poll();
            List<Integer> neighbors = wizards.get(curr.id);
            for (int neigh : neighbors) {
                Wizard next = map.get(neigh);
                int weight = (int) Math.pow(next.id - curr.id, 2);
                if (curr.cost + weight < next.cost) {
                    parents[next.id] = curr.id;
//                    pq.remove(next);
                    next.cost = weight + curr.cost;
//                     queue.offer(next);
                }
                queue.offer(next);
            }
        }
        List<Integer> res = new ArrayList<>();
        int t = target;
        while (t != source) {
            res.add(0, t);
            t = parents[t];
        }
        res.add(0, source);
        res.add(0, map.get(target).cost);
        return res;
    }

}
