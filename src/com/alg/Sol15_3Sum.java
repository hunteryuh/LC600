package com.alg;

/**
 * Created by HAU on 7/4/2017.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

                    if ( nums[j] + nums[k] == sum) {
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

    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0015.%E4%B8%89%E6%95%B0%E4%B9%8B%E5%92%8C.md
    public static List<List<Integer>> ThreeSum(int[] nums) {
        //O(n^2) time complexity
        //外层for 执行n 次，里面俩层循一共也只有n 次，因为lo 和 hi 是单调变化的，一个从头往后，一个从后往前
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++){
            // 错误去重方法，将会漏掉-1,-1,2 这种情况
            /*
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            */
            if (i > 0 && nums[i - 1] == nums[i]) {  // or i != 0
                continue;
            }
            int j = i + 1;
            int k = n - 1;
            int sum = 0 - nums[i];
            while ( j < k) {
                if (nums[j] + nums[k] == sum) {
                    list.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j+1]) j++;  // skip the same values
                    while (j < k && nums[k] == nums[k-1]) k--;

                    j++;  // increase j to a different nums[j]
                    k--;
                } else if (nums[j] + nums[k] < sum){
                    j++;
                } else if (nums[j] + nums[k] > sum ){
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
        int[] n = {1, 0, -1,2,-3,4,5};
        Sol15_3Sum s = new Sol15_3Sum();
        List<List<Integer>> res = s.ThreeSumsquare(n);
        System.out.println(res);
        System.out.println(s.isTriplet(n));

        int[] p3 = {-5,1,1,1,4,4,4};
        List<List<Integer>> list3 = ThreeSum(p3);
        System.out.println(list3.toString());
        int[] num2 = { 0, 0, 0};
        List<List<Integer>> listnum2 = ThreeSum(num2);
        System.out.println(listnum2.toString());
    }

    // a*a + b* b = c*c  for unsorted integer array
    public List<List<Integer>> ThreeSumsquare(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i< n -2; i++) {
            for (int j = i + 1; j< n-1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i]*nums[i] + nums[j]*nums[j] == nums[k]*nums[k]) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return res;
    }

    // https://www.educative.io/edpresso/how-to-find-pythagorean-triplets-in-an-array
    public boolean isTriplet(int[] nums) {
        int n = nums.length;
        for (int i = 0; i< n; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        for (int i = n-1; i >= 2; i--) {
            int j = 0;
            int k = i - 1;
            while (j < k) {
                if (nums[j] + nums[k] == nums[i]) {
                    return true;
                }
                if (nums[j] + nums[k] < nums[i]) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return false;
    }

    public boolean isTriplet2(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i: nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int p = (nums[i] * nums[i] + nums[j] * nums[j]);
                int q = (int) Math.sqrt(p);
                if (q * q == p && (map.containsKey(q) || map.containsKey(-q))) {
                    return true;
                }
            }
        }
        return false;
    }

    // "no -sort" time: O(n^2)
    public List<List<Integer>> threeSum_nosort(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            if (dups.add(nums[i])) {
                for (int j = i + 1; j < nums.length; ++j) {
                    int complement = -nums[i] - nums[j];
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        return new ArrayList(res);
    }


}
