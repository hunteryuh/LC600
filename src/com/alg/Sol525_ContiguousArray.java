package com.alg;

import java.util.HashMap;
import java.util.Map;

/*Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.*/
public class Sol525_ContiguousArray {
    public static int findMaxLength(int[] nums){
        Map<Integer, Integer> countToIndex = new HashMap<>();
        countToIndex.put(-1,0);
        int maxLen = 0;
        int count = 0;
        for(int i = 0 ; i < nums.length; i++){
            count += (nums[i] == 1 ? 1 : -1);
            if(countToIndex.containsKey(count)){
                maxLen = Math.max(maxLen, i - countToIndex.get(count));
            }else{
                countToIndex.put(count,i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,1,0,0,0,1};
        System.out.println(findMaxLength(nums));
    }
}
