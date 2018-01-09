package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HAU on 12/27/2017.
 */
/*Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array,
find the sum of the element inside the window at each moving.*/
public class Sol0_amz_windowSum {
    public static int[] windowSum(int[] nums, int k){
        if ( nums == null || nums.length == 0 || k < 0)
            return new int[0];
        // may need to check what to do if nums.length < k
        int[] res = new int[nums.length - k + 1];
        for (int i = 0 ; i < k; i++){
            res[0] += nums[i];
        }
        for (int i = 1; i < res.length; i++){
            res[i] = res[i - 1] - nums[i-1]+nums[i-1+k];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,7,8,5};
        int[] res = windowSum(nums,3);
        System.out.println(Arrays.toString(res));
        Integer[] n = {1,2,7,8,4};
        List<Integer> list = new ArrayList<>(Arrays.asList(n));
        System.out.println(windowSumList(list,3));
    }
    // list as input
    public static List<Integer> windowSumList(List<Integer> nums, int k){
        List<Integer> ans = new ArrayList<>();
        if(nums == null || nums.size() == 0 || k <=0) return ans;
        int count = 0;
        for(int i = 0; i < nums.size(); i++){
            count++;
            if(count >= k){
                int sum = 0;
                for(int j = i; j>= i - k + 1; j--){
                    sum += nums.get(j);
                }
                ans.add(sum);
            }
        }
        return ans;
    }
}
