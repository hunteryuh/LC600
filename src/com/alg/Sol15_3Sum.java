package com.alg;

/**
 * Created by HAU on 7/4/2017.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Given an array S of n integers, are there elements a, b, c in S such that
        a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
*/
public class Sol15_3Sum {
    public static  List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++){
            for (int j = i + 1; j < n - 1; j++) {
                //Arrays.sort(nums);
                int k = Arrays.binarySearch(nums,j + 1, n, -nums[i] - nums[j]);
                if ( k > j){
                    List<Integer> intlist = new ArrayList<>();
                    intlist.add(nums[i]);
                    intlist.add(nums[j]);
                    intlist.add(nums[k]);
                    if (!list.contains(intlist)) {
                        list.add(intlist);
                    }
                }
            }
        }
        return list;  // time limit exceeded...
    }
    public static  List<List<Integer>> ThreeSum_timeexceeded(int[] nums) {
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++){

                int j = i + 1;
                int k = n - 1;
                int sum = 0 - nums[i];
                while ( j < k) {

                    if (  nums[j] + nums[k] == sum) {
                        if (!list.contains(Arrays.asList(nums[i], nums[j], nums[k]))) {
                            list.add(Arrays.asList(nums[i], nums[j], nums[k]));  // check if the triplet already exist

                        }
                        j++;
                        k--;
                    }else if ( nums[j] + nums [k] < sum){
                        j++;
                    }else if ( nums[j] + nums[k] > sum ){
                        k--;
                    }
                }

        }
        return list;  //Submission Result: Time Limit Exceeded
    }
    public static  List<List<Integer>> ThreeSum(int[] nums) {
        //O(n^2) time complexity
        //外层for 执行n 次，里面俩层循一共也只有n 次，因为lo 和 hi 是单调变化的，一个从头往后，一个从后往前
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++){
            if ( i > 0 && nums[i - 1] == nums[i]){
                continue;
            }
            int j = i + 1;
            int k = n - 1;
            int sum = 0 - nums[i];
            while ( j < k) {

                if (  nums[j] + nums[k] == sum) {
                    list.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while ( j < k && nums[j] == nums[j+1]) j++;  // skip the same values
                    while ( j < k && nums[k] == nums[k-1]) k--;

                    j++;
                    k--;
                }else if ( nums[j] + nums[k] < sum){
                    j++;
                }else if ( nums[j] + nums[k] > sum ){
                    k--;
                }
            }

        }
        return list;  //passed
    }

    public static void main(String[] args) {
//        int[] nums = { -1, 0 ,1 , 2, -1, -4};
//        List<List<Integer>> list = threeSum(nums);
//        System.out.println(list.toString());

//        int[] p1 = { -1, 0 ,1 , 2, -1, -4};
//        List<List<Integer>> list1 = ThreeSum(p1);
//        System.out.println(list1.toString());
//        System.out.println(list2.toString());
        int[] p3 = {-5,1,1,1,4,4,4};
        List<List<Integer>> list3 = ThreeSum(p3);
        System.out.println(list3.toString());
        int[] num2 = { 0, 0, 0};
        List<List<Integer>> listnum2 = ThreeSum(num2);
        System.out.println(listnum2.toString());
    }
}
