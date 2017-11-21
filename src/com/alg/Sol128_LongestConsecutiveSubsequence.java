package com.alg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 11/21/2017.
 */
/*Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.*/

// optimal time O(n)
public class Sol128_LongestConsecutiveSubsequence {
    // sort approach, O(nlogn)
    public static int longestConsecutivesub(int[] nums){
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int max = 1;
        int cur = 1;
        for (int i = 1; i < nums.length; i++){
            if(nums[i] != nums[i-1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    cur += 1;
                } else {
                    max = Math.max(max, cur);
                    cur = 1;
                }
            }
        }
        /* It is possible that the last element of nums is part of the longest sequence,
        so we return the maximum of the current sequence and the longest one.*/
        return Math.max(max,cur);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,0,1};
        System.out.println(longestConsecutivesub(nums));
        System.out.println(longestConsecutive2(nums));
    }

        //optimal method, using set and loop in the set
    public static int longestConsecutive2(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
