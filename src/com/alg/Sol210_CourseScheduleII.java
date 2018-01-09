package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 1/9/2017.
 */
/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3].
Another correct ordering is[0,2,1,3]..*/
public class Sol210_CourseScheduleII {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // breadth first search
        int len = prerequisites.length;
        if (numCourses == 0 ) return null;
        int[] pCount = new int[numCourses];
        int[] resultOrder = new int[numCourses]; // save the result
        int index = 0;
        for (int i = 0; i < len; i++){
            pCount[prerequisites[i][0]]++;  // count for number of prerequisties for each course

        }

        //store courses that have no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(pCount[i] == 0) {
                queue.add(i);
                resultOrder[index] = i;
                index++;
            }
        }
        //int numNoPre = queue.size(); // number of courses that have no prerequisites

        while(!queue.isEmpty()){
            int top = queue.remove();
            for (int i = 0; i<len; i++){
                // if a course's prerequisite can be satisfied by a course in queue
                if(prerequisites[i][1] == top){
                    pCount[prerequisites[i][0]]--;
                    if(pCount[prerequisites[i][0]] == 0){
                        resultOrder[index] = prerequisites[i][0];
                        index++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }
        return index == numCourses ? resultOrder : new int[0];
    }

    public static void main(String[] args) {
        int numCourse = 2;
        int[][] prereq ={{0,1},{1,0}};
        System.out.println(findOrder(numCourse,prereq));
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
        if(visited[course]){
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
}
