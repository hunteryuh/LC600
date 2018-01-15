package com.alg;

/**
 * Created by HAU on 1/6/2018.
 */
/*Given an array, return the number of possible arithmetic sequence. 给一个数组，返回可能的等差数列个数。
A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.


Example:

A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.*/
public class Sol0_amz_ArithmeticSequence {
    public static int numberOfArithmeticSlice(int[] nums){
        int sum = 0, cur= 0;
        for(int i = 2; i < nums.length; i++){
            if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]){
                cur++;
                sum += cur; // any new number meets the requirement will add cur to the count
                // for example, the 5th item will form 3 new results with length 3,4,5, respectively starting from 3,2,1
            }else{
                cur = 0;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] A = {1,2,3,4,5,100,200,300};
        System.out.println(numberOfArithmeticSlice(A));
        System.out.println(getLAS(A));
    }
    public static int getLAS(int[] A){
        // Time: O(n)
        // Space: O(1)
        if (A.length < 3) return 0;

        int res = 0;
        int diff = Integer.MIN_VALUE;
        int count = 0;
        int start = 0;
        for (int i = 1; i < A.length; i++){
            int currDiff = A[i] - A[i - 1];
            if (diff == currDiff){
                count += i - start - 1 > 0 ? i - start - 1 : 0;
            } else {
                start = i - 1;
                diff = currDiff;
                res += count;
                count = 0;
            }
        }
        res += count;
        return res;
    }
}
