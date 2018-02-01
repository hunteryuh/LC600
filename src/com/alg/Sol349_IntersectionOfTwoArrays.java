package com.alg;

import java.util.*;

/**
 * Created by HAU on 2/1/2018.
 */
/*Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.
*/
public class Sol349_IntersectionOfTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for(int i: nums1){
            set.add(i);
        }
        for(int i: nums2){
            if(set.contains(i)){
                intersect.add(i);
            }
        }
        int[] res = new int[intersect.size()];
        int i = 0;
        for(Integer n : intersect){
            res[i++] = n;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] n1 = {1,1,2,4,2};
        int[] n2 = {4,1};
        int[] ans = intersection(n1,n2);
        System.out.println(Arrays.toString(ans));
    }
    // method 2
    public static int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        for (int i : nums1) set.add(i);
        for (int j : nums2) {
            if (set.contains(j)) {
                res.add(j);
                set.remove(j);
            }
        }
        int[] ans = new int[set.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
