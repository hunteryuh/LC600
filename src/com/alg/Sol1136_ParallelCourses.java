package com.alg;

import java.util.*;

/*
You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.

In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.

Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.



Example 1:

Input: n = 3, relations = [[1,3],[2,3]]
Output: 2
Explanation: The figure above represents the given graph.
In the first semester, you can take courses 1 and 2.
In the second semester, you can take course 3.

Example 2:

Input: n = 3, relations = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: No course can be studied because they are prerequisites of each other.



Constraints:

    1 <= n <= 5000
    1 <= relations.length <= 5000
    relations[i].length == 2
    1 <= prevCoursei, nextCoursei <= n
    prevCoursei != nextCoursei
    All the pairs [prevCoursei, nextCoursei] are unique.

https://leetcode.com/problems/parallel-courses/
 */
public class Sol1136_ParallelCourses {
    // https://leetcode.com/problems/parallel-courses/solutions/344808/java-topological-sort-bfs-w-comment-and-analysis/
    public int minimumSemesters(int n, int[][] relations) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegrees = new int[n + 1];
        for (int[] relation: relations) {
            graph.computeIfAbsent(relation[0], x -> new ArrayList<>()).add(relation[1]);
            indegrees[relation[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        int semester = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int course = queue.poll();
                count++;
                for (int adj: graph.getOrDefault(course, new ArrayList<>())) { // need to use empty list as default for cases where course is not in the graph map
                    indegrees[adj]--;
                    if (indegrees[adj] == 0) {
                        queue.offer(adj);
                    }
                }
            }
            semester++;
        }
        return count == n ? semester : -1;
    }

}
