package com.alg;

import java.util.*;

/**
 * Created by HAU on 11/18/2017.
 */
/*A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write
a program to output the skyline formed by these buildings collectively (Figure B).
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri
are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height.
The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between
any two adjacent buildings should be considered part of the skyline contour.*/
public class Sol218_TheSkylineProblem {
    public static List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> points = new ArrayList<>();

        for (int i = 0; i < buildings.length; i++){
            int[] a = buildings[i];
            points.add(new int[]{a[0],a[2]});  //as entering event from left side
            points.add(new int[]{a[1],-a[2]});  // as leaving event on right side
        }

        Collections.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]){
                    return o1[0] - o2[0];
                }else{
                    return o1[1] - o2[1];
                }
            }
        });
        // use a maxheap to store possible heights
        //Queue<Integer> maxHeap = new PriorityQueue<>( (a,b)-> (b-a));
        //Queue<Integer> maxHeap = new PriorityQueue<>(100, Collections.reverseOrder());
        Queue<Integer> maxHeap = new PriorityQueue<>(100, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int cur = 0;
        int prev = 0;
        for (int[] h : points){
            if ( h[1] > 0){
                maxHeap.add(h[1]);  // same as offer, O(log n )
                cur = maxHeap.peek();  //O(1), same as element(), examine the value
            } else {  //  poll() or remove(), O(log n)
                maxHeap.remove(-h[1]); // remove by value O(n )
                cur = maxHeap.peek() == null ? 0: maxHeap.peek();
            }
            if( cur != prev){
                res.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return res;

    }

    public static void main(String[] args) {
        int[][] buildings = {
                {2,9,10},
                {3,7,15},
                {5,12,12},
                {15,20,10},
                {19,24,8}
        };
        List<int[]> res = getSkyline(buildings);
        System.out.println(Arrays.deepToString(res.toArray()));
        //[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]
    }
}
