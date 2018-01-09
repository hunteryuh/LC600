package com.alg;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by HAU on 1/9/2018.
 */
/*There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.

Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.

Example:
Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
Output: 3
Explanation:
There're totally 4 courses, but you can take 3 courses at most:
First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
*/
public class Sol630_CourseScheduleIII {
    //Iterative Solution, time exceeded
    public static int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int time = 0;
        int count = 0;
        for(int i = 0; i < courses.length; i++){
            if ( time +courses[i][0] <= courses[i][1]){
                time += courses[i][0];
                count++;
            }else{
                int max_dur = i;
                for( int j = 0; j < i ; j++){
                    if (courses[j][0] > courses[max_dur][0]){
                        max_dur = j;
                    }
                }
                if( courses[max_dur][0] > courses[i][0]){
                    time += courses[i][0] - courses[max_dur][0]; // time saved for further
                }
                courses[max_dur][0] = -1; // do not need to be replaced as it is not taken
            }
        }
        return count;  // time limit exceeded
        /*Time complexity : O(n^2). We iterate over the countcount array of size nn once. For every element currently considered, we could scan backwards till the first element, giving O(n^2)O(n
​2
​​ ) complexity. */
    }

    public static void main(String[] args) {
        int[][] courses={
                {3,5},
                {5,7},
                {10,18},
                {4,16},
                {10,14}
        };
        int[][] c2 = new int[5][2];
        for(int i = 0; i < courses.length; i++){
            c2[i] = courses[i].clone();
        }
        //System.arraycopy(courses,0,c2,0,courses.length);
        //System.out.println(Arrays.deepToString(c2));
        System.out.println(scheduleCourse(courses));

        System.out.println(scheduleCourse2(c2));
    }
    public static int scheduleCourse2(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int time = 0;
        int count = 0;
        for (int i = 0; i < courses.length; i++) {
            if (time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0];
                courses[count++] = courses[i];
            } else {
                int max_dur = i;
                for (int j = 0; j < count; j++) {
                    if (courses[j][0] > courses[max_dur][0]) {
                        max_dur = j;
                    }
                }
                if (courses[max_dur][0] > courses[i][0]) {

                    time += courses[i][0] - courses[max_dur][0]; // time saved for further
                    courses[max_dur] = courses[i];// replace the max dur course with current course
                }
            }
        }
        return count;
    }
}
