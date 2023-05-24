package com.alg;

/**
 * Created by HAU on 2/18/2018.
 */
/*You are standing at position 0 on an infinite number line. There is a goal at position target.

On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.

Return the minimum number of steps required to reach the destination.

Example 1:
Input: target = 3
Output: 2
Explanation:
On the first move we step from 0 to 1.
On the second step we step from 1 to 3.
Example 2:
Input: target = 2
Output: 3
Explanation:
On the first move we step from 0 to 1.
On the second move we step  from 1 to -1.
On the third move we step from -1 to 2.*/
public class Sol754_ReachANumber {
    public static int reachNumber(int target) {
        target = Math.abs(target);
        int step = 0;
        int sum = 0;
        while(sum < target){
            step++;
            sum += step;
        }
        while( (sum - target)%2 != 0){
            step++;
            sum +=step; // now sum - target is an even number
        }
        return step; // need to change one positive step to negative step
    }

    public static void main(String[] args) {
        int t = 5;
        System.out.println(reachNumber(t));
    }
}
