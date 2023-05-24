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

    // Time O(n^3)
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

        Sol18_4Sum ss = new Sol18_4Sum();
        List<List<Integer>> list = ss.fourSum2(p1, t1);
        System.out.println(list);
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }

        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                //i++;   do nothing and continue; i++ already happened in the for loop
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j-1]) {
                   // j++;
                    continue;
                }
                int k = j + 1;
                int m = n - 1;
                while (k < m) {
                    if (nums[i] + nums[j] + nums[k] + nums[m] == target) {
                        List<Integer> sol = Arrays.asList(nums[i], nums[j], nums[k], nums[m]);
                        res.add(sol);
                        while (k < m && nums[k] == nums[k + 1]) {  //keep k < m
                            k++;
                        }
                        while (m > k && nums[m] == nums[m - 1]) {
                            m--;
                        }
                        k++;
                        m--;
                    } else if (nums[i] + nums[j] + nums[k] + nums[m] < target) {
                        k++;
                    } else {
                        m--;
                    }
                }
            }
        }
        return res;
    }


    // O N^(k-1)
    public List<List<Integer>> fourSum_k(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, 0, 4, target);
    }
    private List<List<Integer>> kSum (int[] nums, int start, int k, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        //optimize start
        if (start == nums.length) return res;
        int average_value = target / k;
        if (nums[start] > average_value || nums[nums.length - 1] < average_value) return res;
        // optimize end
        if (k == 2) { //two pointers from left and right  // two sum
            return twoSum(nums, target, start);
//            int left = start, right = len - 1;
//            while(left < right) {
//                int sum = nums[left] + nums[right];
//                if(sum == target) {
//                    List<Integer> path = new ArrayList<Integer>();
//                    path.add(nums[left]);
//                    path.add(nums[right]);
//                    res.add(path);
//                    while(left < right && nums[left] == nums[left + 1]) left++;
//                    while(left < right && nums[right] == nums[right - 1]) right--;
//                    left++;
//                    right--;
//                } else if (sum < target) { //move left
//                    left++;
//                } else { //move right
//                    right--;
//                }
//            }
        } else {
            for(int i = start; i < len - (k - 1); i++) {
                if(i > start && nums[i] == nums[i - 1]) continue;
                List<List<Integer>> temp = kSum(nums, i + 1, k - 1, target - nums[i]);
                for(List<Integer> t : temp) {
                    t.add(0, nums[i]);
//                    res.add(t);
                }
                res.addAll(temp); // or at line 143
            }
        }
        return res;
    }

    private List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int i = start;
        int j = nums.length - 1;
        while ( i < j ) {
            int sum = nums[i] + nums[j];
            if (sum < target) i++;
            else if (sum > target) j--;
            else {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[i++]);
                temp.add(nums[j--]);
                res.add(temp);
                while (i < j && nums[i-1] == nums[i]) i++;
                while (i < j && nums[j+1] == nums[j]) j--;
            }
        }
        return res;
    }
}
