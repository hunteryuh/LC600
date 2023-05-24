package com.alg;

/**
 * Created by HAU on 2/10/2018.
 */
/*Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

You start with 0 points. Return the maximum number of points you can earn by applying such operations.

Example 1:
Input: nums = [3, 4, 2]
Output: 6
Explanation:
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.
Example 2:
Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation:
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.
Note:

The length of nums is at most 20000.
Each element nums[i] is an integer in the range [1, 10000].*/
public class Sol740_DeleteAndEarn {
    // dp  sum[i] = Max(sum[i-1], sum[i-2] + sum[i])
        /* It helps to assume a sorted array so that you can place elements in ascending order to visualize the problem. You notice there that if you earn an element, you cannot earn its immediate unequal neighbors on both sides.

You also notice that if you have duplicate values in nums array, if you earn one of them, you end up earning all of them. This is because you have deleted its neighbors and therefore make its remaining duplicates “undeletable”. This is important because you
notice the problem simplifies to which values can earn you the largest total.*/

    public static int deleteAndEarn(int[] nums) {
        int[] sum = new int[10001];
        for(int n: nums){
            sum[n] += n;
        }
        for( int i = 2; i < sum.length; i++){
            sum[i] = Math.max( sum[i-1], sum[i-2] + sum[i]);
        }
        return sum[10000];

    }

    public static void main(String[] args) {
        int[] nums = { 3,9997,10000};
        System.out.println(deleteAndEarn(nums));
    }

}
