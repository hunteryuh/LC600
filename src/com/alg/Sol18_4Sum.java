package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HAU on 7/7/2017.
 */

/*Given an array S of n integers, are there elements a, b, c, and d in S
        such that a + b + c + d = target? Find all unique quadruplets in the array
        which gives the sum of target.

        Note: The solution set must not contain duplicate quadruplets.*/
public class Sol18_4Sum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            if ( i > 0 && nums[i - 1] == nums[i]){
                continue;
            }
            for ( int j = i + 1; j < n - 2; j++){
                if ( j > i + 1 && nums[j-1] == nums[j]){
                    continue;
                }
                int k = j + 1;
                int m = n - 1;
                int sum = target - nums[i] - nums[j];
                while ( k < m){
                    if ( nums[k] + nums[m] == sum){
                        list.add(Arrays.asList(nums[i],nums[j],nums[k],nums[m]));
                        while ( k < m && nums[k] == nums[k+1]) k++;
                        while ( k < m && nums[m] == nums[m-1]) m--;

                        k++;
                        m--;
                    }else if ( nums[k] + nums[m] < sum){
                        k++;
                    }else if ( nums[m] + nums[k] > sum ) {
                        m--;
                    }
                }
            }
        }
        return list;
    }
    public static void main(String[] args) {

        int[] p3 = {-5,1,1,0,4,-2,-4,0};
        int target = 0;

        List<List<Integer>> list3 = fourSum(p3,target);
        System.out.println(list3.toString());
        int[] p1 = {-1,0,1,2,-1,-4};
        int t1 = -1;
        System.out.println(fourSum(p1,t1));
    }
}
