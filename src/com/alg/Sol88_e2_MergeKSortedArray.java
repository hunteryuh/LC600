package com.alg;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by HAU on 12/28/2017.
 */
/*This problem can be solved by using a heap. The time is O(nlog(n)).

Given m arrays, the minimum elements of all arrays can form a heap. It takes O(log(m))
 to insert an element to the heap and it takes O(1) to delete the minimum element.*/
public class Sol88_e2_MergeKSortedArray {
    public static int[] mergeKSortedArray(int[][] arr){
        PriorityQueue<ArrayContainer> pq = new PriorityQueue<ArrayContainer>();
        int totalLen = 0;
        // add arrays to the heap
        for (int i = 0; i < arr.length; i++){
            pq.add(new ArrayContainer(arr[i],0));
            totalLen += arr[i].length;
        }
        int m = 0;
        int res[] = new int[totalLen];
        while(!pq.isEmpty()){
            ArrayContainer ac = pq.poll();
            res[m++] = ac.arr[ac.index];
            if(ac.index < ac.arr.length - 1){
                pq.add(new ArrayContainer(ac.arr,ac.index + 1));
            }
        }
        return res;
    }
    static class ArrayContainer implements Comparable<ArrayContainer> {
        int[] arr;
        int index;

        public ArrayContainer(int[] arr, int index) {
            this.arr = arr;
            this.index = index;
        }

        @Override
        public int compareTo(ArrayContainer o) {
            return this.arr[this.index] - o.arr[o.index];
        }
    }
    public static void main(String[] args) {
        int[] arr1 = { 1, 3, 5, 7 };
        int[] arr2 = { 2, 4, 6, 8 };
        int[] arr3 = { 0, 9, 10, 11 };

        int[] result = mergeKSortedArray(new int[][] { arr1, arr2, arr3 });
        System.out.println(Arrays.toString(result));
    }
}
