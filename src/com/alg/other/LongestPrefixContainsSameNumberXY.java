package com.alg.other;
/*
Jacob and Peter have their favorite number X and Y, respectively. We are given an array with positive interger number and we need to find the longest prefix index which contain equal number of X and Y. We return -1 if there is no prefix with equal number of X and Y.

Suppose we have an array A = [7,42,5,6,42,8,7,5,3,6,7]
X = 7
Y =42
Output should be 9 as prefix will be index from 0 to 9 with equal number of X and Y.
 */
// https://leetcode.com/discuss/interview-question/125289/Groupon-Jacob-and-Peter
public class LongestPrefixContainsSameNumberXY {
    public int findIndex(int[] nums, int x, int y) {
        int nx = 0;
        int ny = 0;
        int res = -1;
        for (int i = 0; i < nums.length; i+=1) {
            if (nums[i] == x) {
                nx++;
            }
            if (nums[i] == y) {
                ny++;
            }
            if (nx == ny && nx > 0) {
                res = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {7,42,5,6,42,8,7,5,3,6,7};
        LongestPrefixContainsSameNumberXY ll = new LongestPrefixContainsSameNumberXY();
        System.out.println(ll.findIndex(nums, 42, 7));  // 9
    }
}
