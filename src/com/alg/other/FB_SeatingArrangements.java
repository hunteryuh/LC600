package com.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
There are n guests attending a dinner party, numbered from 1 to n. The ith guest has a height of arr[i-1] inches.
The guests will sit down at a circular table which has n seats, numbered from 1 to n in clockwise order around the table. As the host, you will choose how to arrange the guests, one per seat. Note that there are n! possible permutations of seat assignments.
Once the guests have sat down, the awkwardness between a pair of guests sitting in adjacent seats is defined as the absolute difference between their two heights. Note that, because the table is circular, seats 1 and n are considered to be adjacent to one another, and that there are therefore n pairs of adjacent guests.
The overall awkwardness of the seating arrangement is then defined as the maximum awkwardness of any pair of adjacent guests. Determine the minimum possible overall awkwardness of any seating arrangement.
Signature
int minOverallAwkwardness(int[] arr)
Input
n is in the range [3, 1000].
Each height arr[i] is in the range [1, 1000].
Output
Return the minimum achievable overall awkwardness of any seating arrangement.
Example
n = 4
arr = [5, 10, 6, 8]
output = 4
If the guests sit down in the permutation [3, 1, 4, 2] in clockwise order around the table (having heights [6, 5, 8, 10], in that order), then the four awkwardnesses between pairs of adjacent guests will be |6-5| = 1, |5-8| = 3, |8-10| = 2, and |10-6| = 4, yielding an overall awkwardness of 4. It's impossible to achieve a smaller overall awkwardness.
 */
public class FB_SeatingArrangements {
    public int minOverallAwkwardness(int[] arr) {
        // Write your code here
        Arrays.sort(arr);
//        LinkedList<Integer> list = new LinkedList<>();
        int[] temp = new int[arr.length];
        for (int i = 0; i< arr.length; i++) {
            if (i % 2 == 0) {
                temp[i/2] = arr[i];  // 0 1 2  3
            } else {
                temp[arr.length - i/2 - 1] = arr[i];  // n-1, n-2,n-3
            }
        }
//        int max = Math.abs(list.get(0) - list.getLast());
        System.out.println(Arrays.toString(temp));
        int max = Math.abs(temp[0] - temp[arr.length - 1]);
        for (int i = 0; i + 1 < temp.length; i++) {
            max = Math.max(max, Math.abs(temp[i+1] - temp[i]));
        }
        return max;
    }

    public static void main(String[] args) {
//        int[] arr = {1,2,12,23};   // 1 12, 23, 2
        int[] arr = {5,10,6,8};  //5,8,10, 6
        FB_SeatingArrangements f = new FB_SeatingArrangements();
        System.out.println(f.minOverallAwkwardness(arr));
    }
}
