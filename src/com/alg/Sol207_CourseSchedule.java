package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by HAU on 11/18/2017.
 */
/*There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0,
and to take course 0 you should also have finished course 1. So it is impossible.*/

/*This problem can be converted to finding if a graph contains a cycle.*/
public class Sol207_CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites.length == 0) return true;

        List<Integer>[] graph = new ArrayList[numCourses]; // an ArrayList, each item in the array is a List of integer

        int[] inDegrees = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        // build the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int pre = prerequisites[i][1];
            graph[pre].add(course);
            inDegrees[course]++;
        }
        //add courses that have no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < inDegrees.length; i++){
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;
            for (int i = 0; i < graph[curr].size(); i++) {
                int course = graph[curr].get(i);
                // decrease the in-degree until 0 (get topological sort result)
                inDegrees[course]--;
                if (inDegrees[course] == 0) queue.offer(course);
            }
        }

        return count == numCourses;
    }

    public static boolean canFinish0(int numCourses, int[][] prerequisites) {
        // breadth first search
        int len = prerequisites.length;
        if (numCourses == 0 || len == 0) return true;
        int[] pCount = new int[numCourses];
        for (int i = 0; i < len; i++){
            pCount[prerequisites[i][0]]++;  // count for number of prerequisties for each course
        }

        //store courses that have no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(pCount[i] == 0) queue.add(i);
        }
        int numNoPre = queue.size(); // number of courses that have no prerequisites

        while(!queue.isEmpty()){
            int top = queue.remove();
            for (int i = 0; i<len; i++){
                // if a course's prerequisite can be satisfied by a course in queue
                if (prerequisites[i][1] == top) {
                    pCount[prerequisites[i][0]]--;
                    if(pCount[prerequisites[i][0]] == 0) {
                        numNoPre++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }
        return numNoPre == numCourses;
    }

    public static void main(String[] args) {
        int numCourse = 2;
        int[][] prereq ={{0,1},{1,0}};
        System.out.println(canFinish0(numCourse,prereq));
        System.out.println(canFinish2(numCourse,prereq));
    }
    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        // dfs method
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < prerequisites.length; i++){
            graph[prerequisites[i][1]].add(prerequisites[i][0]); // 0 is ready to take; 1 is the prerequiste
        }
        for(int i = 0; i < numCourses; i++){
            if(!dfs(graph,visited,i)){
                return false;
            }
        }
        return true;
    }

    private static boolean dfs(ArrayList<Integer>[] graph, boolean[] visited, int course) {
        if (visited[course]){
            return false;
        }else{
            visited[course] = true;
        }
        for (int i = 0; i< graph[course].size(); i++){
            if(!dfs(graph,visited,graph[course].get(i))){
                return false;
            }
        }
        visited[course] = false;
        return true;
    }

    // DFS 三色法
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = false;
    public boolean canFinish4(int N, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0;i < N; i++) {
            edges.add(new ArrayList<>());
        }
        visited = new int[N];
        for (int[] edge: prerequisites) {
            edges.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }
    // 三色法 0： unvisited; 1 in progress; 2 visited.
    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
            } else if (visited[v] == 1) {
                valid = false;
            }
        }
        visited[u] = 2;
    }

    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        int count = 0;
        //
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indegrees = new int[numCourses]; // to get number of prerequisites for each course, start with those who do not have pre
        for (int[] pairs: prerequisites) {
            int pre = pairs[1];
            int cur = pairs[0];
            if (!map.containsKey(pre)) {
                map.put(pre, new ArrayList<>());
            }
            map.get(pre).add(cur);
            indegrees[cur]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            if (map.containsKey(cur)) { // otherwise it can cause null exception at map.get(cur)
                for (Integer next : map.get(cur)) {
                    indegrees[next]--;
                    if (indegrees[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return count == numCourses;
    }
}
